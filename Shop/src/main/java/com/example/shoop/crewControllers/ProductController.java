package com.example.shoop.crewControllers;

import com.example.shoop.config.FileTool;
import com.example.shoop.model.*;
import com.example.shoop.repo.*;
import com.example.shoop.session.NameComparator;
import com.example.shoop.session.NameComparatorR;
import com.example.shoop.session.PriceComparator;
import com.example.shoop.session.PriceComparatorR;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired private HttpServletRequest httpServletRequest;



    @RequestMapping(value = {"/crew/product/list/{categoryId}","/crew/product/list"}, method = RequestMethod.GET)
    public String crewProductListGET( @PathVariable(required = false) Long categoryId, Model model ){
        Category category=null;
        if ( categoryId!=null ) { Optional<Category> OC=categoryService.findById( categoryId ); if (OC.isPresent()) { category=OC.get();} }
        prepareModelForProductList( model , category );
        return "product/crew_list";
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
        return "product/crew_category_list";
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








    @RequestMapping(value = {"/product/list/{categoryId}","/product/list"}, method = RequestMethod.GET)
    public String productList_GET( @PathVariable(required = false) Long categoryId, Model model ){

        Category category=null;
        if ( categoryId!=null ) { Optional<Category> OC=categoryService.findById( categoryId ); if (OC.isPresent()) { category=OC.get();} }
        if ( category!=null ) {
            prepareModelForProductList(model, category);
            return "product/product_list";
        }
        model.addAttribute("categories",categoryService.findAll());
        return "product/category_list";
    }




    public void prepareIndex( Model model ){ prepareIndex( model , null ); }

    public void prepareIndex( Model model , Long categoryId ){
        Iterable<Product> allProducts=null;
        Category category=null;
        if ( categoryId!=null ) {
            Optional<Category> OCategory = categoryService.findById(categoryId);
                if (OCategory.isPresent() ){ category=OCategory.get();}
        }
        /* products */    prepareModelForProductList(  model ,  category );
        model.addAttribute( "gridProducts" , allProducts );
        model.addAttribute( "allCategory" , categoryService.findAll() );
    }


    public void prepareModelForProductList( Model model , Category category ){
        // System.out.println( "prepareModelForProductList(SORT,PAGEABLE)" );
        // Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        // Pageable secondPageWithFiveElements = PageRequest.of(1, 5);
        // Sort sortByName = Sort.by("name");

        /*
        Pageable sortedByName =
        PageRequest.of(0, 3, Sort.by("name"));

        Pageable sortedByPriceDesc =
        PageRequest.of(0, 3, Sort.by("price").descending());

        Pageable sortedByPriceDescNameAsc =
        PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));
         */

        Iterable<Product> products= Collections.EMPTY_SET;
        if ( category==null ) {
            products=productService.findAll();
            // products=productService.findAll( sortByName ) ;
            // products=productService.findAll(firstPageWithTwoElements);
            // products=productService.findAll(firstPageWithTwoElements);
        } else {
            products=category.getProducts();
        }

        List<Product> sorted = pagineAndSort( products );

        model.addAttribute( "products" , sorted );
        //model.addAttribute( "products" , products );
    }

    public void prepareModelForProductEdit( Model model , Product product ){
        model.addAttribute("product", product );
        model.addAttribute( "categoryList" , categoryService.findAll() );
        model.addAttribute( "statusList" , Status.getAsComboList() );
        model.addAttribute( "paramList" , getParametersAsStringByProduct(  product ) );
    }


    public List<String> getParametersAsStringByProduct( Product product ){
        Long id=product.getId();
        List <String> outList = new ArrayList<>(5);
        Iterable<Map<String, String>> allParams= paramService.findAllByProductId(product.getId());
        for ( Map<String,String> map : allParams ){
            String U = "";
            if ( map.containsKey("UNIT") &&  map.get("UNIT")!=null && map.get("UNIT").length()>0 ) { U=" "+ map.get("UNIT"); }
            outList.add( "" + map.get("NAME") + " : " + map.get("VAL") + U );
        }
        return outList  ;
    }


    List <Product> pagineAndSort( Iterable<Product> productsUnsorted ){

        ArrayList< Product > products = new ArrayList<>(10);
        Iterator<Product> it = productsUnsorted.iterator();
        while  ( it.hasNext() ){
            products.add( it.next() );
        }


        HttpSession session = httpServletRequest.getSession();
        String sortType="NA";
        if ( session.getAttribute("sort")!=null ) {
            sortType = session.getAttribute("sort").toString();
        }

        Comparator<Product> comparator=null;
        switch ( sortType ){
            case "NA": comparator=new NameComparator();  break;
            case "ND": comparator=new NameComparatorR();  break;
            case "PA": comparator=new PriceComparator(); break;
            case "PD": comparator=new PriceComparatorR(); break;
        }
        products.sort( comparator );
        // paginate ?
        return products;
    }

}
