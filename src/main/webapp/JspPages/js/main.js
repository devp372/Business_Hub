'use strict';


(function ($) {


    var DEBUG = true;

    DEBUG && console.log("main.js added")

    if (($(".redirect_to_signin")[0])) {
        window.location.href = "JspPages/LogIn.jsp";
    }

    $('.sellername').text(sessionStorage.getItem("sellerName"))

    var signUpButton = $('#su_but');
    $('.email_check').hide();
    $('.phone_check').hide();
    $('.pass_check').hide();
    $('.pass_weak').hide();


    signUpButton.on('click', function () {

        $('.email_check').hide();
        $('.phone_check').hide();
        $('.pass_check').hide();
        $('.pass_weak').hide();

        var title = $('#title').val()




        var FirstName = $('#firstName').val();
        var LastName = $('#lastName').val();
        var EmailID = $('#email').val();
        var Password = $('#password').val();
        var repass = $('#re-password').val();
        var MobileNumber = $('#mobileNumber').val();
        var storeName = $('#storeName').val();
        // send ajax request
        DEBUG && console.log(Password);
        DEBUG && console.log("Sign Up button works");

        var err = 0;

        if (Password != repass) {
            err = 1;
        }


        if (!err) {
            $.ajax({
                type: 'post',
                url: '/SimpleSell_war/SignUp',
                data: {
                    FirstName: FirstName, LastName: LastName, EmailID: EmailID, Password: Password,
                    MobileNumber: MobileNumber, StoreName: storeName
                },
                success: function (response) {

                    DEBUG && console.log(response);

                    if (response == "Email Exists") {
                        $('.email_check').show();
                        $('#email').addClass('err');
                    }

                    if (response == "Phone Exists") {
                        $('.phone_check').show();
                        $('#phoneNumber').addClass('err');
                    }


                    if (response == "User Successfully Registered") {
                        window.alert("User Registered.")
                        window.location.href = "LogIn.jsp";

                    }

                    if (response == "Password Too weak") {
                        $('.pass_weak').show();
                        $('#password').addClass('err');
                        $('#re-password').addClass('err');
                    }

                    if (response == "Email format invalid") {
                        window.alert("Email format invalid");
                    }



                    // if (data != 1) {
                    //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'red');
                    // }
                    // else {
                    //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'green');
                    // }
                }
            });
        } else {
            $('.pass_check').show();
            $('#password').addClass('err');
            $('#re-password').addClass('err');
        }
    });







    var securityButton = $('#sec_questions_button');
    securityButton.on('click', function () {
        var q1 = $("#q1 :selected").text();
        var q2 = $("#q2 :selected").text();
        var ans1 = $("#q1_answer").val();
        var ans2 = $("#q2_answer").val();
        console.log(q1);
        console.log(q2);
        console.log(ans1);
        console.log(ans2);


        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/ModifySecurityQuestions',
            data: {
                SellerID: sessionStorage.getItem("sellerID"),
                SecurityQuestion1: q1,
                Answer1: ans1,
                SecurityQuestion2: q2,
                Answer2: ans2
            },
            success: function (response) {

                DEBUG && console.log(response);

                if (response === "Security Questions updated") {
                    window.alert("Security Questions Updated!");
                }

                window.location.href = "ModifyAccountDetails.jsp";


            }
        });


    });


    var logInButton = $('#li_but');

    logInButton.on('click', function () {
        var EmailID = $('#email').val();
        var Password = $('#password').val();
        // send ajax request
        DEBUG && console.log(Password);
        DEBUG && console.log(EmailID);

        DEBUG && console.log("Sign IN button works");

        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/Login',
            data: {
                EmailID: EmailID, Password: Password
            },

            success: function (response) {

                DEBUG && console.log(response);

                if (response == "Wrong Details") {
                    window.alert("Wrong Details");

                } else {
                    var seller = JSON.parse(response);
                    console.log(seller);
                    sessionStorage.setItem("sellerID", seller.sellerID);
                    sessionStorage.setItem(("storeName"), seller.appName);
                    sessionStorage.setItem("sellerName", seller.firstName + " " + seller.lastName);
                    window.location.href = "Inventory.jsp";
                }

                // if (data != 1) {
                //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'red');
                // }
                // else {
                //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'green');
                // }

            }
        });
    });



    if (($(".all_products")[0])) {

        console.log(sessionStorage.getItem("sellerID"))

        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/Inventory',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },

            success: function (response) {

                DEBUG && console.log(response);
                var obj = JSON.parse(response);

                DEBUG && console.log(obj[0]["name"]);

                DEBUG && console.log(obj.length);


                for (let i = 0; i < obj.length; i ++ ) {

                    var hasSub = "No";
                    if(obj[i]["hasSubCategories"] == true ) {
                        hasSub = "Yes";
                    }



                    var soldout_btn = "<button class=\"product_btns sold_out\" >Mark as sold out</button>\n"
                    var instock_btn = "<button class=\"product_btns in_stock\" >Mark as in stock</button>\n"

                    var final_btn;










                    $('.all_products').append("<div class=\"prod_wrapper prod_" + i +"\">\n" +
                        "                        <div class=\"product_content\">\n" +
                        "                            <img class=\"inv_img\" src=\"img/product_image.jpg\">\n" +
                        "                            <div class=\"product_txt\">\n" +
                        "                                <div class=\"title_price\">\n" +
                        "                                    <input class=\"prod_title\" value=\"" + obj[i]["name"] + "\">\n" +
                        "                                    <section class=\"product_price\">\n" +
                        "                                        <span class=\"price_dollar\">$</span>\n" +
                        "                                        <input class=\"prod_title_price\" value=\"" + obj[i]["price"] + "\">\n" +
                        "                                    </section>\n" +
                        "                                </div>\n" +
                        "                                <textarea class=\"prd_desc\">" + obj[i]["description"] + " </textarea>\n" +
                        "                                <input class=\"prod_cat\" placeholder='Category' value=\"" + obj[i]["category"] + "\">\n" +
                        "                                <button class=\"product_btns save_mod_prod\">Save</button>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"prod_right\">\n" +
                        "                                <p class = \"prod_id\">SKU Number: " + obj[i]["productID"] + "</p>\n" +
                        "                                <p class = \"has_sub\">Has Sub Products: " + hasSub +"</p>\n" +
                        "\n<p class='units_sold'>Units Sold: " + obj[i]["unitsSold"] +  " </p>" +
                        "                                <button class=\"product_btns sold_out\">Mark as sold out</button>\n" +
                        "                                <button class=\"product_btns in_stock\" >Mark as in stock</button>\n" +
                        "                                <button class=\"product_btns delete_prod\" >Remove from inventory</button>\n" +
                        "\n" +
                        "                            </div>\n" +
                        "\n" +
                        "\n" +
                        "                        </div>\n" +
                        "\n" +
                        "                    </div>")

                    DEBUG &&
                    console.log(obj[i]["in_Stock"]);


                    if (obj[i]["in_Stock"] == "IN_STOCK") {
                        $('.prod_'+ i).find('.in_stock').hide();
                        DEBUG && console.log("is in stock  " + i);

                    } else {
                        $('.prod_'+ i).find('.sold_out').hide();
                    }



                    // $('.all_products').append("<div class=\"prod_wrapper\">\n" +
                    //     "                        <div class=\"product_content\">\n" +
                    //     "                            <img class=\"inv_img\" src=\"img/product_image.jpg\">\n" +
                    //     "                            <div class=\"product_txt\">\n" +
                    //     "                            <div class=\"title_price\">\n" +
                    //     "                                <input class=\"prod_title\" value=\"" + obj[i]["name"] + "\">\n" +
                    //     "                                <section class=\"product_price\">\n" +
                    //     "                                    <span class=\"price_dollar\">$</span>\n" +
                    //     "                                    <input class=\"prod_title_price\" value=\"" + obj[i]["price"] + "\">\n" +
                    //     "                                </section>\n" +
                    //     "                            </div>\n" +
                    //     "                            <textarea class=\"prd_desc\">" + obj[i]["description"] + " </textarea>\n" +
                    //     "                                <button class=\"product_btns\">Save</button>\n" +
                    //     "                            </div>\n" +
                    //     "                           <p id='prod_id'>SKU Number: " + obj[i]["skuNumber"] + "</p>\n" +
                    //     "                        </div>\n" +
                    //     "\n" +
                    //     "                    </div>")


                }

                $('.scripts').append("<script src=\"js/save_moded.js\"></script>")


            }
        });
    }

    var discountButton = $('#apply_discount');

    discountButton.on('click', function () {
        var check_b = "0";
        if ($('#isPercentage').is(":checked"))
        {
            check_b = "1";
        }
        console.log($("#isPercentage").val());
        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/DiscountCode',
            data: {
                SellerID: sessionStorage.getItem("sellerID"),
                FlatPercentage: check_b,
                DiscountCode:$("#discountCode1").val(),
                DiscountAmount: $("#discountCode2").val()
            },
            success: function (response) {
                if (response === "Code Exists") {
                    console.log("Code Already Exists");
                } else if (response === "Discount Enabled"){
                    console.log("Code Created");
                    window.alert("Discount Created");
                    window.location.href = "Discounts.jsp";

                }else if (response === "Discount Not Enabled") {
                    console.log("PROBLEM");
                }else {
                    console.log("GOD KNOWS");
                }

            }
        });
    });


    if (($(".reset_password")[0])) {
        //var sellerID = sessionStorage.getItem("sellerID");
        var getButton = $('#su_but_reset_button');
        getButton.on('click', function () {
            $.ajax({
                type: 'post',
                url: '/SimpleSell_war/GetSecurity',
                data: {
                    Email: $('#email1').val()
                },
                success: function (response) {
                    console.log(response);
                    var security = JSON.parse(response);
                    $("#securityQuestion1").text(security.q1);
                    $("#securityQuestion2").text(security.q2);
                }
            });

        });

        var resetButton = $('#su_but_reset');
        resetButton.on('click', function () {
            console.log($("#password").val())
            $.ajax({
                type: 'post',
                url: '/SimpleSell_war/PasswordReset',
                data: {
                    Email: $('#email1').val(),
                    NewPassword: $("#password").val(),
                    Answer1:  $("#q1_answer").val(),
                    Answer2:  $("#q2_answer").val()
                },
                success: function (response) {
                    if (response === "Seller Info Updated") {
                        window.location.href = "LogIn.jsp";
                        console.log("FUN IS HERE");

                    } else {
                        window.alert(response);
                    }
                }
            });
        });

    }



var test = "<tr>\n" +
    "        <th colspan=\"5\">\n" +
    "            <hr>\n" +
    "        </th>\n" +
    "    </tr>\n" +
    "    </tbody>\n" +
    "</table>\n" +
    "\n" +
    "</body>\n" +
    "</html>"

    if (($(".all_orders")[0])) {


        var sellerID = sessionStorage.getItem("sellerID");




        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/FetchOrder',
            data: {
                SellerID: sellerID
            },

            success: function (response) {

                DEBUG && console.log(response);

                var obj = JSON.parse(response);

                var html_append = "";

                for (var i = 0; i < obj.length; i++) {


                    var accepted = false;

                    var str = "<div class=\"order_btns_wrapper\">\n" +
                        "            <button class=\"decline_order order_btns\">Decline</button>\n" +
                        "            <button class=\"accept_order order_btns\">Accept</button>\n" +
                        "        </div>";

                    if (obj[i]["accept"] == "ACCEPTED") {
                        str = "<div class=\"order_btns_wrapper\">\n" +
                            "    <button class=\"complete_order order_btns\">Mark as Completed</button>\n" +
                            "</div>"
                    }

                    html_append += "<div class=\"order_wrapper\">\n" +
                        "\n" +
                        "    <div class=\"all_order_products\">"


                    html_append += "<div>\n" +
                        "    <p class=\"order_id\">Order ID: " + obj[i]["orderId"] + "</p>\n" +
                        "</div>"

                    for (var j = 0; j < obj[i]["products"].length; j++) {

                        html_append += "<div class=\"order_prod_wrapper\">\n" +
                            "            <img src=\"img/product_image.jpg\" class=\"order_image\">\n" +
                            "            <section class=\"order_prod_name\">" + obj[i]["products"][j]["name"] + "</section>\n" +
                            "            <div class=\"order_prod_details\">\n" +
                            "                <span class=\"order_prod_price\">Price: " + obj[i]["products"][j]["price"] + "</span>\n" +
                            "                <span class=\"order_prod_qty\">Qty.: " + obj[i]["quantities"][j] + "</span>\n" +
                            "            </div>\n" +
                            "        </div>"

                    }

                    html_append += "<div class=\"order_bottom\">\n" +
                        "\n" +
                        "        <div class=\"order_buyer_details\">\n" +
                        "            <span class=\"order_buyer_name\">" + obj[i]["firstName"] + " " + obj[i]["lastName"] + "</span>\n" +
                        "            <span class=\"order_buyer_email\">" + obj[i]["email"] + "</span>\n" +
                        "            <span class=\"order_buyer_phnumber\">" + obj[i]["mobileNum"] + "</span>\n" +
                        "        </div>\n" +
                        "\n" +
                        str +
                        "\n" +
                        "    </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "</div>" + "</div>";



                }


                $('.all_orders').append(html_append);
                $('.scripts').append("<script src=\"js/save_moded.js\"></script>")



                // if (response == "Wrong Details") {
                //
                //
                //
                // } else {
                //     var seller = JSON.parse(response);
                //     console.log(seller);
                //     sessionStorage.setItem("sellerID", seller.sellerID);
                //     sessionStorage.setItem(("storeName"), seller.appName);
                //     sessionStorage.setItem("sellerName", seller.firstName + " " + seller.lastName);
                //
                // }
                //
                // window.location.href = "Inventory.jsp";

                // if (data != 1) {
                //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'red');
                // }
                // else {
                //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'green');
                // }

            }
        });

    }

    if (($(".all_abandoned_orders")[0])) {


        var sellerID = sessionStorage.getItem("sellerID");




        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/FetchCarts',
            data: {
                SellerID: sellerID
            },

            success: function (response) {

                DEBUG && console.log(response);

                var obj = JSON.parse(response);

                var html_append = "";

                for (var i = 0; i < obj.length; i++) {


                    var accepted = false;

                    var str = "<div class=\"order_btns_wrapper\">\n" +
                        "            <button class=\"decline_order order_btns\">Decline</button>\n" +
                        "            <button class=\"accept_order order_btns\">Accept</button>\n" +
                        "        </div>";

                    // if (obj[i]["accept"] == "ACCEPTED") {
                    //     str = "<div class=\"order_btns_wrapper\">\n" +
                    //         "    <button class=\"complete_order order_btns\">Mark as Completed</button>\n" +
                    //         "</div>"
                    // }

                    html_append += "<div class=\"order_wrapper\">\n" +
                        "\n" +
                        "    <div class=\"all_order_products\">"


                    // html_append += "<div>\n" +
                    //     "    <p class=\"order_id\">Order ID: " + obj[i]["orderId"] + "</p>\n" +
                    //     "</div>"

                    for (var j = 0; j < obj[i]["products"].length; j++) {

                        html_append += "<div class=\"order_prod_wrapper\">\n" +
                            "            <img src=\"img/product_image.jpg\" class=\"order_image\">\n" +
                            "            <section class=\"order_prod_name\">" + obj[i]["products"][j]["name"] + "</section>\n" +
                            "            <div class=\"order_prod_details\">\n" +
                            "                <span class=\"order_prod_price\">Price: " + obj[i]["products"][j]["price"] + "</span>\n" +
                            "                <span class=\"order_prod_qty\">Qty.: " + obj[i]["quantities"][j] + "</span>\n" +
                            "            </div>\n" +
                            "        </div>"

                    }

                    html_append += "<div class=\"order_bottom\">\n" +
                        "\n" +
                        "        <div class=\"order_buyer_details\">\n" +
                        "            <span class=\"order_buyer_name\">" + obj[i]["firstName"] + " " + obj[i]["lastName"] + "</span>\n" +
                        "            <span class=\"order_buyer_email\">" + obj[i]["email"] + "</span>\n" +
                        "            <span class=\"order_buyer_phnumber\">" + obj[i]["mobileNum"] + "</span>\n" +
                        "        </div>\n" +
                        "\n" +
                        "    </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "</div>" + "</div>";



                }


                $('.all_abandoned_orders').append(html_append);
                $('.scripts').append("<script src=\"js/save_moded.js\"></script>")



                // if (response == "Wrong Details") {
                //
                //
                //
                // } else {
                //     var seller = JSON.parse(response);
                //     console.log(seller);
                //     sessionStorage.setItem("sellerID", seller.sellerID);
                //     sessionStorage.setItem(("storeName"), seller.appName);
                //     sessionStorage.setItem("sellerName", seller.firstName + " " + seller.lastName);
                //
                // }
                //
                // window.location.href = "Inventory.jsp";

                // if (data != 1) {
                //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'red');
                // }
                // else {
                //     $('h6:contains("Cream of Mushroom")').parent().css('background-color', 'green');
                // }

            }
        });

    }
    if (($(".styled-table")[0])) {
        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/orderPerCustomer',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },
            success: function (response) {

                var obj = JSON.parse(response);

                var html_append = "";
                var html_append = "";

                for (var i = 0; i < obj.length; i++) {
                    html_append += "<tr>\n" +
                        "                            <td>" + obj[i]["email"] + "</td>\n" +
                        "                            <td>" + obj[i]["count"] + "</td>\n" +
                        "                        </tr>"

                }

                $('.tableBody').append(html_append);
            }
        });
    }


    if (($(".modify_details")[0])) {

        $(".modify_details").hide();
        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/ModifyAccountDetails',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },
            success: function (response) {
                var seller = JSON.parse(response);
                $("#firstName").val(seller.firstName);
                $("#lastName").val(seller.lastName);
                $("#storeName").val(seller.appName);
                $("#description").val(seller.Description);
                $("#email").val(seller.email);
                $("#phone").val(seller.phoneNumber);
                $("#pass").val("");
                $("#ConfirmPass").val("");
                $("#instaID").val(seller.InstaHandle);
                $("#fbID").val(seller.FbHandle);
                $(".modify_details").show();
            }
        });
    }

    var modifySaveBut = $("#modify_account_button");

    modifySaveBut.on('click', function () {
        var FirstName = $('#firstName').val();
        var LastName = $('#lastName').val();
        var storeName = $('#storeName').val();
        var description = $('#description').val();
        var EmailID = $('#email').val();
        var Password = $('#password').val();

        var MobileNumber = $('#phone').val();
        var instaID = $('#instaID').val();
        var fbID = $('#instaID').val();
        // send ajax request

        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/ModifyAccountDetails',
            data: {
                SellerID: sessionStorage.getItem("sellerID"),
                FirstName: FirstName,
                LastName: LastName,
                EmailID: EmailID,
                Password: Password,
                MobileNumber: MobileNumber,
                StoreName: storeName,
                Description: description,
                InstaHandle: instaID,
                FbHandle: fbID
            },

            success: function (response) {

                DEBUG && console.log(response);

                if (response == "Seller Info Updated") {
                    window.alert("Account Details Updated!");
                    window.location.href = "ModifyAccountDetails.jsp";
                }

                //TODO password too weak

            }
        });
    });

    if (($("#analytics_page")[0])) {

        $("#analytics_page").hide();
        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/FinancialReports',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },
            success: function (response) {
                var seller = JSON.parse(response);
                console.log(response);
                $("#total_sales").text(seller.sales)
                $("#avg_order_value").text(seller.avg)
                // $("#analytics_page").show();
            }
        });

        $.ajax({
            type: 'get',
            url: '/SimpleSell_war/orderPerCustomer',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },
            success: function (response) {
                var seller = JSON.parse(response);
                console.log(response);
                // $("#total_sales").text(seller.sales)
                // $("#avg_order_value").text(seller.avg)
                // // $("#analytics_page").show();
            }
        });

        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/returningCustomer',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },
            success: function (response) {
                var seller = JSON.parse(response);
                console.log(response);
                $("#ret_cust_rate").text(seller.returningRate + "%");
                // $("#analytics_page").show();
            }
        });

        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/GetNumberOrders',
            data: {
                SellerID: sessionStorage.getItem("sellerID")
            },
            success: function (response) {
                var seller = JSON.parse(response);
                console.log(response);
                $("#total_orders").text(seller.temp);
                $("#analytics_page").show();
            }
        });
    }

    $('.sub_input').hide();
    $('#has_no_sub_add').hide();





    $('#is_sub_add').on('click', function () {
        $('.sub_input').show();
        $('#has_sub_add').show();
        $('#has_no_sub_add').hide();
        $
    });


    $('#has_sub_add').on('click', function () {
        $('#has_no_sub_add').show();
        $('#has_sub_add').hide();
        $('.sub_input').hide();
    });

    $('#has_no_sub_add').on('click', function () {
        $('#has_no_sub_add').hide();
        $('#has_sub_add').show();
        $('.sub_input').hide();
    });

    var addButton = $('#save_add_prod');

    addButton.on('click', function () {
        var sellerID = sessionStorage.getItem("sellerID");
        var title = $('#title').val();
        var description = $('#description').val();
        var price = $('#price').val();
        var category = $('#category').val();

        var hasSub = 0;

        var parentID = 0;

        if ($('#has_sub_add').is(":hidden")) {
            hasSub = 1;

        } else if (!$('.sub_input').is(":hidden")) {
            parentID = $('#sub_of').val();
        }


        DEBUG && console.log(title);
        DEBUG && console.log(hasSub);
        DEBUG && console.log(description);
        DEBUG && console.log(price);
        DEBUG && console.log(category);
        DEBUG && console.log(parentID);
        DEBUG && console.log(sellerID);



            $.ajax({
                type: 'post',
                url: '/SimpleSell_war/InventoryAdd',
                data: {
                    Name: title, hasSubcategories: hasSub, Description: description, price: price,
                    Category: category, inStock: 1, ProductID: parentID, SellerID : sellerID
                },
                success: function (response) {

                    DEBUG && console.log(response);

                    if (response == "Product Inserted") {
                        window.alert("Product added!")
                    }
                }
            });


    });






})(jQuery);