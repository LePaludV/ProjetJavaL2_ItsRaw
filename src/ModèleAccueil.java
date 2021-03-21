import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ModèleAccueil extends Observable {
	
	ArrayList<Recette> recettes;
	HashMap<Ingrédient, ArrayList<Recette>> classeIng;
	HashMap<String, ArrayList<Recette>> catégories;
	Vue vue;
	
	public ModèleAccueil(Vue v) {
		this.recettes = new ArrayList<Recette>();
		this.vue = v;
	}
	
	public void goToAjouterRecette()
	{
		this.vue.currentInterface = this.vue.currentInterface.AJOUT_RECETTE;
		this.vue.changeWindow();
	}
	
	public void ajouterRecette(Recette rct) {
		recettes.add(rct);
		for (Ingrédient i : rct.ingrédients) {
			if (this.classeIng.get(i) == null) {
				this.classeIng.put(i, new ArrayList<Recette>());
			}
			this.classeIng.get(i).add(rct);
		}
		for (String s : rct.catégories) {
			if (this.catégories.get(s) == null) {
				this.catégories.put(s, new ArrayList<Recette>());
			}
			this.catégories.get(s).add(rct);
		}
	}
	
	public void afficherParCatègories(String catègorie) {
		if (this.catégories.get(catègorie) != null) {
			this.notifyObservers(this.catégories.get(catègorie));
		}
	}
	
	public void afficherParIngrèdients(Ingrédient ing) {
		if (this.catégories.get(ing) != null) {
			this.notifyObservers(this.classeIng.get(ing));
		}
	}
	
	public void afficherRecettes() {
		this.setChanged();
		this.notifyObservers(this.recettes);
	}
	
	public void changerNote(Recette rct, int note) {
		this.notifyObservers(rct);
	}
}
