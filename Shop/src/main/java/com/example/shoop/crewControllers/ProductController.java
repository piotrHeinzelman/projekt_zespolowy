package com.example.shoop.crewControllers;

import com.example.shoop.config.FileTool;
import com.example.shoop.model.*;
import com.example.shoop.repo.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@Controller
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private PictureService pictureService;
    @Autowired private PriceService priceService;
    @Autowired private CategoryService categoryService;


    @Autowired private ParamService paramService;
    @Autowired private ParamInCategoryService paramInCategoryService;
    @Autowired private PValService pValService;



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


    @RequestMapping(value = {"/crew/product/edit/{productId}"}, method = RequestMethod.GET)
    public String productEditGET( @PathVariable(required = false) Long productId, Model model ){
        Optional<Product> OProduct = productService.findById( productId );
        if ( OProduct.isPresent() ){
            prepareModelForProductEdit( model, OProduct.get() );
        }
        return "product/edit";
    }

    @RequestMapping(value = {"/crew/product/edit"}, method = RequestMethod.POST)
    public String productEditPOST( Model model,  @RequestParam Map<String,String> paramMap , @RequestParam(required = false)  MultipartFile uploadedFile )   {
        Category category = null;
        if ( paramMap.containsKey("id") ) {
            Optional<Product> OProduct = productService.findById( Long.parseLong( paramMap.get("id")) );
            if (OProduct.isPresent() ) {
                Product product = OProduct.get();

                    if ( paramMap.containsKey("name")) { product.setName( paramMap.get("name") ); }
                    if ( paramMap.containsKey("description")) { product.setDescription( paramMap.get("description") ); }
                    if ( paramMap.containsKey("status")) { product.setStatus( Status.getByLong( paramMap.get("status"))); }
                    if ( paramMap.containsKey("category")) {
                        Long categoryId = null;
                        try { categoryId = Long.parseLong(paramMap.get("category")); } catch(Throwable th) {}
                        Optional<Category> OCategory = categoryService.findById( categoryId );
                        if (OCategory.isPresent() ){
                            product.setCategory( OCategory.get() );
                        }
                    }

                // parse Price & Promo
                Double promoVal=null;
                Double priceVal=null;
                try {
                    String Sp=paramMap.get("price"); if ( Sp.length()>0 && Sp.indexOf(".")==-1) { Sp = Sp+".0"; }
                    String Sr=paramMap.get("promo"); if ( Sr.length()>0 && Sr.indexOf(".")==-1) { Sr = Sr+".0"; }
                    priceVal = Double.parseDouble( Sp );
                    promoVal = Double.parseDouble( Sr );
                } catch (Throwable th ){}


                // set Price if need
                if (( product.getPriceVal()==null && priceVal==null ) || ( product.getPriceVal()!=null && product.getPriceVal().equals( priceVal ) )){ /* no change */}
                else {
                    if ( product.getPrice()==null ){ product.setPrice( new Price( product, priceVal ) ); if ( promoVal!=null && promoVal!=0.0 )  { product.getPrice().setPromo( promoVal ); }  }
                    else {
                        Optional<Price> OPrice = priceService.findById(product.getPrice().getId());
                        if (OProduct.isPresent() ) { OPrice.get().setPrice( priceVal );
                            if ( promoVal!=null && priceVal!=0.0 )  { OPrice.get().setPromo( promoVal ); }
                            priceService.save( OPrice.get() );
                        }
                    }
                }

                // set Promo
                if ( product.getPromoVal()!=promoVal && product.getPrice()!=null ){
                    product.getPrice().setPromo( promoVal );
                    priceService.save( product.getPrice() );
                }

                // add image
                if( !uploadedFile.isEmpty() ){
                    addPictureToProduct( product , paramMap.containsKey("caption") ? paramMap.get("caption") : "", uploadedFile );
                }

                //save Product
                productService.save( product );



                // post prepare list
                category = product.getCategory();
            }
        }
        prepareModelForProductList( model , category );
        return "redirect:/crew/product/list"+((category!=null) ? "/"+category.getId() : "" ) ;
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
        model.addAttribute( "products" , products );
        //model.addAttribute( "categoryList" , categoryService.findAll() );
    }

    public void prepareModelForProductEdit( Model model , Product product ){
        //System.out.println( product );
        model.addAttribute("product", product );
        model.addAttribute( "categoryList" , categoryService.findAll() );
        model.addAttribute( "statusList" , Status.getAsComboList() );
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


    public void addParameter_ToCategory( Category category , String parameterName, String unit ) {
        System.out.println( "-- FAKE MOCKUP -- add: " + parameterName + ", with unit: " + unit + " to category: " + category );
    }


    public List<String> getParametersAsStringByProduct( Product product ){
        Long id=product.getId();
        return Collections.EMPTY_LIST;
    }

}