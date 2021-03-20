import java.util.ArrayList;

import javafx.scene.image.Image;

public class Recette {
		
	String nom, description;
	Image photo;
	ArrayList<Ingrédient> ingrédients;
	float temps,difficulté,note;
	ArrayList<String> étapes, catégories;
	

	public Recette(String nom) {
		this.nom = nom;
		this.ingrédients = new ArrayList<Ingrédient>();
		this.étapes = new ArrayList<String>();
		this.catégories = new ArrayList<String>();
	}
	

}
