import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.sun.org.apache.xalan.internal.templates.Constants;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;

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
		this.recette_courante.étapes.add(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutDescription(String s){
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

	public void ajoutDifficulté(int difficulté) {
		
		for (int i=0;i<5;i++) {
			if (i <= difficulté) {
				this.recette_courante.difficulté[i] = true;
			} else {
				this.recette_courante.difficulté[i] = false;
			}
		}
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
		//Ajouter un image io read
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void sauvegarder(String s, String desc) {
		this.recette_courante.saved=true;
		this.recette_courante.nom = s;
		this.recette_courante.description = desc;
		this.saveImage(this.recette_courante.photo);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	private void saveImage(Image image) {
		try {
            File file = new File("imagesRecette/"+this.recette_courante.nom+".png");
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
