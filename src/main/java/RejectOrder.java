import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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

@WebServlet(name = "RejectOrder", value = "/RejectOrder")
public class RejectOrder extends HttpServlet {
    @Override
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
                System.out.println("Database connection is successful !!!!");
                Statement s1 = con.createStatement();
                String deleteOne = "DELETE FROM Orders WHERE OrderID = '" + orderID + "'";
                s1.executeUpdate(deleteOne);
                Statement s2 = con.createStatement();
                String deleteTwo = "DELETE FROM Order_Product_Count WHERE OrderID = '" + orderID + "'";
                s2.executeUpdate(deleteTwo);
                Statement s3 = con.createStatement();
                String deleteThree = "DELETE FROM Seller_Buyer_Orders WHERE OrderID = '" + orderID + "'";
                s3.executeUpdate(deleteThree);
                System.out.println("Orders Rejected");
                response.getWriter().write("All Orders Rejected");
            } else {
                System.out.println("Order not found.");
                response.getWriter().write("Order does not exist.");
            }
        } catch (Exception e) {
            response.getWriter().write("Order not found.");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
