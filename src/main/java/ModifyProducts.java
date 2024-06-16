import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//testing changes
@WebServlet(name = "ModifyProducts", value = "/ModifyProducts")
public class ModifyProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("ProductID"));
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
                String checkProduct = "SELECT * FROM Product WHERE ProductID = '" + productID + "' ";
                //String checkProductSubCategory = "SELECT * FROM Product_Subcategories WHERE ProductID = '" + productID + "' ";
                ResultSet productCheck = s1.executeQuery(checkProduct);
                //ResultSet productCheck1 = s1.executeQuery(checkProductSubCategory);
                inStock stock = inStock.OUT_OF_STOCK;
                if (productCheck.getBoolean("inStock")) {
                    stock = inStock.IN_STOCK;
                }
                if (productCheck.next()) {
                    Product product = new Product(productCheck.getString("Name"),
                            productCheck.getString("Description"),
                            productCheck.getString("Category"),
                            productCheck.getBoolean("hasSubcategories"),
                            productCheck.getDouble("price"),
                            productID, stock, null);
                    Gson gson = new Gson();
                    String jsonProduct = gson.toJson(product);
                    response.getWriter().write(jsonProduct);
                } else {
                    response.getWriter().write("Product does not exist");
                }
                // check and return appropriate message to response.getWriter

            }
        } catch (Exception e) {
            response.getWriter().write("Product Not Registered");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ProductID = request.getParameter("ProductID");
        String category = request.getParameter("Category");
        String name = request.getParameter("Name");
        double price = Double.parseDouble(request.getParameter("Price"));
        String in_Stock = request.getParameter("inStock");
        String description = request.getParameter("Description");
        boolean inStock, isSubProduct;
        inStock = in_Stock.equalsIgnoreCase("1");
        String url = "jdbc:mysql://selldb.cqt5tgj7qyws.us-east-2.rds.amazonaws.com:3306/simpledb";
        String username = "simpledb";
        String password = "sell1234";
        System.out.println(ProductID);

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                Statement s1 = con.createStatement();
                //String check = "SELECT * FROM Sellers WHERE Email = '" + emailID + "' AND Pswd ='" + hashedPass + "'";
                String checkProductQuery = "SELECT * FROM Product WHERE ProductID = '" + ProductID + "'";
                System.out.println(checkProductQuery);
                ResultSet result = s1.executeQuery(checkProductQuery);
                if (result.next()) {
                    System.out.println("Product Found");
                    int productId = result.getInt("ProductID");
                    //String checkCategoryQuery = "SELECT * FROM Product WHERE Category = '" + category + "'";
                    //String update = "UPDATE Product SET Category=" + category +  " WHERE ProductID=" + productId;
                    String update = "UPDATE Product SET Category = '" + category + "', Name = '" + name +
                            "', price = '" + price + "', inStock = '" + (inStock ? 1 : 0) +
                            "', Description = '" + description + "' WHERE ProductID = '" + productId + "'";
                    System.out.println(update);
                    //ResultSet result1 = s1.executeQuery(update);
                    s1.executeUpdate(update);
                    System.out.println("Updated successfully");
                    response.getWriter().write("Updated successfully");
                } else {
                    System.out.println("Product Not Found");
                    response.getWriter().write("Product Not Found");
                }
            }
        } catch (Exception e) {
            response.getWriter().write("There was some Problem");
            e.printStackTrace();
        }
    }
}
