import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "InventoryAdd", value = "/InventoryAdd")
public class InventoryAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sellerID = Integer.parseInt(request.getParameter("SellerID"));
        String name = request.getParameter("Name");
        String category = request.getParameter("Category");
        boolean hasSubs = Boolean.parseBoolean(request.getParameter("hasSubcategories"));
        double price = Double.parseDouble(request.getParameter("price"));
        boolean inStock = Boolean.parseBoolean(request.getParameter("inStock"));
        String description = request.getParameter("Description");
        boolean isSub = Boolean.parseBoolean(request.getParameter("isSubProduct"));
        int mainID = Integer.parseInt(request.getParameter("ProductID"));


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
                ResultSet result = null;
                if (!isSub) {
                    String checkSeller = "SELECT * FROM Sellers WHERE SellerID = '" + sellerID + "'";
                    result = s1.executeQuery(checkSeller);
                } else {
                    String checkSeller = "SELECT * FROM Product WHERE ProductID = '" + mainID + "'";
                    result = s1.executeQuery(checkSeller);
                }
                if (result.next()) {
                    String sqlquery = "INSERT INTO Product (Name, Category, hasSubcategories, price, inStock, Description, isSubProduct) " +
                            "VALUES ('" + name + "','" + category + "','" + ((hasSubs) ? 1 : 0) + "','" + price + "','" +
                            ((inStock) ? 1 : 0) + "','" + description + "','" + ((isSub) ? 1 : 0) + "')";
                    Statement s2 = con.createStatement();
                    s2.executeUpdate(sqlquery);
                    String maxQ = "SELECT MAX(ProductID) AS maxProd from Product";
                    Statement s3 = con.createStatement();
                    ResultSet prodIDRes =  s3.executeQuery(maxQ);
                    prodIDRes.next();
                    int productID = prodIDRes.getInt("maxProd");
                    if (!isSub) {
                        String secQuery = "INSERT INTO Seller_Product (SellerID, ProductID) VALUES ('" + sellerID +
                                "','" + productID + "')";
                        s1.executeUpdate(secQuery);
                    } else {
                        String secQuery = "INSERT INTO Product_Subcategories (ProductID, SubItemID) VALUES ('" + mainID +
                                "','" + productID + "')";
                        s1.executeUpdate(secQuery);
                    }
                    System.out.println("Inserted Product into Inventory");
                    response.getWriter().write("Product Inserted");
                } else {
                    System.out.println("There is some problem");
                    response.getWriter().write("Seller not Present or Problem with Subcategories");
                }
            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
