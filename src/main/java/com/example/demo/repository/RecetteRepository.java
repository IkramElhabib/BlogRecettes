package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.model.Recette;
import com.example.demo.model.User;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Long>{
	// Méthode pour rechercher les recettes par utilisateur
    List<Recette> findByUser(User user);

    // Méthode pour rechercher les recettes par titre
    List<Recette> findByTitreContainingIgnoreCase(String titre);
    
    // Méthode pour créer une nouvelle recette
    Recette save(Recette recette);

    // Méthode pour mettre à jour une recette existante
    Recette saveAndFlush(Recette recette);

    // Méthode pour supprimer une recette
    void delete(Recette recette);
    
    List<Recette> findAll();
    
    void deleteById(Long id);
    
    Optional<Recette> findById(Long id);
    
    @Transactional
    @Modifying
    @Query("UPDATE Recette r SET r.titre = :titre, r.description = :description, r.instructions = :instructions, r.tempsPreparation = :tempsPreparation, r.tempsCuisson = :tempsCuisson WHERE r.id = :id")
    void updateRecette(@Param("id") Long id, @Param("titre") String titre, @Param("description") String description, @Param("instructions") String instructions, @Param("tempsPreparation") Integer tempsPreparation, @Param("tempsCuisson") Integer tempsCuisson);


}
