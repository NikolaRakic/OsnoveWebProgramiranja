package servlets;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjekcijaDao;
import dao.SedisteDao;
import model.Projekcija;

/**
 * Servlet implementation class ProjekcijaServlet
 */
public class ProjekcijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjekcijaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String status = request.getParameter("status");
		
			if(status.equals("getAll")) {
				List<Projekcija> projekcije = ProjekcijaDao.getAll();
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("projekcije", projekcije);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}
			
			if(status.equals("getOne")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				try {
					Projekcija projekcija = ProjekcijaDao.getOne(id);
					if(projekcija != null) {
						java.util.Date datumPrikazivanja =  projekcija.getDatumPrikazivanja();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
						String datumPrikazivanja1 = sdf.format(datumPrikazivanja);
						
						int brSlobodnihSedista = SedisteDao.brojSlobodnihSedista(projekcija.getId());
						Map<String, Object> data = new LinkedHashMap<>();
						data.put("datumPrikazivanja", datumPrikazivanja1);
						data.put("projekcija", projekcija);
						data.put("brSlobodnihSedista", brSlobodnihSedista);
						request.setAttribute("data", data);
						request.getRequestDispatcher("./SuccessServlet").forward(request, response);
					}
					else{
						request.getRequestDispatcher("./FailureServlet").forward(request, response);
					}
							
				}catch (Exception ex) {
					ex.printStackTrace();
					
				}
				
				
			}
			
			
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = request.getParameter("status");
		
		if(status.equals("add")) {
			
			int filmId = Integer.parseInt(request.getParameter("filmId"));		
			String salaNaziv = request.getParameter("salaNaziv");			
			String tipProjekcije = request.getParameter("tipProjekcije");			
			String datumPrikazivanja = request.getParameter("datumPrikazivanja");			
			int cenaKarte = Integer.parseInt(request.getParameter("cenaKarte"));			
			String admin = (String) request.getSession().getAttribute("UlogovaniKorisnik");
			
			
			}
		
		if(status.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				ProjekcijaDao.delete(id);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			}catch (Exception e) {
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
		}
	}

}
