package com.sabulous.store.controller;

import java.util.List;

import com.sabulous.store.exception.ProductNotFoundException;
import com.sabulous.store.model.Product;
import com.sabulous.store.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("products/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        if(!productService.containsProductWithId(id)) throw new ProductNotFoundException();
        Product product = productService.getProductById(id);
        model.addAttribute("prod", product);
        response.setStatus(HttpServletResponse.SC_OK);
        return "productDetails";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> list = productService.getAllProducts();
        model.addAttribute("productList", list);
        return "products";
    }

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, UriComponentsBuilder builder) {

        // if there is already a product with the id of passed product parameter, stop.
        if(product.getProductId() != null && productService.containsProductWithId(product.getProductId())) {
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }

        if(productService.addProduct(product)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/products/{id}").buildAndExpand(product.getProductId()).toUri());
            return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
        } else {
            //there is already such a product, thus return 409 conflict response
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping(value = "/products", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        
        // if there is already a product with the id of passed product parameter, go ahead.
        if(productService.containsProductWithId(product.getProductId())) {
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        } else {
            throw new ProductNotFoundException();
        }

    }

    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {

        // this conditional is left intentionally as another way of checking if exists such a product.
        if(productService.containsProductWithId(id)) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ProductNotFoundException();
        }

    }
    
}
