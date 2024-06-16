import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ModifyAccountDetails", value = "/ModifyAccountDetails")
public class ModifyAccountDetails extends HttpServlet {
    @Override
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
                String checkUser = "SELECT * FROM Sellers WHERE SellerID = '" + sellerID + "' ";
                ResultSet userCheck = s1.executeQuery(checkUser);
                if (userCheck.next()) {

                    Seller seller = new Seller(userCheck.getInt("SellerID"),
                            userCheck.getString("FirstName"), userCheck.getString("LastName"),
                            userCheck.getString("Email"), userCheck.getString("MobileNum"),
                            userCheck.getString("StoreName"), userCheck.getString("Description"));
                    seller.setFbHandle(userCheck.getString("FbHandle"));
                    seller.setInstaHandle(userCheck.getString("InstaHandle"));
                    Gson gson = new Gson();
                    String jsonSeller = gson.toJson(seller);
                    response.getWriter().write(jsonSeller);
                } else {
                    response.getWriter().write("User does not exist");
                }
                // check and return appropriate message to response.getWriter

            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sellerID = Integer.parseInt(request.getParameter("SellerID"));
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String emailID = request.getParameter("EmailID");
        String mobileNum = request.getParameter("MobileNumber");
        String password = request.getParameter("Password");
        String storeName = request.getParameter("StoreName");
        String description = request.getParameter("Description");
        String instaHandle = request.getParameter("InstaHandle");
        String fbHandle = request.getParameter("FbHandle");

        boolean updatePass = true;

        boolean check = pwcheck.isValidPass(password);
        if (!check) {
            updatePass = false;
//            response.getWriter().write("Password Too weak");
//            return;
        }

        //HASHING THE PASSWORD
        String hashedPass = "";
        if (updatePass)
            hashedPass = SHA256Hash.hash(password);

        boolean chk = mailchk.isValidEmail(emailID);

//        if (!chk) {
//            response.getWriter().write("Email Too weak");
//            response.getWriter().write("Email needs to have an @");
//            //doPost(request, response);
//            //return;
//            //
//        }

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
                String checkUser = "SELECT * FROM Sellers WHERE SellerID = '" + sellerID + "' ";
                ResultSet userCheck = s1.executeQuery(checkUser);
                if (!userCheck.next()) {
                    response.getWriter().write("User does not exist");
                    return;
                }
                String checkEmailQuery = "SELECT * FROM Sellers WHERE Sellers.Email = '" + emailID + "'";
                ResultSet set = s1.executeQuery(checkEmailQuery);
                if ((set.next() && !set.getString("SellerID").equals(request.getParameter("SellerID")))) {
                    response.getWriter().write("Email Exists");
                    return;
                }
                String checkPhoneQuery = "SELECT * FROM Sellers WHERE Sellers.MobileNum = '" + mobileNum + "'";
                set = s1.executeQuery(checkPhoneQuery);
                if ((set.next() && !set.getString("SellerID").equals(request.getParameter("SellerID")))) {
                    response.getWriter().write("Phone Exists");
                    return;
                }
                String sqlquery;
                if (updatePass)
                    sqlquery = "UPDATE Sellers SET FirstName = '" + firstName + "', LastName = '" + lastName +
                            "', Email = '" + emailID + "', MobileNum = '" + mobileNum + "', Pswd = '" + hashedPass +
                            "', StoreName = '" + storeName + "', Description = '" + description + "', InstaHandle = '" +
                            instaHandle + "', FbHandle = '" + fbHandle + "' WHERE SellerID = '" + sellerID + "'";
                else
                    sqlquery = "UPDATE Sellers SET FirstName = '" + firstName + "', LastName = '" + lastName +
                            "', Email = '" + emailID + "', MobileNum = '" + mobileNum + "', StoreName = '" + storeName
                            + "', Description = '" + description + "', InstaHandle = '" +
                            instaHandle + "', FbHandle = '" + fbHandle + "' WHERE SellerID = '" + sellerID + "'";

                System.out.println(sqlquery);
                s1.executeUpdate(sqlquery);
                System.out.println("Updated Seller Info");
                response.getWriter().write("Seller Info Updated");
                // check and return appropriate message to response.getWriter


            }
        } catch (Exception e) {
            response.getWriter().write("User Not Registered");
            e.printStackTrace();
        }
    }
}
