package com.sabulous.store.service;

import com.sabulous.store.exception.ProductNotFoundException;
import com.sabulous.store.model.Cart;
import com.sabulous.store.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService implements ICartService {

    @Autowired
    private Cart cart;

    @Autowired
    private ProductService productService;

    CartService() {
        System.out.println("CartService created.");
    }

    public void addProductToCart(Long id, Integer quantity) {

        if(!productService.containsProductWithId(id)) {
            throw new ProductNotFoundException();
        }

        Map<Long, Integer> productsInCart = cart.getProducts();

        Integer productInCartCurrentCount = productsInCart.get(id);
        if(productInCartCurrentCount == null) {
            productInCartCurrentCount = 0;
        }

        productsInCart.put(id, productInCartCurrentCount + quantity);
    }

    public void addProductToCart(CartItem product) {
        addProductToCart(product.getProductId(), product.getQuantity());
    }

    @Override
    public void removeProductFromCart(Long id, Integer count) {
        if(!containsProductWithId(id)) {
            throw new ProductNotFoundException();
        }

        Map<Long, Integer> productsInCart = cart.getProducts();
        Integer productInCartCurrentCount = productsInCart.get(id);

        if(count >= productInCartCurrentCount) {
            productsInCart.remove(id);
        } else {
            productsInCart.put(id, productInCartCurrentCount - count);
        }
    }

    @Override
    public boolean containsProductWithId(long productId) {
        return cart.getProducts().containsKey(productId);
    }

    public Map<Long, Integer> getCartProducts () {

        return cart.getProducts();
    }

    public List<CartItem> getProductsAsList() {
        Map<Long, Integer> products = cart.getProducts();
        List<CartItem> productList = new ArrayList<>();
        for(Map.Entry<Long, Integer> product : products.entrySet()) {
            productList.add(new CartItem(product.getKey(), product.getValue()));
        }
        return productList;
    }


//    @Override
//    public String serialize(Map<Long, Integer> products) {
//        try
//        {
//            String serializedCart = "";
//
//            FileOutputStream fos = new FileOutputStream(serializedCart);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//            oos.writeObject(products);
//
//            oos.close();
//            fos.close();
//
//            System.out.println(serializedCart);
//            return serializedCart;
//        }
//        catch (IOException ioe)
//        {
//            System.out.println("SERIALIZATION EXCEPTION");
//            ioe.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Map<Long, Integer> deserialize(String serializedCart) {
//        try {
//            Map<Long, Integer> products;
//
//            FileInputStream fis = new FileInputStream(serializedCart);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//
//            products = (HashMap<Long, Integer>) ois.readObject();
//
//            fis.close();
//            ois.close();
//
//            System.out.println(products.toString());
//            return products;
//
//        } catch (IOException e) {
//            System.out.println("deSERIALIZATION EXCEPTION");
//
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            System.out.println("deSERIALIZATION EXCEPTION");
//
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
