<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inventory</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/7dff33b5c2.js" crossorigin="anonymous"></script>

</head>
<body style="overflow: scroll">

<div>
    <div class="nav_bar">
        <div class="logo_nav">
            <img class="nav_logo" src="img/logo.png">
        </div>

        <div class="right_nav">
            <i class="fas fa-user-circle"></i>
            <div class="sellername">
                Jon Doe
            </div>

        </div>
    </div>
    <div class="content">
        <div class="side_bar">
            <span class = "side_bar_item">
                <i class="fas fa-home"></i>
                Home
            </span>
            <span style="cursor: pointer;" onclick="window.location='Inventory.jsp';" class = "side_bar_item ">
                <i class="fas fa-dolly-flatbed"></i>
                Inventory

            </span>
            <span style="cursor: pointer;" onclick="window.location='Analytics.jsp';" class = "side_bar_item">
                <i class="fas fa-chart-bar"></i>
                Analytics

            </span>
            <span style="cursor: pointer;" onclick="window.location='Discounts.jsp';" class = "side_bar_item">
                <i class="fas fa-star"></i>
                Discounts
            </span>
            <span style="cursor: pointer;" onclick="window.location='Orders.jsp';" class = "side_bar_item">
                <i class="fas fa-boxes"></i>
                Orders
            </span>
            <span style="cursor: pointer;" onclick="window.location='ModifyAccountDetails.jsp';" class = "selected_nav_item side_bar_item">
                <i class="fas fa-user-alt"></i>
                Profile
            </span>

        </div>
        <div class="content_main_out">
            <div class="content_main modify_details" >
                <h1 class="product_text prd_title">Account Details</h1><br></br>
                <h2 class= "product_text">First Name</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="firstName"/>
                <h2 class= "product_text push_right">Last Name</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="lastName"/>

                <h2 class= "product_text">Store Name</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="storeName"/>

                <h2 class= "product_text">Store Description</h2>
                <textarea class="product_page_txt_box mdfy_Acc_Desc" id = "description"></textarea>

                <h2 class= "product_text">Email</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="email"/>

                <h2 class= "product_text">Mobile Number</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="phone"/>

                <h2 class= "product_text">Password</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="pass" type="password"/>

                <h2 class= "product_text">Confirm Password</h2>
                <input class="sml_prd_txt_box product_page_txt_box" type="password" id="ConfirmPass"/>

                <h2 class= "product_text">Instagram ID</h2>
                <input class="sml_prd_txt_box product_page_txt_box"  id="instaID"/>

                <h2 class= "product_text">Facebook ID</h2>
                <input class="sml_prd_txt_box product_page_txt_box" id="fbID"/>
                <br></br>
                <button class="product_btns" id = "modify_account_button">Save</button>

                <div class="add_title">
                    <h1 class="product_text prd_title">Security Questions</h1><br></br>
                </div>
                <h2 class= "product_text">Question #1</h2>
                <select id="q1" name="q1" class="sec_drop_down">
                    <option selected>Select one...</option>
                    <option value="tour">What Is your favorite book?</option>
                    <option value="dinner">What is the name of the road you grew up on?</option>
                </select>
                <br></br>
                <input class="sml_prd_txt_box product_page_txt_box"  id="q1_answer" placeholder="Answer"/>
                <h2 class= "product_text">Question #2</h2>
                <select id="q2" name="q2" class="sec_drop_down">
                    <option selected>Select one...</option>
                    <option value="dance">What is your mothers maiden name?</option>
                    <option value="whales">What was the first company that you worked for?</option>
                </select>
                <br></br>
                <input class="sml_prd_txt_box product_page_txt_box"  id="q2_answer" placeholder="Answer"/>
                <br></br>
                <button class="sec_btns" id = "sec_questions_button">Save</button>

            </div>
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
