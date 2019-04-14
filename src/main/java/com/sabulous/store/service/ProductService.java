package com.sabulous.store.service;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.store.model.Product;
import com.sabulous.store.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    ProductService() {
        System.out.println("ProductService created.");
    }

    @Override
    public Product getProductById(long productId) {
        Product product = productRepository.findByProductId(productId).get(0);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
		productRepository.findAll().forEach(e -> list.add(e));
		return list;
    }

    @Override
    public boolean addProduct(Product product) {
        List<Product> list = productRepository.findByProductName(product.getProductName());
        if(list.size() > 0) {
            return false;
        } else {
            productRepository.save(product);
            return true;
        }
    }

    @Override
    public void updateProduct(Product product) {
        List<Product> list = productRepository.findByProductId(product.getProductId());
        if(list.size() > 0) {
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.delete(getProductById(productId));
    }

    @Override
    public long getProductCount() {
        return productRepository.count();
    }

    @Override
    public boolean containsProductWithId(long productId) {
        return productRepository.existsById(productId);
    }

}
