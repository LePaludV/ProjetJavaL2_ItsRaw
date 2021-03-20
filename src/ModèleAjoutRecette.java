import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import javafx.scene.image.Image;

public class ModèleAjoutRecette extends Observable {

	Recette recette_courante;

	public ModèleAjoutRecette(String nom) {
		this.recette_courante = new Recette(nom);
	}

	public void ajoutEtape(String s) {
		this.recette_courante.étapes.add(s);
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutDescription(String s){
		this.recette_courante.description = s;
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutIngrédient(String nom, int quantité, String mesure) {
		this.recette_courante.ingrédients.add(new Ingrédient(nom,quantité,mesure));
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutCatégorie(String s) {
		this.recette_courante.catégories.add(s);
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutTemps(int temps) {
		this.recette_courante.temps = temps;
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutDifficulté(int difficulté) {
		this.recette_courante.difficulté = difficulté;
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutNote(int note) {
		this.recette_courante.note = note;
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutPhoto(File file) throws FileNotFoundException {
		this.recette_courante.photo = new Image(new FileInputStream(file));
		this.notifyObservers(this.recette_courante);
	}
}
