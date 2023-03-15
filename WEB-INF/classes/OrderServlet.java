// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/check-out")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class OrderServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();


      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/popmart?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "root", "Darryl1783");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
            //String toy_id = request.getParameter("toy_id");
            //String user_id = request.getParameter("user_id");
            //String cart_id = request.getParameter("cart_id");

            StringBuilder sqlStr = new StringBuilder();
            sqlStr.append("SELECT * FROM cart");
            //sqlStr.append("STRCMP(cart.cart_id, '")
              //    .append(cart_id).append("') = 0 ");
       
            System.out.println(sqlStr);
   
            ResultSet rs = stmt.executeQuery(sqlStr.toString()); 
   
            while (rs.next()){
               PreparedStatement st = conn.prepareStatement("insert into order_records (toy_id, user_id, qty, order_date, order_id) VALUES (?,?,?,?,?)");
               st.setString(1, rs.getString("toy_id"));
               st.setString(2, rs.getString("user_id"));
               st.setInt(3, 1);
               st.setDate(4, null);
               st.setString(5, null);
   
               st.executeUpdate();
               st.close();
            }

           // Print an HTML page as the output of the query
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>Success</title></head>");
      out.println("<link rel=\"icon\" type=\"image/png\" href=\"./Images/popmart_logo.png\">");

      out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
      out.println("<link href=\"https://getbootstrap.com/docs/5.3/assets/css/docs.css\" rel=\"stylesheet\">");

      out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
      out.println("<div class='container'>");
      out.println("<a class='navbar-brand' href='ProductList.jsp'></a>");
      out.println("<img width=\"15%\"src=\"Images/popmart_logo.png\"/>");
      out.println("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"");
      out.println("data-target=\"#navbarSupportedContent\"");
      out.println("aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"");
      out.println("aria-label=\"Toggle navigation\">");
      out.println("<span class=\"navbar-toggler-icon\"></span>");
      out.println("</button>");
      out.println("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
      out.println("<ul class=\"navbar-nav ml-auto\">");
      out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"ProductList.jsp\">Home</a></li>");
      out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"Cart.jsp\">Cart</a></li>");
      out.println("<li class=\"nav-item\"><a class=\"nav-link\" onclick=\"showAlert()\" href=\"Login.html\">Logout</a></li>");
      out.println("</ul>");
      out.println("</div>");
      out.println("</div>");
      out.println("</nav>");

      out.println("<body1>");
      //out.println(" <center><img src='Images/green-tick.gif' style='width:200px; height:200px;margin-top: 50px;'></center>");
      //out.println( "<div class='img-container'style='display:flex;''>");
      //out.println("<img width=\"100%\" height=\"100%\" src = 'Images/dimoo.gif'>");
      //out.println( "</div>");
      //out.println( "<div class='image' >");
      //out.println(" <img src='Images/dimoo.png' style='width:280px; height:500px;'>");
      //out.println( "</div>");
    
      //out.println("<div class=\"container\">");
     // out.println("<div class=\"row\">");
      //out.println("<div class=\"col-xs-12 col-sm-6 col-md-8 col-centered\">");
    //  out.println("<div class=\"maintxt\">");
     // out.println( " <div class='overlay-text' style='padding-left: 80px; position: relative;'>");
      out.println("<img width=\"100%\" height=\"100%\" src = 'Images/paymentvid.gif'>");
      out.println("<div class=\"card\" style=\"position: absolute; width: 500px; height: 300px; border-radius: 20px; box-shadow:0 0 30px rgba(0, 0, 0, .5); background: transparent; backdrop-filter: blur(10px); top: 300px; left: 325px;\">");
      out.println("<div class=\"text-content\" style=\"padding-left: 100px; padding: 10px; text-align: center;\">");
      out.println("<h1 style=\"color:DarkGreen; font-size:5vw; text-align:center; font-family:Courier;\">Order Successful</h1>");
      out.println("<p style=\"font-size:2vw; text-align:center; font-family:Helvetica;\">Your order is being processed,<br>Dimoo will be with you shortly!</p>");
      out.println("</div>");
      out.println("</div>");
      out.println("<div class=\"audio-box\"> <audio id=\"myAudio\"  src=\"Images/paymentvid.mp3\" loop >  </div> ");
     // out.println( " <h2 class='carousel-caption' style='color:MediumSeaGreen; font-size:10vw; text-align:center; font-family:verdana; position: absolute; '>Payment Successful</h2> ");
      //out.println( " <p  class='carousel-caption' style='font-size:3vw; text-align:center; font-family:verdana;''>Your order is being processed,<br>Dimoo will be with you shortly!</p>");
     // out.println( "</div>");
      //out.println( "</div>");
      //out.println( "</div>");
      //out.println( "</div>");
      //out.println( "</div>");
      out.println("</body1>");
      out.println("<script>function showAlert() {var Success=\"You have logged out!\"; alert(Success); }</script>");
      out.println("<script>navigator.mediaDevices.getUserMedia({ audio: true }).then(function (stream) {var x = document.getElementById('myAudio');  x.play(); stream.getTracks().forEach(function (track) { track.stop(); }); }); </script> ");
         



      PreparedStatement st1 = conn.prepareStatement("DELETE FROM cart");
      st1.executeUpdate();
      st1.close();


      } catch(Exception ex) {
        out.println("<p>Error: " + ex.getMessage() + "</p>");
        out.println("<p>Check Tomcat console for details.</p>");
        ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
    }
}