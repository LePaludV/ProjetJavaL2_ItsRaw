import java.io.Serializable;

class Ingrédient  implements Serializable {
	
	String nom;
	float quantité;
	String mesure;
	
	public Ingrédient(String nom,float quantité, String mesure) {
		this.nom = nom;
		this.quantité=quantité;
		this.mesure=mesure;
	}
	
	public Ingrédient() {
		this.nom = "";
		this.quantité = 0.0f;
		this.mesure = "";
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