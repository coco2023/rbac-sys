package com.prac.ecomm.api;

import com.prac.ecomm.entity.Cart;
import com.prac.ecomm.entity.ProductInOrder;
import com.prac.ecomm.entity.User;
import com.prac.ecomm.service.CartService;
import com.prac.ecomm.service.ProductInOrderService;
import com.prac.ecomm.service.ProductService;
import com.prac.ecomm.service.UserService;
import com.prac.ecomm.vo.request.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInOrderService productInOrderService;

    @GetMapping("")
    public ResponseEntity<Cart> getCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var productInfo = productService.findOne(form.getProductId());
        try {
            var prodToCart = Collections.singleton(new ProductInOrder(productInfo, form.getQuantity()));
            mergeCart(prodToCart, principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        User user = userService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(productInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @PutMapping("/{itemId}")
    public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.findOne(principal.getName());
        productInOrderService.update(itemId, quantity, user);
        return productInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        User user = userService.findOne(principal.getName());
        cartService.delete(itemId, user);
        // flush memory into DB
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal) {
        User user = userService.findOne(principal.getName());// Email as username
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }

}


