package sessions;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */

//The previous problem tracked the
//referring page and ignored repeated entries. In this problem, you should track a
//request parameter and count the repeats. Make a servlet that keeps track of the number of each item being ordered. For example, if the user orders yacht, car, book,
//yacht, the servlet should show something like this:
//- yacht (2)
//- car (1)
//- book (1)
//To simplify your code, you can just use the item name as sent from the HTML form;
//there is no need to check each name against a table of legal names as in the muchmore-complicated Shopping Cart example in the book.

@WebServlet("/print-items")
public class Question4 extends HttpServlet {
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
		
		String newItem = request.getParameter("item");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Items Purchased";
		
		out.println(ServletUtilities.headWithTitle(title) + 
				"<BODY BGCOLOR=\"FDF5E6\">\n" +
				"<H1>" + title + "</H1>");
		
		
		synchronized(listOfURL) {
			
			if (newItem != null)
				listOfURL.add(newItem);	
			
			if (listOfURL.size() ==0)
				out.println("<I>No items</I>");
		    
			else {
				Set<String> set = new HashSet<String>(listOfURL);
				out.println("<UL>");				   
				    for (String r : set) {
						out.println("<LI>" + r + ": " + Collections.frequency(listOfURL, r));
				    }
				out.println("</UL>");
			}
			out.println("</BODY></HTML>");	
			

		} 
	}
		// method 
}
