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

@WebServlet(name = "DiscountCode", value = "/DiscountCode")
public class DiscountCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String discountCode = request.getParameter("DiscountCode");
        int discountAmount = Integer.parseInt(request.getParameter("DiscountAmount"));
        int flatOrPerc = Integer.parseInt(request.getParameter("FlatPercentage"));
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
                String checkDiscQuery = "SELECT COUNT(*) FROM Discount WHERE Discount.DiscountCode = '" + discountCode + "'";
                ResultSet set = s1.executeQuery(checkDiscQuery);
                if (set.next() && set.getString("COUNT(*)").equals("1")) {
                    response.getWriter().write("Code Exists");
                    return;
                }
                String insertDisc = "INSERT INTO Discount (DiscountCode, DiscountAmount, FlatPercentage, SellerID) "+
                        "VALUES ('" + discountCode +"','"+discountAmount+"','"+flatOrPerc+"', '"+sellerID+"')";
                s1.executeUpdate(insertDisc);
                System.out.println("Inserted Discount into Table");
                response.getWriter().write("Discount Enabled");

            }
        } catch (Exception e) {
            response.getWriter().write("Discount Not Enabled");

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
