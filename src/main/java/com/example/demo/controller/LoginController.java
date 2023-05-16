package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
		 
	 @Autowired
	    private UserService userService;
	 
	 @RequestMapping("/login")
	    public String loginForm() {
	        return "login";
	    }
	 
	 @RequestMapping("/logintest")
	    public String loginFormTest() {
	        return "testlogin";
	    }
	 
	 @GetMapping("/registration")
	    public String registrationForm(Model model) {
	        UserDto user = new UserDto();
	        model.addAttribute("user", user);
	        return "registration";
	    }
	 
	 @PostMapping("/registration")
	    public String registration( @Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
	        User existingUser = userService.findUserByEmail(userDto.getEmail());

	        if (existingUser != null)
	            result.rejectValue("email", null,
	                    "User already registered !!!");

	        if (result.hasErrors()) {
	            model.addAttribute("user", userDto);
	            return "/registration";
	        }

	        userService.saveUser(userDto);
			
			
	        return "redirect:/login";
	    }
}
