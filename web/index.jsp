<%@page import="java.sql.Connection"%>
<%@page import="DBconnector.ConnectionManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import ="Useful.Category"%>
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
    <title>Info</title>
  </head>
  <body>
  <%
      Category c = new Category();

      c.addCategories();

      c.returnCategories();

      c.returnElementsPerProtocol();

      c.returnFirstAppearences();

      c.returnProtocolMetadata();

      c.returnProtocolEmbeddingMethods();


  %>
  </body>
</html>
