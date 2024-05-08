package com.example.shoop.model;

import com.example.shoop.crewControllers.CrewController;
import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.repo.*;
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
    @Autowired private ParamInCategoryService paramInCategoryService;
    @Autowired private PValService pValService;
    @Autowired private ParamService paramService;

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

        productService.deleteAll();

        priceService.deleteAll();
        pictureService.deleteAll();
        categoryService.deleteAll();
        paramInCategoryService.deleteAll();
        paramService.deleteAll();

        productService.deleteAll();

//if (true) return;

        Category catDyski = new Category("Dyski SSD");
        categoryService.save(catDyski);

        /*
        properties:("producent")ADATA | WD | Crucial | Corsair
        properties:("pojemność")2000Gb
        properties:("interfejs")PCIe NVMe4.0 | SATA
        properties:("prędkość zapisu")3300 MB/s
        properties:("prędkość odczytu")3000 MB/s
         */





        Category catKartyG = new Category("Karty Graficzne");
        catKartyG = categoryService.save(catKartyG);

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
      //  Product dyskXAdata = createProductTest("Dysk M.2 AData 1TB", catDyski, 289.0, Collections.EMPTY_LIST);
      //  if (true) return;
        Product dysk1Adata = createProductTest("Dysk M.2 AData 1TB", catDyski, 289.0, List.of("DYSKI\\ADATA-1TB-M.png"));
        Product dysk2Crucial = createProductTest("Dysk Sata 2,5\" Crucial 0.5TB", catDyski, 299.0, List.of("DYSKI\\Crucial_500GB_2,5_SATA_SSD_MX500.png"));
        Product dysk3Crucial = createProductTest("Dysk M.2 Crucial 1.0TB", catDyski, 329.0, List.of("DYSKI\\Crucial-1TB-M.png"));
        Product dysk4Samsung = createProductTest("Dysk M.2 Samsung 1.0TB", catDyski, 489.0, List.of("DYSKI\\Samsung_1TB_M.png"));
        Product dysk5Western = createProductTest("Dysk M.2 Western Digital 2.0TB", catDyski, 719.0, List.of("DYSKI\\WD_2TB_M.png"));

        Parm paramDProducent = paramService.save( new Parm( "producent" ));
        Parm paramDPojemnosc = paramService.save( new Parm( "pojemność", "GB" ));
        Parm paramDInterfejs = paramService.save( new Parm( "interfejs", "GB" ));
        Parm paramDpredkoscOdczytu = paramService.save( new Parm( "prędkość odczytu", "MB/s" ));
        Parm paramDpredkoscZapisu = paramService.save( new Parm( "prędkość zapisu", "MB/s" ));

/*
        properties:()ADATA | WD | Crucial | Corsair
        properties:("pojemność")2000Gb
        properties:("interfejs")PCIe NVMe4.0 | SATA
        properties:("prędkość zapisu")3300 MB/s
        properties:("prędkość odczytu")3000 MB/s
*/
        paramInCategoryService.save( new ParamInCategory( catDyski , paramDProducent ));
        paramInCategoryService.save( new ParamInCategory( catDyski , paramDPojemnosc ));
        paramInCategoryService.save( new ParamInCategory( catDyski , paramDInterfejs ));
        paramInCategoryService.save( new ParamInCategory( catDyski , paramDpredkoscOdczytu ));
        paramInCategoryService.save( new ParamInCategory( catDyski , paramDpredkoscZapisu ));

        // Long product_id, Long param_id, String value
        // AData
        // https://www.x-kom.pl/p/1107495-dysk-ssd-adata-1tb-m2-pcie-gen4-nvme-legend-850.html
        pValService.save( new PValue( dysk1Adata, paramDProducent, "AData" ) );
        pValService.save( new PValue( dysk1Adata, paramDPojemnosc, "1000" ) );
        pValService.save( new PValue( dysk1Adata, paramDInterfejs, "NVMe 4.0" ) );
        pValService.save( new PValue( dysk1Adata, paramDpredkoscOdczytu, "5000" ) );
        pValService.save( new PValue( dysk1Adata, paramDpredkoscZapisu, "4500" ) );

        // https://www.x-kom.pl/p/400627-dysk-ssd-crucial-2tb-25-sata-ssd-mx500.html
        pValService.save( new PValue( dysk2Crucial, paramDProducent, "Crucial" ) );
        pValService.save( new PValue( dysk2Crucial, paramDPojemnosc, "2000" ) );
        pValService.save( new PValue( dysk2Crucial, paramDInterfejs, "SATA" ) );
        pValService.save( new PValue( dysk2Crucial, paramDpredkoscOdczytu, "560" ) );
        pValService.save( new PValue( dysk2Crucial, paramDpredkoscZapisu, "510" ) );

        // https://www.x-kom.pl/p/1149957-dysk-ssd-crucial-1tb-m2-pcie-gen5-nvme-t700-heatsink.html
        pValService.save( new PValue( dysk3Crucial, paramDProducent, "Crucial" ) );
        pValService.save( new PValue( dysk3Crucial, paramDPojemnosc, "1000" ) );
        pValService.save( new PValue( dysk3Crucial, paramDInterfejs, "NVMe 5.0" ) );
        pValService.save( new PValue( dysk3Crucial, paramDpredkoscOdczytu, "11700" ) );
        pValService.save( new PValue( dysk3Crucial, paramDpredkoscZapisu, "9500" ) );

        // https://www.x-kom.pl/p/634238-dysk-ssd-samsung-1tb-m2-pcie-nvme-980.html
        pValService.save( new PValue( dysk4Samsung, paramDProducent, "Samsung" ) );
        pValService.save( new PValue( dysk4Samsung, paramDPojemnosc, "1000" ) );
        pValService.save( new PValue( dysk4Samsung, paramDInterfejs, "NVMe 3.0" ) );
        pValService.save( new PValue( dysk4Samsung, paramDpredkoscOdczytu, "3500" ) );
        pValService.save( new PValue( dysk4Samsung, paramDpredkoscZapisu, "3000" ) );

        // https://www.x-kom.pl/p/525243-dysk-ssd-wd-2tb-m2-sata-ssd-red-sa500.html
        pValService.save( new PValue( dysk5Western, paramDProducent, "WesternDigital" ) );
        pValService.save( new PValue( dysk5Western, paramDPojemnosc, "2000" ) );
        pValService.save( new PValue( dysk5Western, paramDInterfejs, "M.2 SATA" ) );
        pValService.save( new PValue( dysk5Western, paramDpredkoscOdczytu, "560" ) );
        pValService.save( new PValue( dysk5Western, paramDpredkoscZapisu, "530" ) );



// ---------------------------

        // String name, Category category, Double price, List<String> pictureNames
        // https://www.x-kom.pl/p/1068603-karta-graficzna-amd-amd-radeon-rx-6950-xt-16gb-gddr6.html
        Product karta1AMD = createProductTest("Karta RX 6950 XT 16GB", catKartyG, 2699.0, List.of("KartyGraficzne\\AMD-Radeon-RX-6950-XT-16GB-GDDR6.png"));
        // https://www.x-kom.pl/p/1184180-karta-graficzna-amd-asus-radeon-rx-7600-dual-oc-v2-8gb-gddr6.html
        Product karta2ASUS = createProductTest("Karta ASUS Radeon RX 7600 Dual OC", catKartyG, 1279.0, List.of("KartyGraficzne\\ASUS-Radeon-RX-7600-Dual-OC-V2-8GB-GDDR6.png"));
        // https://www.x-kom.pl/p/1156772-karta-graficzna-nvidia-gigabyte-geforce-rtx-4060-eagle-oc-8gb-gddr6.html
        Product karta3Gigabyte = createProductTest("Karta Gigabyte GeForce RTX 4060 Eagle OC", catKartyG, 1450.0, List.of("KartyGraficzne\\Gigabyte-GeForce-RTX-4060-Eagle-OC-8GB-GDDR6.png",
                "KartyGraficzne\\Gigabyte-GeForce-RTX-4060-Eagle-OC-8GB-GDDR6a.png",
                "KartyGraficzne\\Gigabyte-GeForce-RTX-4060-Eagle-OC-8GB-GDDR6b.png"));

        // https://www.x-kom.pl/p/687464-karta-graficzna-amd-gigabyte-radeon-rx-6600-eagle-8gb-gddr6.html
        Product karta4GigabyteRadeon = createProductTest("Karta Gigabyte Radeon RX 6600 EAGLE 8GB", catKartyG, 999.0, List.of("KartyGraficzne\\Gigabyte-Radeon-RX-6600-EAGLE-8GB-GDDR6.png",
                "KartyGraficzne\\Gigabyte-Radeon-RX-6600-EAGLE-8GB-GDDR6a.png",
                "KartyGraficzne\\Gigabyte-Radeon-RX-6600-EAGLE-8GB-GDDR6b.png"));

        // https://www.x-kom.pl/p/1088561-karta-graficzna-nvidia-msi-geforce-rtx-3060-ventus-2x-oc-8gb-gddr6.html
        Product karta5MSI = createProductTest("Karta MSI GeForce RTX 3060 VENTUS 2X OC", catKartyG, 1269.0, List.of("KartyGraficzne\\MSI-GeForce-RTX-3060-VENTUS-2X-OC-12GB-GDDR6.png"));

        // https://www.x-kom.pl/p/688539-karta-graficzna-amd-sapphire-radeon-rx-6600-gaming-pulse-8gb-gddr6.html
        Product karta6Sapphire = createProductTest("Karta Sapphire Radeon RX 6600 GAMING Pulse", catKartyG, 999.0, List.of("KartyGraficzne\\Sapphire-Radeon-RX-6600-GAMING-Pulse-8GB-GDDR6.png"));

        // https://www.x-kom.pl/p/1209711-karta-graficzna-nvidia-zotac-geforce-rtx-4070-super-twin-edge-12gb-gddr6x.html
        Product karta7Zoltac = createProductTest("Karta Zotac GeForce RTX 4070 SUPER Twin Edge", catKartyG, 2999.0, List.of("KartyGraficzne\\Zotac-GeForce-RTX-4070-SUPER-Twin-Edge-12GB-GDDR6X.png"));



/*
        addCategory("Karty Graficzne");
        properties:("producent") Gigabyte | Sapphire | AMD | ASUS
        properties:("układ pamięci")RadeonTM RX6600 XT
        properties:("pamięć")8GB
        properties:("rodzaj pamięci")GDDR6
        properties:("złącza")HDMI 2szt. DP 2szt.
*/
        Parm paramKGProducent = paramService.save( new Parm( "producent" ));
        Parm paramKGUklad = paramService.save( new Parm( "układ graficzny" ));
        Parm paramKGPamiec = paramService.save( new Parm( "pamięc","GB" ));
        Parm paramKGTypPamiec = paramService.save( new Parm( "rodzaj pamięci","" ));
        Parm paramKGZlacza = paramService.save( new Parm( "rodzaj pamięci","" ));

        paramInCategoryService.save( new ParamInCategory( catKartyG , paramKGProducent ));
        paramInCategoryService.save( new ParamInCategory( catKartyG , paramKGUklad ));
        paramInCategoryService.save( new ParamInCategory( catKartyG , paramKGPamiec ));
        paramInCategoryService.save( new ParamInCategory( catKartyG , paramKGTypPamiec ));
        paramInCategoryService.save( new ParamInCategory( catKartyG , paramKGZlacza ));



        // https://www.x-kom.pl/p/1068603-karta-graficzna-amd-amd-radeon-rx-6950-xt-16gb-gddr6.html
        pValService.save( new PValue( karta1AMD, paramKGProducent, "AMD" ) );
        pValService.save( new PValue( karta1AMD, paramKGUklad, "RX 6950 XT" ) ); // Radeon™
        pValService.save( new PValue( karta1AMD, paramKGPamiec, "16" ) );
        pValService.save( new PValue( karta1AMD, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta1AMD, paramKGZlacza, "HDMI 2.1 - 1 szt., DisplayPort 1.4 - 2 szt., USB Typu-C" ) );

        // https://www.x-kom.pl/p/1184180-karta-graficzna-amd-asus-radeon-rx-7600-dual-oc-v2-8gb-gddr6.html
        pValService.save( new PValue( karta2ASUS, paramKGProducent, "ASUS" ) );
        pValService.save( new PValue( karta2ASUS, paramKGUklad, "RX 7600" ) ); // Radeon™
        pValService.save( new PValue( karta2ASUS, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta2ASUS, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta2ASUS, paramKGZlacza, "HDMI 2.1 - 1 szt., DisplayPort 1.4a - 3 szt." ) );

        // https://www.x-kom.pl/p/1156772-karta-graficzna-nvidia-gigabyte-geforce-rtx-4060-eagle-oc-8gb-gddr6.html
        pValService.save( new PValue( karta3Gigabyte, paramKGProducent, "Gigabyte" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGUklad, "RTX 4060" ) ); // Radeon™
        pValService.save( new PValue( karta3Gigabyte, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGZlacza, "HDMI 2.1 - 2 szt., DisplayPort 1.4 - 2 szt." ) );







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


    public Product createProductTest( String name, Category category, Double price, List<String> pictureNames ){
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
        return product;
    }
}


