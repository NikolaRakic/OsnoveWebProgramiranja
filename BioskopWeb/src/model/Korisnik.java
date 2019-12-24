package model;

import java.util.Date;

public class Korisnik {

	public enum Uloga {neprijavljen, korisnik, admin}
	
	private String korisnickoIme;
	private String lozinka;
	private Date datumRegistracije;
	private Uloga uloga;
	private boolean obrisan;
	
	public Korisnik(String korisnickoIme, String lozinka, Date datumRegistracije, Uloga uloga, boolean obrisan) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga;
		this.obrisan = obrisan;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Date getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
}
