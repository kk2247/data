<%@ page import="com.kyka.data.entity.Manager" %>
<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
	<title>Karmo - Responsive Creative HTML Template</title>
	<!-- META TAGS -->
</head>
<body>
<%
    Manager manager= (Manager) request.getAttribute("user");
%>
<h1><%manager.getUserName();%></h1>
dasf
</body>
</html>
