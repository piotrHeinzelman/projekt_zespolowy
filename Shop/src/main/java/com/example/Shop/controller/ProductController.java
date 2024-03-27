package com.example.Shop.controller;


import com.example.Shop.model.Product;
import com.example.Shop.service.DescriptionService;
import com.example.Shop.service.ProductService;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private final ProductService productService;
    @Autowired private final DescriptionService descriptionService;

    public ProductController(ProductService productService, DescriptionService descriptionService) {
        this.productService = productService;
        this.descriptionService = descriptionService;
        autostart();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping( value={"/{productId}" } , method = RequestMethod.PUT )
    @ResponseBody
    public String updateProductByPUT( @PathVariable Long productId,  @RequestBody Product product , Response response ) {
        //System.out.println( "updateProductByPUT" );
        if ( !productService.existsById( productId )) { return addProductByPOST(  product, response ); } // nie istnieje
        Optional<Product> ObyId = productService.findById(productId);
        if (ObyId.isPresent() ){
            Product prod=ObyId.get();
            prod.fullUpdate(product);
            productService.save( prod );
        }
        response.setStatus( HttpStatus.OK.value() );
        return "UPDATED";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping( value={"/" } , method = RequestMethod.POST )
    @ResponseBody
    public String addProductByPOST(@RequestBody Product product, Response response) {
        //System.out.println( "addProductByPOST" );
        Product newProduct = productService.save(product);
        response.setStatus( HttpStatus.CREATED.value() );
        return ("CREATED "+newProduct.getId());
    }


//    @RequestMapping( value={"/edit/exact_item/{codeId}/{langId}" } , method = RequestMethod.POST )
    /*
    public Product _add(String sku, String name){
        sku=sku.toUpperCase();
        Optional<Product> OProd = productService.getBySKU(sku);
        if (OProd.isEmpty()) {
            return productService.save(new Product(sku, name));
        }
        return OProd.get();
    }
    */

    private void autostart(){
        if ( productService.count()>2 ) return;
        ArrayList<String> strings=new ArrayList<>();
        strings.add("PRO070=AMD Ryzen 9 5950X");
        strings.add("PRO071=AMD Ryzen 7 5700X");
        strings.add("PRO072=AMD Ryzen 5 7600");
        strings.add("PRO073=AMD Ryzen 3 1200 OEM");
        strings.add("PRO074=Intel Core i7‑14700KF");
        strings.add("PRO075=Intel Core i5‑12400F");
        strings.add("PRO076=Intel Core i3‑12100F");
        strings.add("PRO077=Gigabyte B550 GAMING X V2");
        strings.add("PRO078=ASUS ROG STRIX B550‑F GAMING");
        strings.add("PRO079=Kingston FURY 16GB (2x8GB) ");
        strings.add("PRO080=Patriot 16GB (2x8GB) 3600MHz CL17 Viper 4");
        strings.add("PRO081=Silver Monkey X Chest TG");
        strings.add("PRO082=Genesis IRID 505F");
        strings.add("PRO083=Corsair RM750x 750W 80 Plus Gold");
        strings.add("PRO084=ENDORFY Vero L5 700W 80 Plus");
        strings.add("PRO085=be quiet! Pure Power 12 M 850W 80");

        Product P=null;
        for ( String s : strings ){
            String c=s.split("=")[0];
            String n=s.split("=")[1];
            P = productService.getBySKU( c ).orElse( new Product( c,n ) ); if ( P.getId()==null ) productService.save( P );
        }
    }

}
