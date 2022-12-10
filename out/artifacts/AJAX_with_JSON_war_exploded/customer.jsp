<%--
  Created by IntelliJ IDEA.
  User: Lakmal
  Date: 12/7/2022
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


    <%--    Jason Use krddi setpes tika-->--%>
    <%--      1.forms,formmethord,formactin okkoma ain wenna ona.--%>
    <%--            {   form action ekn ynwa kiynne page eka aniwrn refresh wela ynne }--%>
    <%--      2. button type eka "button" wena ona.--%>


</head>
<body>

<form id="customerForm">

    <input id="custoemrId" type="text" placeholder="Id" name="id">
    <input id="custoemrName" type="text" placeholder="Name" name="name">
    <input id="custoemrAddress" type="text" placeholder="Address" name="address">
    <input id="custoemrSalary" type="text" placeholder="Salary" name="salary">


    <button type="button" id="btnSave">Save</button>


</form>


<script src="assests/jquery-3.6.1.min.js"></script>

<script>


   /*Do get using QurrySString*/
    // $("#btnSave").click(function () {
    //     //send ajax request to customer servlet to get methord..(ui ekn get methord ekta req ekk gnnwanm)
    //
    //     let customerId = $("#custoemrId").val();
    //     let customerName = $("#custoemrName").val();
    //     let customerAddress = $("#custoemrAddress").val();
    //     let customerSalary = $("#custoemrSalary").val();
    //
    //     $.ajax({
    //         /*Do get ekta ui ekn value ekk enwann qurryString ekk widhta enna one*/
    //
    //         url: "customer?id=" + customerId + "&name=" + customerName,
    //
    //     });
    // });



   /*DO post METHORD*/

$("#btnSave").click(function (){



   var res =$("#customerForm").serialize();
     // qurryString form data ekkta genarate krnwa
      console.log(res);
      $.ajax({
          //send req to cutomer servelt
          url: "customer",
          method: "post",
          data: res
          // serialize use kral convert krla gnna data tika "data methord eka dnwaa


      });

      });



</script>


</body>
</html>
