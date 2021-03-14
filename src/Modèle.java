import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Mod�le extends Observable {
	
	ArrayList<Recette> recettes;
	HashMap<Ingr�dient, ArrayList<Recette>> classeIng;
	HashMap<String, ArrayList<Recette>> cat�gories;
	
	public Mod�le() {
		this.recettes = new ArrayList<Recette>();
	}
	
	public void ajouterRecette(Recette rct) {
		recettes.add(rct);
		for (Ingr�dient i : rct.ingr�dients) {
			ArrayList<Recette> liste = this.classeIng.get(i);
			liste.add(rct);
		}
		for (String s : rct.cat�gories) {
			ArrayList<Recette> liste = this.cat�gories.get(s);
			liste.add(rct);
		}
	}
	
	public void afficherParCat�gories(String cat�gorie) {
		if (this.cat�gories.get(cat�gorie) != null) {
			this.notifyObservers(this.cat�gories.get(cat�gorie));
		}
	}
	
	public void afficherParIngr�dients(Ingr�dient ing) {
		if (this.cat�gories.get(ing) != null) {
			this.notifyObservers(this.classeIng.get(ing));
		}
	}
	
	public void afficherRecettes() {
		this.notifyObservers(this.recettes);
	}
}
