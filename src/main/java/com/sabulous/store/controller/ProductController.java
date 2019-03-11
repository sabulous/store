package com.sabulous.store.controller;

import java.net.http.HttpRequest;
import java.util.List;

import com.sabulous.store.model.Product;
import com.sabulous.store.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        Product product = null;
        try {
            product = productService.getProductById(id);    
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> addProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        System.out.println("listening SIR.");
        boolean flag = productService.addProduct(product);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}").buildAndExpand(product.getProductId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value="products/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        //TODO: process PUT request

        if(getProductById(product.getProductId()).getStatusCode().equals(HttpStatus.OK)) {
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /* TODO
        - add deleteProduct controller
    */
}