package model;

public class Sala {

	private String naziv;
	private String tipProjekcije;
	
	
	public Sala(String naziv, String tipProjekcije) {
		super();
		this.naziv = naziv;
		this.tipProjekcije = tipProjekcije;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getTipProjekcije() {
		return tipProjekcije;
	}


	public void setTipProjekcije(String tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	
	
	
	
}
