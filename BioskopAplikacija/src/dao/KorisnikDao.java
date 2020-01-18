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
				System.out.println("lozinka" + lozinka);
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
				System.out.println("datumRegistracijeStr" + datumRegistracijeStr);
				Date datumRegistracije = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(datumRegistracijeStr);
				String uloga = Uloga.valueOf(rset.getString(index++).toUpperCase()).toString();
				boolean obrisan = rset.getBoolean(index++);

				Korisnik k = new Korisnik(korisnickoIme, lozinka, datumRegistracije, uloga, obrisan);
				korisnici.add(k);
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
		}

		return null;
	}

	public static boolean add(Korisnik korisnik) {
	
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO korisnici (korisnickoIme, lozinka, obrisan)" + "VALUES (?,?,?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, "0");

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
			String query = "UPDATE korisnici SET korisnickoIme = ?, lozinka = ?, uloga = ?, obrisan = ?";
			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setBoolean(index++, korisnik.isObrisan());

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

}
