package model;

public class Sala {

	private int id;
	private String naziv;
	private TipProjekcije tipProjekcije;
	
	
	public Sala(int id, String naziv, TipProjekcije tipProjekcije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tipProjekcije = tipProjekcije;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public TipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}


	public void setTipProjekcije(TipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	
	
	
	
}
