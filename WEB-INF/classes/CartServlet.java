
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

@WebServlet("/add-to-cart")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class CartServlet extends HttpServlet {

   // The doPost() runs once per HTTP GET request to this servlet.
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
         String[] toy_name = request.getParameterValues("toy_name");
         String toy_id = request.getParameter("toy_id");
         String[] toy_qty = request.getParameterValues("toy_qty");
         //String [] toy_name = request.getAttribute("items");


        /*  if (toy_name == null) {
            out.println(request.getParameter("toy_name"));
            out.println("<h2>No Dimoos selected. Please go back to select Dimoo(s)</h2><body></html>");
            return; // Exit doGet()
         } */
        // execute query to insert data into cart table 
         

         StringBuilder sqlStr = new StringBuilder();
         sqlStr.append("SELECT * FROM stock WHERE ");
         sqlStr.append("STRCMP(stock.toy_id, '")
               .append(toy_id).append("') = 0 ");

         System.out.println(sqlStr);

         ResultSet rs = stmt.executeQuery(sqlStr.toString()); 

         while (rs.next()){
            PreparedStatement st = conn.prepareStatement("insert into cart (cart_id, toy_id, toy_qty, user_id, total_price, cost_per_item, toy_name, series) VALUES (?,?,?,?,?,?,?,?)");
            st.setString(1, null);
            st.setString(2, toy_id);
            st.setInt(3, 1);
            st.setInt(4, 1);
            st.setFloat(5, rs.getFloat("price"));
            st.setFloat(6, rs.getFloat("price"));
            st.setString(7, rs.getString("toy_name"));
            st.setString(8, rs.getString("series"));

            st.executeUpdate();
            st.close();
         }

         Statement stmt1 = conn.createStatement();
         StringBuilder sqlStr_item = new StringBuilder();
         sqlStr_item.append("SELECT * FROM cart");
         ResultSet res = stmt1.executeQuery(sqlStr_item.toString()); 
         StringBuilder htmlStr = new StringBuilder();
         while(res.next()){
            htmlStr.append("<tr>");
            htmlStr.append("<td>"+res.getString("toy_name")+"</td>");
            htmlStr.append("<td>"+res.getString("series")+"</td>");
            htmlStr.append("<td>"+res.getDouble("cost_per_item")+"</td>");
            htmlStr.append("<div class=\"card-body\">");
            htmlStr.append("<td>");
            htmlStr.append("<form action=\"order-now\" method=\"post\" class=\"form-inline\">");
            htmlStr.append("<input type=\"text\" name=\"quantity\" value=\"\" class=\"form-control\">");
            htmlStr.append("</div>");
            htmlStr.append("</form>");
            htmlStr.append("</td>");
            htmlStr.append("<td> <form action='remove-from-cart' method='Get'><a class=\"btn btn-sm btn-danger\">Remove</a></form></td>");
            htmlStr.append("</tr>");
         }
         request.setAttribute("added", htmlStr);
         //request.setAttribute("total", res.getFloat("total_price"));
         request.getRequestDispatcher("Cart.jsp").forward(request, response);
      
					
         

         response.sendRedirect("Cart.jsp");
        } catch(Exception ex) {
            out.println("<p>Error: " + ex.getMessage() + "</p>");
            out.println("<p>Check Tomcat console for details.</p>");
            ex.printStackTrace();
         }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    
      }
   }