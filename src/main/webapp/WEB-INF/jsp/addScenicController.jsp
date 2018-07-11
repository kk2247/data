<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kyka.data.entity.Manager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kyka.data.entity.ScenicSpot" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manager controller</title>
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
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Dream</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="managerIndex.jsp"></a>
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
                        <a class="active-menu" href="managerIndex.jsp"><i class="fa fa-dashboard"></i>Scenic Spot</a>
                    </li>
                    <li>
                        <a href="ui-elements.html"><i class="fa fa-desktop"></i> Line</a>
                    </li>
					<li>
                        <a href="chart.html"><i class="fa fa-bar-chart-o"></i>Publish</a>
                    </li>
                </ul>

            </div>
        </nav>
        <!-- /. NAV SIDE  -->

        <div id="page-wrapper">
            <form action="addScenic" method="post">
                <table style="border: white">
                    <tr>
                        <td>name</td>
                        <td>population</td>
                        <td>relax</td>
                        <td>toilet</td>
                        <td>introduce</td>
                    </tr>
                    <tr>
                        <td><input  type="text" name="name"/></td>
                        <td><input type="text" name="welcome" /></td>
                        <td><input  type="text" name="relax"/></td>
                        <td><input  type="text" name="toilet"/></td>
                        <td><input type="text" name="introduce"/></td>
                    </tr>
                    <tr><button type="submit">submit</button></tr>
                </table>
            </form>


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