package com.example.shoop.model;

import com.example.shoop.repo.PriceService;
import com.example.shoop.repo.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductTest {

    @Autowired private ProductService productService;
    @Autowired private PriceService priceService;

    @Test
    void createProduct() {


        productService.deleteAll();
        priceService.deleteAll();
        if (true) return;
        String name="Mysz";
        String SKU="Sh1002";
        Product product = new Product( SKU, name );
        //product.setPrice( new Price( 0.2 ) );
        product = productService.save( product );
        System.out.println( product );

        product.setPrice( new Price( product, 0.2 ) );
        productService.save( product );


   //     Price price=new Price(product, 0.2);
   //     product.setPrice( price );

   //     System.out.println( price );
   //     priceService.save(price);
  //      productService.save( product );

     //   System.out.println( product );



//        product.setPrice( price );
//        productService.save( product );



    }
}