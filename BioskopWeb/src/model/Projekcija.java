package model;

public class Projekcija {
	private int id;
	private FIlm film;
	private Sediste sediste;
	private String vremePrikazivanja;//
	private Korisnik korisnik;
	
	public Projekcija(int id, FIlm film, Sediste sediste, String vremePrikazivanja, Korisnik korisnik) {
		super();
		this.id = id;
		this.film = film;
		this.sediste = sediste;
		this.vremePrikazivanja = vremePrikazivanja;
		this.korisnik = korisnik;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FIlm getFilm() {
		return film;
	}

	public void setFilm(FIlm film) {
		this.film = film;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public String getvremePrikazivanja() {
		return vremePrikazivanja;
	}

	public void setvremePrikazivanja(String vremePrikazivanja) {
		this.vremePrikazivanja = vremePrikazivanja;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	

}
