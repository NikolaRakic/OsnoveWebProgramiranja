package model;

import java.util.Date;

public class Karta {
	
	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private Date vremeProdaje;
	private Korisnik kupacKarte;
	private boolean obrisan;
	
	public Karta(int id, Projekcija projekcija, Sediste sediste, Date vremeProdaje, Korisnik kupacKarte,
			boolean obrisan) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.vremeProdaje = vremeProdaje;
		this.kupacKarte = kupacKarte;
		this.obrisan = obrisan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public Date getVremeProdaje() {
		return vremeProdaje;
	}

	public void setVremeProdaje(Date vremeProdaje) {
		this.vremeProdaje = vremeProdaje;
	}

	public Korisnik getKupacKarte() {
		return kupacKarte;
	}

	public void setKupacKarte(Korisnik kupacKarte) {
		this.kupacKarte = kupacKarte;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
	

}
