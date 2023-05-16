package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repository.RecetteRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Favoris;
import com.example.demo.model.Recette;
import com.example.demo.model.User;

@Controller
public class RecetteController {

	 @Autowired
	    private RecetteRepository recetteRepository;
	 
	 @Autowired
	 	private UserRepository userRepository;
	 
	 @Autowired
	    private UserDetailsService userDetailsService;


	    // Affiche le formulaire d'ajout de recette
	    @GetMapping("/ajouteUneRecette")
	    public String afficherFormulaireAjoutRecette(Model model) {
	        model.addAttribute("recette", new Recette());
	        return "ajouterRecette";
	    }

	    @PostMapping("/ajouteUneRecette")
	    public String ajouterRecette(@ModelAttribute Recette recette, Authentication authentication , RedirectAttributes redirectAttributes) {
	    	String userEmail = authentication.getName();
	        User user = userRepository.findByEmail(userEmail);
	        recette.setUser(user);
	        recetteRepository.save(recette);
	        redirectAttributes.addFlashAttribute("successMessage", "La recette a été ajoutée avec succès !");
	        return "redirect:/mesRecettes";
	    }
	    
	    @GetMapping("/mesRecettes")
	    public String mesRecettes(Model model, Authentication authentication) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        User user = userRepository.findByEmail(userDetails.getUsername()); // assuming UserRepository has a method to find user by email

	        List<Recette> recettes = recetteRepository.findByUser(user);
	        model.addAttribute("recettes", recettes);

	        return "mesRecettes";
	    }
	   



	    
	    @GetMapping("/listRecettes")
	    public String afficherListeRecettes(Model model) {
	        List<Recette> recettes = recetteRepository.findAll();
	        model.addAttribute("recettes", recettes);
	        return "listRec";
	    }
	    
	    @GetMapping("/listRecettesG")
	    public String afficherListeRecettesGarde(Model model) {
	        List<Recette> recettes = recetteRepository.findAll();
	        model.addAttribute("recettes", recettes);
	        return "garde";
	    }
	    
	    @GetMapping("/supprimerRecette/{id}")
	    public String supprimerRecette(@PathVariable("id") Long id) {
	        recetteRepository.deleteById(id);
	        return "redirect:/mesRecettes";
	    }
	    
	    @GetMapping("/recettess/{id}")
	    public String showRecette(@PathVariable Long id, Model model) {
	        Optional<Recette> recette = recetteRepository.findById(id);
	        if (recette.isPresent()) {
	            model.addAttribute("recette", recette.get());
	            return "showRecette";
	        } else {
	            return "redirect:/mesRecettes";
	        }
	    }
	    
	    @PostMapping("/recettess/{id}")
	    public String updateRecette(@PathVariable Long id, @RequestParam String titre, @RequestParam String description, @RequestParam String instructions, @RequestParam Integer tempsPreparation, @RequestParam Integer tempsCuisson, Model model, RedirectAttributes redirectAttributes) {
	        recetteRepository.updateRecette(id, titre, description, instructions, tempsPreparation, tempsCuisson);
	        redirectAttributes.addFlashAttribute("successMessage", "La recette a été modifiée avec succès !");
	        return "redirect:/mesRecettes";
	    }

}

