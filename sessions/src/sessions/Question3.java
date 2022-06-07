package sessions;


import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//Make a servlet that prints a list of the URLs of the pages that the current user has
//used to link to it (within the current browsing session). That is, if one user has followed hypertext links from three different pages to the servlet, the servlet should
//show the user the URLs of those three pages. Test out your servlet by making a couple of different static Web pages that link to it. If you feel inspired, modify the basic
//approach from the notes so that you do not store repeated entries; if the same user
//follows a link from page1 to the servlet twice, the servlet should list the URL of
//page1 only once in the list. Start with my ShowItems class, and make a few small
//changes such as getting the data from a request header (which one?) and changing
//doPost to doGet. If you are unfamiliar with the list-related data structures in Java, see
//the notes on the next page regarding the ArrayList class.

/**
 * @author Kristina Korzhenevskaya
 */

@WebServlet("/print-url")
public class Question3 extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
		throws ServletException, IOException {

		doPost(request,response);
	} 
	public void doPost(HttpServletRequest request,
			  HttpServletResponse response)
throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		ArrayList<String> listOfURL =
				(ArrayList)session.getAttribute("previousItems");
		if (listOfURL == null){
			listOfURL = new ArrayList();
			session.setAttribute("previousItems", listOfURL);
		} 
		
		//String newItem = "http://localhost" + request.getRequestURI();
		//String newItem = request.getParameter("url");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Items Purchased";
		
		out.println(ServletUtilities.headWithTitle(title) + 
				"<BODY BGCOLOR=\"FDF5E6\">\n" +
				"<H1>" + title + "</H1>");

		
		synchronized(listOfURL) {
			String newItem = request.getHeader("Referer");
			//System.out.println(newItem);
			if (newItem != null && !(listOfURL.contains(newItem)))
				listOfURL.add(newItem);	
			
			if (listOfURL.size() ==0)
				out.println("<I>No items</I>");
			else {
				
				out.println("<UL>");
				for(int i = 0; i < listOfURL.size(); i++) {
					
						out.println("<LI>" + (String)listOfURL.get(i));
					
				}
				out.println("</UL>");
			}
			
			out.println("</BODY></HTML>");
			
		} 
	}
	
	//add check
}
