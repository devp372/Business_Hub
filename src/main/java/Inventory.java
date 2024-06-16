import com.google.gson.Gson;
//import com.sun.tools.javac.jvm.Items;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Inventory", value = "/Inventory")
public class Inventory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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
                String itemList = "SELECT * FROM Seller_Product WHERE SellerID = " + sellerID;
                System.out.println(itemList);
                ResultSet resultFromSellerProduct = s1.executeQuery(itemList);
                if (resultFromSellerProduct.next()) {
                    ArrayList<Product> inventory = new ArrayList<Product>();
                    System.out.println("Inventory found");
                    //creating new Seller object from SQL response
                    do {
                        int productId = resultFromSellerProduct.getInt("ProductID");
                        System.out.println("Current Product ID: " + productId);
                        Statement s4 = con.createStatement();
                        String getProduct = "SELECT * FROM Product WHERE ProductID ='" + productId + "'";
                        ResultSet result = s4.executeQuery(getProduct);
                        if (!result.next()) {
                            throw new Exception("There is some error in the database");
                        }
                        boolean isSub = result.getBoolean("isSubProduct");
                        ArrayList<Product> subcategory = new ArrayList<Product>();
                        if (!isSub) {
                            Statement s2 = con.createStatement();
                            String sublist = "SELECT * FROM Product_Subcategories WHERE ProductID = '" + productId + "'";
                            ResultSet res2 = s2.executeQuery(sublist);
                                while (res2.next()) {
                                    int subID = res2.getInt("SubItemID");
                                    Statement s3 = con.createStatement();
                                    String retrieveSub = "SELECT * FROM Product WHERE ProductID ='" + subID + "'";
                                    ResultSet res3 = s3.executeQuery(retrieveSub);
                                    if (!res3.next()) {
                                        throw new Exception("There is some error in the database");
                                    }
                                    System.out.println(res3);
                                    inStock stock = inStock.OUT_OF_STOCK;
                                    if (res3.getBoolean("inStock")) {
                                        stock = inStock.IN_STOCK;
                                    }
                                    Product p = new Product(res3.getString("Name"), res3.getString("Description"),
                                            res3.getString("Category"), false, res3.getDouble("price"),
                                            subID, stock, null);
                                    p.setUnitsSold(res3.getInt("UnitsSold"));
                                    subcategory.add(p);
                                }
                        }
                        inStock stock = inStock.OUT_OF_STOCK;
                        if (result.getBoolean("inStock")) {
                            stock = inStock.IN_STOCK;
                        }
                        Product product = new Product(result.getString("Name"), result.getString("Description"),
                                result.getString("Category"), result.getBoolean("hasSubcategories"), result.getDouble("price"),
                                productId, stock, subcategory);
                        product.setUnitsSold(result.getInt("UnitsSold"));
                        inventory.add(product);
                    } while (resultFromSellerProduct.next());

                    Gson gson = new Gson();
                    String jsonSeller = gson.toJson(inventory);
                    response.getWriter().write(jsonSeller);
                } else {
                    System.out.println("No items");
                    response.getWriter().write("No items");
                }

            }
        } catch (Exception e) {
            response.getWriter().write("No Items in Inventory");
            e.printStackTrace();
        }
    }
}
