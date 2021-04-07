package Etapes;
import java.util.Observable;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ModèleEtapes extends Observable implements EventHandler {
	Vue vue;
	int indexEtape;
	public Recette recette_courante;
	
	public ModèleEtapes(Vue v) {
		this.vue = v;
		this.indexEtape = 0;
	}

	public void setEtapes()
	{
		this.setChanged();
		this.notifyObservers(this.indexEtape);
	}
	
	public void setRecette(Recette rct)
	{
		this.recette_courante = rct;
	}

	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL_RECETTE);
	}

	public void  clickSuivant()
	{
		this.indexEtape +=1;
		this.setEtapes();
	}

	public void clickPrecedent()
	{
		if(this.indexEtape>0)
		{
			this.indexEtape -=1;
			this.setEtapes();
		}
	}
	
	@Override
	public void handle(Event event)
	{
		KeyEvent evt = (KeyEvent) event;
		if(evt.getCode() == KeyCode.RIGHT)
		{
			clickSuivant();
		}
		else if(evt.getCode() == KeyCode.LEFT)
		{
			clickPrecedent();
		}
	}

}
