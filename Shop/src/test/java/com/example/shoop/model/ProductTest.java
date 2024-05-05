package com.example.shoop.model;

import com.example.shoop.crewControllers.CrewController;
import com.example.shoop.repo.CategoryService;
import com.example.shoop.repo.PictureService;
import com.example.shoop.repo.PriceService;
import com.example.shoop.repo.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@SpringBootTest
class ProductTest {

    @Autowired private ProductService productService;
    @Autowired private PriceService priceService;
    @Autowired private PictureService pictureService;
    @Autowired private CrewController crewController;
    @Autowired private CategoryService categoryService;

    @Test
    void createProduct() {



        priceService.deleteAll();
        pictureService.deleteAll();
        categoryService.deleteAll();
        productService.deleteAll();
        //if (true) return;

        // add product
        String name="Mysz";
        String SKU="Sh1002";
        Double priceValue = 0.2;
        Product product = new Product( SKU, name );
        productService.save( product );
                                            System.out.println( product );


        // add price to product
/*        Price price = new Price( product, 0.2 );
        product.setPrice( price );
        productService.save( product );
                                            System.out.println( product );
                                            System.out.println( product.getPrice() );
                                            System.out.println( priceService.findAll() );
  */

        crewController.addPriceToProduct(  product, priceValue );
                                            System.out.println( product ) ;
                                            System.out.println( priceService.findAll() );



        // add picture to product
/*        Picture picture = new Picture( product,  "new Picture");
        pictureService.save( picture );


            MF multipartFile = new MF();
            try {
                multipartFile.setInputStream( new FileInputStream( "F:\\ProjektZespolowy\\Shop\\src\\test\\java\\resources\\600.png"  ));
            } catch (FileNotFoundException e) { throw new RuntimeException(e); }

        FileTool.moveUploadedToFile( picture , multipartFile );



        product.getPictures().add( picture );
        productService.save( product );

                                            System.out.println( product );
                                            System.out.println( pictureService.findAll() );
*/


                MF multipartFile = new MF();
                try {
                    multipartFile.setInputStream( new FileInputStream( "F:\\ProjektZespolowy\\Shop\\src\\test\\java\\resources\\600.png"  ));
                } catch (FileNotFoundException e) { throw new RuntimeException(e); }

        crewController.addPictureToProduct( product , " this is a picture ", multipartFile );

        System.out.println( product );
        System.out.println( product.getPictures().iterator().next() );
        System.out.println( pictureService.findAll() );



        Category category = new Category("kategogia 1");
        categoryService.save( category );

        product.setCategory( category );
        productService.save( product );

        //category.getProducts().add( product );
        //categoryService.save( category );

        System.out.println( category );
        System.out.println( product );






   //     Price price=new Price(product, 0.2);
   //     product.setPrice( price );

   //     System.out.println( price );
   //     priceService.save(price);
  //      productService.save( product );

     //   System.out.println( product );



//        product.setPrice( price );
//        productService.save( product );



    }


    private class MF implements MultipartFile {

        private InputStream inputStream;

        @Override
        public InputStream getInputStream() throws IOException {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override public String getName() {return null;}
        @Override public String getOriginalFilename() {return null;}
        @Override public String getContentType() {return null;}
        @Override public boolean isEmpty() {return false;}
        @Override public long getSize() {return 0;}
        @Override public byte[] getBytes() throws IOException {return new byte[0];}
        @Override public void transferTo(File dest) throws IOException, IllegalStateException {}
    }
}


