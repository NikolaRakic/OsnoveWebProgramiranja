package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Projekcija;
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
				int projekcijaaId = rset.getInt(index++);
				boolean zauzeto = rset.getBoolean(index++);
				
				return new Sediste(id, redniBroj, projekcijaaId, zauzeto);
				
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
	
	
	
	public static ArrayList<Sediste> getSlobodnaSedistaZaProjekciju(int projekcijaId){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Sediste> slobodnaSedista = new ArrayList<Sediste>();
		try {
			String query = "SELECT * FROM sedista WHERE projekcijaId = ? and zauzeto = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projekcijaId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				int redniBroj = rset.getInt(index++);
				int projekcijaaId = rset.getInt(index++);
				boolean zauzeto = rset.getBoolean(index++);
				
				slobodnaSedista.add(new Sediste(id, redniBroj, projekcijaaId, zauzeto));
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
	
	
	
	public static boolean zauzmiSediste(int projekcijaId, int id) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE sedista SET zauzeto = ? WHERE projekcijaId = ? AND id = ?";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setBoolean(index++, true);
			pstmt.setInt(index++, projekcijaId);
			pstmt.setInt(index++, id);
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	
	public static boolean oslobodiSediste(int id) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE sedista SET zauzeto = ? WHERE  id = ?";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setBoolean(index++, false);
			pstmt.setInt(index++, id);
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	public static int brojSlobodnihSedista(int projekcijaId) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int rezultat = 0;
			try {
				String query = "SELECT * FROM sedista WHERE zauzeto = ? AND projekcijaId = ?";
				pstmt = conn.prepareStatement(query);
				
				
				
				int index = 1;
				pstmt.setBoolean(index++, false);
				pstmt.setInt(index++, projekcijaId);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					rezultat ++;
				}
				
				return rezultat;
				
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}
			return (Integer) null;
	}
	
	
	public static boolean dodajSedistaZaProjekciju(int projekcijaId, int brojSedista) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			
			for (int i = 1; i < brojSedista+1; i++) {
				String query = "INSERT INTO sedista (redniBroj, projekcijaId) VALUES (?,?)";
				pstmt = conn.prepareStatement(query);
				
				int index = 1;
				pstmt.setInt(index++, i);
				pstmt.setInt(index++, projekcijaId);
				
				pstmt.executeUpdate();
			}
			
			
			
			return  true;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
		

}
