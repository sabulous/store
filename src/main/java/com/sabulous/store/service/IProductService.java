package com.sabulous.store.service;

import java.util.List;

import com.sabulous.store.model.Product;

public interface IProductService {

    Product getProductById(long productId);
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(long productId);
    long getProductCount();
    boolean containsProductWithId(long productId);
    
}
