<%--
  Created by IntelliJ IDEA.
  User: shivambairoliya
  Date: 4/1/21
  Time: 11:57 PM
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body style="overflow: scroll">
<div class="back login">
    <div class="li_su_box">
        <div class="logo login">
            <img src="img/logo_full.png">
        </div>


        <div class="login_txt reset_password">

            <section class="si_txt">Reset Password. </section>
            <p id="email">Email Address</p>
            <input class="su_txt_box" id="email1" type="email" placeholder="Email">
            <button id="su_but_reset_button" >Get my Security Questions</button>
            <p id="securityQuestion1">Security Question 1</p>
            <input class="su_txt_box" id="q1_answer" type="password" placeholder="Answer">
            <p id="securityQuestion2">Security Question 2</p>
            <input class="su_txt_box" id="q2_answer" type="password" placeholder="Answer">
            <input class="su_txt_box" id="password" type="password" placeholder="New Password">
            <input class="su_txt_box" id="re-password" type="password" placeholder="Re-type New Password">
            <section class="pass_weak">Password too weak</section>
            <section class="pass_check">Passwords do not match!</section>
        </div>

        <button id="su_but_reset" >Reset Password</button>
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

