import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "DeleteProduct", value = "/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("ProductID");
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
                String check = "SELECT * FROM Product WHERE ProductID = '" + productID + "'";
                ResultSet result = s1.executeQuery(check);
                if (result.next()) {
                    System.out.println("Product to Delete Found");
                    if (result.getBoolean("isSubProduct")) {
                        Statement s2 = con.createStatement();
                        String query = "DELETE FROM Product WHERE ProductID = '" + productID + "'";
                        s2.executeUpdate(query);
                        Statement s3 = con.createStatement();
                        query = "DELETE FROM Product_Subcategories WHERE SubItemID = '" + productID + "'";
                        s3.executeUpdate(query);
                        Statement s4 = con.createStatement();
                        query = "DELETE FROM Seller_Product WHERE ProductID = '" + productID + "'";
                        s4.executeUpdate(query);
                    } else if (result.getBoolean("hasSubcategories")) {
                        Statement s2 = con.createStatement();
                        String query = "SELECT * FROM Product_Subcategories WHERE ProductID = '" + productID + "'";
                        ResultSet resultSet = s2.executeQuery(query);
                        while (resultSet.next()) {
                            Statement s3 = con.createStatement();
                            int subProdID = resultSet.getInt("SubItemID");
                            query = "DELETE FROM Product WHERE ProductID = '" + subProdID + "'";
                            s3.executeUpdate(query);
                            Statement s4 = con.createStatement();
                            query = "DELETE FROM Seller_Product WHERE ProductID = '" + subProdID + "'";
                            s4.executeUpdate(query);
                        }
                        query = "DELETE FROM Product WHERE ProductID = '" + productID + "'";
                        s2.executeUpdate(query);
                        Statement s3 = con.createStatement();
                        query = "DELETE FROM Product_Subcategories WHERE ProductID = '" + productID + "'";
                        s3.executeUpdate(query);
                        Statement s4 = con.createStatement();
                        query = "DELETE FROM Seller_Product WHERE ProductID = '" + productID + "'";
                        s4.executeUpdate(query);
                    } else {
                        Statement s2 = con.createStatement();
                        String query =  "DELETE FROM Product WHERE ProductID = '" + productID + "'";
                        s2.executeUpdate(query);
                        Statement s4 = con.createStatement();
                        query = "DELETE FROM Seller_Product WHERE ProductID = '" + productID + "'";
                        s4.executeUpdate(query);
                    }
                    System.out.println("Everything removed successfully");
                    response.getWriter().write("Product/s Deleted");
                } else {
                    System.out.println("No Product with the ID exists");
                    response.getWriter().write("No Product with the ID exists");
                }
            }
        } catch (Exception var16) {

            var16.printStackTrace();
        }
    }
}
