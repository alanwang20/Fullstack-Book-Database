package controllers;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelpers.BookDBHelper;

/**
 * Servlet implementation class ReturnAuthorServlet
 */
@WebServlet("/filterByAuthor")
public class ReturnAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnAuthorServlet() {
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
        // get author string
		String author = request.getParameter("author");
		
		// Create a dbHelper object
        BookDBHelper bdb = new BookDBHelper();
        
        // Do the query
        ResultSet results = bdb.doReturnAuthor(author);
        
        // Get the html table from the dbHelper object
        String table = bdb.getHTMLTable(results);
        
        // pass execution control to read.jsp along with the table
        request.setAttribute("table", table);
        String url = "/read.jsp";
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
