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
		this.setChanged();
		this.notifyObservers(this.recettes);
		this.fichier = new File("data.xml");
	}
	
	public void goToAjouterRecette()
	{
		this.vue.currentInterface = this.vue.currentInterface.AJOUT_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.AJOUT_RECETTE);
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
		
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		System.out.println("liste de recettes : "+this.recettes.toString());
		this.saveData();
		this.afficherRecettes();
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
		
		try {
			FileOutputStream fos = new FileOutputStream("data.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			System.out.println(this.recettes.get(0).photo.impl_getUrl());
			encoder.writeObject(this.recettes.get(0).photo.impl_getUrl());
			//encoder.writeObject(this.recettes);
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
		System.out.println("liste de recettes : "+this.recettes.toString());
		try {
			FileInputStream fis = new FileInputStream("data.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			this.recettes = (ArrayList<Recette>) decoder.readObject();
			
		} catch (Exception e) {
			throw new RuntimeException("Chargement des données impossible !");
		} finally {
			if (decoder != null) decoder.close();
		}
	}
}
