package it.polito.tdp.anagrammi.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {

	private AnagrammaDAO anagrammaDAO;
	private List<String> anagrammiLista;
	
	public Model() {
		anagrammaDAO = new AnagrammaDAO();
	}
	
	/* Data una parola inserita dall'utente stampa tutti gli anagrammi. 
	 	La ricorsione viene avviata a partire dal livello 0 con una solu-
	 		zione parziale uguale a stringa vuota e con tutte le lettere
	 		costituenti la parola inserita ancora da aggiungere alla so-
	 		luzione parziale. Di volta in volta aggiungo una lettera */
	public List<String> anagrammi(String parolaInserita) {
		anagrammiLista = new LinkedList<String>();	// nuova lista per ciascuna parola
		recursiveAnagramma("",0,parolaInserita);	// inizio della ricorsione
		return anagrammiLista;
	}
	
	/* FUNZIONE RICORSIVA CHE CHIAMA SE' STESSA */
	public void recursiveAnagramma(String soluzioneParziale, int livello, String lettereRimanenti) {
		// casi terminali
		if(lettereRimanenti.length() == 0) {
			boolean ripetuto = false;
			// aggiungo solo se non e' gia' presente
			for(String stringa: anagrammiLista) {
				if(stringa.equals(soluzioneParziale))
					ripetuto = true;
			}
			if(ripetuto == false)
				anagrammiLista.add(soluzioneParziale);
		}
		
		for(int indice = 0; indice < lettereRimanenti.length(); indice++) {
			String nuovaSoluzioneParziale = soluzioneParziale + lettereRimanenti.charAt(indice);
			String nuoveLettereRimanenti = lettereRimanenti.substring(0,indice) + lettereRimanenti.substring(indice+1);
			recursiveAnagramma(nuovaSoluzioneParziale,livello+1,nuoveLettereRimanenti);
		}
	}
	
	/* DAL DAO AL MODELLO */
	public boolean isCorrect(String anagramma) {
		return anagrammaDAO.isCorrect(anagramma);
	}
	
	/* Conta numero parole corrette. Sono effettivamente anagrammi */
	public int corretti(String anagramma) {
		int numeroCorretti = 0;
		for(String s: anagrammiLista)
			if(isCorrect(s))
				numeroCorretti++;
		return numeroCorretti;
	}
	
	/* Conta numero parole errate. Sono solo permutazioni, non in dizionario */
	public int errati(String anagramma) {
		return anagrammiLista.size()-corretti(anagramma);
	}
}
