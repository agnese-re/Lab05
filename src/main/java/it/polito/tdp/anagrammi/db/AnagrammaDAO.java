package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.anagrammi.model.Anagramma;

public class AnagrammaDAO {

	
	public boolean isCorrect(String anagramma) {
		try {
			String sql = "SELECT * FROM parola WHERE nome = ?";
			Connection conn = DBConnect.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, anagramma);
			ResultSet rs = pst.executeQuery();
			
			Anagramma parola = null;
			while(rs.next())
				parola = new Anagramma(anagramma,true);
			rs.close();
			pst.close();
			conn.close();
			if(parola != null)
				return true;
			return false;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException("Errore nel DB", sqle);
		}
	}
}
