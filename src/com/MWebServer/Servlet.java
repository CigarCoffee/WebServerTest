package com.MWebServer;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Server server;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }
    @Override
    public void init() throws ServletException {
    	server=new Server();
    	super.init();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value =request.getParameter(en);
				res.put(en, value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
		}}}
		String ret=server.HandleMessage(res);
		request.getSession().setAttribute("data", ret);
		switch (res.get("case")) {
		case "login":
		case "post":
		case "edit":
			response.sendRedirect("login.jsp");
			break;
		case "getall":
			response.sendRedirect("list.jsp");
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
