package model;

public class Sediste {
	private int id;
	private int redniBroj;
	private int projekcijaId;
	private boolean zauzeto;
	
	

	public Sediste(int id, int redniBroj, int projekcijaId, boolean zauzeto) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.projekcijaId = projekcijaId;
		this.zauzeto = zauzeto;
	}
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public int getprojekcijaId() {
		return projekcijaId;
	}

	public void setprojekcijaId(int projekcijaId) {
		this.projekcijaId = projekcijaId;
	}

	public boolean isZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	
	
	
	
	

}
