<%@ page import="com.kyka.data.entity.Car" %>
<%@ page import="javafx.concurrent.Task" %>
<%@ page import="java.util.*" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>
	<%
		int size= (int) request.getAttribute("number");
		List<Car> cars= (List<Car>) request.getAttribute("cars");
	%>
	<title>停车场登记</title>

	<!-- Meta-Tags -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- //Meta-Tags -->

	<!-- Style --> <link rel="stylesheet" href="http://127.0.0.1:8080/data/garagecss/style.css" type="text/css" media="all">



</head>
<!-- //Head -->

<!-- Body -->
<body>
<a href="http://127.0.0.1:8080/data/index">index</a>

	<h1>停车场登记</h1>

	<div class="container w3layouts agileits">

		<div class="login w3layouts agileits">
			<h2>登 记</h2>
			<form action="http://127.0.0.1:8080/data/getin" method="post">
				<input type="text" name="licenseNum" placeholder="车牌号" required="">
				<div class="send-button w3layouts agileits">
					<input type="submit" value="登 记">
				</div>
			</form>
			<form action="http://127.0.0.1:8080/data/getout" method="post">
				<select  name="licenseNum" >
					<%
						if(cars!=null){
							if(cars.size()!=0){
						for(Car car:cars){
					%>
					<option value="<%=car.getLicenseNum()%>"><%=car.getLicenseNum()%></option>
					<%
						}
					%>
				</select>
				<div class="send-button w3layouts agileits">
					<input type="submit" value="退 出">
				</div>
				<%
						}}
				%>
			</form>

			<div class="clear"></div>
		</div><div class="copyrights">Collect from</div>
		<div class="register w3layouts agileits">
			<h2>当前信息</h2>
			<h2 >剩余车位：</h2>
			<h2>
				<%=size%>
			</h2>
			<%--<form action="#" method="post">--%>
				<%--<input type="text" Name="Name" placeholder="用户名" required="">--%>
				<%--<input type="text" Name="Email" placeholder="邮箱" required="">--%>
				<%--<input type="password" Name="Password" placeholder="密码" required="">--%>
				<%--<input type="text" Name="Phone Number" placeholder="手机号码" required="">--%>
			<%--</form>--%>
			<%--<div class="send-button w3layouts agileits">--%>
				<%--<form>--%>
					<%--<input type="submit" value="免费注册">--%>
				<%--</form>--%>
			<%--</div>--%>
			<%--<div class="clear"></div>--%>
		</div>

		<div class="clear"></div>
<%
	String str= (String) request.getAttribute("result");
	if(str!=null){
	if(str.equals("true")){
	    %>
		<img src="http://127.0.0.1:8080/data/garageimages/1a8b889117dfb1833319014b314e6b7f.gif">
		<%
	}else{
		%>
		<img src="http://127.0.0.1:8080/data/garageimages/fb5eca2195a14242aa980bf20bff97cf_afb49a529822720e909d3d6371cb0a46f31fab98.png">
		<%
	}
			}
%>
	</div>

</body>
<!-- //Body -->

</html>