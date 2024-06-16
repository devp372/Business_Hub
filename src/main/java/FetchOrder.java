import com.google.gson.Gson;

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
import java.util.ArrayList;

@WebServlet(name = "FetchOrder", value = "/FetchOrder")
public class FetchOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sellerID = Integer.parseInt(request.getParameter("SellerID"));
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
                String orderQuery = "SELECT * FROM Seller_Buyer_Orders WHERE SellerID = '" + sellerID + "'";
                ResultSet ordersSet = s1.executeQuery(orderQuery);
                ArrayList<Order> orders = new ArrayList<Order>();
                if (ordersSet.next()) {
                    do {
                        int orderID = ordersSet.getInt("OrderID");
                        Statement s2 = con.createStatement();
                        String checkUser = "SELECT * FROM Orders WHERE OrderID = '" + orderID + "' ";
                        ResultSet userCheck = s2.executeQuery(checkUser);
                            if (userCheck.next()) {
                                Statement s3 = con.createStatement();
                                String productCount = "SELECT * FROM Order_Product_Count WHERE OrderID = '" + orderID + "'";
                                ResultSet productCounts = s3.executeQuery(productCount);
                                ArrayList<Product> products = new ArrayList<Product>();
                                ArrayList<Integer> count = new ArrayList<Integer>();
                                if (productCounts.next()) {
                                    do {
                                        int productID = (productCounts.getInt("ProductID"));
                                        Statement s4 = con.createStatement();
                                        String getProduct = "SELECT * FROM Product WHERE ProductID = '" + productID + "'";
                                        ResultSet productQuery = s4.executeQuery(getProduct);
                                        if (productQuery.next()) {
                                            ArrayList<Product> subProducts = null;
                                            if (productQuery.getBoolean("hasSubcategories")) {
                                                subProducts = new ArrayList<Product>();
                                                Statement s5 = con.createStatement();
                                                String subQuery = "SELECT * FROM Product_Subcategories WHERE ProductID = '" + productID + "'";
                                                ResultSet subProdRes = s5.executeQuery(subQuery);
                                                if (subProdRes.next()) {
                                                    do {
                                                        inStock substock = inStock.IN_STOCK;
                                                        if (!subProdRes.getBoolean("inStock"))
                                                            substock = inStock.OUT_OF_STOCK;
                                                        Product subProd = new Product((subProdRes.getString("Name")),
                                                                subProdRes.getString("Description"),
                                                                subProdRes.getString("Category"),
                                                                false,
                                                                subProdRes.getDouble("price"),
                                                                subProdRes.getInt("ProductID"),
                                                                substock,
                                                                null);
                                                        subProducts.add(subProd);
                                                    } while (subProdRes.next());
                                                } else {
                                                    System.out.println("Subproducts not found when expecting subproducts");
                                                    return;
                                                }
                                            }
                                            inStock stock = inStock.IN_STOCK;
                                            if (!productQuery.getBoolean("inStock"))
                                                stock = inStock.OUT_OF_STOCK;
                                            Product product = new Product(productQuery.getString("Name"),
                                                    productQuery.getString("Description"),
                                                    productQuery.getString("Category"),
                                                    productQuery.getBoolean("hasSubcategories"),
                                                    productQuery.getDouble("price"),
                                                    productQuery.getInt("ProductID"),
                                                    stock,
                                                    subProducts);
                                            products.add(product);
                                            count.add(productCounts.getInt("Count"));
                                        } else {
                                            System.out.println("Product in Order not Found");
                                            return;
                                        }
                                    } while (productCounts.next());
                                } else {
                                    System.out.println("No Products in Order");
                                    return;
                                }

                                orderState state = orderState.PLACED;
                                if (userCheck.getInt("OrderState") == 1)
                                    state = orderState.COMPLETED;
                                orderAccept accept = orderAccept.REJECTED;
                                if (userCheck.getInt("OrderAccept") == 0)
                                    accept = orderAccept.ACCEPTED;
                                Order order = new Order(userCheck.getInt("BuyerID"),
                                        products, count, userCheck.getDouble("totalPrice"),
                                        userCheck.getInt("OrderID"), userCheck.getString("FirstName"),
                                        userCheck.getString("LastName"), userCheck.getString("Email"),
                                        userCheck.getString("MobileNum"), state, accept);
                                orders.add(order);
                            }
                    } while (ordersSet.next());
                    Gson gson = new Gson();
                    String jsonOrders = gson.toJson(orders);
                    response.getWriter().write(jsonOrders);
                } else {
                    response.getWriter().write("Seller has no orders");
                }
                // check and return appropriate message to response.getWriter

            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");
            e.printStackTrace();
        }

    }
}
