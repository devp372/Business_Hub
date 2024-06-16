<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello, I am a Java web app!</title>
</head>
<body>
<h1>Simple Java Web App Demo</h1>
<p>To invoke the java servlet click <a href="${pageContext.request.contextPath}/SignUp">here</a></p>
<p>To invoke the java servlet click <a href="/SignUp">here</a></p>
<ul>
    <li class="redirect_to_signin"><a href="JspPages/LogIn.jsp">Sign In</a></li>
    <li><a href="JspPages/SignUp.jsp">Sign Up</a></li>
    <li><a href="JspPages/Products.jsp">Add Product</a></li>
    <li><a href="JspPages/ModifyAccountDetails.jsp">Modify Account Details</a></li>
    <li><a href="JspPages/Inventory.jsp">Inventory</a></li>
    <li><a href="JspPages/test_inv.jsp">Test Inventory</a></li>
    <li><a href="JspPages/Orders.jsp">Orders</a></li>
    <li><a href="JspPages/Analytics.jsp">Analytics</a></li>

</ul>


<script src="JspPages/js/jquery-3.5.1.js"></script>
<script src="JspPages/js/bootstrap.min.js"></script>
<script src="JspPages/js/jquery-confirm.min.js"></script>
<script src="JspPages/js/jquery.nice-select.min.js"></script>
<script src="JspPages/js/jquery-ui.min.js"></script>
<script src="JspPages/js/jquery.slicknav.js"></script>
<script src="JspPages/js/main.js"></script>
</body>


</html>