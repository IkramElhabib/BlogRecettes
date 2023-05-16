package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Favoris;
import com.example.demo.model.Recette;
import com.example.demo.repository.FavorisRepository;
import com.example.demo.repository.RecetteRepository;
import org.springframework.security.core.userdetails.UserDetails;




@Controller
@RequestMapping("/user")
public class UserController {
	
	 @Autowired
	    private RecetteRepository recetteRepository;
	 
	    @Autowired
	    private FavorisRepository favorisRepository;
	    
	    @GetMapping("/")
	    public String redirectToHomePage(Model model) {
	        List<Recette> recettes = recetteRepository.findAll();
	        model.addAttribute("recettes", recettes);
	        return "garde";
	    }
	    
	 
	    
	  @GetMapping("/profile")
	  public String userProfile(Model model, Authentication authentication) {
	      // get authenticated user's details
	      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	      
	   
	      
	   	      return "user";
	  }
	  
	  @GetMapping("/garde")
	    public String garde(Model model) {
		  List<Recette> recettes = recetteRepository.findAll();
	        model.addAttribute("recettes", recettes);
	        return "garde";
	    }
	  
	
}
