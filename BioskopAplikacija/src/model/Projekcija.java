package model;

import java.sql.Time;
import java.util.Date;

public class Projekcija {
	
	public enum TipProjekcijeEnum {D2, D3, D4}
	
	private int id;
	private Film film;
	private Sala sala;
	private TipProjekcijeEnum tipProjekcije;
	private Date datumPrikazivanja;
	private String vremePrikazivanja;
	private int cenaKarte;
	private Korisnik admin;
	private boolean obrisan;
	
	public Projekcija(int id, Film film, Sala sala, TipProjekcijeEnum tipProjekcije, Date datumPrikazivanja,
			String vremePrikazivanja, int cenaKarte, Korisnik admin, boolean obrisan) {
		super();
		this.id = id;
		this.film = film;
		this.sala = sala;
		this.tipProjekcije = tipProjekcije;
		this.datumPrikazivanja = datumPrikazivanja;
		this.vremePrikazivanja = vremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
		this.obrisan = obrisan;
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

	public TipProjekcijeEnum getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(TipProjekcijeEnum tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public Date getDatumPrikazivanja() {
		return datumPrikazivanja;
	}

	public void setDatumPrikazivanja(Date datumPrikazivanja) {
		this.datumPrikazivanja = datumPrikazivanja;
	}

	public String getVremePrikazivanja() {
		return vremePrikazivanja;
	}

	public void setVremePrikazivanja(String vremePrikazivanja) {
		this.vremePrikazivanja = vremePrikazivanja;
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