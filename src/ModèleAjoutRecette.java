import java.util.Observable;
import javafx.scene.image.Image;

public class ModèleAjoutRecette extends Observable {
	
	Recette recette_courante;
	Vue vue;
	
	public ModèleAjoutRecette(Vue v) {
		this.recette_courante=new Recette();
		this.vue = v;
	}

	
	public void ajoutNom(String s) {
		this.recette_courante.nom = s;
	}
	public void ajoutEtape(String s) {
		System.out.println("oui");
		this.recette_courante.étapes.add(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutDescription(String s){
		System.out.println("desc");
		this.recette_courante.description = s;
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutIngrédient() {
		this.vue.ingredientWindow();
	}
	
	public void validerIngrédient(String nom, int quantité, String mesure) {
		this.recette_courante.ingrédients.add(new Ingrédient(nom, quantité,mesure));
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutCatégorie(String s) {
		this.recette_courante.catégories.add(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutTemps(int temps) {
		this.recette_courante.temps = temps;
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutDifficulté(int difficulté) {
		this.recette_courante.difficulté = difficulté;
		this.setChanged();
		this.notifyObservers(this.recette_courante);		
	}
	
	public void ajoutNote(int note) {
		for (int i=0;i<5;i++) {
			if (i <= note) {
				this.recette_courante.note[i] = true;
			} else {
				this.recette_courante.note[i] = false;
			}
		}
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutPhoto(Image img) {
		this.recette_courante.photo = img;
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void sauvegarder() {
		this.recette_courante.saved=true;
		this.vue.currentInterface = Vue.typeInterface.ACCUEIL;
		this.vue.changeWindow();
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

}
