package com.example.shoop.guestAndUserControllers;

import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.model.Cart;
import com.example.shoop.model.CartItem;
import com.example.shoop.model.Product;
import com.example.shoop.model.UA;
import com.example.shoop.repo.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class CartController {

    @Autowired private ProductService productService;
    @Autowired private ProductController productController;
    @Autowired private JdbcUserDetailsManager users;
    @Autowired private CartService cartService;
    @Autowired private CartItemService cartItemService;

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


    @RequestMapping(value = {"/cart/addTo/{productId}"}, method = RequestMethod.GET)
    public String addToCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
        Long cartId = getActiveCartId( request );
        if ( cartId!=null ) {
            Optional<Product> OProduct = productService.findById(productId);
            if ( OProduct.isPresent() ) {
                Product product = OProduct.get();
                cartItemService.save(new CartItem(product.getId(), cartId, product.getValue()));
                return "redirect:/cart/view";
            }
        }
        return "redirect:/user/add";
    }




    @RequestMapping(value = {"/cart/view"}, method = RequestMethod.GET)
    public String crewImgSendGET( Model model, @PathVariable Long productId, HttpServletRequest request ){
        Long cartId = getActiveCartId( request );
        if ( cartId==null ) return "redirect:/index";

        Map<Long,Product> cartProductList = new HashMap<>();
        List<Product> allProduct = productService.getAllItemFromCartByCartId(cartId);//.iterator().forEachRemaining( f->{ cartProductList.put( f.getId(), f ); } );
        System.out.println( allProduct );
        /*
        Optional<Cart> OCart = cartService.findById(cartId);
        if (OCart.isPresent() ) {
            Cart cart = OCart.get();
            cart.setItems(  );
        }
        */
        return "cart/view";
    }



    public String getUsernameByRequest( HttpServletRequest request ){
        if ( request.getUserPrincipal()!=null ) {
            String userName = request.getUserPrincipal().getName();
            if ( users.userExists( userName )) { return userName; }
        }
        return null;
    }

    public Long getActiveCartIdByUserNam_orCreateNew( String userName ) {
        if ( users.userExists( userName )){
            Optional<Long> OCartId=cartService.findOpenIdByUserName(userName);
            if (OCartId.isPresent()) { return OCartId.get();}
            else{Cart cart=cartService.save(new Cart(userName));return cart.getId();}
        }
        return null;
        }

    public Long getActiveCartId(  HttpServletRequest request ){
        String userName = getUsernameByRequest(request);
            if ( userName ==null ) return  null;
        return getActiveCartIdByUserNam_orCreateNew(userName);
    }


}








