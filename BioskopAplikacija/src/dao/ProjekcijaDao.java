package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Projekcija.TipProjekcijeEnum;
import model.Sala;

public class ProjekcijaDao {

	public static Projekcija getOne(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM projekcije WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int index = 2;
				
				Film film = FilmDao.getOne(rset.getInt(index++));
				Sala sala = SalaDao.getOne(rset.getString(index++));
				TipProjekcijeEnum tipProjekcije = TipProjekcijeEnum.valueOf(rset.getString(index++));
				Date datumPrikazivanja = rset.getDate(index++);
				String vremePrikazivanja = rset.getTime(index++).toString();
				int cenaKarte = rset.getInt(index++);
				Korisnik admin = KorisnikDao.getOne(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				return new Projekcija(id, film, sala, tipProjekcije, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin, obrisan);
			}
			
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	public static ArrayList<Projekcija> getAll(){
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();
		
		try {
			String query = "SELECT * FROM projekcije";
			
			pstmt = conn.prepareStatement(query);	
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				Film film = FilmDao.getOne(rset.getInt(index++));
				Sala sala = SalaDao.getOne(rset.getString(index++));
				TipProjekcijeEnum tipProjekcije = TipProjekcijeEnum.valueOf(rset.getString(index++));
				Date datumPrikazivanja = rset.getDate(index++);
				String vremePrikazivanja = rset.getTime(index++).toString();
				int cenaKarte = rset.getInt(index++);
				Korisnik admin = KorisnikDao.getOne(rset.getString(index++));
				boolean obrisan = rset.getBoolean(index++);
				
				projekcije.add(new Projekcija(id, film, sala, tipProjekcije, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin, obrisan));
			}
			return projekcije;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
		try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	
	
	public static boolean create(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO projekcije (filmID, salaNaziv, tipProjekcije, datumPrikazivanja, vremePrikazivanja, cenaKarte, admin)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, projekcija.getFilm().getId());
			pstmt.setString(index++, projekcija.getSala().getNaziv());
			pstmt.setString(index++, projekcija.getTipProjekcije().toString());
			pstmt.setDate(index++, (java.sql.Date) projekcija.getDatumPrikazivanja());
			pstmt.setString(index++, projekcija.getVremePrikazivanja());
			pstmt.setInt(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdmin().getKorisnickoIme());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static boolean update(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE projekcije SET filmID = ?, salaNaziv = ?, tipProjekcije = ?, datumPrikazivanja = ?, vremePrikazivanja = ?, cenaKarte = ?, obrisan = ? ";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, projekcija.getFilm().getId());
			pstmt.setString(index++, projekcija.getSala().getNaziv());
			pstmt.setString(index++, projekcija.getTipProjekcije().toString());
			pstmt.setDate(index++, (java.sql.Date) projekcija.getDatumPrikazivanja());
			pstmt.setString(index++, projekcija.getVremePrikazivanja());
			pstmt.setInt(index++, projekcija.getCenaKarte());
			pstmt.setBoolean(index++, projekcija.isObrisan());
			
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