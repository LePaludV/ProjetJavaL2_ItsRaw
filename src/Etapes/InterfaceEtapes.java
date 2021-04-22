package Etapes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class InterfaceEtapes implements Observer {

	static BorderPane rootLayout;
	static EtapesController ctrlEtapes;
	private static final float DIVISION_RATION = 3f;
	Recette rct = new Recette();

	public InterfaceEtapes(EtapesController ctrl)
	{
		ctrlEtapes = ctrl;
	}

	public static BorderPane getRoot()
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("../etapes.fxml"));
        loader.setController(ctrlEtapes);
		try {
			rootLayout = (BorderPane) loader.load();
			System.out.println(rootLayout);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rootLayout;
	}

	@Override
	public void update(Observable o, Object index) {
		int i = (int) index;
		if(i<this.rct.getEtapes().size())
		{
			ctrlEtapes.textEtape.setText("Etape " + (i+1));
			ctrlEtapes.textDescription.setText(this.rct.getEtapes().get(i));
		}
		else
		{
			ctrlEtapes.finRecette();
			ctrlEtapes.textEtape.setText("Etape " + (this.rct.getEtapes().size()));
			ctrlEtapes.textDescription.setText(this.rct.getEtapes().get(this.rct.getEtapes().size()-1));
		}
	}
	
	/*public String description(String des)
	{
		String newText = "";
		int compteurChar = 0;
		for(int i = 2; i < des.length(); i++)
		{
			if(compteurChar<50 || des.charAt(i) != ' ')
			{
				newText = newText + des.charAt(i);
				compteurChar += 1;
			}
			else
			{
				compteurChar = 0;
				newText = newText + '\n';
			}
		}
		return newText;
	}*/
	
	public void loadRecette(Recette rct)
	{
		this.rct = rct;
		if(rct == null)
		{
			ctrlEtapes.textDescription.setText("Problème chargement de la recette séléctionner dans l'accueil "
					+ "\nOU interface lancé par défaut (cf ''currentInterface'' dans Vue.java)");
		}
		else
		{
			ctrlEtapes.nom.setText(rct.getNom());
			ctrlEtapes.image.setImage(rct.getPhoto());
			ctrlEtapes.textEtape.setText("Etape 1");
			ctrlEtapes.textDescription.setText(rct.getEtapes().get(0));
			String ingr = null;
			for(Ingrédient s: rct.getIngrédients()) {
				ingr+="- "+s.quantité+s.mesure+" "+s.nom+"\n";
			}

			ctrlEtapes.textIngredients.setText("ingr");
			
			ctrlEtapes.textIngredients.setText(rct.getIngrédients().toString());
			StringBuilder Ingrédient = new StringBuilder();
			for(int i = 0;i<rct.getIngrédients().size();i++) {
				Ingrédient.append(rct.getIngrédients().get(i).nom+" "+(int)rct.getIngrédients().get(i).quantité+" "+rct.getIngrédients().get(i).mesure+"\n");
			}
			ctrlEtapes.textIngredients.setText(Ingrédient.toString());
		}
		try {
			ImageView d = new ImageView(new Image(new FileInputStream("imgs/FlecheDroite.png")));
			ImageView g = new ImageView(new Image(new FileInputStream("imgs/FlecheGauche.png")));
			d.setFitHeight(35);
			g.setFitHeight(35);
			d.setFitWidth(50);
			g.setFitWidth(50);
			ctrlEtapes.suivant.setGraphic(d);
			ctrlEtapes.precedent.setGraphic(g);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ctrlEtapes.retour.setOnAction(e -> {
			this.ctrlEtapes.backToAccueilRecette(rct);
		});
	}

}
