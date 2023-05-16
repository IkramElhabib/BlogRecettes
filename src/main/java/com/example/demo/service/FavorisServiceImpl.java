package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


import com.example.demo.model.Recette;
import com.example.demo.model.Favoris;
import com.example.demo.model.User;


import com.example.demo.repository.FavorisRepository;
import com.example.demo.repository.RecetteRepository;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FavorisServiceImpl implements FavorisService{
	
	@Autowired
	private FavorisRepository favorisRepository;
	@Autowired
	private RecetteRepository recetteRepository;
    @Autowired
    private UserRepository userRepository;

    public FavorisServiceImpl(FavorisRepository favorisRepository, RecetteRepository recetteRepository) {
        this.favorisRepository = favorisRepository;
        this.recetteRepository = recetteRepository;
    }



		@Override
		@Transactional
	    public void ajouterFavori(Long recetteId, Long utilisateurId) {
	        Recette recette = recetteRepository.findById(recetteId)
	                .orElseThrow(() -> new EntityNotFoundException("Recette not found"));

	        User user = userRepository.findById(utilisateurId)
	                .orElseThrow(() -> new EntityNotFoundException("User not found"));


	        Favoris favori = new Favoris();
	        favori.setRecette(recette);
	        favori.setUser(user);

	        favorisRepository.save(favori);
	    }

		 @Override
		    @Transactional
		    public void supprimerFavori(Long recetteId, User user) {
		        Recette recette = recetteRepository.findById(recetteId).orElse(null);
		        if (recette != null) {
		            favorisRepository.deleteByUserAndRecette(user, recette);
		        }
		    }

		 @Override
		    public List<Recette> getFavorisUser(Long userId) {
		        List<Favoris> favoris = favorisRepository.findByUserId(userId);
		        List<Recette> recettes = new ArrayList<>();
		        for (Favoris favori : favoris) {
		            recettes.add(favori.getRecette());
		        }
		        return recettes;
		    }

}
