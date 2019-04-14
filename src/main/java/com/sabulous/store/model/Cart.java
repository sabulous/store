package com.sabulous.store.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {
    private Map<Long, Integer> products;

    public Cart() {
        System.out.println("Cart created.");
        products = new HashMap<Long, Integer>();
    }

    public Map<Long, Integer> getProducts() {
        return products;
    }

}
