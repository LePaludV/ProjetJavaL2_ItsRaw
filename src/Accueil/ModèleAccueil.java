package Accueil;
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
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class ModèleAccueil extends Observable {

	public ArrayList<Recette> recettes;
	public HashMap<String, ArrayList<Recette>> classeIng;
	public HashMap<String, ArrayList<Recette>> catégories;
	public Vue vue;
	public File fichier;

	public ModèleAccueil(Vue v) {
		this.recettes = new ArrayList<Recette>();
		this.catégories = new HashMap<String, ArrayList<Recette>>();
		this.classeIng = new HashMap<String, ArrayList<Recette>>();
		this.vue = v;
		this.jeuxDeTests();
		this.loadData();
	}

	private void jeuxDeTests() {
		Recette rct1 = new Recette();
		rct1.nom = "Tarte aux citrons";
		Recette rct2 = new Recette();
		rct2.nom = "Flans";
		Recette rct3 = new Recette();
		rct3.nom = "Salade de tomates";
		Recette rct4 = new Recette();
		rct4.nom = "Pot au feu";
		Recette rct5 = new Recette();
		rct5.nom = "Pizza";
		Recette rct6 = new Recette();
		rct6.nom = "Hamburger";
		Recette rct7 = new Recette();
		rct7.nom = "Pâtes au fromage";
		Recette rct8 = new Recette();
		rct8.nom = "Crèpes";
		Recette rct9 = new Recette();
		rct9.nom = "Tiramisu";
		Recette rct10 = new Recette();
		rct10.nom = "Tartiflette";
		Recette rct11 = new Recette();
		rct11.nom = "Lasagne à la bolognaise";

		ArrayList<Recette> fromage = new ArrayList<Recette>();
		fromage.add(rct5);
		fromage.add(rct6);
		fromage.add(rct7);
		fromage.add(rct10);
		this.catégories.put("Fromage", fromage);

		ArrayList<Recette> tomate = new ArrayList<Recette>();
		tomate.add(rct3);
		tomate.add(rct5);
		tomate.add(rct11);
		this.catégories.put("Tomate", tomate);

		ArrayList<Recette> dessert = new ArrayList<Recette>();
		dessert.add(rct2);
		dessert.add(rct1);
		dessert.add(rct8);
		dessert.add(rct9);
		this.catégories.put("Dessert", dessert);

		ArrayList<Recette> plat = new ArrayList<Recette>();
		plat.add(rct4);
		plat.add(rct5);
		plat.add(rct6);
		plat.add(rct7);
		plat.add(rct10);
		plat.add(rct11);
		this.catégories.put("Plat", plat);

		ArrayList<Recette> farine = new ArrayList<Recette>();
		farine.add(rct2);
		farine.add(rct5);
		farine.add(rct6);
		farine.add(rct8);
		farine.add(rct11);
		this.classeIng.put("Farine", farine);

		ArrayList<Recette> tomateIng = new ArrayList<Recette>();
		tomateIng.add(rct3);
		tomateIng.add(rct5);
		tomateIng.add(rct6);
		tomateIng.add(rct11);
		this.classeIng.put("Tomate", tomateIng);

		ArrayList<Recette> viandeIng = new ArrayList<Recette>();
		viandeIng.add(rct4);
		viandeIng.add(rct5);
		viandeIng.add(rct6);
		viandeIng.add(rct10);
		viandeIng.add(rct11);
		this.classeIng.put("Viande", viandeIng);

		ArrayList<Recette> fromageIng = new ArrayList<Recette>();
		fromageIng.add(rct3);
		fromageIng.add(rct5);
		fromageIng.add(rct6);
		fromageIng.add(rct7);
		fromageIng.add(rct10);
		this.classeIng.put("Fromage", fromageIng);
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
		
		for(int i =0;i<this.recettes.size();i++) {
			System.out.println(this.recettes.get(i).nom+ " | "+rct.nom);
			if(this.recettes.get(i).nom.contentEquals(rct.nom)) {
				System.out.println("Supprimer"+i);
				this.recettes.remove(i);
			}
		}
		
		recettes.add(rct);
		if (this.classeIng != null) {
			for (Ingrédient i : rct.ingrédients) {
				if (this.classeIng.get(i.nom) == null) {
					this.classeIng.put(i.nom, new ArrayList<Recette>());
				}
				this.classeIng.get(i.nom).add(rct);
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
		System.out.println(this.recettes);
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
			encoder.writeObject(this.recettes);
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
		try {
			FileInputStream fis = new FileInputStream("data.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);

			this.recettes = (ArrayList<Recette>) decoder.readObject();

		} catch (Exception e) {
			
			e.printStackTrace();
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
