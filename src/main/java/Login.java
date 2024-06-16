import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import java.sql.*;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailID = request.getParameter("EmailID");
        String Password = request.getParameter("Password");
        String url = "jdbc:mysql://selldb.cqt5tgj7qyws.us-east-2.rds.amazonaws.com:3306/simpledb";
        String username = "simpledb";
        String password = "sell1234";
        String hashedPass = SHA256Hash.hash(Password);
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                Statement s1 = con.createStatement();
                String check = "SELECT * FROM Sellers WHERE Email = '" + emailID + "' AND Pswd ='" + hashedPass + "'";
                ResultSet result = s1.executeQuery(check);
                if (result.next()) {

                    System.out.println("Seller found");
                    //creating new Seller object from SQL response
                    Seller currentSeller = new Seller(result.getInt("SellerID"),
                            result.getString("FirstName"), result.getString("LastName"),
                            result.getString("Email"), result.getString("MobileNum"),
                            result.getString("StoreName"), result.getString("Description"));
                    Gson gson = new Gson();
                    String jsonSeller = gson.toJson(currentSeller);
                    response.getWriter().write(jsonSeller);
                } else {
                    System.out.println("Wrong Details");
                    response.getWriter().write("Wrong Details");
                }
                //If exists then get the whole object return sellerID for now
            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");
            System.out.println(Password);
            System.out.println(emailID);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
