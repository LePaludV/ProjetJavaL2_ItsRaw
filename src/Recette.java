import java.util.ArrayList;

import javafx.scene.image.Image;

public class Recette {
		
	String nom, description;
	Image photo;
	ArrayList<Ingrédient> ingrédients;
	float temps;
	boolean[] note = {false,false,false,false,false};
	boolean saved;
	boolean[] difficulté = {false,false,false,false,false};
	ArrayList<String> étapes, catégories;

	public Recette() {
		this.ingrédients = new ArrayList<Ingrédient>();
		this.étapes = new ArrayList<String>();
		this.catégories = new ArrayList<String>();
		this.saved=false;
	}
}
