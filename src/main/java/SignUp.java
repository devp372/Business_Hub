import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String emailID = request.getParameter("EmailID");
        String mobileNum = request.getParameter("MobileNumber");
        String password = request.getParameter("Password");
        String storeName = request.getParameter("StoreName");
        String description = request.getParameter("Description");
//        String SecurityQuestion = request.getParameter("SecurityQuestion");

        //check password here
        boolean check = pwcheck.isValidPass(password);
        if (!check) {
            response.getWriter().write("Password Too weak");
            return;
        }

        //HASHING THE PASSWORD
        String hashedPass = SHA256Hash.hash(password);


        //Not Important
//
        boolean chk = mailchk.isValidEmail(emailID);

        if (!chk) {
            //response.getWriter().write("Email Too weak")
            response.getWriter().write("Email format invalid");
            //doPost(request, response);
            return;
            //
        }

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
                String checkEmailQuery = "SELECT COUNT(*) FROM Sellers WHERE Sellers.Email = '" + emailID + "'";
                ResultSet set = s1.executeQuery(checkEmailQuery);
                if (set.next() && set.getString("COUNT(*)").equals("1")) {
                    response.getWriter().write("Email Exists");
                    return;
                }
                String checkPhoneQuery = "SELECT COUNT(*) FROM Sellers WHERE Sellers.MobileNum = '" + mobileNum + "'";
                set = s1.executeQuery(checkPhoneQuery);
                if (set.next() && set.getString("COUNT(*)").equals("1")) {
                    response.getWriter().write("Phone Exists");
                    return;
                }
                String sqlquery = "INSERT INTO Sellers (FirstName,LastName,Email,MobileNum,Pswd,StoreName,Description)" +
                        "VALUES ('" + firstName + "','" + lastName + "','" + emailID + "','" + mobileNum + "','" +
                        hashedPass + "','" + storeName + "','" + description + "')";
                System.out.println(sqlquery);
                s1.executeUpdate(sqlquery);
                System.out.println("Inserted Seller into Table");
                response.getWriter().write("User Successfully Registered");



                // check and return appropriate message to response.getWriter




            }
        } catch (Exception e) { 
            response.getWriter().write("User Not Registered");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
