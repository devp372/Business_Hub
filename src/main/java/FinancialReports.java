import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.nimbus.State;

@WebServlet(name = "FinancialReports", value = "/FinancialReports")
public class FinancialReports extends HttpServlet {
    private class Report {
        float sales;
        float avg;

        public Report(float sales, float avg) {
            this.sales = sales;
            this.avg = avg;
        }
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
                Statement s1 = con.createStatement();
                String itemList = "SELECT OrderID FROM Seller_Buyer_Orders WHERE SellerID = " + sellerID;
                System.out.println(itemList);
                ResultSet resultFromSellerProduct = s1.executeQuery(itemList);
                float sales = 0;
                int numberOfOrder = 0;
                while (resultFromSellerProduct.next()) {
                    int orderId = resultFromSellerProduct.getInt("OrderID");
                    Statement s2 = con.createStatement();
                    String getPrice = "SELECT totalPrice FROM Orders WHERE OrderID =" + orderId ;
                    ResultSet price = s2.executeQuery(getPrice);
                    if (!price.next()) {
                        throw new Exception(" There is some error");
                    }
                    sales = sales + price.getInt("totalPrice");
                    numberOfOrder++;
                }
                float avg = sales / numberOfOrder;
                Report report = new Report(sales, avg);
                Gson gson = new Gson();
                String json = gson.toJson(report);
                response.getWriter().write(json);
            }
        }catch (Exception e) {
            response.getWriter().write("No seller exists");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
