import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "GetSecurity", value = "/GetSecurity")
public class GetSecurity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("Email");
        String url = "jdbc:mysql://selldb.cqt5tgj7qyws.us-east-2.rds.amazonaws.com:3306/simpledb";
        String username = "simpledb";
        String password = "sell1234";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                Statement s1 = con.createStatement();
                String check = "SELECT SecurityQuestion1, SecurityQuestion2, Answer1, Answer2 FROM Sellers WHERE Email ='" + email + "'";
                System.out.println(check);
                ResultSet result = s1.executeQuery(check);
                if (result.next()) {
                    System.out.println("Seller found");
                    //creating new Seller object from SQL response
                    Security info = new Security(result.getString("SecurityQuestion1"),
                            result.getString("Answer1"), result.getString("SecurityQuestion2"), result.getString("Answer2"));
                    Gson gson = new Gson();
                    String jsonInfo = gson.toJson(info);
                    response.getWriter().write(jsonInfo);
                } else {
                    System.out.println("Wrong Details");
                    response.getWriter().write("Wrong Details");
                }
                //If exists then get the whole object return sellerID for now
            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");
            e.printStackTrace();
        }


    }
}
