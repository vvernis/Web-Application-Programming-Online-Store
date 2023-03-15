
import java.io.*;
import java.sql.*;
import java.util.*;

import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;            // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/user-login")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class LoginServlet extends HttpServlet {

   // The doPost() runs once per HTTP GET request to this servlet.
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
         //PreparedStatement st = conn.prepareStatement("insert into users values(?, ?)");
         //st.setString(1, request.getParameter("email"));
         // st.setString(2, request.getParameter("password"));

         // st.executeUpdate();
         // st.close();
         // conn.close();
         // Retrieve and process request parameters: username and password
         String userName = request.getParameter("email");
         String password = request.getParameter("password");
         String username = request.getParameter("username");

         StringBuilder sqlStr = new StringBuilder();
         sqlStr.append("SELECT * FROM users WHERE ");
         sqlStr.append("STRCMP(users.email, '")
               .append(userName).append("') = 0 ");
         sqlStr.append("AND STRCMP(users.password, '")
               .append(password).append("') = 0 ");

         Boolean user=false;
         ResultSet rs = stmt.executeQuery(sqlStr.toString()); 
         while (rs.next()){
               user = true;
               Statement stmt1 = conn.createStatement();
               // request.getSession().setAttribute("msg", "Success");
               //response.sendRedirect("Product.html");
               StringBuilder sqlStr_item = new StringBuilder();
               sqlStr_item.append("SELECT * FROM stock");
               ResultSet res = stmt1.executeQuery(sqlStr_item.toString()); 
               StringBuilder htmlStr = new StringBuilder();
               while(res.next()){
                  htmlStr.append("<div class=\"col-md-3 my-3\">");
                  htmlStr.append("<form action='add-to-cart' method='Post'>");
                  htmlStr.append("<div class=\"card w-100\">");
                  htmlStr.append("<img class=\"card-img-top\" style=\"width: 100%; height: 100%; padding-top: 30px\" src=\"Images/StockImg/"+res.getString("toy_img")+"\"alt=\"Card image cap\">");
                  htmlStr.append("<div class=\"card-body\">");
                  htmlStr.append("<h5 class=\"card-title\">"+res.getString("toy_name")+"</h5>");
                  htmlStr.append("<h6 class=\"price\">Price: "+res.getDouble("price")+"</h6>");
                  htmlStr.append("<div class=\"mt-3 d-flex justify-content-between\">");
                  htmlStr.append("<input type='hidden' class='toy_id' name='toy_id' value='" +res.getString("toy_id")+"'>");
                  htmlStr.append("<button type=\"submit\" class=\"btn btn-outline-dark\">Add to Cart</button>");
                  htmlStr.append("</div>");
                  htmlStr.append("</div>");
                  htmlStr.append("</div>");
                  htmlStr.append("</form>");
                  htmlStr.append("</div>");


               }
               if(rs.getString("username")!=""){
                  request.setAttribute("name", rs.getString("username"));
               }else if(userName != ""){
                  request.setAttribute("name", userName);
               }
               request.setAttribute("items", htmlStr);
               request.getRequestDispatcher("ProductList.jsp").forward(request, response);
            }
             // Check if username/password are correct
             if (!rs.next()) {  // empty ResultSet
               user = false;
               response.sendRedirect("Login.html");
            }



      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
   }
}