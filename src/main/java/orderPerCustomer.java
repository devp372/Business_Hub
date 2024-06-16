import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "orderPerCustomer", value = "/orderPerCustomer")
public class orderPerCustomer extends HttpServlet {

    private class ResultObj {
        String email;
        int count;

        public ResultObj(String email, int count) {
            this.email = email;
            this.count = count;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sellerID = request.getParameter("SellerID");
        String url = "jdbc:mysql://selldb.cqt5tgj7qyws.us-east-2.rds.amazonaws.com:3306/simpledb";
        String username = "simpledb";
        String password = "sell1234";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                HashMap<String, Integer> map = new HashMap<>();
                Statement s1 = con.createStatement();
                String check = "SELECT BuyerID, Count(OrderID) from Seller_Buyer_Orders where SellerID = '" + sellerID + "' group by BuyerID";
                ResultSet result = s1.executeQuery(check);
                while (result.next()) {
                    int buyer = result.getInt("BuyerID");
                    int count = result.getInt("Count(OrderID)");
                    Statement s2 = con.createStatement();
                    String buyerEmail = "SELECT Email FROM Buyers WHERE BuyerID ='" + buyer + "'";
                    ResultSet result2 = s2.executeQuery(buyerEmail);
                    if (result2.next()) {
                        String email = result2.getString("Email");
                        map.put(email, count);
                    }
                }
                ArrayList<ResultObj> resultObjs = new ArrayList<>();
                for(Map.Entry<String,Integer> entry : map.entrySet()) {
                    resultObjs.add(new ResultObj(entry.getKey(), entry.getValue()));
                }
                Gson gson = new Gson();
                String data = gson.toJson(resultObjs);
                response.getWriter().write(data);


            }
        } catch (Exception e) {
            response.getWriter().write("Some Problem");
            e.printStackTrace();
        }

    }
}
