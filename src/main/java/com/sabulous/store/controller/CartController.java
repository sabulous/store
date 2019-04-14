package com.sabulous.store.controller;

import com.sabulous.store.model.CartItem;
import com.sabulous.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String getCart(Model model, HttpServletResponse response) {

        model.addAttribute("cartItems", cartService.getProductsAsList());
        response.setStatus(HttpServletResponse.SC_OK);
        return "cart";

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addProductToCart(@RequestBody CartItem product, Model model, HttpServletResponse response) {

        cartService.addProductToCart(product);
        model.addAttribute("cartItems", cartService.getProductsAsList());
        response.setStatus(HttpServletResponse.SC_OK);
        return "cart";

    }

    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<Long, Integer>> deleteProductFromCart(@RequestParam("id") Long productId,
                                                                @RequestParam("count") Integer count) {
        cartService.removeProductFromCart(productId, count);
        return new ResponseEntity<Map<Long, Integer>>(cartService.getCartProducts(),HttpStatus.OK);

    }
}
