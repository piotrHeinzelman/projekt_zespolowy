package com.example.Shop.controller;

import com.example.Shop.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRestTest {

    @Autowired
    private ProductRest productRest;

    @Test
    void findByName() {
        Optional<Product> OById = productRest.findById(1L);
        if ( OById.isPresent() ){
            System.out.println( OById.get() );
        }
        productRest.findById( OById.get().getId() );
    }

    @Test
    void findById() {
    }
}