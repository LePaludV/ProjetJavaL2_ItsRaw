import javafx.scene.image.Image;

public class Recette {
		
	String nom, description,photo;
	Ingrédient[] ingrédients;
	float temps,difficulté,note;
	String[] étapes, catégories;

	public Recette(String nom, Ingrédient[] ingrédients, float temps, float difficulté, float note, String photo, String description, String[] étapes) {
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
