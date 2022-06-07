package sessions;

import java.io.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */

//Use session tracking to make a servlet that says “Welcome Aboard” to first-time visitors (within a browsing session) and “Welcome Back” to repeat visitors. If you did
//this same task earlier with the Cookie API, was this servlet harder or easier than the
//equivalent version using cookies explicitly?

@WebServlet("/remember-user")
public class Question1 extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		String heading;
		
		Integer accessCount =
				(Integer)session.getAttribute("accessCount");
		if (accessCount == null) {
			accessCount = new Integer(0);
			
			heading = "Welcome, Newcomer";
			session.setAttribute("accessCount", accessCount +1);
			
		} else {
			
			heading = "Welcome Back";
			session.setAttribute("accessCount", accessCount +1);
			
		} 
		
		PrintWriter out = response.getWriter();
		String title = "Session Tracking Example";
		
		out.println(ServletUtilities.headWithTitle(title) + 
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<CENTER>\n" +
				"<H1>" + heading + "</H1>\n" +
				"<H2>Information on Your Session:</H2>\n" +
				"<H3>Number of visits:</H3>\n" +
				"<H4>"+ accessCount + "</H4>\n" +
				
				"</CENTER></BODY></HTML>");
		
	}

}
