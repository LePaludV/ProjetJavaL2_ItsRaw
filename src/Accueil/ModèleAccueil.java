package Accueil;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javafx.scene.control.ComboBox;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.Recette;
import Main.Vue;
import Main.Ingrédient;

public class ModèleAccueil extends Observable {

	public HashMap<String, Recette> recettes;
	public HashMap<String, ArrayList<String>> classeIng;
	public HashMap<String, ArrayList<String>> catégories;
	public Vue vue;
	public File fichier;


	public ModèleAccueil(Vue v) {
		this.fichier=new File("data.xml");
		this.recettes = new HashMap<String, Recette>();
		this.catégories = new HashMap<String, ArrayList<String>>();
		this.classeIng = new HashMap<String, ArrayList<String>>();
		this.vue = v;
		this.loadData("data");
		this.loadData("dataCatégories");
		this.loadData("dataIngrédients");
	}

	public void goToAjouterRecette()
	{
		this.vue.currentInterface = this.vue.currentInterface.AJOUT_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.AJOUT_RECETTE);
	}

	public void changeWindow(boolean save, Recette rct) {
		if (save) {
			this.ajouterRecette(rct);
		}
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		this.afficherRecettes();
		this.afficherCategories();
	}

	public void changerNoteDifficulte(Recette rct) {
		Recette recetteCourante = this.recettes.get(rct.getNom());
		recetteCourante.setDifficulté(rct.getDifficulté());
		recetteCourante.setNote(rct.getNote());
		this.saveData("data");
	}

	public void ajouterRecette(Recette rct) {

		this.recettes.put(rct.getNom(), rct);
		for (Ingrédient i : rct.getIngrédients()) {
			if (this.classeIng.get(i.nom) == null) {
				this.classeIng.put(i.nom, new ArrayList<String>());
			}
			this.classeIng.get(i.nom).add(rct.getNom());
		}

		for (String s : rct.getCatégories()) {
			if (this.catégories.get(s) == null) {
				this.catégories.put(s, new ArrayList<String>());
			}
			this.catégories.get(s).add(rct.getNom());
		}

		this.saveData("data");
		this.saveData("dataCatégories");
		this.saveData("dataIngrédients");
	}

	public void afficherCategories() {
		this.setChanged();
		this.notifyObservers(this.catégories);
	}

	public void afficherParCatégories(String catégorie) {
		if (this.catégories.get(catégorie) != null) {
			ArrayList<Recette> liste = new ArrayList<Recette>();
			for (String nomRecette : this.catégories.get(catégorie)) {
				liste.add(this.recettes.get(nomRecette));
			}
			this.setChanged();
			this.notifyObservers(liste);
		}
	}

	public void afficherParIngrèdients(String ing) {
		if (this.classeIng.get(ing) != null) {
			ArrayList<Recette> liste = new ArrayList<Recette>();
			for (String nomRecette : this.classeIng.get(ing)) {
				liste.add(this.recettes.get(nomRecette));
			}
			this.setChanged();
			this.notifyObservers(liste);
		}
	}

	public void afficherRecettes() {
		this.setChanged();
		ArrayList<Recette> liste = new ArrayList<Recette>();
		for (String nom : this.recettes.keySet()) {
			liste.add(this.recettes.get(nom));
		}
		this.notifyObservers(liste);
	}

	public void changerNote(Recette rct, int note) {
		this.setChanged();
		this.notifyObservers(rct);
	}

	private void saveData(String url) {
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream(url+".xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			if (url.equals("data")) {
				encoder.writeObject(this.recettes);				
			} else if (url.equals("dataCatégories")) {
				encoder.writeObject(this.catégories);
			} else if (url.equals("dataIngrédients")) {
				encoder.writeObject(this.classeIng);
			}
			encoder.flush();

		} catch (final java.io.IOException e) {
			throw new RuntimeException("Ecriture des données impossible !");
		} finally {
			if (encoder != null) encoder.close();
		}
		System.out.println("Data saved !");
	}

	private void loadData(String url) {
		System.out.println("liste des recettes "+this.recettes);
		XMLDecoder decoder = null;
		try {
			FileInputStream fis = new FileInputStream(url+".xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			if (url.equals("data")) {
				this.recettes = (HashMap<String, Recette>) decoder.readObject();				
			} else if (url.equals("dataCatégories")) {
				this.catégories = (HashMap<String, ArrayList<String>>) decoder.readObject();
			} else if (url.equals("dataIngrédients")) {
				this.classeIng = (HashMap<String, ArrayList<String>>) decoder.readObject();				
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (decoder != null) decoder.close();
		}
		
		for (String r : this.recettes.keySet()) {
			this.recettes.get(r).setPhoto();
		}
		System.out.println("Data loaded !");
	}

	public void goToAjouterAccueilRecette() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL_RECETTE);
	}

	public void goToPanier() {
		this.vue.currentInterface = this.vue.currentInterface.PANIER;
		this.vue.changeWindow(Vue.typeInterface.PANIER);
		
	}

	public void goToFav() {
		this.vue.currentInterface = this.vue.currentInterface.FAVORIS;
		this.vue.changeWindow(Vue.typeInterface.FAVORIS);
	}
	
	public HashMap<String, Recette> getListeRecettes() {
		return this.recettes;
	}
}
