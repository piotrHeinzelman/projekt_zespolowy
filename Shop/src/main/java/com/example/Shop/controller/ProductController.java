package com.example.Shop.controller;


import com.example.Shop.model.Product;
import com.example.Shop.service.DescriptionService;
import com.example.Shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private final ProductService productService;
    @Autowired private final DescriptionService descriptionService;

    public ProductController(ProductService productService, DescriptionService descriptionService) {
        this.productService = productService;
        this.descriptionService = descriptionService;
    }

     @RequestMapping( value={"/" } , method = RequestMethod.POST )
     @ResponseStatus(HttpStatus.CREATED)
     @ResponseBody
     public String addProductByPOST( @RequestBody Product product ) {
         System.out.println( "addProductByPOST( @RequestBody Product product )" );
         Product newProduct = productService.save(product);
         return ("CREATED "+newProduct.getId());
     }

    @RequestMapping( value={"/{productId}" } , method = RequestMethod.PUT )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String updateProductByPUT( @PathVariable Long productId,  @RequestBody Product product ) {
        if ( !productService.existsById( productId )) { return addProductByPOST(  product ); } // nie istnieje
        Optional<Product> ObyId = productService.findById(productId);
        if (ObyId.isPresent() ){
            Product prod=ObyId.get();
            prod.fullUpdate(product);
            productService.save( prod );
        }
        return "UPDATED";
    }


//    @RequestMapping( value={"/edit/exact_item/{codeId}/{langId}" } , method = RequestMethod.POST )
    public Product _add(String sku, String name){
        sku=sku.toUpperCase();
        Optional<Product> OProd = productService.getBySKU(sku);
        if (OProd.isEmpty()) {
            return productService.save(new Product(sku, name));
        }
        return OProd.get();
    }

}
