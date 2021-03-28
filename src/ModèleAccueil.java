import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javafx.scene.control.ComboBox;

@SuppressWarnings("deprecation")
public class ModèleAccueil extends Observable {
	
	ArrayList<Recette> recettes;
	HashMap<Ingrédient, ArrayList<Recette>> classeIng;
	HashMap<String, ArrayList<Recette>> catégories;
	Vue vue;
	File fichier;
	
	public ModèleAccueil(Vue v) {
		this.recettes = new ArrayList<Recette>();
		this.vue = v;
		this.fichier = new File("data.xml");
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
	}

	
	public void ajouterRecette(Recette rct) {
		
		recettes.add(rct);
		if (this.classeIng != null) {
			for (Ingrédient i : rct.ingrédients) {
				if (this.classeIng.get(i) == null) {
					this.classeIng.put(i, new ArrayList<Recette>());
				}
				this.classeIng.get(i).add(rct);
			}
		}
		
		if (this.catégories != null) {
			for (String s : rct.catégories) {
				if (this.catégories.get(s) == null) {
					this.catégories.put(s, new ArrayList<Recette>());
				}
				this.catégories.get(s).add(rct);
			}			
		}
		
		this.saveData();
	}
	
	public void afficherParCatègories(String catègorie) {
		if (this.catégories.get(catègorie) != null) {
			this.setChanged();
			this.notifyObservers(this.catégories.get(catègorie));
		}
	}
	
	public void afficherParIngrèdients(Ingrédient ing) {
		if (this.catégories.get(ing) != null) {
			this.setChanged();
			this.notifyObservers(this.classeIng.get(ing));
		}
	}
	
	public void afficherRecettes() {
		this.setChanged();
		this.notifyObservers(this.recettes);
	}
	
	public void changerNote(Recette rct, int note) {
		this.setChanged();
		this.notifyObservers(rct);
	}
	
	private void saveData() {
		XMLEncoder encoder = null;
		ArrayList<Object> liste = new ArrayList<Object>();
		try {
			FileOutputStream fos = new FileOutputStream("data.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			liste.add(this.recettes);
			liste.add(this.catégories);
			encoder.writeObject(liste);
			encoder.flush();
			
		} catch (final java.io.IOException e) {
			throw new RuntimeException("Ecriture des données impossible !");
		} finally {
			if (encoder != null) encoder.close();
		}
		System.out.println("Data saved !");
		
	}
	
	private void loadData() {
		XMLDecoder decoder = null;
		ArrayList<Object> liste = new ArrayList<Object>();
		try {
			FileInputStream fis = new FileInputStream("data.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			liste = (ArrayList<Object>) decoder.readObject();
			this.recettes = (ArrayList<Recette>) liste.get(0);
			this.catégories = (HashMap<String, ArrayList<Recette>>) liste.get(1);
			
		} catch (Exception e) {
			throw new RuntimeException("Chargement des données impossible !");
		} finally {
			if (decoder != null) decoder.close();
		}
	}

	public void goToAjouterAccueilRecette() {
		System.out.println("gotoacc");
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL_RECETTE);
		
		
	}
}
