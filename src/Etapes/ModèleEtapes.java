package Etapes;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class ModèleEtapes extends Observable{
	Vue vue;
	int indexEtape;
	Recette recette_courante;
	public ModèleEtapes(Vue v) {
		this.vue = v;
		this.indexEtape = 0;
	}

	public void setRecette(Recette rct) {
		this.recette_courante = rct;
		this.notifyObservers(recette_courante);
	}

	public void setEtapes()
	{
		this.setChanged();
		this.notifyObservers(this.indexEtape);
	}

	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL_RECETTE);
	}

	public void  clickSuivant()
	{
		if(this.indexEtape<this.recette_courante.getEtapes().size())
		{
			this.indexEtape +=1;
			this.setEtapes();
		}
	}

	public void clickPrecedent()
	{
		if(this.indexEtape>0)
		{
			this.indexEtape -=1;
			this.setEtapes();
		}
	}

}
