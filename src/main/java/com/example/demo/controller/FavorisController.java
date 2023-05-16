package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.demo.model.Favoris;
import com.example.demo.model.Recette;
import com.example.demo.model.User;
import com.example.demo.repository.FavorisRepository;
import com.example.demo.repository.RecetteRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FavorisService;
import com.example.demo.service.RecetteService;
import com.example.demo.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("/favoris")
public class FavorisController {
    private final FavorisService favorisService;
    private final RecetteService recetteService;
    private final UserRepository userRepository;
    private final FavorisRepository favorisRepository;
    private final RecetteRepository recetteRepository;



    public FavorisController(FavorisService favorisService , RecetteService recetteService, UserRepository userRepository,
    		FavorisRepository favorisRepository, RecetteRepository recetteRepository) {
        this.favorisService = favorisService;
        this.recetteService = recetteService;
        this.userRepository = userRepository;
        this.favorisRepository = favorisRepository;
        this.recetteRepository = recetteRepository;

    }

    @PostMapping("/favoris")
    public ModelAndView addFavoris(@ModelAttribute("id") Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
        Recette recette = recetteRepository.findById(id).orElse(null);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        // Check if the favoris already exists
        if (favorisRepository.existsByRecetteAndUser(recette, user)) {
            redirectAttributes.addFlashAttribute("errorMessage", "La recette est déjà dans vos favoris.");
        } else {
            Favoris favoris = new Favoris();
            favoris.setRecette(recette);
            favoris.setUser(user);
            favorisRepository.save(favoris);
            redirectAttributes.addFlashAttribute("successMessage", "La recette a été ajoutée à vos favoris.");
        }
        return new ModelAndView("redirect:/favoris/listRecFav");
    }




    @GetMapping("/listRecFav")
    public String listFavoris(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()); // assuming UserRepository has a method to find user by email
        List<Favoris> favorisList = favorisRepository.findByUser(user);
        model.addAttribute("favorisList", favorisList);
        return "listRecFav";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteFavoris(@PathVariable("id") Long id , RedirectAttributes redirectAttributes) {
        favorisRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Le favori a été supprimé avec succès !");

        return "redirect:/favoris/listRecFav"; // redirect to the list of favoris page
    }

    
  
}