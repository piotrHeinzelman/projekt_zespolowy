package com.example.shoop.guestAndUserControllers;

import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.model.Cart;
import com.example.shoop.model.CartItem;
import com.example.shoop.model.Product;
import com.example.shoop.repo.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


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
            // System.out.println( ">>>" + productId + " : " + product.getPictures() );
            productController.prepareModelForProductEdit( model ,  product );
            return "product/product_full";
        }
        productController.prepareIndex( model );
        return "redirect:/index";
    }


    @RequestMapping(value = {"/cart/addTo/{productId}"}, method = RequestMethod.GET)
    public String addToCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
        return addOneProductToCartGET( model, productId, request );
        /*
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
         */
    }

/*
<a th:href="@{'/cart/addOne/'+row.id}"/>[ +1 ]</a>
<a th:href="@{'/cart/subOne/'+row.id}"/>[ -1 ]</a>
<a th:href="@{'/cart/clearRow/'+row.id}"/>[ clear ]</a>
*/
    @RequestMapping(value = {"/cart/addOne/{productId}"}, method = RequestMethod.GET)
    public String addOneProductToCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
        Optional<Product> OProduct = productService.findById( productId );
        if ( OProduct.isPresent() ) {
            Cart cart = getActiveCart( request );
            if ( cart!=null ) {
                Product product = OProduct.get();

                System.out.println( cart );
                System.out.println( product );

                //cartItemService.save(new CartItem(product.getId(), cartId, product.getValue()));
                return "redirect:/cart/view";
            }
        }
        return "redirect:/user/add";
    }

    @RequestMapping(value = {"/cart/view"}, method = RequestMethod.GET)
    public String crewImgSendGET( Model model, HttpServletRequest request ){
        Long cartId = getActiveCartId( request );

        if ( cartId==null ) return "redirect:/index";
        Optional<Cart> OCart = cartService.findById( cartId );
        if ( OCart.isPresent() ) {
            Cart cart = OCart.get();
        List<CartItem> items = new ArrayList<>();
        cart.setItems( items );

            Map<Long, Product> cartProductList = new HashMap<>();
            productService.getAllProductsFromCartByCartId(cartId).iterator().forEachRemaining(f -> {
                cartProductList.put(f.getId(), f);
            });

            cartItemService.getAllItemByCartId(cartId).iterator().forEachRemaining( f->{
                        CartItem c = f;
                        c.setProduct_name( cartProductList.get(f.getProduct_id()).getName() );
                        c.setPricePerItem( cartProductList.get(f.getProduct_id()).getValue() );
                        c.setProduct_SKU( cartProductList.get(f.getProduct_id()).getSKU() );
                        items.add( c ); } );

            Double sum=0.0;
            for ( CartItem ci : cart.getItems() ){
                sum+=ci.getQuantity()*ci.getPricePerItem();
            }
            cart.setSum( sum );
            model.addAttribute( "cart", cart );
            return "cart/view";
        }
        return "redirect:/index";
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
            Long numberOfCarts=cartService.countCartBelongsToUserName( userName );
            if ( numberOfCarts==1L ){
                // cart exists
                Optional<Long> OCartId=cartService.findOpenIdByUserName(userName);
                if ( OCartId.isPresent() ) { return OCartId.get();}
            }
            if ( numberOfCarts==0L ) {
                // cart NOT exists
                Cart cart=cartService.save( new Cart( userName )); return cart.getId();
            }
        }
        return null;
        }

    public Long getActiveCartId(  HttpServletRequest request ){
        String userName = getUsernameByRequest(request);
            if ( userName ==null ) return  null;
        return getActiveCartIdByUserNam_orCreateNew(userName);
    }

    public Cart getActiveCart(  HttpServletRequest request ){
        Long cartId = getActiveCartId( request );
        if ( cartId==null ) return null;
        Optional<Cart> OCart = cartService.findById(cartId);
        if (OCart.isPresent()) { return OCart.get(); }
        return null;
    }





}








