<%--
  Created by IntelliJ IDEA.
  User: shivambairoliya
  Date: 2/28/21
  Time: 1:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body style="overflow: scroll">
<div class="back login">
    <div class="li_su_box">
        <div class="logo login">
            <img src="img/logo_full.png">
        </div>

        <div class="login_txt">

            <section class="si_txt">Create Account. </section>
            <div class="name_box">
            <input class="su_txt_box name" id="firstName" type="text" placeholder="First Name">
            <input class="su_txt_box name" id="lastName" type="text" placeholder="Last Name">
            </div>
            <input class="su_txt_box" id="email" type="text" placeholder="Email ID">
            <section class="email_check">Email already registered!</section>
            <input class="su_txt_box" id="mobileNumber" type="text" placeholder="Mobile Number">
            <section class="phone_check">Phone Number already exists!</section>
            <input class="su_txt_box" id="password" type="password" placeholder="Password">
            <input class="su_txt_box" id="re-password" type="password" placeholder="Re-type Password">
            <section class="pass_weak">Password too weak</section>
            <section class="pass_check">Passwords do not match!</section>
            <input class="su_txt_box" id="storeName" type="text" placeholder="Store Name">
            <input class="su_txt_box" id="description" type="text" placeholder="About">
            <section class="already"><a href="LogIn.jsp">Already registed? Sign In.</a></section>
        </div>

            <button id="su_but">Start Selling!</button>
            <script>
                console.log("${pageContext.request.contextPath}/SignUp");
            </script>
    </div>
</div>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-confirm.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/main.js"></script>

</body>
</html>
