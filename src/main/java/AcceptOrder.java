import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "AcceptOrder", value = "/AcceptOrder")
public class AcceptOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("OrderId"));

        String dburl = "jdbc:mysql://selldb.cqt5tgj7qyws.us-east-2.rds.amazonaws.com:3306/simpledb";
        String dbusername = "simpledb";
        String dbpassword = "sell1234";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dburl, dbusername, dbpassword);
            if (con != null) {
                Statement s1 = con.createStatement();
                System.out.println("Database connection is successful !!!!");
                String checkExist = "SELECT * FROM Orders WHERE OrderID = '" + orderID + "'";
                ResultSet result = s1.executeQuery(checkExist);
                if (result.next()) {
                    String sqlquery = "UPDATE Orders SET OrderAccept = '" + 0 +"', OrderState = " +
                            "'" + 1 + "' WHERE " +
                            "OrderID = '" + orderID + "'";
                    System.out.println(sqlquery);
                    Statement s2 = con.createStatement();
                    s2.executeUpdate(sqlquery);
                    String sqlCommand = "SELECT SellerID, BuyerID FROM Seller_Buyer_Orders WHERE OrderID = '" + orderID + "'";
                    Statement s3 = con.createStatement();
                    ResultSet res = s3.executeQuery(sqlCommand);
                    if (res.next()) {
                        String sell = res.getString("SellerID");
                        String buy = res.getString("BuyerID");
                        String sq = "INSERT INTO Notification (OrderID, SellerID, BuyerID) VALUES ( '" + orderID + "','" + sell + "','" + buy + "')";
                        Statement s4 = con.createStatement();
                        s4.executeUpdate(sq);
                        System.out.println("Notification Created");

                    }

                    System.out.println("Updated Order to Accepted");
                    response.getWriter().write("Order Accepted");
                } else {
                    System.out.println("Order not found.");
                    response.getWriter().write("Order does not exist.");
                }
            }
        } catch (Exception e) {
            response.getWriter().write("Order not found.");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
