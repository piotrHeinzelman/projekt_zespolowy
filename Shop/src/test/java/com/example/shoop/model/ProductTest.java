package com.example.shoop.model;

import com.example.shoop.repo.PictureService;
import com.example.shoop.repo.PriceService;
import com.example.shoop.repo.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductTest {

    @Autowired private ProductService productService;
    @Autowired private PriceService priceService;
    @Autowired private PictureService pictureService;

    @Test
    void createProduct() {



        priceService.deleteAll();
        pictureService.deleteAll();
        productService.deleteAll();
        //if (true) return;

        // add product
        String name="Mysz";
        String SKU="Sh1002";
        Product product = new Product( SKU, name );
        productService.save( product );
                                            System.out.println( product );


        // add price to product
        Price price = new Price( product, 0.2 );
        product.setPrice( price );
        productService.save( product );
                                            System.out.println( product );
                                            System.out.println( product.getPrice() );
                                            System.out.println( priceService.findAll() );


        // add picture to product
        Picture picture = new Picture( product,  "new Picture");
        pictureService.save( picture );

        Picture picture2 = new Picture( product,  "second Picture");
        pictureService.save( picture2 );

        product.getPictures().add( picture );
        product.getPictures().add( picture2 );
        productService.save( product );

                                            System.out.println( product );
                                            System.out.println( pictureService.findAll() );












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