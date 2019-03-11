package com.sabulous.store.repository;

import java.util.List;

import com.sabulous.store.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByProductName(String productName);
    List<Product> findByProductId(Long productId);
}