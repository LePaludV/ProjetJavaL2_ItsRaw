import java.util.Observable;

public class ModèleAccueilRecette extends Observable{
	Recette recette_courante;
	Vue vue;
	
	
	public ModèleAccueilRecette(Vue v) {
		this.vue = v;
		this.recette_courante=new Recette();
	}
	
	public void setRecette(Recette rct) {
		this.recette_courante = rct;
		System.out.println("Recette choisi : "+rct.nom);
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void ajoutDifficulté(int difficulté) {
	//Pb pas de recette_courante
		
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
	

	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		// TODO Auto-generated method stub
		
	}
}
