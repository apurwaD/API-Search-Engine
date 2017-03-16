import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbCon.MongoDBConnection;

/**
 * Servlet implementation class SearchApiMashup
 */
@WebServlet("/SearchApiMashup")
public class SearchApiMashup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchApiMashup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		try {
			String typeOfSearch = request.getParameter("typeOfSearch");
			System.out.println(typeOfSearch);
			String criteria = request.getParameter("criteria");
			String searchvalue = request.getParameter("searchvalue");
			if (criteria != null) {
				System.out.println("criteria" + criteria);
				if (criteria.equals("updatedyear")) {
					response.getWriter().println("search by year");
					response.getWriter().println("search value:" + searchvalue);
					MongoDBConnection dbc = new MongoDBConnection();
					HashMap<String, String> listApi = dbc.searchbyUpdatedYear(
							searchvalue, typeOfSearch);
					sess.setAttribute("apis", listApi);
					sess.setAttribute("typeApiMashup", typeOfSearch);
					response.sendRedirect("resultList.jsp");

				}
				if (criteria.equals("tags")) {

					response.getWriter().println("search by tags");
					response.getWriter().println("search value:" + searchvalue);
					MongoDBConnection dbc = new MongoDBConnection();
					HashMap<String, String> listApi = dbc.searchbyTags(
							searchvalue, typeOfSearch);
					sess.setAttribute("apis", listApi);
					response.sendRedirect("resultList.jsp");

				}
				if (criteria.equals("protocols")) {

					response.getWriter().println("search by protocols");
					response.getWriter().println("search value:" + searchvalue);
					MongoDBConnection dbc = new MongoDBConnection();
					HashMap<String, String> listApi = dbc.searchbyProtocol(
							searchvalue, typeOfSearch);
					sess.setAttribute("apis", listApi);
					response.sendRedirect("resultList.jsp");

				}

				if (criteria.equals("category")) {

					response.getWriter().println("search by category");
					response.getWriter().println("search value:" + searchvalue);
					MongoDBConnection dbc = new MongoDBConnection();
					HashMap<String, String> listApi = dbc.searchbyCategory(
							searchvalue, typeOfSearch);
					sess.setAttribute("apis", listApi);
					response.sendRedirect("resultList.jsp");
				}
				if (criteria.equals("rating")) {
					response.getWriter().println("search by rating" + criteria);
					response.getWriter().println("search value:" + searchvalue);
					String compare = request.getParameter("ratingvalue");
					System.out.println(compare);
					MongoDBConnection dbc = new MongoDBConnection();
					HashMap<String, String> listApi = null;
					listApi = dbc.searchbyRating(searchvalue, typeOfSearch,
							compare);
					sess.setAttribute("apis", listApi);
					response.sendRedirect("resultList.jsp");
				}

				if (criteria.equalsIgnoreCase("usedapis")) {

					response.getWriter().println(
							"search by usedapis" + criteria);
					response.getWriter().println("search value:" + searchvalue);
					MongoDBConnection dbc = new MongoDBConnection();
					HashMap<String, String> listApi = null;
					listApi = dbc.searchbyUsedApis(searchvalue, typeOfSearch);
					sess.setAttribute("apis", listApi);
					response.sendRedirect("resultList.jsp");
				}
			} else {
				// if keyword search
				System.out.println(criteria);
				response.getWriter().println("search by rating" + criteria);
				response.getWriter().println("search value:" + searchvalue);
				MongoDBConnection dbc = new MongoDBConnection();
				HashMap<String, String> listApi = null;
				listApi = dbc.searchKeyword(searchvalue, typeOfSearch);
				sess.setAttribute("apis", listApi);
				response.sendRedirect("resultList.jsp");
			}
		} catch (NullPointerException ex) {
			
			response.getWriter().println(
					"You have not entered any value in the search box");
		}catch(Exception ex){
			response.getWriter().println(
					"There was some problem");
		}

	}
}
