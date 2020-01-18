package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Sala;

public class SalaDao {
	
	public static Sala getOne(String naziv) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM sale WHERE naziv = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, naziv);
			rset = pstmt.executeQuery();	
			
			if(rset.next()) {
				int index = 2;
				String tipProjekcije = rset.getString(index++);
				
				return new Sala(naziv, tipProjekcije);
			}
		} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
		} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	
	public static ArrayList<Sala> getAll(){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Sala> sale = new ArrayList<Sala>();
		
		try {
			String query = "SELECT * FROM sale";
					
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				String naziv = rset.getString(index++);
				String tipProjekcije = rset.getString(index++);
				
				sale.add(new Sala(naziv, tipProjekcije));
			}
			return sale;
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}

}
