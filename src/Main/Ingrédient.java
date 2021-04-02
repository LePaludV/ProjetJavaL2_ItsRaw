package Main;
import java.io.Serializable;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class Ingrédient  implements Serializable {

	private static final long serialVersionUID = 1L;
	public String nom;
	public float quantité;
	public String mesure;
	
	/*ublic Ingrédient(String nom,float quantité, String mesure) {
		this.nom = nom;
		this.quantité=quantité;
		this.mesure=mesure;
	}*/
	
	public Ingrédient() {
		
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String s) {
		this.nom = s;
	}
	
	public float getQuantité() {
		return this.quantité;
	}
	
	public void setQuantité(float f) {
		this.quantité = f;
	}
	
	public String getMesure() {
		return this.mesure;
	}
	
	public void setMesure(String s) {
		this.mesure = s;
	}
}