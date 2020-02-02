package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDao;
import dao.KorisnikDao;
import model.Film;
import model.Korisnik;

/**
 * Servlet implementation class KorisnikServlet
 */
public class KorisnikServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KorisnikServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ulogovaniKorisnikKorIme = (String) request.getSession().getAttribute("UlogovaniKorisnik");

		if(ulogovaniKorisnikKorIme == null) {
			request.getRequestDispatcher("./OdjavaServlet").forward(request, response);
			
			return;
		}
		else {
			try {
				Korisnik ulogovaniKorisnik = KorisnikDao.getOne(ulogovaniKorisnikKorIme);

				if(ulogovaniKorisnik == null) {
					request.getRequestDispatcher("./OdjavaServlet").forward(request, response);
					return;
				}
				else {
					
					Map<String, Object> data = new LinkedHashMap<>();
					String action = request.getParameter("action");
					if(action.equals("ulogovaniKorisnik")) {
			
							data.put("ulogovaniKorisnikUloga", ulogovaniKorisnik.getUloga());
							data.put("korisnickoIme", ulogovaniKorisnik.getKorisnickoIme());
							request.setAttribute("data", data);
							request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			
						}
					if(action.equals("getOne")) {
						String korIme = request.getParameter("korIme");
						Korisnik korisnik = KorisnikDao.getOne(korIme);
						if(korisnik != null) {
							
							java.util.Date datumRegistracije =  korisnik.getDatumRegistracije();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
							String datumReg = sdf.format(datumRegistracije);
							
							data.put("datumRegistracije", datumReg);
							data.put("korisnik", korisnik);
							request.setAttribute("data", data);
							request.getRequestDispatcher("./SuccessServlet").forward(request, response);
						}
						else {
							request.getRequestDispatcher("./FailureServlet").forward(request, response);
						}
						
						
					}
					if(action.equals("getAll")) {
						List<Korisnik> korisnici = KorisnikDao.getAll();
						
						ArrayList<Korisnik> neobrisaniKorisnici = new ArrayList<Korisnik>();
						
						for (Korisnik korisnik : korisnici) {
							if(!korisnik.isObrisan()) {
								neobrisaniKorisnici.add(korisnik);
								
							}
						}
						data.put("korisnici", neobrisaniKorisnici);
						request.setAttribute("data", data);
						request.getRequestDispatcher("./SuccessServlet").forward(request, response);
					}
					
					
				}
			
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("edit")) {
			String korisnickoIme = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			String admin = request.getParameter("admin");
			String uloga;
			
			Korisnik izmenjenKorisnik;
			
			if(admin.equals("true")) {
				uloga = "ADMIN";
			}
			else {
				uloga = "KORISNIK";
			}
			System.out.println("ULOGA JE : " + uloga);
			
			if(lozinka.equals("")) {
				String staraLozinka = KorisnikDao.getOne(korisnickoIme).getLozinka();
				izmenjenKorisnik = new Korisnik(korisnickoIme, staraLozinka, null, uloga, false);
				
			}
			else {
				izmenjenKorisnik = new Korisnik(korisnickoIme, lozinka, null, uloga, false);
			}
			
			if(KorisnikDao.update(izmenjenKorisnik)) {
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
			else {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
		}
		
		
		
		if(action.equals("delete")) {
			String korisnickoIme = request.getParameter("korisnickoIme");
			if(KorisnikDao.delete(korisnickoIme)) {
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
			else {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
		}
	}

}
