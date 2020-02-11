package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement.ParseInfo;

import constants.Constants;
import model.Film;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sediste;

public class KartaDao {
	
	public static Karta getOne(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM karte WHERE id = ? AND obrisan = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int index = 2;
				Projekcija projekcija = ProjekcijaDao.getOne(rset.getInt(index++));
				Sediste sediste = SedisteDao.getOne(rset.getInt(index++));
				String vremeProdajeStr = rset.getString(index++);
				Date vremeProdaje = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(vremeProdajeStr);
				Korisnik kupacKarte = KorisnikDao.getOne(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				return new Karta(id, projekcija, sediste, vremeProdaje, kupacKarte, obrisan);
			}
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	public static ArrayList<Karta> getAll(int projekcijaId){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Karta> karte = new ArrayList<Karta>();
		
		try {
			String query = "SELECT * FROM karte WHERE projekcijaID = ? AND obrisan = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projekcijaId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				Projekcija projekcija = ProjekcijaDao.getOne(rset.getInt(index++));
				Sediste sediste = SedisteDao.getOne(rset.getInt(index++));
				String vremeProdajeStr = rset.getString(index++);
				Date vremeProdaje = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(vremeProdajeStr);
				Korisnik kupacKarte = KorisnikDao.getOne(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				karte.add(new Karta(id, projekcija, sediste, vremeProdaje, kupacKarte, obrisan));
			}
			return karte;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return null;
	}
	
	
	
	
	
	public static ArrayList<Karta> getKarteZaKorisnika(String korIme){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Karta> karte = new ArrayList<Karta>();
		
		try {
			String query = "SELECT * FROM karte WHERE kupacKarte = ? AND obrisan = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korIme);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				Projekcija projekcija = ProjekcijaDao.getOne(rset.getInt(index++));
				Sediste sediste = SedisteDao.getOne(rset.getInt(index++));
				String vremeProdajeStr = rset.getString(index++);
				Date vremeProdaje = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(vremeProdajeStr);
				Korisnik kupacKarte = KorisnikDao.getOne(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				karte.add(new Karta(id, projekcija, sediste, vremeProdaje, kupacKarte, obrisan));
			}
			return karte;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return null;
	}
	
	
	
	

	
	public static boolean add(Karta karta) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO karte (projekcijaID, sedisteID,  kupacKarte)"
					+ "VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, karta.getProjekcija().getId());
			pstmt.setInt(index++, karta.getSediste().getId());
			pstmt.setString(index++, karta.getKupacKarte().getKorisnickoIme());
			
			return pstmt.executeUpdate() == 1;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	public static boolean update(Karta karta) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE karte SET projekcijaId = ?, sedisteID = ?, kupacKarte = ?";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, karta.getProjekcija().getId());
			pstmt.setInt(index++, karta.getSediste().getId());
			pstmt.setDate(index++, (java.sql.Date) karta.getVremeProdaje());
			pstmt.setString(index++, karta.getKupacKarte().getKorisnickoIme());
			
			return pstmt.executeUpdate() == 1;
		}catch (SQLException ex) {
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
			String query = "UPDATE karte SET obrisan = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, id);
			
			
			return pstmt.executeUpdate() == 1;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	public static int getSedisteIdZaKartu(int idKarte) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
			try {
				String query = "SELECT sedisteID FROM karte WHERE id = ? ";
				pstmt = conn.prepareStatement(query);
				
				
				
				int index = 1;
				pstmt.setInt(index++, idKarte);
			
				rset = pstmt.executeQuery();
				
				int rezultat =  ((Number) rset.getObject(1)).intValue();
				System.out.println("REZULTAT JE :"  + rezultat);
				
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
	
	
}
	
	
	
	
	

	


