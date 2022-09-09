package com.wallet.walletmgt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wallet.walletmgt.messege.Messege;
import com.wallet.walletmgt.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
    private UserRepository userrepo;
	
	
	
	@GetMapping(value = "/")
	public String viewHomePage(Model model, HttpServletRequest request, HttpSession session) {
		
		model.addAttribute("users",userrepo.findAll());
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
		 
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String username = ((UserDetails) principal).getUsername();
         Messege msg = new Messege();
		 if (hasUserRole) {
	            return "adminindex";
	            
	        } else {
	        	return "userindex";
	        }
	}

}
