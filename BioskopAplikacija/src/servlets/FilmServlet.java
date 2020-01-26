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

import dao.FilmDao;
import model.Film;

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
				System.out.println("ID FILMA JE : " + id);
				Film film = FilmDao.getOne(id);
				System.out.println("naziv filma je : " + film.getNaziv());
				if(film != null) {
					System.out.println("nasao je neki film");
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
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		Film filmIzmenjen = null;
		if(action.equals("edit")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String naziv = request.getParameter("naziv");
				System.out.println(naziv);
				String reziser = request.getParameter("reziser");
				System.out.println(reziser);
				String glumci = request.getParameter("glumci");
				System.out.println(glumci);
				String zanr = request.getParameter("zanr");
				System.out.println(zanr);
				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				System.out.println(trajanje);
				String distributer = request.getParameter("distributer");
				System.out.println(distributer);
				String zemlja = request.getParameter("zemlja");
				System.out.println(zemlja);
				int godina = Integer.parseInt(request.getParameter("godina"));
				System.out.println(godina);
				String opis = request.getParameter("opis");
				System.out.println(opis);
				
			
				
				filmIzmenjen = new Film(id, naziv, reziser, glumci, zanr, trajanje, distributer, zemlja, godina, opis, false);
			}catch (Exception ex) {
				ex.printStackTrace();
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
			
			if(FilmDao.update(filmIzmenjen)) {
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
									
		}
		
		if(action.equals("delete")) {
			
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
		
		
		
		if(action.equals("add")) {
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
