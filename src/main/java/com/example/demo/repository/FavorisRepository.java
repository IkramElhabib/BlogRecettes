package com.example.demo.repository;

import com.example.demo.model.Favoris;
import com.example.demo.model.Recette;
import com.example.demo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FavorisRepository extends JpaRepository<Favoris, Long> {
	// Méthode pour trouver les favoris par utilisateur en utilisant une requête JPQL
    List<Favoris> findByUser(User user);

    // Méthode pour trouver les favoris par recette en utilisant une requête JPQL
    @Query("SELECT f FROM Favoris f WHERE f.recette = ?1")
    List<Favoris> findByRecette(Recette recette);

    // Méthode pour trouver un favori par utilisateur et recette en utilisant une requête JPQL
    @Query("SELECT f FROM Favoris f WHERE f.user = ?1 AND f.recette = ?2")
    Favoris findByUserAndRecette(User user, Recette recette);

    // Méthode pour supprimer un favori par utilisateur et recette en utilisant une requête JPQL
 
  
    @Query("DELETE FROM Favoris f WHERE f.user = ?1 AND f.recette = ?2")
    void deleteByUserAndRecette(User user, Recette recette);
    
    List<Favoris> findByUserId(Long userId);
    
    boolean existsByRecetteAndUser(Recette recette, User user);
    
    void deleteById(Long id);

    

}

