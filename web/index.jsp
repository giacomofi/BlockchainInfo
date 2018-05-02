<%@page import="java.sql.Connection"%>
<%@page import="DBconnector.ConnectionManager"%>
<%@page import="java.sql.DriverManager"%>
<%--
  Created by IntelliJ IDEA.
  User: giaco

  Date: 01/03/2018
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>XD</title>
  </head>
  <body>
  <%
    ConnectionManager db = new ConnectionManager();
    Connection conn  = db.getConnection();
    if(conn == null){
        System.out.print("Connection failed");
    }
    else{
        System.out.print("Connection Succesful");
    }
    



  %>
  </body>
</html>
