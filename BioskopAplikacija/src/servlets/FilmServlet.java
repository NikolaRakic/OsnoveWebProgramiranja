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
import dao.ProjekcijaDao;
import model.Film;
import model.Projekcija;

/**
 * Servlet implementation class FilmServlet
 */
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("getAll")) {
			List<Film> filmovi = FilmDao.getAll();
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filmovi", filmovi);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}
		if(action.equals("getOne")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Film film = FilmDao.getOne(id);
				if(film != null) {
					Map<String, Object> data = new LinkedHashMap<>();
					data.put("film", film);
					request.setAttribute("data", data);
					request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				}
				else {
					request.getRequestDispatcher("./FailureServlet").forward(request, response);
				}
			}catch (Exception ex) {
				ex.printStackTrace();
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}	
			
		}
		
		
		if(action.equals("sort")) {
			
			String vrednostSortiranja = request.getParameter("vrednostSortiranja");
			String nacinSortiranja = request.getParameter("nacinSortiranja");
			
			System.out.println("vrednost " + vrednostSortiranja);
			System.out.println("nacin " + nacinSortiranja);
			
			 List<Film> filmovi  = FilmDao.sort(vrednostSortiranja, nacinSortiranja);
		
			
			
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filmovi", filmovi);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			
		}
		
		
		if(action.equals("pretraga")) {
			String pretragaInput = request.getParameter("pretragaInput");
			
			List<Film> filmovi = new ArrayList<Film>();
			
			
			filmovi = FilmDao.pretraga(pretragaInput);
				
			
			
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filmovi", filmovi);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			
			
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String ulogovaniKorisnikKorIme = (String) request.getSession().getAttribute("UlogovaniKorisnik");
		String ulogovaniKorisnikUloga = (String) request.getSession().getAttribute("UlogovaniKorisnikUloga");
		
		Film filmIzmenjen = null;
		if(action.equals("edit")) {
			if(ulogovaniKorisnikUloga.equals("ADMIN")) {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					String naziv = request.getParameter("naziv");
					String reziser = request.getParameter("reziser");
					String glumci = request.getParameter("glumci");
					String zanr = request.getParameter("zanr");
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					String distributer = request.getParameter("distributer");
					String zemlja = request.getParameter("zemlja");
					int godina = Integer.parseInt(request.getParameter("godina"));
					String opis = request.getParameter("opis");
					
				
					
					filmIzmenjen = new Film(id, naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godina, opis, false);
				}catch (Exception ex) {
					ex.printStackTrace();
					request.getRequestDispatcher("./FailureServlet").forward(request, response);
				}
				
				if(FilmDao.update(filmIzmenjen)) {
					request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				}
			}
									
		}
		
		if(action.equals("delete")) {
			if(ulogovaniKorisnikUloga.equals("ADMIN")) {
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					if(FilmDao.delete(id)) {
						request.getRequestDispatcher("./SuccessServlet").forward(request, response);
					}
				}catch (Exception ex) {
					ex.printStackTrace();
					request.getRequestDispatcher("./FailureServlet").forward(request, response);
				}
			}
		}
		
		
		
		if(action.equals("add")) {
			if(ulogovaniKorisnikUloga.equals("ADMIN")) {
				String nazivFilma = request.getParameter("nazivFilma");
				String reziser = request.getParameter("reziser");
				String glumci = request.getParameter("glumci");
				String zanr = request.getParameter("zanr");
				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				String distributer = request.getParameter("distributer");
				String zemlja = request.getParameter("zemlja");
				int godina = Integer.parseInt(request.getParameter("godina"));
				String opis = request.getParameter("opis");
				
				Film film = new Film(0, nazivFilma, reziser, glumci, zanr, trajanje, distributer, zemlja, godina, opis, false);
				try {
					if(FilmDao.create(film)) {
						request.getRequestDispatcher("./SuccessServlet").forward(request, response);
					}
				}catch (Exception ex) {
					ex.printStackTrace();
					request.getRequestDispatcher("./FailureServlet").forward(request, response);
				}
			}
			
		}
		
	}

}
