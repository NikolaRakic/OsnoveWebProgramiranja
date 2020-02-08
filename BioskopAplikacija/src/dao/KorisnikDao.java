package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import constants.Constants;
import model.Film;
import model.Korisnik;
import enums.Uloga;

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
			if (rset.next()) {
				int index = 2;

				String lozinka = rset.getString(index++);
				String datumRegistracijeStr = rset.getString(index++);
				Date datumRegistacije = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(datumRegistracijeStr);
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);
				return new Korisnik(korisnickoIme, lozinka, datumRegistacije, uloga, obrisan);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	
	public static Korisnik getOne(String korisnickoIme, String lozinka) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM korisnici WHERE korisnickoIme = ? AND lozinka = ? AND obrisan = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			pstmt.setString(2, lozinka);
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int index = 3;

				String datumRegistracijeStr = rset.getString(index++);
				Date datumRegistacije = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(datumRegistracijeStr);
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				return new Korisnik(korisnickoIme, lozinka, datumRegistacije, uloga, false);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	
	
	

	public static ArrayList<Korisnik> getAll() {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		try {
			String query = "SELECT * FROM korisnici";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				String korisnickoIme = rset.getString(index++);
				String lozinka = rset.getString(index++);
				String datumRegistracijeStr = rset.getString(index++);
	
				Date datumRegistracije = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(datumRegistracijeStr);
				
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);

				Korisnik k = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
				
				korisnici.add(k);
			}
			return korisnici;

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return null;
	}
	
	
	
	
	
	public static ArrayList<Korisnik> sort(String vrednostSortiranja, String nacinSortiranja) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		String query = "SELECT * FROM korisnici WHERE obrisan = '0'";
		String sortQuery = "ORDER BY korisnickoIme";
		try {
			if (vrednostSortiranja != null) {
				switch (vrednostSortiranja) {
				case "korisnickoIme":
					if (nacinSortiranja.equals("DESC"))
						sortQuery = " ORDER BY korisnickoIme DESC";
					else
						sortQuery = " ORDER BY korisnickoIme ";
					break;
				case "uloga":
					if (nacinSortiranja.equals("DESC"))
						sortQuery = " ORDER BY uloga DESC";
					else
						sortQuery = " ORDER BY uloga ";
					break;
				
				default:
					break;
				}
			}
				query += sortQuery;
			
				System.out.println(query);

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				String korisnickoIme = rset.getString(index++);
				String lozinka = rset.getString(index++);
				String datumRegistracijeStr = rset.getString(index++);
	
				Date datumRegistracije = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(datumRegistracijeStr);
				
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);

				Korisnik k = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
				
				korisnici.add(k);
			}
			return korisnici;

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return null;
	}
	
	
	
	
	
	public static ArrayList<Korisnik> pretraga(String pretragaInput) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		String query = "SELECT * FROM korisnici WHERE obrisan = '0' ";
		String pretragaQuery = null;
		try {
			if(pretragaInput != null) {
				pretragaQuery = "AND korisnickoIme like '%"+pretragaInput+"%' OR uloga like '%"+pretragaInput+"%'";
			}
			query += pretragaQuery;
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				String korisnickoIme = rset.getString(index++);
				String lozinka = rset.getString(index++);
				String datumRegistracijeStr = rset.getString(index++);
	
				Date datumRegistracije = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(datumRegistracijeStr);
				
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);

				Korisnik k = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
				
				korisnici.add(k);
			}
			return korisnici;

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return null;
	}
	
	
	
	

	public static boolean add(Korisnik korisnik) {
	
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO korisnici (korisnickoIme, lozinka, obrisan) VALUES (?,?,?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setBoolean(index++, false);

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;
	}

	public static boolean update(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnici SET lozinka = ?, uloga = ?, obrisan = ? WHERE korisnickoIme = ?";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setBoolean(index++, korisnik.isObrisan());
			pstmt.setString(index++, korisnik.getKorisnickoIme());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;
	}
	
	
	public static boolean delete(String korisnickoIme) {
		Connection conn = ConnectionManager.getConnection();	
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE korisnici SET obrisan = ? WHERE korisnickoIme = ?";	
			
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
		
			pstmt.setBoolean(index++, true);
			pstmt.setString(index++, korisnickoIme);			
			
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
