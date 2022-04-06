package it.polito.tdp.anagrammi.model;

import java.util.LinkedList;
import java.util.List;

public class TestAnagrammi {
	
	public static void main(String args[]) {
		Model model = new Model();
		List<String> listaAnagrammi = new LinkedList<String>(model.anagrammi("mare"));
		for(String s: listaAnagrammi)
			System.out.println(s);
		
	}
}
