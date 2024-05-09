package com.example.shoop.guestAndUserControllers;

import com.example.shoop.adminControllers.AccountService;
import com.example.shoop.crewControllers.ProductController;
import com.example.shoop.model.UA;
import com.example.shoop.repo.CategoryService;
import com.example.shoop.repo.ProductService;
import com.example.shoop.repo.UAService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    @Autowired private ProductService productService;
    @Autowired private ProductController productController;
    @Autowired private CategoryService categoryService;

    @Autowired private JdbcUserDetailsManager users;
    @Autowired private UserDetailsService userDetailsService;

    @Autowired private UAService uaService;


    @RequestMapping( value={ "/","","/index","index","index.php","index.asp","/index.php","/index.asp" } ) // , method = RequestMethod.POST
    public String home( Model model ){
        return "index";
    }


    @RequestMapping( value={ "/user/add" } , method = RequestMethod.GET )
    public String homeAddUser(){
        return "user/add";
    }

    @RequestMapping( value={ "/user/add" } , method = RequestMethod.POST )
    public String homeAddUserPOST( Model model , @RequestParam Map<String,String> paramMap ) {
        try {
            accountService.addUser(paramMap);
        } catch ( Throwable th ){ model.addAttribute("errorMsg", th.getMessage() ); return "/user/add"; }
        return "index";
    }




    @RequestMapping( value={ "/user/login" } , method = RequestMethod.GET )
    public String login() {
        return "user/login";
    }




    @RequestMapping( value={ "/user/logout" } , method = RequestMethod.GET )
    public String logout( HttpServletRequest request, HttpServletResponse response ) {
        accountService.logout( request, response );
        return "redirect:/";
    }



    @RequestMapping( value={ "/user/info" } , method = RequestMethod.GET )
    public String homeUserInfo( Model model ){
        model.addAttribute("success", accountService.getUserGroup() );
        return "index";
    }


    @RequestMapping( value={ "/my_user/address" } , method = RequestMethod.GET )
    public String userSetAddressGET( Model model , HttpServletRequest request  ){
        if ( request.getUserPrincipal()==null ) { return "index"; }
        UA userAddress = getUserAddress(request);
        if (userAddress!=null) {
            model.addAttribute("userAddress", userAddress);
        }
        return "user/edit_address";
    }

    @RequestMapping( value={ "/my_user/address" } , method = RequestMethod.POST )
    public String userSetAddressPOST( Model model ,
        @RequestParam String first_Name,
        @RequestParam String last_Name,
        @RequestParam String postal_Code,
        @RequestParam String Stre3t,
        @RequestParam String numb,
        @RequestParam String theCity,
        @RequestParam String mobil,
        HttpServletRequest request ) {
        UA userAddress = getUserAddress( request );
        if (userAddress!=null){
            userAddress.setFirst_Name ( ""+first_Name );
            userAddress.setLast_Name ( last_Name );
            userAddress.setPostal_Code ( postal_Code );
            userAddress.setStre3t ( Stre3t );
            userAddress.setNumb ( numb );
            userAddress.setTheCity ( theCity );
            userAddress.setMobil ( mobil );
            uaService.save( userAddress );
        }

        return "redirect:/index";
    }

    public UA getUserAddress( HttpServletRequest request ){
        String userName = request.getUserPrincipal().getName();
        if ( users.userExists( userName )) {
            UA userAddress = null;
            Iterable<UA> OuserAddress = uaService.findByEmail( userName );
            if (OuserAddress.iterator().hasNext() ) { userAddress=OuserAddress.iterator().next(); }
            else { userAddress=uaService.save( new UA( userName ) ); userAddress=uaService.save( userAddress ); }
            return userAddress;
    }
        return null;
    }


}
