package model;

import java.sql.Time;
import java.util.Date;

public class Projekcija {
	
	
	
	private int id;
	private Film film;
	private Sala sala;
	private String tipProjekcije;
	private String datumPrikazivanja;
	private int cenaKarte;
	private Korisnik admin;
	private boolean obrisan;
	
	public Projekcija(int id, Film film, Sala sala, String tipProjekcije, String datumPrikazivanja,
			 int cenaKarte, Korisnik admin, boolean obrisan) {
		super();
		this.id = id;
		this.film = film;
		this.sala = sala;
		this.tipProjekcije = tipProjekcije;
		this.datumPrikazivanja = datumPrikazivanja;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
		this.obrisan = obrisan;
	}


	public Projekcija(int id, int cenaKarte, Film film) {
		super();
		this.id = id;
		this.film = film;
		this.cenaKarte = cenaKarte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(String tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public String getDatumPrikazivanja() {
		return datumPrikazivanja;
	}

	public void setDatumPrikazivanja(String datumPrikazivanja) {
		this.datumPrikazivanja = datumPrikazivanja;
	}


	public int getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(int cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public Korisnik getAdmin() {
		return admin;
	}

	public void setAdmin(Korisnik admin) {
		this.admin = admin;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
	
	
	
}