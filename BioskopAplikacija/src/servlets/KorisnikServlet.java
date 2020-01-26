package servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KorisnikDao;
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
		try {
			Korisnik ulogovaniKorisnik = KorisnikDao.getOne(ulogovaniKorisnikKorIme);
			
			if(ulogovaniKorisnik == null) {
				request.getRequestDispatcher("./OdjavaServlet").forward(request, response);
				return;
			}
			Map<String, Object> data = new LinkedHashMap<>();
			
			String action = request.getParameter("action");
			switch (action) {
				case "ulogovaniKorisnikUloga": {
					data.put("ulogovaniKorisnikUloga", ulogovaniKorisnik.getUloga());
					break;
				}
			}
	
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
