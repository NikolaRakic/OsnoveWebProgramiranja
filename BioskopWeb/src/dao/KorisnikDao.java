package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Korisnik;
import model.Korisnik.Uloga;

public class KorisnikDao {
	
	public static Korisnik getOne(String korisnickoIme) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM korisnici WHERE korisnickoIme = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			rset = pstmt.executeQuery();
			System.out.println(pstmt.toString());
			if(rset.next()) {
				int index = 2;
				
				String lozinka = rset.getString(index++);
				Date datumRegistacije = rset.getDate(index++);
				Uloga uloga = Uloga.valueOf(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				return new Korisnik(korisnickoIme, lozinka, datumRegistacije, uloga, obrisan);
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
	
	public static ArrayList<Korisnik> getAll(){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		try {
			String query = "SELECT * FROM korisnici";
			
			pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	String korisnickoIme = rset.getString(index++);
            	String lozinka = rset.getString(index++);
				Date datumRegistracije = rset.getDate(index++);
				Uloga uloga = Uloga.valueOf(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				Korisnik k = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
				korisnici.add(k);
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
	
	
	public static boolean add(Korisnik korisnik) {
		/*Korisnik kor = getOne(korisnik.getKorisnickoImeIme());
		
		try {
			if(kor != null) return false;
		} catch (NullPointerException e) {
			return false;
		}
		*/
		Connection conn = ConnectionManager.getConnection();
        PreparedStatement pstmt = null;
        try {
        	String query = "INSERT INTO korisnici (korisnickoIme, lozinka, datumRegistracije, uloga))" +
        "VALUES (?,?, ?, ?)";
        			
        	pstmt = conn.prepareStatement(query);
        	int index = 1;
        	pstmt.setString(index++, korisnik.getKorisnickoIme());
        	pstmt.setString(index++, korisnik.getLozinka());
        	pstmt.setDate(index, (java.sql.Date) korisnik.getDatumRegistracije());
        	pstmt.setString(index++, korisnik.getUloga().toString());
        	
        	return pstmt.executeUpdate() == 1;
        }catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	
	public static boolean update(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnici SET korisnickoIme = ?, lozinka = ?, datumRegistracije = ?, uloga = ?, obrisan = ?";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
        	pstmt.setString(index++, korisnik.getLozinka());
        	pstmt.setDate(index, (java.sql.Date) korisnik.getDatumRegistracije());
        	pstmt.setString(index++, korisnik.getUloga().toString());
        	pstmt.setBoolean(index++, korisnik.isObrisan());
        	
        	return pstmt.executeUpdate() == 1;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}

}
