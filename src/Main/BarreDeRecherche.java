package Main;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Observable;

import com.sun.corba.se.pept.transport.Acceptor;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class BarreDeRecherche<T> extends Observable implements EventHandler {
	
    private ComboBox<T> comboBox;
    private StringBuilder sb;
    private ObservableList<T> data;
    private boolean moveCaretToPos = false;
    private int caretPos;

    public BarreDeRecherche(final ComboBox<T> comboBox) {
        this.comboBox = comboBox;
        sb = new StringBuilder();
        data = comboBox.getItems();

        this.comboBox.setEditable(true);
        this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                comboBox.hide();
            }
        });
        this.comboBox.setOnKeyReleased(BarreDeRecherche.this);
    }

    @Override
    public void handle(Event event) {
    	
    	KeyEvent evt = (KeyEvent) event;

        if(evt.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if(evt.getCode() == KeyCode.DOWN) {
            if(!comboBox.isShowing()) {
                comboBox.show();
            }
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if(evt.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        } else if(evt.getCode() == KeyCode.DELETE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        }

        if (evt.getCode() == KeyCode.RIGHT || evt.getCode() == KeyCode.LEFT
                || evt.isControlDown() || evt.getCode() == KeyCode.HOME
                || evt.getCode() == KeyCode.END || evt.getCode() == KeyCode.TAB 
                || evt.getCode() == KeyCode.CAPS) {
            return;
        } 
        
        if (evt.getCode() == KeyCode.ENTER) {
        	this.setChanged();
        	this.notifyObservers(this.comboBox.getEditor().getText());
        	this.comboBox.getEditor().setText(null);
        	return;
        }
        

        ObservableList list = FXCollections.observableArrayList();
        for (int i=0; i<data.size(); i++) {
            if(data.get(i).toString().toLowerCase().startsWith(
            		BarreDeRecherche.this.comboBox
                .getEditor().getText().toLowerCase())) {
                list.add(data.get(i));
            }
        }
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if(!list.isEmpty()) {
            comboBox.show();
        }
    }

    private void moveCaret(int textLength) {
        if(caretPos == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(caretPos);
        }
        moveCaretToPos = false;
    }
}
