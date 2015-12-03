<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/css/main.css"/>"></link>
<link rel="stylesheet" href="<c:url value="/css/flight_seats.css"/>"></link>

</head>
<body>
	<jsp:include page="/include/adminHeader.jsp" />
	<div id="Header">
		<!-- TMPL_CALL global-nav -->

		
		
		<div id="Tabs">
			<ul id="MainTabs" class="inline">
				<li><a href="<c:url value="/admin/report/listAllUsers.html"/>"
					class="Flights first forceHttp" title="List all users">List All Users</a>
				</li>
				<li><a href="<c:url value="/admin/report/listAllBookings.html"/>"
					class="Hotels forceHttp" title="Add an flight">List All Bookings</a>
				</li>
				<li><a href="<c:url value="/admin/report/listAllAirline.html"/>"
					class="Rail forceHttp" title="Add Route">List All Airline</a>
				</li>
				<li><a href="<c:url value="/admin/report/listAllScheduledFlight.html"/>"
					class="Rail forceHttp" title="List All Scheduled Flights">List All Scheduled Flights</a>
				</li>
				<li><a href="<c:url value="/admin/report/listAllRoutes.html"/>"
					class="Rail forceHttp" title="List All Routes">List All Routes</a>
				</li>
			</ul>
		</div>

	</div>

</body>
