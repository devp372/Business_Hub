import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ModifySecurityQuestions", value = "/ModifySecurityQuestions")
public class ModifySecurityQuestions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sellerID = request.getParameter("SellerID");
        String secQ1 = request.getParameter("SecurityQuestion1");
        String secQ2 = request.getParameter("SecurityQuestion2");
        String ans1 = request.getParameter("Answer1");
        String ans2 = request.getParameter("Answer2");
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
                String check = "UPDATE Sellers SET SecurityQuestion1 = '" + secQ1 + "',Answer1 = '" + ans1 + "'," +
                        "SecurityQuestion2 = '" + secQ2 + "',Answer2 = '" + ans2 + "' WHERE SellerID = '" + sellerID + "'";
                System.out.println(check);
                s1.executeUpdate(check);
                System.out.println("Security Questions updated");
                response.getWriter().write("Security Questions updated");



            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");
            e.printStackTrace();
        }




    }
}
