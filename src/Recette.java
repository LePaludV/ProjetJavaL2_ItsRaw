import javafx.scene.image.Image;

public class Recette {
		
	String nom, description;
	Ingrédient[] ingrédients;
	int temps,difficulté,note;
	Image photo;
	String[] étapes, catégories;
	
	public Recette(String nom, Ingrédient[] ingrédients, int temps, int difficulté, int note, Image photo, String description, String[] étapes) {
		this.nom = nom;
		this.ingrédients = ingrédients;
		this.temps = temps;
		this.difficulté = difficulté;
		this.note = note;
		this.photo = photo;
		this.description = description;
		this.étapes = étapes;
	}
	

}
