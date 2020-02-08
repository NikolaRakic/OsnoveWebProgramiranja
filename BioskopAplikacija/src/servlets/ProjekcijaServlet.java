package servlets;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import constants.Constants;
import dao.FilmDao;
import dao.KorisnikDao;
import dao.ProjekcijaDao;
import dao.SalaDao;
import dao.SedisteDao;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;

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
			String action = request.getParameter("action");
			System.out.println(action);
			
			if(action.equals("getAllForDate")) {
				String datumInput = request.getParameter("datum");
				if(datumInput == null) {
					
					java.util.Date danasnjiDatum = new java.util.Date();
					String danasnjiDatumStr= new SimpleDateFormat("yyyy-MM-dd").format(danasnjiDatum);
					
					List<Projekcija> projekcije = ProjekcijaDao.getAllForDate(danasnjiDatumStr, null, null);
					
					Map<String, Object> data = new LinkedHashMap<>();
					data.put("projekcije", projekcije);
					request.setAttribute("data", data);
					request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				}
				else {
					List<Projekcija> projekcije = ProjekcijaDao.getAllForDate(datumInput, null, null);			
					
					Map<String, Object> data = new LinkedHashMap<>();
					data.put("projekcije", projekcije);
					request.setAttribute("data", data);
					request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				}
				
				
			}
			
			if(action.equals("sort")) {
				
				String vrednostSortiranja = request.getParameter("vrednostSortiranja");
				String nacinSortiranja = request.getParameter("nacinSortiranja");
				String datumInput = request.getParameter("datum");
				
				
				System.out.println("vrednost " + vrednostSortiranja);
				System.out.println("nacin " + nacinSortiranja);
				System.out.println("DATUM JE : " + datumInput);
				List<Projekcija> projekcije = new ArrayList<Projekcija>();
				if(datumInput == null) {
					java.util.Date danasnjiDatum = new java.util.Date();
					String danasnjiDatumStr= new SimpleDateFormat("yyyy-MM-dd").format(danasnjiDatum);
					
					projekcije = ProjekcijaDao.getAllForDate(danasnjiDatumStr, vrednostSortiranja, nacinSortiranja);
				}
				else {
					projekcije = ProjekcijaDao.getAllForDate(datumInput, vrednostSortiranja, nacinSortiranja);
				}
				
				
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("projekcije", projekcije);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				
			}
			
			
			
			
			if(action.equals("pretraga")) {
				String pretragaInput = request.getParameter("pretragaInput");
				String datumInput = request.getParameter("datum");
				List<Projekcija> projekcije = new ArrayList<Projekcija>();
				
				if(datumInput == null) {
					java.util.Date danasnjiDatum = new java.util.Date();
					String danasnjiDatumStr= new SimpleDateFormat("yyyy-MM-dd").format(danasnjiDatum);
					
					projekcije = ProjekcijaDao.pretraga(pretragaInput, danasnjiDatumStr);
					
				}
				else {
					projekcije = ProjekcijaDao.pretraga(pretragaInput, datumInput);
					
				}
				
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("projekcije", projekcije);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				
				
			}
			
			
			
			if(action.equals("getAllForMovie")) {
				int idFilma = Integer.parseInt(request.getParameter("idFilma"));
				System.out.println("ID FILMA JE: " + idFilma);
				
				List<Projekcija> projekcije = ProjekcijaDao.getAllForMovie(idFilma);			
				
				Map<String, Object> data = new LinkedHashMap<>();
				data.put("projekcije", projekcije);
				request.setAttribute("data", data);
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				
			}
			
			
			if(action.equals("getOne")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				try {
					Projekcija projekcija = ProjekcijaDao.getOne(id);
					if(projekcija != null) {
						
						int brSlobodnihSedista = SedisteDao.brojSlobodnihSedista(projekcija.getId());
						Map<String, Object> data = new LinkedHashMap<>();
						data.put("datumPrikazivanja", projekcija.getDatumPrikazivanja());
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
		
		String action = request.getParameter("action");
		
		if(action.equals("add")) {
			
			int filmId = Integer.parseInt(request.getParameter("filmId"));	
			Film film = FilmDao.getOne(filmId);
			String salaNaziv = request.getParameter("salaNaziv");	
			System.out.println("pronadjen salaNaziv -> " +salaNaziv);
			Sala sala = SalaDao.getOne(salaNaziv);
			
			int brojSedista = Integer.parseInt(request.getParameter("brojSedista"));
			
			
			String tipProjekcije = request.getParameter("tipProjekcije");			
			String datumPrikazivanja = request.getParameter("datumPrikazivanja");
			
			java.util.Date datumIvreme = null;
			
			System.out.println("DATUM PRE PARSIRANJE JE :" + datumPrikazivanja);


			try {
				datumIvreme = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datumPrikazivanja);
				System.out.println("DATUM POSLE PARSIRANJA JE : "+ datumIvreme);
			} catch (ParseException ex) {
			
				ex.printStackTrace();
			}
			String dateValidFormat = datumPrikazivanja + ":00";
			int cenaKarte = Integer.parseInt(request.getParameter("cenaKarte"));			
			String adminKorIme = (String) request.getSession().getAttribute("UlogovaniKorisnik");
			Korisnik admin = KorisnikDao.getOne(adminKorIme);
			Projekcija novaProjekcije = new Projekcija(1, film, sala, tipProjekcije, dateValidFormat, cenaKarte, admin, false);
			
			
			
			
			try{
				ProjekcijaDao.create(novaProjekcije);
				int novaProjekcijaId = ProjekcijaDao.getMaxId();
				SedisteDao.dodajSedistaZaProjekciju(novaProjekcijaId, brojSedista);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			}
		
		if(action.equals("delete")) {
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
