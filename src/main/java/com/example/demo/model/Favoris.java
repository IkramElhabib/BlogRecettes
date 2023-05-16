package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Favoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recette_id")
    private Recette recette;
    
   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Favoris(Long id, User user, Recette recette ) {
		super();
		this.id = id;
		this.user = user;
		this.recette = recette;
	}

	
	public Favoris(Recette recette, User user) {
		super();
		this.recette = recette;
		this.user = user;
	}

	public Favoris() {
		super();
	}
    
	

	 public Favoris(Favoris favoris, Recette recette2) {
	}

	public Favoris(User user, Recette recette) {
		// TODO Auto-generated constructor stub
	}

	
}
