/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.anagrammi;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // fx:id="btnCalcolaAnagrammi"
    private Button btnCalcolaAnagrammi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblErrore"
    private Label lblErrore; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnagrammiCorretti"
    private TextArea txtAnagrammiCorretti; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnagrammiErrati"
    private TextArea txtAnagrammiErrati; // Value injected by FXMLLoader

    @FXML // fx:id="txtParolaInserita"
    private TextField txtParolaInserita; // Value injected by FXMLLoader

    private Model modello;
    
    @FXML
    void handleCalcolaAnagrammi(ActionEvent event) {
    	lblErrore.setText(""); 	// pulisco da eventuali errori precedenti
    	txtAnagrammiCorretti.clear();	// pronta per prossima parola
    	txtAnagrammiErrati.clear();		// ...
    	
    	boolean passatiControlli = true;
    	// Lettura parola inserita dall'utente
    	String parolaInserita = txtParolaInserita.getText();
    	// Controllo che il campo non sia vuoto
    	if(parolaInserita.equals("") || parolaInserita == null) {
    		lblErrore.setText("ERRORE: inserire una parola");
    		passatiControlli = false;
    	}
    	// Controllo che sia state inserite solamente lettere alfabetiche [A-Z a-z]
    	if(!parolaInserita.matches("[A-Za-z]+")) {
    		lblErrore.setText("ERRORE: inserire solamente lettere alfabetiche [A-Z a-z]");
    		passatiControlli = false;
    	}
    	
    	if(passatiControlli) {
	    	for(String possibileAnagramma: modello.anagrammi(parolaInserita))
	    		if(modello.isCorrect(possibileAnagramma))
	    			txtAnagrammiCorretti.appendText(possibileAnagramma + "\n");
	    		else
	    			txtAnagrammiErrati.appendText(possibileAnagramma + "\n");
	    	lblErrore.setText(" CORRETTI: " + modello.corretti(parolaInserita) + 
	    			"\tERRATI: " + modello.errati(parolaInserita));
    	}
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtParolaInserita.clear();
    	txtAnagrammiCorretti.clear();
    	txtAnagrammiErrati.clear();
    }

    public void setModel(Model modello) {
    	this.modello = modello;
    }
}