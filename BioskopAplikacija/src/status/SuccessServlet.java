package status;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class SuccessServlet
 */
public class SuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> data = (Map<String, Object>) request.getAttribute("data");
		if (data == null) {
			 data = new LinkedHashMap<>();
		}
		
		data.put("status", "success");

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
