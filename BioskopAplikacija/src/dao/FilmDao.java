package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Film;

public class FilmDao {
	
	public static List<Film> getAll(){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Film> filmovi = new ArrayList<Film>();
		
		try {
			String query = "SELECT * FROM filmovi WHERE obrisan = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanr = rset.getString(index++);
				int trajanje = rset.getInt(index++);
				String distributer = rset.getString(index++);
				String zemlja = rset.getString(index++);
				int godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				boolean obrisan = rset.getBoolean(index);
				
				filmovi.add(new Film(id, naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis, obrisan));
			}
			return filmovi;
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return null;
	}
	
	public static Film getOne(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM filmovi WHERE id = ?";
		
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int index = 2;
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanr = rset.getString(index++);
				int trajanje = rset.getInt(index++);
				String distributer = rset.getString(index++);
				String zemlja = rset.getString(index++);
				int godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				boolean obrisan = rset.getBoolean(index);
				
				return new Film(id, naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis, obrisan);
			}
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean create(Film film) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO filmovi (naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godinaProizvodnje, opis)"
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanr());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemlja());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			
			return pstmt.executeUpdate() == 1;
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static boolean update(Film film) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		System.out.println("Opis: " + film.getOpis());
		try {
			String query = "UPDATE filmovi SET naziv = ?, reziser = ?, glumci = ?, zanr = ?, trajanje = ?,"
					+ "distributer = ?, zemlja = ?, godinaProizvodnje = ?, opis = ?, obrisan = ? WHERE id = ?";
			
			
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanr());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemlja());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			pstmt.setBoolean(index++, film.isObrisan());
			pstmt.setInt(index++, film.getId());
			
			System.out.println("ID FILMA ZA IZMENU JE : " + film.getId());
			
			return pstmt.executeUpdate() == 1;
		 } catch (SQLException ex) {
		System.out.println("Greska u SQL upitu!");
		ex.printStackTrace();
		 } finally {
			 try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		 }

  		 return false;
 
	}
	
	
	
	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE filmovi SET obrisan = ? WHERE id = ?";	
			
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
		
			pstmt.setBoolean(index++, true);
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
}
