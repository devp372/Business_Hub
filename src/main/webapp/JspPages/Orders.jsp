<html class="" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
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
            <span style="cursor: pointer;" onclick="window.location='Orders.jsp';" class = "side_bar_item selected_nav_item">
                <i class="fas fa-boxes"></i>
                Orders
            </span>
            <span style="cursor: pointer;" onclick="window.location='ModifyAccountDetails.jsp';" class = "side_bar_item">
                <i class="fas fa-user-alt"></i>
                Profile
            </span>

        </div>
        <div class="content_main_out">
            <div class="content_main">
                <h1 class="product_text prd_title">Orders</h1>

                <div class="all_orders">



                </div>
            </div>
        </div>
    </div>
</div>
<div class="scripts">
    <script src="js/jquery-3.5.1.js"></script>


    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-confirm.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/main.js"></script>



</div>

</body>
</html>
