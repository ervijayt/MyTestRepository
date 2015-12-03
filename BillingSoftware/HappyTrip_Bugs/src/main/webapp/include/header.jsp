<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
		<div id="Header">
		<!-- TMPL_CALL global-nav -->
		


<ul class="inline" id="global">
	<sec:authorize access="isAuthenticated()">
		<li>Welcome <sec:authentication property="principal.username" /></li>
   		<li><a class="forceHttps" href="<c:url value="/mytrips.html"/>" rel="nofollow">My Trips</a></li>
   		<li><a class="forceHttps" href="<c:url value="/aboutus.html"/>" rel="nofollow">About us</a></li>
   		<li><a class="forceHttps" href="<c:url value="/profile.html"/>" rel="nofollow">View and Edit profile</a></li>
   		<li><a class="forceHttps" href='<c:url value="/logout" />' rel="nofollow">Sign Out</a></li>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
    	<li><a class="forceHttps" href="<c:url value="/login.html"/>" rel="nofollow">Sign in</a></li>
    	<li><a class="forceHttps" href="<c:url value="/aboutus.html"/>" rel="nofollow">About us</a></li>
    	<li><a class="forceHttps" href="<c:url value="/loginAdmin.html"/>" rel="nofollow">Log in as admin</a></li>
		<li><a href="<c:url value="/register.html"/>" rel="nofollow">Register</a></li>
	</sec:authorize>
	
</ul>

		<div class="LogoContainer">
	<a class="forceHttp" href="<c:url value="/home.html"/>" title="HappyTrip's home page"><img src="<c:url value="/images/logo.png"/>" alt="Happytrip: India's Favourite Flights and Hotels Booking Agency"></a>
</div>
		

	</div>

</body>
</html>