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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {

        if(!productService.containsProductWithId(id)) throw new ProductNotFoundException();
        Product product = productService.getProductById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> list = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

    }

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        
        // if there is already a product with the id of passed product parameter, stop.
        if(productService.containsProductWithId(product.getProductId())) {
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }

        productService.addProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}").buildAndExpand(product.getProductId()).toUri());
        return new ResponseEntity<Product>(headers, HttpStatus.CREATED);

    }
    
    @PutMapping(value = "/products/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        
        // if there is already a product with the id of passed product parameter, go ahead.
        if(productService.containsProductWithId(product.getProductId())) {
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        } else {
            throw new ProductNotFoundException();
        }

    }

    @DeleteMapping(value = "/products/delete/{id}", produces = "application/json")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {

        // this conditional is left intentionally as another way of checking if exists such a product.
        if(getProductById(id).getStatusCode().equals(HttpStatus.OK)) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ProductNotFoundException();
        }

    }
    
}
