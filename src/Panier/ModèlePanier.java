package Panier;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import Main.*;

public class ModèlePanier extends Observable{
	Vue vue;
	ArrayList<Ingrédient> Ingrédients;
	File panier;
	String[][] liquide =  {{"","l","0,01"},{"100","cl","0,5"},{"1.5","cuil. soupe","0,33"},{"3","cuil. café",""}};
	String[][] poids = {{"","kg","0,001"},{"1000","g","0,2"},{"5","pincé",""}};


	
	
	public ModèlePanier(Vue vue) {
		this.vue = vue;
		this.panier=new File("panier.xml");
		
	}
	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		
	}
	public void addIngredient(ArrayList<Ingrédient> ingr) {
		this.Ingrédients=ChargementPanier();
		
		for(Ingrédient i: ingr) {
			Ingrédient maj=i;
			for(int j =0;j<this.Ingrédients.size();j++) {
				
				System.out.println((i.nom+"   "+this.Ingrédients.get(j).nom));
				if(i.nom.equals(this.Ingrédients.get(j).nom)) {
					System.out.println("Existe déjà"+i.nom);
					
					if(estLiquide(i.mesure) & estLiquide(this.Ingrédients.get(j).mesure)) {
						maj=majIngrédientLiquide(this.Ingrédients.get(j),i);
						System.out.println("Ingrédients Liquide déjà existant  "+maj.quantité);
						this.Ingrédients.remove(j);
						

					}
					else if (estSolide(i.mesure) & estSolide(this.Ingrédients.get(j).mesure)) {
						maj=majIngrédientPoids(this.Ingrédients.get(j),i);
						System.out.println("Ingrédients Solide déjà existant  "+maj.quantité);
						this.Ingrédients.remove(j);
					
						
					}else {
						maj=i;
					}
					
					
				
				
					
				}
					
				
			}
			this.Ingrédients.add(maj);
			
			
		}
		
		SauvegardePanier();
		
	}
	private boolean estSolide(String m) {
		String[] nomPoids= {"kg","g","pincé"};
		for(String i : nomPoids) {
			if(i.contains(m)) {
				return true;
			}
		}
		return false;
	}
	private boolean estLiquide(String m) {
		
		String[] nomLiquide= {"l","cl","cuil. soupe","cuil. café"};
		for(String i : nomLiquide) {
			System.out.println("test de 2 "+i);
			if(i.contains(m)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	private Ingrédient majIngrédientLiquide(Ingrédient j, Ingrédient i) {
		//j = Ingrédient déjà dans la liste qu'il faut modifier 
		//i = Ingrédient qu'il faut comparer avec j pour additioner les quatité en fonction des mesures.
		System.out.println("MODIF QUANTITE LIQUIDE ");
		
		
		//On va charcher qui à la plus grande mesure , pour unifier les quantiré avec la plus grande mesures
		//Si j est en Litres
		if(j.mesure.equals("l")) {
			if(i.mesure.equals("cl")) {
				j.quantité+=i.quantité*0.01;
			}
			else if(i.mesure.equals("cuil. soupe")) {
				j.quantité+=i.quantité*(0.01*0.5);
			}
			else if(i.mesure.equals("cuil. café")) {
				j.quantité+=i.quantité*(0.01*0.5*0.33);
			}
			else if(i.mesure.equals("l")) {
				j.quantité+=i.quantité;
			}
			return j;
		}
		//Sinon si i est en litres 
		else if(i.mesure.equals("l")) {
				if(j.mesure.equals("cl")) {
					i.quantité+=j.quantité*0.01;
				}
				else if(j.mesure.equals("cuil. soupe")) {
					i.quantité+=j.quantité*(0.01*0.5);
				}
				else if(j.mesure.equals("cuil. café")) {
					i.quantité+=j.quantité*(0.01*0.5*0.33);
				}
				else if(j.mesure.equals("l")) {
					i.quantité+=j.quantité;
				}
				return i;
			}
		//Sinon si j est en cl
		else if(j.mesure.equals("cl")) {
			if(i.mesure.equals("cuil. soupe")) {
				j.quantité+=i.quantité*(0.5);
			}
			else if(i.mesure.equals("cuil. café")) {
				j.quantité+=i.quantité*(0.5*0.33);
			}
			else if(i.mesure.equals("cl")) {
				j.quantité+=i.quantité;
			}
			return j;
		}
		//Sinon si i est en cl
		else if(i.mesure.equals("cl")) {
			if(j.mesure.equals("cuil. soupe")) {
				i.quantité+=j.quantité*(0.5);
			}
			else if(j.mesure.equals("cuil. café")) {
				i.quantité+=j.quantité*(0.5*0.33);
			}
			else if(j.mesure.equals("cl")) {
				i.quantité+=j.quantité;
			}
			return i;
		}
		
		//Sinon si j est en cuil a soupe
		else if(j.mesure.equals("cuil. soupe")) {
			if(i.mesure.equals("cuil. café")) {
				j.quantité+=i.quantité*(0.33);
			}
			else if(i.mesure.equals("cuil. soupe")) {
				j.quantité+=i.quantité;
			}
			return j;
		}
		//Sinon si i est en cuil a soupe
		else if(i.mesure.equals("cuil. soupe")) {
			if(j.mesure.equals("cuil. café")) {
				i.quantité+=j.quantité*(0.33);
			}
			else if(j.mesure.equals("cuil. soupe")) {
				i.quantité+=j.quantité;
			}
			return i;
		}
		else {
			if(j.mesure.equals(i.mesure)) {
				j.quantité+=i.quantité;
				return j;
			}
		}
		//Reste des possibilité : même mesures donc 1er cas 		
		return j;
	}
	private Ingrédient majIngrédientPoids(Ingrédient j, Ingrédient i) {
		//j = Ingrédient déjà dans la liste qu'il faut modifier 
		//i = Ingrédient qu'il faut comparer avec j pour additioner les quatité en fonction des mesures.
		
		if(j.mesure.equals("kg")) {
			if(i.mesure.equals("g")) {
				j.quantité+=i.quantité*0.001;
			}
			else if(i.mesure.equals("pincé")) {
				j.quantité+=i.quantité*(0.001*0.2);
			}
			else if(i.mesure.equals("kg")) {
				j.quantité+=i.quantité;
			}
			return j;
		}
		else if(i.mesure.equals("kg")) {
			if(j.mesure.equals("g")) {
				i.quantité+=j.quantité*0.001;
			}
			else if(j.mesure.equals("pincé")) {
				i.quantité+=j.quantité*(0.001*0.2);
			}
			if(j.mesure.equals("kg")) {
				i.quantité+=j.quantité;
			}
			return i;
		}
		else if(j.mesure.equals("g")) {
			if(i.mesure.equals("pincé")) {
				j.quantité+=i.quantité*(0.2);
			}
			else if(i.mesure.equals("g")) {
				j.quantité+=i.quantité;
			}
			return j;
		}
		else if(i.mesure.equals("g")) {
			if(j.mesure.equals("pincé")) {
				i.quantité+=j.quantité*(0.2);
			}
			else if(j.mesure.equals("g")) {
				i.quantité+=j.quantité;
			}
			return i;
		}
		else{
			if(j.mesure.equals(i.mesure)) {
		}
			j.quantité+=i.quantité;
			return j;
		}
	}
	private void SauvegardePanier() {
		
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream(this.panier);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			encoder.writeObject(this.Ingrédients);
			encoder.flush();
			
		}catch(final java.io.IOException e) {
			throw new RuntimeException("Ecriture des données impossible");
		}finally {
			if (encoder !=null) encoder.close();
		}
		System.out.println("Panier sauvegarder ! ");
	}
	
	public ArrayList<Ingrédient> ChargementPanier() {
		XMLDecoder decoder = null;
		try {
			FileInputStream fis = new FileInputStream(this.panier);
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			this.Ingrédients=(ArrayList<Ingrédient>) decoder.readObject();
;		}catch (Exception e) {
	throw new RuntimeException("Chargement des données impossible");
} finally {
	if (decoder !=null) decoder.close();
}
		return this.Ingrédients;
	}
	
	public void AfficherPanier(){
		this.setChanged();
		this.notifyObservers();
	}
	public void supprimerIngr(Ingrédient ingrédient) {
		this.Ingrédients.remove(ingrédient);
		this.SauvegardePanier();
		this.setChanged();
		this.notifyObservers();
		
	}
	public void viderPanier() {
		this.Ingrédients.clear();
		this.SauvegardePanier();
		this.setChanged();
		this.notifyObservers();
		
	}

}
