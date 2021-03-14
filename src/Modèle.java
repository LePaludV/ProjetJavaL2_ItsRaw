import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Modèle extends Observable {
	
	ArrayList<Recette> recettes;
	HashMap<Ingrédient, ArrayList<Recette>> classeIng;
	HashMap<String, ArrayList<Recette>> catégories;
	
	public Modèle() {
		this.recettes = new ArrayList<Recette>();
	}
	
	public void ajouterRecette(Recette rct) {
		recettes.add(rct);
		for (Ingrédient i : rct.ingrédients) {
			ArrayList<Recette> liste = this.classeIng.get(i);
			liste.add(rct);
		}
		for (String s : rct.catégories) {
			ArrayList<Recette> liste = this.catégories.get(s);
			liste.add(rct);
		}
	}
	
	public void afficherParCatégories(String catégorie) {
		if (this.catégories.get(catégorie) != null) {
			this.notifyObservers(this.catégories.get(catégorie));
		}
	}
	
	public void afficherParIngrédients(Ingrédient ing) {
		if (this.catégories.get(ing) != null) {
			this.notifyObservers(this.classeIng.get(ing));
		}
	}
	
	public void afficherRecettes() {
		this.notifyObservers(this.recettes);
	}
}
