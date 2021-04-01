package AccueilRecette;
import java.util.Observable;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class ModèleAccueilRecette extends Observable{
	public Recette recette_courante;
	Vue vue;
	ModèleAccueil mdlAcc;

	public ModèleAccueilRecette(Vue v,ModèleAccueil mdl) {
		this.mdlAcc=mdl;
		this.vue = v;

	}

	public void setRecette(Recette rct) {
		this.recette_courante = rct;
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


	public void goToAccueil() {
		this.mdlAcc.ajouterRecette(this.recette_courante);
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
	}

	public void goToEtapes()
	{
		this.vue.currentInterface = this.vue.currentInterface.ETAPE_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ETAPE_RECETTE);
	}
	
	
}
