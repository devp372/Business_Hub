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

@WebServlet(name = "CompleteOrder")
public class CompleteOrder extends HttpServlet {
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
                    String sqlquery = "UPDATE Orders SET OrderState = " +
                            "'" + orderState.COMPLETED + "' WHERE " +
                            "OrderID = '" + orderID + "'";
                    System.out.println(sqlquery);
                    s1.executeUpdate(sqlquery);
                    System.out.println("Updated Order to Completed");
                    response.getWriter().write("Order Completed");
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
