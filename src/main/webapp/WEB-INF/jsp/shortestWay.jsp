<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kyka.data.entity.Line" %>
<%@ page import="com.kyka.data.entity.Notice" %>
<%@ page import="com.kyka.data.entity.ScenicSpot" %>
<%@ page language="java" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%
        String result= (String) request.getAttribute("shortResult");
            ArrayList<String> shortestWay= (ArrayList<String>) request.getAttribute("shortestWay");
            int length= (int) request.getAttribute("length");
        Notice notice= (Notice) request.getAttribute("notice");
        ArrayList<ScenicSpot> scenicSpot= (ArrayList<ScenicSpot>) request.getAttribute("scenicSpots");
    %>
    <style type="text/css">
        #table-1 thead, #table-1 tr {
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: rgb(230, 189, 189);
        }
        #table-1 {
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: rgb(230, 189, 189);
        }

        /* Padding and font style */
        #table-1 td, #table-1 th {
            padding: 5px 10px;
            font-size: 12px;
            font-family: Verdana;
            color: rgb(177, 106, 104);
        }

        /* Alternating background colors */
        #table-1 tr:nth-child(even) {
            background:rgb(223, 216, 232);
        }
        #table-1 tr:nth-child(odd) {
            background: #FFF
        }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Education</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://127.0.0.1:8080/data/css/templatemo_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<a href="http://127.0.0.1:8080/data/index">index</a>

<div id="templatemo_header_wrapper">
    <div id="templatemo_header">
        <div id="site_title">
            <h1><a href="#">
                <img src="http://127.0.0.1:8080/data/customerimages/templatemo_logo.png" alt="Site Title" />
                <span>welcome</span>
            </a></h1>
        </div>
        <p>Notice</p>
        <p><%=notice.getContent()%></p>
    </div> <!-- end of templatemo_header -->

</div> <!-- end of templatemo_menu_wrapper -->

<div id="templatemo_menu_wrapper">
    <div id="templatemo_menu">
        
        <ul>
            <li><a href="http://127.0.0.1:8080/data/customer" class="current">Graph</a></li>
            <li><a href="http://127.0.0.1:8080/data/guild">guider</a></li>
            <li><a href="http://127.0.0.1:8080/data/shortestway">shortest way</a></li>
            <%--<li><a href="#">Contact</a></li>--%>
        </ul>    	
    
    </div> <!-- end of templatemo_menu -->
</div>

<div id="templatemo_content_wrapper">
    <form action="http://127.0.0.1:8080/data/short" method="post">
        <div>start</div>
        <select name="start">
            <%
                for(ScenicSpot scenicSpot1:scenicSpot){
            %>
            <option value="<%=scenicSpot1.getName()%>"><%=scenicSpot1.getName()%></option>
            <%
                }
            %>
        </select>
        <div>end</div>
        <select name="end">
            <%
                for(ScenicSpot scenicSpot1:scenicSpot){
            %>
            <option value="<%=scenicSpot1.getName()%>"><%=scenicSpot1.getName()%></option>
            <%
                }
            %>
        </select>
        <button type="submit">submit</button>
    </form>

        <table id="table-1">
        <%
            if(result.equals("true")){
            if(shortestWay!=null){
                int i=1;
                for(String str:shortestWay){
                    %>
            <tr>
                <td><h4>pass<%=i%></h4></td>
                <td><h4><%=str%></h4></td>
            </tr>
            <%
                    i++;
                }%>
                <h4>length:<%=length%></h4>
<%            }}
        %>
        </table>

</div>

<div id="templatemo_footer_wrapper">

    <div id="templatemo_footer">

    </div>
    
</div>
<!-- templatemo 213 education -->
<!-- 
Education Template 
http://www.templatemo.com/preview/templatemo_213_education 
-->
</body>
</html>