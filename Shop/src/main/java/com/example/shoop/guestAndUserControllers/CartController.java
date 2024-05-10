package com.example.shoop.guestAndUserControllers;

import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.model.Cart;
import com.example.shoop.model.CartItem;
import com.example.shoop.model.Category;
import com.example.shoop.model.Product;
import com.example.shoop.repo.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class CartController {

    @Autowired private ProductService productService;
    @Autowired private ProductController productController;
    @Autowired private JdbcUserDetailsManager users;
    @Autowired private CartService cartService;
    @Autowired private CartItemService cartItemService;


    @RequestMapping(value = {"/product/find"}, method = RequestMethod.POST)
    public String crewProductListGET( @RequestParam String find_name, Model model ){
        model.addAttribute( "products" , productService.findByFragName( find_name ) );
        return "product/product_list";
    }

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
        return "redirect:/user/add";
    }


    @RequestMapping(value = {"/cart/addTo/{productId}"}, method = RequestMethod.GET)
    public String addToCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
        return addOneProductToCartGET( model, productId, request );
    }

/*
<a th:href="@{'/cart/addOne/'+row.id}"/>[ +1 ]</a>
<a th:href="@{'/cart/subOne/'+row.id}"/>[ -1 ]</a>
<a th:href="@{'/cart/clearRow/'+row.id}"/>[ clear ]</a>
*/

    @RequestMapping(value = {"/cart/clearCart"}, method = RequestMethod.GET)
    public String removeProductFromCartGET( Model model, HttpServletRequest request ){
        Cart cart = getActiveCart( request );
        if ( cart!=null ){
            cartItemService.clearCart( cart.getId() );
        }
        return "redirect:/product/list";
    }

    @RequestMapping(value = {"/cart/clearRow/{productId}"}, method = RequestMethod.GET)
    public String removeProductFromCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
        CartItem cartItem = null;
        cartItem = getCartItem(  request ,  productId );
        if ( cartItem!=null ) {
            cartItemService.delete( cartItem );
        }
        return "redirect:/cart/view";
    }

@RequestMapping(value = {"/cart/subOne/{productId}"}, method = RequestMethod.GET)
public String subOneProductToCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
    CartItem cartItem = null;
    cartItem = getCartItem(  request ,  productId );
    if ( cartItem!=null ) {
        Long Q=cartItem.getQuantity();
        Q--;
        if ( Q==0 ){
            cartItemService.delete( cartItem );
        } else {
        cartItem.setQuantity( Q );
        cartItemService.save( cartItem );
        }
    }
    return "redirect:/cart/view";
}

    @RequestMapping(value = {"/cart/addOne/{productId}"}, method = RequestMethod.GET)
    public String addOneProductToCartGET( Model model, @PathVariable Long productId , HttpServletRequest request ){
        CartItem cartItem = null;
        cartItem = getCartItem(  request ,  productId );
        if ( cartItem==null ) {
            createONEcartItem( request ,  productId );
        } else {
            cartItem.setQuantity(1 + cartItem.getQuantity());
            cartItemService.save( cartItem );
        }
        return "redirect:/cart/view";
    }

    @RequestMapping(value = {"/cart/view"}, method = RequestMethod.GET)
    public String crewImgSendGET( Model model, HttpServletRequest request ){
        Long cartId = getActiveCartId( request );

        if ( cartId==null ) return "redirect:/user/add";
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
            model.addAttribute( "cartProductList", cartProductList );
            return "cart/view";
        }
        return "redirect:/user/add";
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

    public CartItem getCartItem( HttpServletRequest request , Long productId ){
    Optional<Product> OProduct = productService.findById( productId );
        if ( OProduct.isPresent() ) {
        Cart cart = getActiveCart( request );
        if ( cart!=null ) {
            Product product = OProduct.get();
            Optional<CartItem> OCartItem = cartItemService.getByCartIdProdId(cart.getId(), product.getId());
            if (OCartItem.isPresent() ) return OCartItem.get();
    }}
    return null;
    }

    public CartItem createONEcartItem( HttpServletRequest request , Long productId ) {
        Optional<Product> OProduct = productService.findById(productId);
        if (OProduct.isPresent()) {
            Cart cart = getActiveCart(request);
            if (cart != null) {
                Product product = OProduct.get();
                Optional<CartItem> OCartItem = cartItemService.getByCartIdProdId(cart.getId(), product.getId());
                if ( OCartItem.isEmpty() ){
                    return cartItemService.save( new CartItem( product.getId(),  cart.getId(), product.getValue() ) );
                }
            }
        }
        return null;
    }





}








