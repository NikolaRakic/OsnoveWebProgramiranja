package servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KartaDao;
import model.Karta;

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
		
		if(action.equals("getAll")) {
			int idProjekcije = Integer.parseInt(request.getParameter("idProjekcije"));
			
			
			List<Karta> karte = KartaDao.getAll(idProjekcije);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("karte", karte);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}
		
		
		if(action.equals("getOne")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Karta karta = KartaDao.getOne(id);
			System.out.println(karta.toString());
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("karta", karta);
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			
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
