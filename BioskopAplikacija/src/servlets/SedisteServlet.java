package servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SedisteDao;
import model.Sediste;

/**
 * Servlet implementation class SedisteServlet
 */
public class SedisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SedisteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int projekcijaId = Integer.parseInt(request.getParameter("projekcijaId"));
		List<Sediste> slobodnaSedista = SedisteDao.getSlobodnaSedistaZaProjekciju(projekcijaId);
		
		Map<String, Object> data = new LinkedHashMap<>();
		
		data.put("slobodnaSedista", slobodnaSedista);
		request.setAttribute("data", data);
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
