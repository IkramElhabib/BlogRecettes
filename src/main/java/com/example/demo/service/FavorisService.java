package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.FavorisRepository;
import com.example.demo.model.User;
import com.example.demo.model.Favoris;
import com.example.demo.model.Recette;

@Service
public interface FavorisService {
	
	 void ajouterFavori(Long recetteId, Long utilisateurId);
	 
	    void supprimerFavori(Long recetteId, User user);
	    List<Recette> getFavorisUser(Long utilisateurId);

}
