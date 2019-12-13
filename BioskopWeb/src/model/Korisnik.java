package model;

import java.util.Date;

public class Korisnik {

	public enum Uloga {neprijavljen, korisnik, admin}
	
	private String koriscnickoIme;
	private String lozinka;
	private Date datumRegistracije;
	private Uloga uloga;
	private boolean obrisan;
	
	public Korisnik(String koriscnickoIme, String lozinka, Date datumRegistracije, Uloga uloga, boolean obrisan) {
		super();
		this.koriscnickoIme = koriscnickoIme;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga;
		this.obrisan = obrisan;
	}

	public String getKoriscnickoIme() {
		return koriscnickoIme;
	}

	public void setKoriscnickoIme(String koriscnickoIme) {
		this.koriscnickoIme = koriscnickoIme;
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
