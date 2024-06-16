'use strict';


(function ($) {
    var DEBUG = true;
    $('.save_mod_prod').on('click', function () {

        DEBUG && console.log("Save Button Pressed");

        var prod_name = $(this).parent().find('.prod_title').val();
        var prod_desc = $(this).parent().find('.prd_desc').val();
        var prod_price = $(this).parent().find('.prod_title_price').val();
        var prod_cat = $(this).parent().find('.prod_cat').val();
        var tempProd = $(this).parent().parent().find('.prod_id').text();
        var prod_id = tempProd.substring(12);

        var stock;
        if ($(this).parent().parent().find('.sold_out').is(":hidden")) {
        stock = 0;
    } else {
            stock = 1;


        }

        DEBUG && console.log(stock);

        //add other details

        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/ModifyProducts',
            data: {
                ProductID: prod_id,
                Category: prod_cat,
                Name: prod_name,
                hasSubcategories: 0,
                Price: prod_price,
                inStock: stock,
                Description: prod_desc,
            },

            success: function (response) {
                DEBUG && console.log(response);
                DEBUG && console.log(response);


                if (response == "Updated successfully") {
                    window.alert("Product Details Updated!");
                    window.location.href = "Inventory.jsp";


                }



            }
        });




    });


    $('.in_stock').on('click', function () {
        $(this).hide()
        $(this).parent().find('.sold_out').show();
    });

    $('.sold_out').on('click', function () {
        $(this).hide()
        $(this).parent().find('.in_stock').show();
    });

    $('.delete_prod').on('click', function () {
        var prod_id = $(this).parent().find('.prod_id').text();
        prod_id = prod_id.substring(12);

        DEBUG && console.log(prod_id)

        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/DeleteProduct',
            data: {
                ProductID: prod_id
            },

            success: function (response) {
                DEBUG && console.log(response);

                window.location.href = "Inventory.jsp";
            }
        });

    });



    $('.accept_order').on('click', function () {

        var order_id = $(this).parent().parent().parent().find('.order_id').text();
        order_id = order_id.substring(10);
        DEBUG && console.log(order_id);


        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/AcceptOrder',
            data: {
                OrderId: order_id
            },

            success: function (response) {
                DEBUG && console.log(response);

                window.location.href = "Orders.jsp";
            }
        });
    });

    $('.decline_order').on('click', function () {

        var order_id = $(this).parent().parent().parent().find('.order_id').text();
        order_id = order_id.substring(10);
        DEBUG && console.log(order_id);


        $.ajax({
            type: 'post',
            url: '/SimpleSell_war/RejectOrder',
            data: {
                OrderId: order_id
            },

            success: function (response) {
                DEBUG && console.log(response);

                window.location.href = "Orders.jsp";
            }
        });
    });


})(jQuery);