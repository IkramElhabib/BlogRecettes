package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String instructions;
    private int tempsPreparation;
    private int tempsCuisson;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;



    // Relation Many-to-Many avec User pour les favoris
    @ManyToMany(mappedBy = "favoris")
    private List<User> userFavoris;
    


	public Recette() {
		super();
	}

	public Recette(Long id, String titre, String description, String instructions, int tempsPreparation,
			int tempsCuisson, User user, List<User> userFavoris) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.instructions = instructions;
		this.tempsPreparation = tempsPreparation;
		this.tempsCuisson = tempsCuisson;
		this.user = user;
		this.userFavoris = userFavoris;
	}

	public Recette(String titre, String description, String instructions, int tempsPreparation, int tempsCuisson,
			User user, List<User> userFavoris) {
		super();
		this.titre = titre;
		this.description = description;
		this.instructions = instructions;
		this.tempsPreparation = tempsPreparation;
		this.tempsCuisson = tempsCuisson;
		this.user = user;
		this.userFavoris = userFavoris;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public int getTempsPreparation() {
		return tempsPreparation;
	}

	public void setTempsPreparation(int tempsPreparation) {
		this.tempsPreparation = tempsPreparation;
	}

	public int getTempsCuisson() {
		return tempsCuisson;
	}

	public void setTempsCuisson(int tempsCuisson) {
		this.tempsCuisson = tempsCuisson;
	}

	public User getUser() {
		return user;
	}

	public List<User> getUserFavoris() {
		return userFavoris;
	}

	public void setUserFavoris(List<User> userFavoris) {
		this.userFavoris = userFavoris;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
    
 }