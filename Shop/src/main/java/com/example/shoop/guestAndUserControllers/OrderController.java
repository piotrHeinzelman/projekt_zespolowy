package com.example.shoop.guestAndUserControllers;

import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.model.Cart;
import com.example.shoop.model.CartItem;
import com.example.shoop.model.Product;
import com.example.shoop.repo.CartItemService;
import com.example.shoop.repo.CartService;
import com.example.shoop.repo.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class OrderController {

    @Autowired private ProductService productService;
    @Autowired private ProductController productController;
    @Autowired private JdbcUserDetailsManager users;
    @Autowired private CartService cartService;
    @Autowired private CartItemService cartItemService;

    @RequestMapping(value = {"/cart/convert_to_order/{cartId}"}, method = RequestMethod.GET)
    public String crewImgSendGET( Model model, HttpServletRequest request ) {

        return "index";

    }

}








