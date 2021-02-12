package org.sid.web;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Controller
public class ApplicationController{
	
	@Autowired
	private KeycloakRestTemplate keycloakRestTemplate;
	


    @GetMapping("/")
    public String index(){
        return "index";
    }
  
    @GetMapping("/products")
    public String products(Model model){   
    PagedModel<Product> pageProducts= keycloakRestTemplate.getForObject("http://localhost:8082/products",PagedModel.class);
    model.addAttribute("products",pageProducts);   
    return "products";    
    }
    @GetMapping("/customers")
    public String customers(Model model){   
    PagedModel<Customer> pageCustomers= keycloakRestTemplate.getForObject("http://localhost:8084/customers",PagedModel.class);
    model.addAttribute("customers",pageCustomers);   
    return "customers";    
    }
    @GetMapping("/bills")
    public String bills(Model model){   
    PagedModel<Bill> pageBills= keycloakRestTemplate.getForObject("http://localhost:8083/bills",PagedModel.class);
    model.addAttribute("bills",pageBills);   
    return "bills";    
    }
    
    @GetMapping("/jwt")
    @ResponseBody
    public Map<String,String> map(HttpServletRequest request){
    	KeycloakAuthenticationToken token =(KeycloakAuthenticationToken) request.getUserPrincipal();
    	KeycloakPrincipal principal=(KeycloakPrincipal) token.getPrincipal();
    	KeycloakSecurityContext keycloakSecurityContext=principal.getKeycloakSecurityContext();
    	Map<String,String> map=new HashMap<>();
    	map.put("access_token", keycloakSecurityContext.getTokenString());
    	return map;
    }
   
   @ExceptionHandler(Exception.class) 
   public String exception(Exception e,Model model) {
	   model.addAttribute("ErreurMessage", "Problème d'autorisation");
	   return "erreur";
   }

}

@Data
class Product{
	private Long id;
	private String name;
	private double price;
	private double quantity;
}

@Data
class Customer{
	private Long id;
	private String name;
	private String email;
}

@Data
class Bill{
	private Long id;
	private Date billingDate;
	private Long customerID;
	private Customer customer;
}
