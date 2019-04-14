package com.sabulous.store.service;

interface ICartService {
    void addProductToCart(Long id, Integer quantity);
    void removeProductFromCart(Long id, Integer count);
    boolean containsProductWithId(long productId);
//    String serialize(Map<Long, Integer> products);
//    Map<Long, Integer> deserialize(String serializedCart);
}
