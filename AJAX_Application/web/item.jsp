<%--
  Created by IntelliJ IDEA.
  User: Lakmal
  Date: 12/10/2022
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    table, th, tr {
        border: 1px solid black;
    }

</style>
<html>
<head>
    <title>Item jsp file</title>
</head>
<body>


<form id="itemForm">
    <label>id</label>
    <input id="txtItemID" type="text" name="id">

    <label>name</label>
    <input id="txtItemName" type="text" name="name">

    <label>qty</label>
    <input id="txtItemQty" type="text" name="qty">

    <label>price</label>
    <input id="txtItemPrice" type="text" name="price">


</form>

<button type="button" id="btngetAll">getAll</button>
<button type="button" id="btnAdd">Add</button>
<button type="button" id="btnDelete">Delete</button>
<button type="button" id="btnUpdate">Delte</button>


<table style="width:100% ; position: relative; top: 200px">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>QTY</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody id="tblItem">

    </tbody>


</table>


<script src="assests/jquery-3.6.1.min.js"></script>


<script>


    /*Item Add Button*/
    $("#btnAdd").click(function () {

        var formData = $("#itemForm").serialize();
        // will generate a query String including form data

        //send ajax request to the customer servlet
        $.ajax({
            // url: "item?option=add",
            url: "item",
            method: "post",
            data: formData,
            success: function () {
                console.log("item added")

            }
        });
    });


    /*btn Delete*/

    $("#btnDelete").click(function () {

        let id = $("#txtItemID").val();

        $.ajax({

            url: "item?id=" + id,

            method: "delete",
            // data: formData,
            success: function () {
                console.log("item delte req")
            }


        });


    });


    /*btmUpdate*/

    $("#btnUpdate").click(function () {


        let ItemID = $("#txtItemID").val();
        let ItemName = $("#txtItemName").val();
        let ItemQty = $("#txtItemQty").val();
        let ItemPrice = $("#txtItemPrice").val();

        // var formData = $("#itemForm").serialize();
        // form data ekk widhta put methord allganna bari nisa
        /*Lilteral base obj ekk hadgena ekta keys values set krgnnwa.Herders wala parts 2i ->Key and Value*/
        // content header eka wens krnwann mine type eka wens wenna one values welata

        // foces fully jason krnna hduwama keys jason format wenna one.natnn playlod eke wens widhta enneupdate wenne na.req ehder eka applicatoin/json widhta wens wea

        // eka nisa api Json wala thiyna "JSON.stringify(a)" use klaham js literl base obj ekk jason type ekta convert wenwa

        var item = {
            id: ItemID,
            name: ItemName,
            qty: ItemQty,
            price: ItemPrice,
        }
        /*values yanna ona nisa jason obj ekk widhta hadtta.form data ekk widhta put methord allganna bari nisa */
        // "Json.parase()" walin jason string obj ekk js walta convert wenwa

        $.ajax({

            url: "item",
            method: "put",
            contentType:"application/json",
            data:JSON.stringify(item),

            success:function (req){
                console.log("update in axj req send")
            }


        })


    })


    $("#btngetAll").click(function () {

            $("#tblItem").empty();

            $.ajax({

                    url: "item",
                    dataType: "json",
                    // method: "get",

                    success: function (resp) {
                        console.log("data showing")
                        console.log(resp)
                        for (let item of resp) {

                            var row = '<tr><td>' + item.id + '</td><td>' + item.name + '</td><td>' + item.qty + '</td><td>' + item.price + '</td></tr>';
                            $("#tblItem").append(row);
                        }
                    }
                }
            );


        }
    );


</script>


</body>
</html>
