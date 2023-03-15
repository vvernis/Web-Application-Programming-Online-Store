

import java.io.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;        // Tomcat 10
import jakarta.servlet.annotation.*;  // Tomcat 10
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;        // Tomcat 9
//import javax.servlet.annotation.*;  // Tomcat 9
 
@WebServlet("/user-logout")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class LogoutServlet extends HttpServlet {

   private String databaseURL, username, password;
 
   @Override
   public void init(ServletConfig config) throws ServletException {
      // Retrieve the database-URL, username, password from webapp init parameters
      super.init(config);
      ServletContext context = config.getServletContext();
      databaseURL = context.getInitParameter("databaseURL");
      username = context.getInitParameter("username");
      password = context.getInitParameter("password");
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
            //code the button that leads to this!!!!!
      // Set the response MIME type of the response message
      response.setContentType("text/html");
      // Allocate a output writer to write the response message into the network socket

      response.sendRedirect("Login.html");
   }
}