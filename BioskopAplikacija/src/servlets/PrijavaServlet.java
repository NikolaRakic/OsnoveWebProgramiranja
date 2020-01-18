package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.KorisnikDao;
import model.Korisnik;

/**
 * Servlet implementation class PrijavaServlet
 */
public class PrijavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PrijavaServlet() {
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
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
	
		System.out.println(password + " " + userName);
		
		try {
			
			Korisnik korisnik = KorisnikDao.getOne(userName, password);
			
			if (korisnik == null) {
				throw new Exception("Pogresno korisnicko ime ili lozinka!");
				
			}
			else {
				request.getSession().setAttribute("UlogovaniKorisnik", korisnik.getKorisnickoIme());
				
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				}
		
			
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
