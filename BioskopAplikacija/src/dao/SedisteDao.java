package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Sala;
import model.Sediste;

public class SedisteDao {
	
	public static Sediste getOne(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM sedista WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int index = 2;
				int redniBroj = rset.getInt(index++);
				Sala sala = SalaDao.getOne(rset.getString(index++));
				boolean zauzeto = rset.getBoolean(index++);
				
				return new Sediste(id, redniBroj, sala, zauzeto);
				
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
	
	
	
	public static ArrayList<Sediste> getSlobodnaSedistaZaSalu(String salaNaziv){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Sediste> slobodnaSedista = new ArrayList<Sediste>();
		try {
			String query = "SELECT * FROM sedista WHERE salaNaziv = ? and zauzeto = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, salaNaziv);
			pstmt.setBoolean(2, false);
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				int redniBroj = rset.getInt(index++);
				Sala sala = SalaDao.getOne(rset.getString(index++));
				boolean zauzeto = rset.getBoolean(index++);
				
				slobodnaSedista.add(new Sediste(id, redniBroj, sala, zauzeto));
			}
			return slobodnaSedista;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	
	public static boolean zauzmiSediste(String salaNaziv, int redniBroj) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE sedista SET zauzeto = ? WHERE salaNaziv = ? AND redniBroj = ?";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setBoolean(index++, true);
			pstmt.setString(index++, salaNaziv);
			pstmt.setInt(index++, redniBroj);
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

}
