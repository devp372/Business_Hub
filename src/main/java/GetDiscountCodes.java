import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "GetDiscountCodes")
public class GetDiscountCodes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sellerID = Integer.parseInt(request.getParameter("SellerID"));
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
                String check = "SELECT * FROM Discount WHERE SellerID = '" + sellerID + "'";
                ResultSet result = s1.executeQuery(check);
                int exec = 0;
                ArrayList<Discount> discounts = new ArrayList<Discount>();
                while (result.next()) {
                    exec = 1;
                    Discount discount = new Discount(result.getString("DiscountCode"),
                            result.getInt("DiscountID"), result.getBoolean("FlatPercentage"),
                            result.getInt("DiscountAmount"), result.getInt("SellerID"));
                    discounts.add(discount);
                }
                if (exec == 0) {
                    System.out.println("No discount codes");
                }
                Gson gson = new Gson();
                String jsonSeller = gson.toJson(discounts);
                response.getWriter().write(jsonSeller);
            } else {
                System.out.println("Wrong Details");
                response.getWriter().write("Wrong Details");
            }


            //If exists then get the whole object return sellerID for now
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
