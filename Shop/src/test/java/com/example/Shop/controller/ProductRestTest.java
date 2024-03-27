package com.example.Shop.controller;

import com.example.Shop.model.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class ProductRestTest {

    @Autowired
    private ProductRest productRest;
/*
    @Disabled
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

 */
}