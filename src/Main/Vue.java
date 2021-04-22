package Main;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.sun.javafx.css.FontFace.FontFaceSrc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Etapes.EtapesController;
import Etapes.InterfaceEtapes;
import Etapes.ModèleEtapes;
import Favoris.*;
import Main.*;
import Panier.*;
import Etapes.*;
public class Vue extends Application {
	Stage primaryStage;
	InterfaceAccueil acc=new InterfaceAccueil(null);
	GridPane rootLayout;
	ModèleAjoutRecette mdlAjout;
	ModèleAccueil mdlAccueil;
	ModèleAccueilRecette mdlAccueilRecette;
	ModèleEtapes mdlEtapes;
	ModèlePanier mdlPanier;
	ModèleFavoris mdlFav;

	public enum typeInterface {ACCUEIL, AJOUT_RECETTE, ACCUEIL_RECETTE, ETAPE_RECETTE, PANIER, FAVORIS};
	public typeInterface currentInterface = typeInterface.ACCUEIL;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.mdlAccueil = new ModèleAccueil(this);
        this.mdlPanier=new ModèlePanier(this);
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("It's Raw");
        this.primaryStage.setResizable(false);
        this.mdlFav= new ModèleFavoris(this);
        this.mdlAccueilRecette = new ModèleAccueilRecette(this, mdlAccueil, mdlPanier, mdlFav);
        
        this.changeWindow(this.currentInterface);
        primaryStage.show();

	}

	public void changeWindow(typeInterface inter) {
		this.currentInterface = inter;
        if (this.currentInterface == typeInterface.AJOUT_RECETTE) {
            this.mdlAjout = new ModèleAjoutRecette(this);
            AjoutRecetteController ctrlAjout = new AjoutRecetteController(mdlAjout, mdlAccueil);
            InterfaceAjouterRecette vueAjout = new InterfaceAjouterRecette(ctrlAjout);
            this.mdlAjout.addObserver(vueAjout);
            Scene scene=new Scene(InterfaceAjouterRecette.getRoot());
            ctrlAjout.personnesSpinner();
            this.mdlAjout.afficherInterface();
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();
        } else if (this.currentInterface == typeInterface.ACCUEIL)
        {
            AccueilController ctrlAccueil = new AccueilController(this.mdlAccueil, this.mdlAccueilRecette);
            InterfaceAccueil vueAccueil = new InterfaceAccueil(ctrlAccueil);
            this.mdlAccueil.addObserver(vueAccueil);
            Scene scene=new Scene(InterfaceAccueil.getRoot());
            this.mdlAccueil.afficherRecettes();
            ctrlAccueil.compléterComboBox(this.mdlAccueil.classeIng.keySet());
            ctrlAccueil.compléterComboBox(this.mdlAccueil.catégories.keySet());
            ctrlAccueil.createSearchBar();
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();

        } else if (this.currentInterface == typeInterface.ACCUEIL_RECETTE) {
        	System.out.println("changewindow");
        	Recette rct=new Recette();
        	if(this.mdlEtapes == null) {rct = null;}
        	else {rct=this.mdlEtapes.recette_courante;}
            AccueilRecetteController ctrlAccueilRecette = new AccueilRecetteController(this.mdlAccueilRecette);
            InterfaceAccueilRecette vueAccueilRecette = new InterfaceAccueilRecette(ctrlAccueilRecette, mdlFav);
            this.mdlAccueilRecette.addObserver(vueAccueilRecette);
            Scene scene=new Scene(InterfaceAccueilRecette.getRoot());
            primaryStage.setScene(scene);
            vueAccueilRecette.loadRecette(rct);
            this.primaryStage.sizeToScene();
            
        } else if (this.currentInterface == typeInterface.ETAPE_RECETTE) {
        	Recette rct = new Recette();
        	rct = this.mdlAccueilRecette.recette_courante;
        	this.mdlEtapes = new ModèleEtapes(this);
        	this.mdlEtapes.setRecette(rct);
            EtapesController ctrlEtapes = new EtapesController(this.mdlEtapes, this.mdlAccueilRecette);
            InterfaceEtapes vueEtapes = new InterfaceEtapes(ctrlEtapes);
            this.mdlEtapes.addObserver(vueEtapes);
            Scene scene=new Scene(InterfaceEtapes.getRoot());
            primaryStage.setScene(scene);
            vueEtapes.loadRecette(rct);
            this.primaryStage.sizeToScene();

        }
        else if (this.currentInterface == typeInterface.PANIER) {
        	
            PanierController ctrlPanier = new PanierController(this.mdlPanier);
            InterfacePanier vuePanier = new InterfacePanier(ctrlPanier, this.mdlPanier);
            this.mdlPanier.addObserver(vuePanier);
       
            Scene scene=new Scene(InterfacePanier.getRoot());
            
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();
            this.mdlPanier.AfficherPanier();

        }
        else if (this.currentInterface == typeInterface.FAVORIS) {
        	
            FavorisController ctrlFav = new FavorisController(this.mdlFav, mdlAccueilRecette);
            InterfaceFavoris vueFav = new InterfaceFavoris(ctrlFav, this.mdlFav,this.mdlAccueil);
            this.mdlFav.addObserver(vueFav);
       
            Scene scene=new Scene(InterfaceFavoris.getRoot());
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();
            this.mdlFav.AfficherFavoris();

        }
	}
}
