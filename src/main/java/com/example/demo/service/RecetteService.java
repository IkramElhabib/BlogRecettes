package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Recette;
import com.example.demo.repository.RecetteRepository;

@Service
public class RecetteService {
    @Autowired
    private RecetteRepository recetteRepository;

    public List<Recette> getAllRecettes() {
        return recetteRepository.findAll();
    }

    public Recette getRecetteById(Long id) {
        return recetteRepository.findById(id).orElse(null);
    }

    public Recette saveRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    public void deleteRecette(Long id) {
        recetteRepository.deleteById(id);
    }
}
