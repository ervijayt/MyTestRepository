<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <link rel="stylesheet" href="../css/main.css" ></link>
    <link rel="stylesheet" href="../css/account.css" ></link>
    <link type="text/css" href="../css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet" ></link>
 	<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="../js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript">
    $(function() {
        $( "#departureDate" ).datepicker({
            showOn: "button",
            buttonImage: "css/ui-lightness/images/calendar.gif",
            buttonImageOnly: true
        });
    });
    </script>
</head>
<body class="Signin">
	<jsp:include page="/include/adminHeader.jsp" />
    <div id="Wrapper">
      <div class="Container">
        <div id="ContentFrame" class="clearfix">
          <div class="Left" style="margin: 0px auto">
            <div class="col">
  <div id="errors1" class="errors">
    <c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
  </div>
  <h1>Schedule a flight</h1>
  <form:form method="post" action="scheduleFlight" commandName="AddSchedule">


    <dl class="vertical">
      <dt><label for="flight" class="required">Choose a Flight</label></dt>
      <dd>
      	<form:select name="flight" path="scheduledFlight.flight.flightId" type="text" id="flight" class="required" title="The flight to be assigned">
      		<c:forEach items="${ flights }" var="flight">
      			<form:option value="${flight.flightId}" label="${flight.flightName}"></form:option>
      		</c:forEach>
      	</form:select>
      </dd>
      <dd class="hint">This is the plane to be flown</dd>
      <dt><label class="required" for="route">Route</label></dt>
      <dd>
        <form:select name="route" path="scheduledFlight.route.routeId" type="text" id="route" class="required" title="The city pair between which the flight will fly">
      		<c:forEach items="${ routes }" var="route">
      			<form:option value="${route.routeId}" label="${route.fromCity.cityName} - ${route.toCity.cityName }"></form:option>
      		</c:forEach>
      	</form:select>
      </dd>
      <dd class="hint">The city pair between which the flight will fly</dd>
      <dt><label for="distance" class="required">Distance</label></dt>
      <dd><form:input name="distance" path="scheduledFlight.distanceInKms" type="text" id="distance" class="required email" title="The distance in Km" />Kms</dd>
      <dt><label for="departureDate" class="required">Departure Date</label></dt>
      <dd><form:input class="datePicker required" type="text" path="scheduledFlight.scheduledFlightDate" name="departureDate" title="Departure date" id="departureDate" size="10" placeholder="Departure Date" /></dd>
      
      <dt><label for="departureTime" class="required">Departure time</label></dt>
      <dd><form:input name="departureTime" path="scheduledFlight.departureTime" type="text" id="departureTime" class="required email" title="The departure time" />hrs</dd>
      <dt><label for="arrivalTime" class="required">Arrival time</label></dt>
      <dd><form:input name="arrivalTime" path="scheduledFlight.arrivalTime" type="text" id="arrivalTime" class="required email" title="The arrival time" />hrs</dd>
      
      <c:forEach items="${ classes }" var="classItem" varStatus="status" begin="0">
      		<dt><label for="class${classItem.classType }" class="required">Cost of ${classItem.classType } Class</label></dt>
      		<dd><form:input  path="ticketCosts[${status.count -1 }].costPerTicket" type="text" id="class${classItem.classType }" class="required email" title="The arrival time" /> Rupees</dd>
      		<form:input type="hidden" path="ticketCosts[${status.count-1 }].flightClass.classId" value="${classItem.classId }" />
      </c:forEach>
      
      <dd class="submit"><button type="submit" id="signInButton" class="primary">Add</button><img src="../images/animations/spinner.gif" id="wait_spinner" style="display: none"></dd>
    </dl>
  </form:form>
</div>


          </div>
          <div class="Right">
  <div class="col clearfix">
	<img src="../images/suitcase.png" height="500" width="500" style="margin-left: 40px;">
</div>
        </div>
      </div>
    </div>

    <div id="Footer">
      <div class="FooterContainer">
        © 2012 Happytrip Travel Services Private Limited
      </div>
    </div>

  

</body>
</html>
 