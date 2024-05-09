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

/*      addCategory("Karty Graficzne");
        properties:("producent") Gigabyte | Sapphire | AMD | ASUS
        properties:("układ pamięci")RadeonTM RX6600 XT
        properties:("pamięć")8GB
        properties:("rodzaj pamięci")GDDR6
        properties:("złącza")HDMI 2szt. DP 2szt. */

        Category catProcesory = new Category("Procesory");
        catProcesory = categoryService.save(catProcesory);

/*             addCategory("Procesory AMD");
    properties:("producent") AMD
    properties:("socket")Socket AM4
    properties:("taktowanie")3.6 GHz
    properties:("liczba rdzeni")4
    properties:("cache")6 MB

 */


        Category catPlytyG = new Category("Płyty główne");
        catPlytyG = categoryService.save(catPlytyG);

/*          addCategory("Płyty główne");
    properties:("producent") AMD
    properties:("socket")Socket AM4
    properties:("chipset")AMD B550 B450
    properties:("format") mATX ATX
    properties:("ilość banków pamięci RAM")4
 */

        Category catObudowy = new Category("Obudowy");
        catObudowy = categoryService.save(catObudowy);

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



// .......................  Karty graficzne

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
        pValService.save( new PValue( karta1AMD, paramKGUklad, "RX 6950 XT" ) );
        pValService.save( new PValue( karta1AMD, paramKGPamiec, "16" ) );
        pValService.save( new PValue( karta1AMD, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta1AMD, paramKGZlacza, "HDMI 2.1 - 1 szt., DisplayPort 1.4 - 2 szt., USB Typu-C" ) );

        // https://www.x-kom.pl/p/1184180-karta-graficzna-amd-asus-radeon-rx-7600-dual-oc-v2-8gb-gddr6.html
        pValService.save( new PValue( karta2ASUS, paramKGProducent, "ASUS" ) );
        pValService.save( new PValue( karta2ASUS, paramKGUklad, "RX 7600" ) );
        pValService.save( new PValue( karta2ASUS, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta2ASUS, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta2ASUS, paramKGZlacza, "HDMI 2.1 - 1 szt., DisplayPort 1.4a - 3 szt." ) );

        // https://www.x-kom.pl/p/1156772-karta-graficzna-nvidia-gigabyte-geforce-rtx-4060-eagle-oc-8gb-gddr6.html
        pValService.save( new PValue( karta3Gigabyte, paramKGProducent, "Gigabyte" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGUklad, "RTX 4060" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta3Gigabyte, paramKGZlacza, "HDMI 2.1 - 2 szt., DisplayPort 1.4 - 2 szt." ) );

        // https://www.x-kom.pl/p/687464-karta-graficzna-amd-gigabyte-radeon-rx-6600-eagle-8gb-gddr6.html
        pValService.save( new PValue( karta4GigabyteRadeon, paramKGProducent, "Gigabyte" ) );
        pValService.save( new PValue( karta4GigabyteRadeon, paramKGUklad, "RX 6600" ) );
        pValService.save( new PValue( karta4GigabyteRadeon, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta4GigabyteRadeon, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta4GigabyteRadeon, paramKGZlacza, "HDMI 2.1 - 2 szt., DisplayPort 1.4 - 2 szt." ) );

        // https://www.x-kom.pl/p/1088561-karta-graficzna-nvidia-msi-geforce-rtx-3060-ventus-2x-oc-8gb-gddr6.html
        pValService.save( new PValue( karta5MSI, paramKGProducent, "MSI" ) );
        pValService.save( new PValue( karta5MSI, paramKGUklad, "RTX 3060" ) );
        pValService.save( new PValue( karta5MSI, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta5MSI, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta5MSI, paramKGZlacza, "HDMI 2.1 - 1 szt., DisplayPort 1.4a - 3 szt." ) );

        // https://www.x-kom.pl/p/688539-karta-graficzna-amd-sapphire-radeon-rx-6600-gaming-pulse-8gb-gddr6.html
        pValService.save( new PValue( karta6Sapphire, paramKGProducent, "Sapphire" ) );
        pValService.save( new PValue( karta6Sapphire, paramKGUklad, "RX 6600" ) );
        pValService.save( new PValue( karta6Sapphire, paramKGPamiec, "8" ) );
        pValService.save( new PValue( karta6Sapphire, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta6Sapphire, paramKGZlacza, "HDMI 2.1 - 1 szt., DisplayPort 1.4 - 3 szt." ) );

        // https://www.x-kom.pl/p/1209711-karta-graficzna-nvidia-zotac-geforce-rtx-4070-super-twin-edge-12gb-gddr6x.html
        pValService.save( new PValue( karta7Zoltac, paramKGProducent, "Zotac" ) );
        pValService.save( new PValue( karta7Zoltac, paramKGUklad, "RTX 4070 Super" ) );
        pValService.save( new PValue( karta7Zoltac, paramKGPamiec, "12" ) );
        pValService.save( new PValue( karta7Zoltac, paramKGTypPamiec, "GDDR6" ) );
        pValService.save( new PValue( karta7Zoltac, paramKGZlacza, "HDMI 2.1a - 1 szt., DisplayPort 1.4a - 3 szt." ) );

// .............................. Procesory
/*
        addCategory("Procesory AMD");
        properties:("producent") AMD
        properties:("socket")Socket AM4
        properties:("taktowanie")3.6 GHz
        properties:("liczba rdzeni")4
        properties:("cache")6 MB
*/

        Parm paramPproducent = paramService.save( new Parm( "producent", "" ));
        Parm paramPsocket = paramService.save( new Parm( "socket", "" ));
        Parm paramPtaktowanie = paramService.save( new Parm( "taktowanie", "GHz" ));
        Parm paramPliczbaRdzeni = paramService.save( new Parm( "liczba rdzeni", "" ));
        Parm paramPcache = paramService.save( new Parm( "cache", "MB" ));

        paramInCategoryService.save( new ParamInCategory( catProcesory , paramPproducent ));
        paramInCategoryService.save( new ParamInCategory( catProcesory , paramPsocket ));
        paramInCategoryService.save( new ParamInCategory( catProcesory , paramPtaktowanie ));
        paramInCategoryService.save( new ParamInCategory( catProcesory , paramPliczbaRdzeni ));
        paramInCategoryService.save( new ParamInCategory( catProcesory , paramPcache ));

        // https://www.x-kom.pl/p/500097-procesor-amd-ryzen-3-amd-ryzen-3-3200g.html
        Product procesor1AMD = createProductTest("AMD Ryzen 3 3200G", catProcesory, 319.0, List.of("Procesory\\AMD-Ryzen-3-3200G.png"));
        // https://www.x-kom.pl/p/1072725-procesor-amd-ryzen-5-amd-ryzen-5-7600x.html
        Product procesor2AMD = createProductTest("AMD Ryzen 5 7600X", catProcesory, 909.0, List.of("Procesory\\AMD-Ryzen-5-7600X-AM5.png"));
        // https://www.x-kom.pl/p/735724-procesor-amd-ryzen-7-amd-ryzen-7-5800x3d.html
        Product procesor3AMD = createProductTest("AMD Ryzen 7 5800X3D", catProcesory, 1299.0, List.of("Procesory\\AMD-Ryzen-7-5800X3D.png"));
        // https://www.x-kom.pl/p/1205835-procesor-amd-threadripper-amd-ryzen-threadripper-7960x.html
        Product procesor4AMD = createProductTest("AMD Ryzen Threadripper 7960X", catProcesory, 7099.0, List.of("Procesory\\AMD-Ryzen-Threadripper-7960X-sTR5.png"));

        // https://www.x-kom.pl/p/500097-procesor-amd-ryzen-3-amd-ryzen-3-3200g.html
        pValService.save( new PValue( procesor1AMD, paramPproducent, "AMD" ) );
        pValService.save( new PValue( procesor1AMD, paramPsocket, "AM4" ) );
        pValService.save( new PValue( procesor1AMD, paramPtaktowanie, "3.6" ) );
        pValService.save( new PValue( procesor1AMD, paramPliczbaRdzeni, "4" ) );
        pValService.save( new PValue( procesor1AMD, paramPcache, "6" ) );

        // https://www.x-kom.pl/p/1072725-procesor-amd-ryzen-5-amd-ryzen-5-7600x.html
        pValService.save( new PValue( procesor2AMD, paramPproducent, "AMD" ) );
        pValService.save( new PValue( procesor2AMD, paramPsocket, "AM4" ) );
        pValService.save( new PValue( procesor2AMD, paramPtaktowanie, "3.6" ) );
        pValService.save( new PValue( procesor2AMD, paramPliczbaRdzeni, "4" ) );
        pValService.save( new PValue( procesor2AMD, paramPcache, "6" ) );

        // https://www.x-kom.pl/p/735724-procesor-amd-ryzen-7-amd-ryzen-7-5800x3d.html
        pValService.save( new PValue( procesor3AMD, paramPproducent, "AMD" ) );
        pValService.save( new PValue( procesor3AMD, paramPsocket, "AM4" ) );
        pValService.save( new PValue( procesor3AMD, paramPtaktowanie, "3.4" ) );
        pValService.save( new PValue( procesor3AMD, paramPliczbaRdzeni, "8" ) );
        pValService.save( new PValue( procesor3AMD, paramPcache, "100" ) );

        // https://www.x-kom.pl/p/1205835-procesor-amd-threadripper-amd-ryzen-threadripper-7960x.html
        pValService.save( new PValue( procesor4AMD, paramPproducent, "AMD" ) );
        pValService.save( new PValue( procesor4AMD, paramPsocket, "sTR5" ) );
        pValService.save( new PValue( procesor4AMD, paramPtaktowanie, "4.2" ) );
        pValService.save( new PValue( procesor4AMD, paramPliczbaRdzeni, "24" ) );
        pValService.save( new PValue( procesor4AMD, paramPcache, "154" ) );

// ----------------------------- Płyty główne

        Parm paramMBproducent = paramService.save( new Parm( "producent", "" ));
        Parm paramMBsocket = paramService.save( new Parm( "socket", "" ));
        Parm paramMBchipset = paramService.save( new Parm( "chipset", "" ));
        Parm paramMBformat = paramService.save( new Parm( "format", "" ));
        Parm paramMBslotRAM = paramService.save( new Parm( "ilość banków pamięci RAM", "DIMM" ));

        paramInCategoryService.save( new ParamInCategory( catPlytyG , paramMBproducent ));
        paramInCategoryService.save( new ParamInCategory( catPlytyG , paramMBsocket ));
        paramInCategoryService.save( new ParamInCategory( catPlytyG , paramMBchipset ));
        paramInCategoryService.save( new ParamInCategory( catPlytyG , paramMBformat ));
        paramInCategoryService.save( new ParamInCategory( catPlytyG , paramMBslotRAM ));

        // https://www.x-kom.pl/p/1107452-plyta-glowna-socket-1700-asrock-b760-pro-rs.html
        Product mother1AsRock = createProductTest("ASRock B760 Pro RS", catPlytyG, 669.0, List.of("PlytyG\\ASRock-B760-Pro-RS.png"));
        // https://www.x-kom.pl/p/1075906-plyta-glowna-socket-1700-asus-prime-z790-a-wifi.html
        Product mother2ASUS = createProductTest("ASUS PRIME Z790-A WIFI", catPlytyG, 1229.0, List.of("PlytyG\\ASUS-PRIME-Z790-A-WIFI.png"));
        // https://www.x-kom.pl/p/1107479-plyta-glowna-socket-1700-asus-tuf-gaming-b760-plus-wifi-ddr4.html
        Product mother3ASUS = createProductTest("ASUS TUF GAMING B760-PLUS WIFI DDR4", catPlytyG, 645.0, List.of("PlytyG\\ASUS-TUF-GAMING-B760-PLUS-WIFI-DDR4.png"));
        // https://www.x-kom.pl/p/609270-plyta-glowna-socket-am4-gigabyte-b550m-aorus-elite.html
        Product mother4GigaBYTE = createProductTest("Gigabyte B550M AORUS ELITE", catPlytyG, 439.0, List.of("PlytyG\\Gigabyte-B550M-AORUS-ELITE.png","PlytyG\\Gigabyte-B550M-AORUS-ELITEa.png", "PlytyG\\Gigabyte-B550M-AORUS-ELITEb.png"));
        // https://www.x-kom.pl/p/572283-plyta-glowna-socket-am4-msi-b550-a-pro.html
        Product mother5MSI = createProductTest("MSI B550-A PRO", catPlytyG, 469.0, List.of("PlytyG\\MSI-B550-A-PRO.png"));

        // https://www.x-kom.pl/p/1107452-plyta-glowna-socket-1700-asrock-b760-pro-rs.html
        pValService.save( new PValue( mother1AsRock, paramMBproducent, "ASRock" ) );
        pValService.save( new PValue( mother1AsRock, paramMBsocket, "1700" ) );
        pValService.save( new PValue( mother1AsRock, paramMBchipset, "B760" ) );
        pValService.save( new PValue( mother1AsRock, paramMBformat, "ATX" ) );
        pValService.save( new PValue( mother1AsRock, paramMBslotRAM, "4" ) );

        // https://www.x-kom.pl/p/1075906-plyta-glowna-socket-1700-asus-prime-z790-a-wifi.html
        pValService.save( new PValue( mother2ASUS, paramMBproducent, "ASUS" ) );
        pValService.save( new PValue( mother2ASUS, paramMBsocket, "1700" ) );
        pValService.save( new PValue( mother2ASUS, paramMBchipset, "Z790" ) );
        pValService.save( new PValue( mother2ASUS, paramMBformat, "ATX" ) );
        pValService.save( new PValue( mother2ASUS, paramMBslotRAM, "4" ) );

        // https://www.x-kom.pl/p/1107479-plyta-glowna-socket-1700-asus-tuf-gaming-b760-plus-wifi-ddr4.html
        pValService.save( new PValue( mother3ASUS, paramMBproducent, "ASUS" ) );
        pValService.save( new PValue( mother3ASUS, paramMBsocket, "1700" ) );
        pValService.save( new PValue( mother3ASUS, paramMBchipset, "B760" ) );
        pValService.save( new PValue( mother3ASUS, paramMBformat, "ATX" ) );
        pValService.save( new PValue( mother3ASUS, paramMBslotRAM, "4" ) );

        // https://www.x-kom.pl/p/609270-plyta-glowna-socket-am4-gigabyte-b550m-aorus-elite.html
        pValService.save( new PValue( mother4GigaBYTE, paramMBproducent, "Gigabyte " ) );
        pValService.save( new PValue( mother4GigaBYTE, paramMBsocket, "AM4" ) );
        pValService.save( new PValue( mother4GigaBYTE, paramMBchipset, "B550" ) );
        pValService.save( new PValue( mother4GigaBYTE, paramMBformat, "mATX" ) );
        pValService.save( new PValue( mother4GigaBYTE, paramMBslotRAM, "4" ) );

        // https://www.x-kom.pl/p/572283-plyta-glowna-socket-am4-msi-b550-a-pro.html
        pValService.save( new PValue( mother5MSI, paramMBproducent, "MSI" ) );
        pValService.save( new PValue( mother5MSI, paramMBsocket, "AM4" ) );
        pValService.save( new PValue( mother5MSI, paramMBchipset, "B550" ) );
        pValService.save( new PValue( mother5MSI, paramMBformat, "ATX" ) );
        pValService.save( new PValue( mother5MSI, paramMBslotRAM, "4" ) );

// ---------------------------  OBUDOWY

        Parm paramOproducent = paramService.save( new Parm( "producent", "" ));
        Parm paramOstd = paramService.save( new Parm( "standard płyty", "" ));
        Parm paramOliczba = paramService.save( new Parm( "liczba wentylatorów", "" ));
        Parm paramOpanel = paramService.save( new Parm( "panel boczny szklany", "" ));
        Parm paramOnapedy = paramService.save( new Parm( "napędy", "3,5\" / 2,5\"" ));

        paramInCategoryService.save( new ParamInCategory( catObudowy , paramOproducent ));
        paramInCategoryService.save( new ParamInCategory( catObudowy , paramOstd ));
        paramInCategoryService.save( new ParamInCategory( catObudowy , paramOliczba ));
        paramInCategoryService.save( new ParamInCategory( catObudowy , paramOpanel ));
        paramInCategoryService.save( new ParamInCategory( catObudowy , paramOnapedy ));

        // https://www.x-kom.pl/p/1051127-obudowa-do-komputera-silver-monkey-x-crate.html
        Product obudowa1 = createProductTest("Silver Monkey X Crate", catObudowy, 295.0, List.of("obudowy\\Silver-Monkey-X-Crate.png"));

        pValService.save( new PValue( obudowa1, paramOproducent,  "Silver Monkey" ) );
        pValService.save( new PValue( obudowa1, paramOstd, "ATX, m-ATX, Mini-ITX" ) );
        pValService.save( new PValue( obudowa1, paramOliczba, "4" ) );
        pValService.save( new PValue( obudowa1, paramOpanel, "TAK" ) );
        pValService.save( new PValue( obudowa1, paramOnapedy, "2 / 2" ) );

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


