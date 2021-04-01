package AjoutRecette;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.sun.org.apache.xalan.internal.templates.Constants;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class ModèleAjoutRecette extends Observable {

	Recette recette_courante;
	Vue vue;

	public ModèleAjoutRecette(Vue v) {
		this.recette_courante=new Recette();
		this.vue = v;
	}


	public void ajoutNom(String s) {
		this.recette_courante.setNom(s);
	}
	public void ajoutEtape(String s) {
		this.recette_courante.getEtapes().add(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutDescription(String s){
		this.recette_courante.setDescription(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutIngrédient(String nom, int quantité, String mesure) {
		this.recette_courante.getIngrédients().add(new Ingrédient(nom, quantité, mesure));
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void validerIngrédient(String nom, int quantité, String mesure) {
		this.recette_courante.getIngrédients().add(new Ingrédient(nom, quantité,mesure));
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutCatégorie(String s) {
		this.recette_courante.getCatégories().add(s);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutDifficulté(int difficulté) {

		for (int i=0;i<5;i++) {
			if (i <= difficulté) {
				this.recette_courante.getDifficulté()[i] = true;
			} else {
				this.recette_courante.getDifficulté()[i] = false;
			}
		}
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutNote(int note) {
		for (int i=0;i<5;i++) {
			if (i <= note) {
				this.recette_courante.getNote()[i] = true;
			} else {
				this.recette_courante.getNote()[i] = false;
			}
		}
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void ajoutPhoto(Image img) {
		this.recette_courante.setPhotoByImage(img);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void sauvegarder(String s, String desc, Integer nbrPersonne) {
		this.recette_courante.setSave(true);
		this.recette_courante.setNom(s);
		this.recette_courante.setDescription(desc);
		this.recette_courante.setNbrPersonne(nbrPersonne);
		this.saveImage(this.recette_courante.getPhoto());
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void afficherInterface() {
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}

	public void saveImage(Image image) {
		try {
            File file = new File("imagesRecette/"+this.recette_courante.getNom()+".png");
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
