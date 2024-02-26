package com.example.Shop.controller;

import com.example.Shop.model.Product;
import com.example.Shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    public void addProduct(){
        String newSKU="GByte_RTX_3060";
        String newName="Karta graficzna Gigabyte RTX 3060";
        productController.add( newSKU, newName );

        newSKU="GByte_RTX_3070";
        newName="Karta graficzna Gigabyte RTX 3070";
        productController.add( newSKU, newName );


        productController.add( "GByte_RTX_3080", "Karta graficzna Gigabyte RTX 3080" );
        productController.add( "NVidia_RTX_3060", "Karta graficzna NVidia RTX 3060" );
        productController.add( "NVidia_RTX_3070", "Karta graficzna NVidia RTX 3070" );
        productController.add( "NVidia_RTX_3080", "Karta graficzna NVidia RTX 3080" );


        System.out.println( "List size: " + productService.count() );

    }


}