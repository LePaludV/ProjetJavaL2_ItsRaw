import java.io.Serializable;

public class Ingrédient  implements Serializable {

	private static final long serialVersionUID = 1L;
	String nom;
	float quantité;
	String mesure;
	
	public Ingrédient(String nom,float quantité, String mesure) {
		this.nom = nom;
		this.quantité=quantité;
		this.mesure=mesure;
	}
	
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