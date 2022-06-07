package sessions;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */

//Write a servlet that displays the values of the firstName, lastName, and emailAddress
//request parameters. But, remember what users told you in the past, and use the old
//values if the current values are missing. So, if a parameter is missing and the client is
//a first-time visitor, have the servlet list “Unknown” for the missing values. If a
//parameter is missing and the client is a repeat visitor, have the servlet use previouslyentered values for the missing values. This should definitely be easier than a version
//that uses cookies explicitly.


@WebServlet("/remember-user-info")
public class Question2 extends HttpServlet{
 	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
 		 HttpSession session =request.getSession();
 		 String firstName = (String) session.getAttribute("firstName"), lastName = (String) session.getAttribute("lastName"), emailAddress = (String) session.getAttribute("emailAddress");

 		 if(firstName==null||firstName.equals("")) {
			 firstName="Undefined";
		 }
		 if(lastName==null||lastName.equals("")) {
			 lastName="Undefined";
		 }
		 if(emailAddress==null||emailAddress.equals("")) {
			 emailAddress="Undefined";
			 }
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Enter your credentials";
		String docType = "<!DOCTYPE HTML PUBLIC\" -//W3C//DTD HTML 4.0" 
		+ "Transitional//EN\">\n";
        String theForm = docType + "<HTML>\n" + "<HEAD><meta charset=\"UTF-8\"><TITLE>" + title + "</TITLE></HEAD>\n" + "<BODY>\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "<FORM ACTION=\"\" METHOD=\"post\">\n" + 
                "First Name:" + "<INPUT TYPE=\"TEXT\" NAME=\"firstName\" value =\"" + firstName + "\"><BR>\n" +
                "Last name:" + "<INPUT TYPE=\"TEXT\" NAME=\"lastName\" value =\"" + lastName + "\"><BR>\n" +
                "Email Address:" + "<INPUT TYPE=\"TEXT\" NAME=\"emailAddress\" value =\"" + emailAddress + "\"><BR>\n" +
                "<CENTER><INPUT TYPE=\"SUBMIT\" value=\"Submit\"></CENTER>\n" + 
                "</FORM>\n" +
                "</BODY></HTML>";
        
        out.print(theForm);
     }
	

	 
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String firstName = request.getParameter("firstName"), lastName = request.getParameter("lastName"), emailAddress = request.getParameter("emailAddress");
		 
		 HttpSession session =request.getSession();
		 
		 if(firstName!=null&&!firstName.equals("")) {
			 session.setAttribute("firstName", firstName);
		 }
		 if(lastName!=null&&!lastName.equals("")) {
			 session.setAttribute("lastName", lastName);
		 }
		 if(emailAddress!=null&&!emailAddress.equals("")) {
			 session.setAttribute("emailAddress", emailAddress);
		 }
		 
		 response.setIntHeader("refresh", 0);
	 }

}
