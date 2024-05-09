package com.example.shoop.guestAndUserControllers;

import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.model.Product;
import com.example.shoop.repo.CategoryService;
import com.example.shoop.repo.PictureService;
import com.example.shoop.repo.PriceService;
import com.example.shoop.repo.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;


@Controller
public class CartController {

    @Autowired private ProductService productService;
    @Autowired private ProductController productController;




    @RequestMapping(value = {"/cart/productinfo/{productId}"}, method = RequestMethod.GET)
    public String crewImgSendGET( Model model, @PathVariable Long productId ){

        Optional<Product> OProduct = productService.findById( productId );
        if (OProduct.isPresent()  ){
            Product product = OProduct.get();
            // System.out.println( ">>>" + productId + " : " + product );
            productController.prepareModelForProductEdit( model ,  product );
            return "product/product_full";
        }

        productController.prepareIndex( model );
        return "redirect:/index";
    }

/*
    @RequestMapping(value = {"/crew/img/send_file/"}, method = RequestMethod.POST)
    public String crewImgSendPOST( Model model, FileForm form ) throws IOException, NullPointerException {

        System.out.println( form.getSKU() );
        if (form.getUploadedFile() == null || form.getUploadedFile().isEmpty()) {
            model.addAttribute("errorMsg", " niewłaściwy plik :- ( ");
        }

        FileTool fileTool = new FileTool();

        String fileName = form.getUploadedFile().getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
        String PathForXMLFile = fileTool.getFileRoot();
        String random = "" + new Random().nextInt(99);
        String random2 = "" + new Random().nextInt(99);

        File destFile = new File(PathForXMLFile + "img_" + random + "_" + random2 + "." + ext);
        System.out.println( destFile.getCanonicalFile() );
        System.out.println( destFile.getAbsoluteFile() );
        System.out.println( destFile.getAbsolutePath() );

        try (InputStream in = form.getUploadedFile().getInputStream();
             OutputStream out = new FileOutputStream(destFile)) {
            IOUtils.copy(in, out);
            System.out.println(destFile.getCanonicalFile().getCanonicalPath());
        } catch (Exception ex) {
            model.addAttribute("errorMsg", ex);
        }


        return "redirect:";
    }
*/

















}