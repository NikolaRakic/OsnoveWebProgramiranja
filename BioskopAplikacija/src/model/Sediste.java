package model;

public class Sediste {
	private int id;
	private int redniBroj;
	private Sala sala;
	private boolean zauzeto;
	
	

	public Sediste(int id, int redniBroj, Sala sala, boolean zauzeto) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.sala = sala;
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

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public boolean isZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	
	
	
	
	

}
