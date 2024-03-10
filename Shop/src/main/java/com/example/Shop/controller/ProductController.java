package com.example.Shop.controller;


import com.example.Shop.model.Product;
import com.example.Shop.repo.DescriptionRepo;
import com.example.Shop.repo.ProductRepo;
import com.example.Shop.service.DescriptionService;
import com.example.Shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired private final ProductService productService;
    @Autowired private final DescriptionService descriptionService;

    public ProductController(ProductService productService, DescriptionService descriptionService) {
        this.productService = productService;
        this.descriptionService = descriptionService;
    }


   // @RequestMapping( value={"/edit/exact_item/{codeId}/{langId}" } , method = RequestMethod.GET )
   // public String editExactItem(  @PathVariable Long codeId , @PathVariable Long langId ,  Model model  ) {


    // Controller - called by request /product/list
    @RequestMapping( value={"/list" } , method = RequestMethod.GET )
    public String listAllProduct_GET(  Model model  ) {
        return listAllProduct_Impl( model );
    }

    private String listAllProduct_Impl( Model model ){
        model.addAttribute( "productList" , productService.findAll() );
        return "product/list";
    }








    public Product add(String sku, String name){
        sku=sku.toUpperCase();
        Optional<Product> OProd = productService.getBySKU(sku);
        if (OProd.isEmpty()) {
            return productService.save(new Product(sku, name));
        }
        return OProd.get();
    }

}
