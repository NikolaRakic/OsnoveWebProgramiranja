package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KorisnikDao;
import enums.Uloga;
import model.Korisnik;

/**
 * Servlet implementation class RegistracijaServlet
 */
public class RegistracijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistracijaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korIme = request.getParameter("userName");
		String lozinka = request.getParameter("password");
		
		
		try {
			if(korIme.equals("") || lozinka.equals("")){
				throw new Exception("Popunite sva polja!");
			}
			if (KorisnikDao.getOne(korIme) != null) {
				throw new Exception("Korisnicko ime vec postoji!");
			}
			

			Date datumRegistracije = new Date(0);
			Korisnik noviKorisnik = new Korisnik(korIme, lozinka, datumRegistracije, Uloga.KORISNIK.toString(), false);
			KorisnikDao.add(noviKorisnik);
			request.getSession().setAttribute("UlogovaniKorisnik", noviKorisnik.getKorisnickoIme());
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);				
			System.out.println("Registrovani korisnik: " + noviKorisnik.getKorisnickoIme());
			
			
		}catch (Exception ex) {
			String message = ex.getMessage();
			
			if (message == null) {
				message = "Nepredvidjena greska!";
				ex.printStackTrace();
			}
			
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("message", message);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}
}
