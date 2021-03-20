import java.util.Observable;
import javafx.scene.image.Image;

public class ModèleAjoutRecette extends Observable {
	
	Recette recette_courante;
	public ModèleAjoutRecette() {
		this.recette_courante=new Recette();
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
	
	public void ajoutIngrédient(String nom, int quantité, String mesure) {
		this.recette_courante.ingrédients.add(new Ingrédient(nom,quantité,mesure));
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
		this.recette_courante.note = note;
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutPhoto(String s) {
		this.recette_courante.photo = new Image(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

}
