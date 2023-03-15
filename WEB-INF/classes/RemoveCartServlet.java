import java.io.*;
import java.lang.System.Logger;
import java.sql.*;
import java.util.*;

import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;            // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/remove-from-cart")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class RemoveCartServlet extends HttpServlet {

   // The doPost() runs once per HTTP GET request to this servlet.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
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
         String cart_id = request.getParameter("cart_id");

         StringBuilder sqlStr = new StringBuilder();
         sqlStr.append("delete FROM cart WHERE ");
         sqlStr.append("STRCMP(cart.cart_id, '")
               .append(cart_id).append("') = 0 ");

         System.out.println(sqlStr);

         stmt.executeUpdate(sqlStr.toString()); 
         stmt.close();
         response.sendRedirect("Cart.jsp");
         
      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }
}
