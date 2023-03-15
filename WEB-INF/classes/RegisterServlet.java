 
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;            // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/user-register")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class RegisterServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query
   
      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/popmart?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "root", "Darryl1783");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         PreparedStatement st = conn.prepareStatement("insert into users values(?, ?, ?)");
         st.setString(1, request.getParameter("username"));
         st.setString(2, request.getParameter("email"));
         st.setString(3, request.getParameter("password"));

         st.executeUpdate();
         st.close();

         response.sendRedirect("Login.html"); //need to find popup that says successful / fail... altho auto rej cuz primary key

         conn.close();

         


      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
   }
}