package com.example.shoop.model;

import com.example.shoop.crewControllers.CrewController;
import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.repo.CategoryService;
import com.example.shoop.repo.PictureService;
import com.example.shoop.repo.PriceService;
import com.example.shoop.repo.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@SpringBootTest
class CategoryGeneratorTest {

    @Autowired private ProductService productService;
    @Autowired private PriceService priceService;
    @Autowired private PictureService pictureService;
    @Autowired private CrewController crewController;
    @Autowired private ProductController productController;
    @Autowired private CategoryService categoryService;

/*
    addCategory("Dyski SSD");
    properties:("producent")ADATA | WD | Crucial | Corsair
    properties:("pojemność")2000Gb
    properties:("interfejs")PCIe NVMe4.0 | SATA
    properties:("prędkość zapisu")3300 MB/s
    properties:("prędkość odczytu")3000 MB/s

    addCategory("Karty Graficzne");
    properties:("producent") Gigabyte | Sapphire | AMD | ASUS
    properties:("układ pamięci")RadeonTM RX6600 XT
    properties:("pamięć")8GB
    properties:("rodzaj pamięci")GDDR6
    properties:("złącza")HDMI 2szt. DP 2szt.

       addCategory("Procesory AMD");
    properties:("producent") AMD
    properties:("socket")Socket AM4
    properties:("taktowanie")3.6 GHz
    properties:("liczba rdzeni")4
    properties:("cache")6 MB

    addCategory("Płyty główne");
    properties:("producent") AMD
    properties:("socket")Socket AM4
    properties:("chipset")AMD B550 B450
    properties:("format") mATX ATX
    properties:("ilość banków pamięci RAM")4

    addCategory("Obudowy");
    properties:("producent") Silver Monkey | BeQuiet |
    properties:("Standard płyty")ATX, m-ATX, Mini-ITX
    properties:("liczba wentylatorów")4
    properties:("panel boczny szklany") TAK/NIE
    properties:("napędy") 2x 3,5" 2x 2,5"
    */





    @Test
    void createProduct() throws FileNotFoundException {

        priceService.deleteAll();
        pictureService.deleteAll();
        categoryService.deleteAll();
        productService.deleteAll();
        //if (true) return;

        Category catDyski = new Category("Dyski SSD");
        categoryService.save(catDyski);
        productController.addParameterToCategory( catDyski , "producent", "" );
        productController.addParameterToCategory( catDyski , "pojemność", "Gb" );
        productController.addParameterToCategory( catDyski , "interfejs", "" );
        productController.addParameterToCategory( catDyski , "prędkość zapisu", "MB/s" );
        productController.addParameterToCategory( catDyski , "prędkość odczytu", "MB/s" );
        /*
        properties:("producent")ADATA | WD | Crucial | Corsair
        properties:("pojemność")2000Gb
        properties:("interfejs")PCIe NVMe4.0 | SATA
        properties:("prędkość zapisu")3300 MB/s
        properties:("prędkość odczytu")3000 MB/s
         */





        Category catKartyG = new Category("Karty Graficzne");
        catKartyG = categoryService.save(catKartyG);
        productController.addParameterToCategory( catKartyG , "producent", "" );
        productController.addParameterToCategory( catKartyG , "układ pamięci", "" );
        productController.addParameterToCategory( catKartyG , "pamięć", "GB" );
        productController.addParameterToCategory( catKartyG , "rodzaj pamięci", "" );
        productController.addParameterToCategory( catKartyG , "złącza", "" );
/*        addCategory("Karty Graficzne");
        properties:("producent") Gigabyte | Sapphire | AMD | ASUS
        properties:("układ pamięci")RadeonTM RX6600 XT
        properties:("pamięć")8GB
        properties:("rodzaj pamięci")GDDR6
        properties:("złącza")HDMI 2szt. DP 2szt. */

        Category catProcesory = new Category("Procesory");
        catProcesory = categoryService.save(catProcesory);
        productController.addParameterToCategory( catProcesory , "producent", "" );
        productController.addParameterToCategory( catProcesory , "socket", "" );
        productController.addParameterToCategory( catProcesory , "taktowanie", "GHz" );
        productController.addParameterToCategory( catProcesory , "liczba rdzeni", "" );
        productController.addParameterToCategory( catProcesory , "cache", "MB" );
/*             addCategory("Procesory AMD");
    properties:("producent") AMD
    properties:("socket")Socket AM4
    properties:("taktowanie")3.6 GHz
    properties:("liczba rdzeni")4
    properties:("cache")6 MB

 */


        Category catPlytyG = new Category("Płyty główne");
        catPlytyG = categoryService.save(catPlytyG);
        productController.addParameterToCategory( catPlytyG , "producent", "" );
        productController.addParameterToCategory( catPlytyG , "socket", "" );
        productController.addParameterToCategory( catPlytyG , "chipset", "" );
        productController.addParameterToCategory( catPlytyG , "format", "" );
        productController.addParameterToCategory( catPlytyG , "ilość banków pamięci RAM", "" );
/*          addCategory("Płyty główne");
    properties:("producent") AMD
    properties:("socket")Socket AM4
    properties:("chipset")AMD B550 B450
    properties:("format") mATX ATX
    properties:("ilość banków pamięci RAM")4
 */

        Category catObudowy = new Category("Obudowy");
        catObudowy = categoryService.save(catObudowy);
        productController.addParameterToCategory( catObudowy , "producent", "" );
        productController.addParameterToCategory( catObudowy , "Standard płyty", "" );
        productController.addParameterToCategory( catObudowy , "liczba wentylatorów", "" );
        productController.addParameterToCategory( catObudowy , "panel boczny szklany", "" );
        productController.addParameterToCategory( catObudowy , "napędy", "3,5\" / 2,5\"" );
/*    addCategory("Obudowy");
    properties:("producent") Silver Monkey | BeQuiet |
    properties:("Standard płyty")ATX, m-ATX, Mini-ITX
    properties:("liczba wentylatorów")4
    properties:("panel boczny szklany") TAK/NIE
    properties:("napędy") 2x 3,5" 2x 2,5"
 */



        // String name, Category category, Double price, List<String> pictureNames
        createProductTest( "Dysk M.2 AData 1TB" , catDyski , 289.0 , List.of( "DYSKI\\ADATA-1TB-M.png" ) );
        createProductTest( "Dysk Sata 2,5\" Crucial 0.5TB" , catDyski , 299.0 , List.of( "DYSKI\\Crucial_500GB_2,5_SATA_SSD_MX500.png" ) );
        createProductTest( "Dysk M.2 Crucial 1.0TB" , catDyski , 329.0 , List.of( "DYSKI\\Crucial-1TB-M.png" ) );
        createProductTest( "Dysk M.2 Samsung 1.0TB" , catDyski , 489.0 , List.of( "DYSKI\\Samsung_1TB_M.png" ) );
        createProductTest( "Dysk M.2 Western Digital 2.0TB" , catDyski , 719.0 , List.of( "DYSKI\\WD_2TB_M.png" ) );






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


    public void createProductTest( String name, Category category, Double price, List<String> pictureNames ){
        String SKU = "SH00" + productService.count()+5;

        Product product = new Product( SKU, name );
        product = productService.save( product );
        product.setCategory( category );
        product.setStatus( Status.ACTIVE );

        for ( String pictureName : pictureNames ) {
            MF multipartFile = new MF();
            try {
                multipartFile.setInputStream(new FileInputStream(".\\src\\test\\java\\resources\\"+ pictureName));
            } catch (FileNotFoundException e) {  throw new RuntimeException(e); }
            productController.addPictureToProduct(product, " ", multipartFile);
        }
        productService.save( product );
        productController.addPriceToProduct(  product, price );
    }



}


