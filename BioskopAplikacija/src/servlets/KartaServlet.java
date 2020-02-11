package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KartaDao;
import dao.KorisnikDao;
import dao.ProjekcijaDao;
import dao.SedisteDao;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sediste;

/**
 * Servlet implementation class KartaServlet
 */
public class KartaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KartaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String ulogovaniKorisnikKorIme = (String) request.getSession().getAttribute("UlogovaniKorisnik");
		String ulogovaniKorisnikUloga = (String) request.getSession().getAttribute("UlogovaniKorisnikUloga");
		
		if(action.equals("getAll")) {
			if(ulogovaniKorisnikUloga.equals("ADMIN")) {
				int idProjekcije = Integer.parseInt(request.getParameter("idProjekcije"));
				
				
				List<Karta> karte = KartaDao.getAll(idProjekcije);
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("karte", karte);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
			
		}
		
		
		if(action.equals("getOne")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Karta karta = KartaDao.getOne(id);
			if(ulogovaniKorisnikUloga.equals("ADMIN") || ulogovaniKorisnikKorIme.equals(karta.getKupacKarte())) {
				
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("karta", karta);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
			
			
		}
		
		
		if(action.equals("getKarteZaKorisnika")) {
			String korIme = request.getParameter("korIme");
			if(ulogovaniKorisnikUloga.equals("ADMIN") || ulogovaniKorisnikKorIme.equals(korIme)) {
				ArrayList<Karta> karte = KartaDao.getKarteZaKorisnika(korIme);
				
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("karte", karte);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String ulogovaniKorisnikKorIme = (String) request.getSession().getAttribute("UlogovaniKorisnik");
		String ulogovaniKorisnikUloga = (String) request.getSession().getAttribute("UlogovaniKorisnikUloga");

		if(action.equals("delete")) {
			if(ulogovaniKorisnikUloga.equals("ADMIN")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				if(KartaDao.delete(id)) {
					Karta obrisanaKarta = KartaDao.getOne(id);
					int idSedista = KartaDao.getSedisteIdZaKartu(id);
					SedisteDao.oslobodiSediste(idSedista);
					
					request.getRequestDispatcher("./SuccessServlet").forward(request, response);
					System.out.println("USPEO");
				}else {
					request.getRequestDispatcher("./FailureServlet").forward(request, response);
					System.out.println("NIJE");
				}
			}
		}
		
		if(action.equals("add")) {
				
				int idProjekcije = Integer.parseInt(request.getParameter("idProjekcije"));
				String kupacKarteKorIme = request.getParameter("kupacKarte");
				int sedisteId = Integer.parseInt(request.getParameter("sediste"));
				
				
				if(ulogovaniKorisnikUloga.equals("KORISNIK") && !(sedisteId == 0)) {
					
					Projekcija projekcija = ProjekcijaDao.getOne(idProjekcije);
					Sediste sediste = SedisteDao.getOne(sedisteId);
					Korisnik kupacKarte = KorisnikDao.getOne(kupacKarteKorIme);
					
					if(KartaDao.add(new Karta(0, projekcija, sediste, null, kupacKarte, false))) {
						SedisteDao.zauzmiSediste(idProjekcije, sedisteId);
					}
				}
					
				
			}
		
		
	}

}
