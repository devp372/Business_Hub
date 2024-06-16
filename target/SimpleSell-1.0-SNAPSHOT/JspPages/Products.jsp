<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/7dff33b5c2.js" crossorigin="anonymous"></script>

</head>
<body style="overflow: scroll">



<div>
    <div class="nav_bar">
        <div class="logo_nav">
            <img class = "nav_logo" src="img/logo.png">
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
            <span style="cursor: pointer;" onclick="window.location='Inventory.jsp';" class = "side_bar_item selected_nav_item">
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
            <span style="cursor: pointer;" onclick="window.location='ModifyAccountDetails.jsp';" class = "side_bar_item">
                <i class="fas fa-user-alt"></i>
                Profile
            </span>

        </div>
        <div class = "content_main_out">
            <div class = "content_main">
                <h1 class= "product_text prd_title">Add Products</h1>

                <h2 class= "product_text">Title</h2>
                <input class="product_page_txt_box " id="title" type="text" placeholder="Product Title">

                <h2 class= "product_text">Description</h2>
                <input class = "product_page_txt_box desc" id="description" type="text" placeholder="Tell us about your product" maxlength="250" height="100px">

                <h2 class= "product_text">Pictures</h2>

                <div class="upload-btn-wrapper  product_text">
                    <button class="btn">Add Images</button>
                    <input id="images" type="file" multiple accept="image/*" height="100px">
                </div>

                <h2 class= "product_text">Price</h2>
                <input class="product_page_txt_box sml_prd_txt_box" id="price" type="text" placeholder="">

                <h2 class= "product_text">Category</h2>
                <input class="product_page_txt_box sml_prd_txt_box" id="category" type="text" placeholder="">

                <br></br>

                <button class="order_btns" id="has_sub_add">has sub-products?</button>
                <button class="order_btns" id="has_no_sub_add">no sub products?</button>
                <button class="order_btns" id="is_sub_add">is sub-product?</button>
                <section class="sub_input">
                    <h2 class= "product_text">Sub-Product of? </h2>
                    <input class="product_page_txt_box sml_prd_txt_box" id="sub_of" type="text" placeholder="">
                </section>

<div>
                <button class="product_btns" id = "save_add_prod">Save</button>
</div>
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
