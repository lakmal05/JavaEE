<%@ page import="java.util.ArrayList" %>
<%@ page import="model.CustomerDTO" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.mysql.cj.jdbc.Driver" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Lakmal
  Date: 12/6/2022
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CuSTOMER JSP</title>
</head>
<body>




<%--we have 3 extentions in jsp file ->--%>
<%--    1.scriplet--%>
<%--    2.Expression--%>
<%--    3.Declaration--%>



<%--the input Ui--%>

<form action="customer"  id="customerForm">
    <!--    ona krna methord eka dnna one-->


    <input type="text" placeholder="Id" name="id">
    <input type="text" placeholder="Name"  name="name">
    <input type="text" placeholder="Address" name="address">
    <input type="text" placeholder="Salary" name="salary">


    <%--   form action ekata deela thiyna ekn page eka refresh krgnna form action ekta jsp file eka denwa--%>
    <button id="btnSave"   formaction="customer?option=add"      formmethod="post">Save</button>
    <button id="btnGetAll" formaction="customer.jsp"             formmethod="post">get All</button>
    <button id="btnDelete" formaction="customer?option=delete"   formmethod="post">Delete</button>


</form>
<button id="btnUpdate" form="customerForm" formaction="customer.jsp" formmethod="post">Update</button>





<%--&lt;%&ndash;Without database using Customer DTO and FOREACH loop load to tabe data&ndash;%&gt;-----------------------------------------%>

<%--<%--%>

<%--    ArrayList<CustomerDTO> allCustomer = new ArrayList();--%>

<%--    allCustomer.add(new CustomerDTO("C001", "Lakmal", "address", 100.00));--%>
<%--    allCustomer.add(new CustomerDTO("C002", "name1", "address", 100.00));--%>
<%--    allCustomer.add(new CustomerDTO("C003", "name3", "address", 100.00));--%>
<%--    allCustomer.add(new CustomerDTO("C004", "Hansi", "address", 100.00));--%>

<%--%>--%>

<%--&lt;%&ndash;array ekin data gnna one nm for each ek dnwa&ndash;%&gt;--%>

<%--&lt;%&ndash;starte table&ndash;%&gt;--%>
<%--<table>--%>
<%--        <tr>--%>
<%--            <th>id</th>--%>
<%--            <th>name</th>--%>
<%--            <th>addrss</th>--%>
<%--            <th>salary</th>--%>
<%--    <%--%>
<%--        for (CustomerDTO c : allCustomer) {--%>
<%--        %>--%>

<%--    <tr>--%>
<%--        <td> <%= c.getId()%></td>--%>
<%--        <td> <%= c.getName()%></td>--%>
<%--        <td><%= c.getAddress()%> </td>--%>
<%--        <td> <%= c.getSalary()%></td>--%>
<%--    </tr>--%>


<%--    <%--%>
<%--           }--%>
<%--    %>--%>

<%--</table>--%>

<%--&lt;%&ndash;tabel end hear&ndash;%&gt;--%>

<%--&lt;%&ndash;End&ndash;%&gt;---------------------------------------------------------------------------------------------------------%>


<%--Using DATABASEE------------------------------------------------------------------------------------------------------------------%>

<%
    ArrayList<CustomerDTO> allCustomer=new ArrayList();

    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","1234");
    PreparedStatement pstm=connection.prepareStatement("select * from customer");
    /*show data  wge ewata executeQurry : update input ewta executeUpdate*/
    ResultSet rst = pstm.executeQuery();

    while (rst.next()){

        String id = rst.getString("customerId");//rst.get string eka thule thiyna value eka db eke thiyna value eka wge wenma one
        String name = rst.getString("custmerName");
        String address = rst.getString("customerAddress");
        double salary = rst.getDouble("customerSalary");

        allCustomer.add(new CustomerDTO(id,name,address,salary));

    }
%>


<table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>addrss</th>
            <th>salary</th>
        </tr>
    <%
        for (CustomerDTO c : allCustomer) {
        %>

    <tr>
        <td> <%= c.getId()%></td>
        <td> <%= c.getName()%></td>
        <td><%= c.getAddress()%> </td>
        <td> <%= c.getSalary()%></td>
    </tr>


    <%
           }
    %>

</table>

<%--End Hear--------------------------------------------------------------------------------------------------------%>



</body>
</html>
