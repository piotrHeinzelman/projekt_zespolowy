package com.example.shoop.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@Scope("session")
public class SessionController {

    @RequestMapping(value = {"/addToSession/{name}/{value}"}, method = RequestMethod.GET)
    @ResponseBody
    public String addToSessionValue(HttpServletRequest httpServletRequest, Model model, @PathVariable String name, @PathVariable String value  ){

        //System.out.println( "!!!" );
        //System.out.println( "!!!" );

        HttpSession session = httpServletRequest.getSession();
        //System.out.println( session );
        session.setAttribute(name, value);
        //System.out.println( session );
        //System.out.println( "AttributeNames: "+session.getAttributeNames() );
        Iterator<String> ite = session.getAttributeNames().asIterator();
        while ( ite.hasNext() ){
            String attribName = ite.next();
        //    System.out.println( attribName + " : " + session.getAttribute( attribName ) );
        }
        //String cart = (String) session.getAttribute("cart");
        return "";
    }
}
