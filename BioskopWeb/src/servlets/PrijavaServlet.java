package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
     * @see HttpServlet#HttpServlet()
     */
    public PrijavaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String status = "sucess";
		System.out.println(password + userName);
		
		try {
			
			Korisnik korisnik = KorisnikDao.getOne(userName);
			if (korisnik == null) {
				throw new Exception("Pogresno korisnicko ime!");
			}
			else {
				if(korisnik.getLozinka() != password) {
					throw new Exception("Pogresna sifra!");
				}
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("ulogovaniKorisnik", korisnik);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			status = "failure";
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("status", status);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	
	
	}

}
