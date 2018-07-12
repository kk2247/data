<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kyka.data.entity.Manager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kyka.data.entity.ScenicSpot" %>
<%@ page import="com.kyka.data.entity.Line" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <%
        String userName="";
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("account")){
                userName=cookie.getValue();
            }
        }
        List<ScenicSpot> scenic= (List<ScenicSpot>) request.getAttribute("scenic");
        ArrayList<ArrayList<Object>> way= (ArrayList<ArrayList<Object>>) request.getAttribute("way");
    %>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manager controller</title>
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
            background: #adadad;
        }
        #table-1 tr:nth-child(odd) {
            background: #FFF
        }
    </style>
    <!-- Bootstrap Styles-->
    <link href="http://127.0.0.1:8080/data/managercss/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="http://127.0.0.1:8080/data/managercss/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="http://127.0.0.1:8080/data/managerjs/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="http://127.0.0.1:8080/data/managercss/custom-styles.css" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>

<body>
<a href="http://127.0.0.1:8080/data/index">index</a>

    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Hello!<%=userName%></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="managerIndex.jsp">Hello!<%=userName%></a>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Doe</strong>
                                    <span class="pull-right text-muted">
                                        <em>Today</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since an kwilnw...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since the...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
            </ul>
        </nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">

                    <li>
                        <a class="active-menu" href="http://127.0.0.1:8080/data/managerIndex"><i class="fa fa-dashboard"></i>Scenic Spot</a>
                    </li>
                    <li>
                        <a href="http://127.0.0.1:8080/data/lineController"><i class="fa fa-desktop"></i> Line</a>
                    </li>
					<li>
                        <a href="http://127.0.0.1:8080/data/publish"><i class="fa fa-bar-chart-o"></i>Publish</a>
                    </li>
                </ul>

            </div>
        </nav>
        <!-- /. NAV SIDE  -->

        <div id="page-wrapper">
            <table id="table-1">
                <tr>
                    <td>name</td>
                    <td>population</td>
                    <td>relax</td>
                    <td>toilet</td>
                    <td>introduce</td>
                </tr>
                <c:forEach items="${scenic}" var="list">
                    <tr>
                        <td>${list.name}</td>
                        <td>${list.welcome}</td>
                        <td>${list.relax}</td>
                        <td>${list.toilet}</td>
                        <td>${list.introduce}</td>
                        <td><a href="http://127.0.0.1:8080/data/delete/${list.id}"> delete</a></td>
                        <td><a href="http://127.0.0.1:8080/data/change/${list.id}"> modify</a></td>
                    </tr>
                </c:forEach>

            </table>
            <a href="http://127.0.0.1:8080/data/add">add</a>
            <table id="table-1">
                <%
                    for(List<Object> list:way){
                %>
                <tr>
                    <%
                        for(Object object:list){
                            if(object instanceof ScenicSpot){
                    %>
                    <td><h4 style="color: brown"><%=((ScenicSpot) object).getName()%></h4></td>
                    <%
                    }else{
                    %>
                    <td><h4 style="color: brown"><%=((Line)object).getLength()%></h4></td>
                    <%
                            }
                        }
                    %>
                </tr>
                <%
                    }
                %>
            </table>

        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="http://127.0.0.1:8080/data/managerjs/jquery-1.10.2.js"></script>
    <!-- Bootstrap Js -->
    <script src="http://127.0.0.1:8080/data/managerjs/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="http://127.0.0.1:8080/data/managerjs/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="http://127.0.0.1:8080/data/managerjs/morris/raphael-2.1.0.min.js"></script>
    <script src="http://127.0.0.1:8080/data/managerjs/morris/morris.js"></script>
    <!-- Custom Js -->
    <script src="http://127.0.0.1:8080/data/managerjs/custom-scripts.js"></script>


</body>

</html>