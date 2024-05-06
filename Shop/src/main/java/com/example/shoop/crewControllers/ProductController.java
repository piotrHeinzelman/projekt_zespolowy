package com.example.shoop.crewControllers;

import com.example.shoop.config.FileTool;
import com.example.shoop.model.Category;
import com.example.shoop.model.Picture;
import com.example.shoop.model.Price;
import com.example.shoop.model.Product;
import com.example.shoop.repo.CategoryService;
import com.example.shoop.repo.PictureService;
import com.example.shoop.repo.PriceService;
import com.example.shoop.repo.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Controller
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private PictureService pictureService;
    @Autowired private PriceService priceService;
    @Autowired private CategoryService categoryService;


    @RequestMapping(value = {"/crew/product/list/{categoryId}","/crew/product/list"}, method = RequestMethod.GET)
    public String productListGET( @PathVariable(required = false) Long categoryId, Model model ){
        Category category=null;
        if ( categoryId!=null ) { Optional<Category> OC=categoryService.findById( categoryId ); if (OC.isPresent()) { category=OC.get();} }
        prepareModelForProductList( model , category );
        return "product/list";
    }


    @RequestMapping(value = {"/crew/product/new"}, method = RequestMethod.POST)
    public String crewProductNewPOST( Model model,  @RequestParam Map<String,String> paramMap )   {
        if (paramMap.containsKey("SKU") && paramMap.containsKey("name")) {
            String SKU=paramMap.get("SKU");
            String name=paramMap.get("name");
            Product product = new Product( SKU, name );
            productService.save( product );
        }
        prepareModelForProductList( model , null );
        return "redirect:/crew/product/list";
    }


    public void addPriceToProduct( Product product, Double priceValue ){
        product.setPrice( new Price( product, priceValue ) );
        productService.save( product );
    }


    public void addPictureToProduct( Product product , String pictDescription, MultipartFile multipartFile ) {

        Picture picture = new Picture( product,  pictDescription );
        pictureService.save( picture );

        FileTool.moveUploadedToFile( picture , multipartFile );

        product.getPictures().add( picture );
        productService.save( product );
    }


    public void prepareModelForProductList( Model model , Category category ){
        Iterable<Product> products= Collections.EMPTY_SET;
        if ( category==null ) {
            products=productService.findAll();
        } else {
            products=category.getProducts();
        }
        System.out.println( "Category:" + category );
        if ( products.iterator().hasNext()) { System.out.println( products.iterator().next() ); }
        model.addAttribute( "products" , products );
    }











    @RequestMapping(value = {"/crew/category/list"}, method = RequestMethod.GET)
    public String categoryListGET( Model model ){
        model.addAttribute("categories", categoryService.findAll());
        return "product/category_list";
    }


    @RequestMapping(value = {"/crew/category/delete/{categoryId}"}, method = RequestMethod.GET)
    public String categoryDeleteGET( @PathVariable Long categoryId, Model model ){
        Optional<Category> byId = categoryService.findById(categoryId);
        if (byId.isPresent()) { categoryService.deleteById( categoryId ); }
        return "redirect:/crew/category/list";
    }

    @RequestMapping(value = {"/crew/category/edit/{categoryId}"}, method = RequestMethod.GET)
    public String categoryEditGET( @PathVariable Long categoryId, Model model ){
        Optional<Category> byId = categoryService.findById(categoryId);
        if (byId.isPresent()) {
            model.addAttribute("category", byId.get());
            return "product/category_edit";
        }
        return "product/category_list";
    }

    @RequestMapping(value = {"/crew/category/new"}, method = RequestMethod.POST)
    public String crewCategoryNewPOST( Model model,  @RequestParam Map<String,String> paramMap )   {
        if (paramMap.containsKey("name")) {
              categoryService.save(new Category( paramMap.get("name") ));
        }
        return "redirect:/crew/category/list";
    }


    @RequestMapping(value = {"/crew/category/edit"}, method = RequestMethod.POST)
    public String crewCategoryEditPOST( Model model,  @RequestParam Map<String,String> paramMap )   {
        if ( paramMap.containsKey("categoryId") ) {
            Long categoryId = Long.parseLong( paramMap.get("categoryId") );
            Optional<Category> OCategory = categoryService.findById(categoryId);
            if (OCategory.isPresent() ){
                Category category=OCategory.get();
                if ( paramMap.containsKey("name") ) { category.setName( paramMap.get("name") ); }
                if ( paramMap.containsKey("description") ) { category.setDescription( paramMap.get("description") ); }
                if ( paramMap.containsKey("ord3r") ) {
                    Long order;
                    try { order = Long.parseLong(paramMap.get("ord3r")); } catch( Throwable th ) { order=null; }
                    category.setOrd3r( order );
                }
                categoryService.save( category );
            }
        }
        return "redirect:/crew/category/list";
    }
}