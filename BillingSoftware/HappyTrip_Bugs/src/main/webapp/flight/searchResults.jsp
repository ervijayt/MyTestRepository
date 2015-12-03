<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Book your flights</title>
<link rel="stylesheet" href="../css/main.css"/>
<link rel="stylesheet" href="../css/flight_seats.css"/>
<script type="text/javascript" src="<c:url value="/js/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript">
	function selectOutbound(flightid){
		updateSelected('outbound',flightid);
	}
	
	function selectReturn(flightid){
		updateSelected('return',flightid);
	}
	
	function updateSelected(type, flightid){
		$('#' + type + '_flightid').val(flightid);
		$('#' + type + '_airline_name').html($('#airlinename_' + flightid).html());
		$('#' + type + '_flight_date').html($('#scheduleddate_' + flightid).html());
		$('#' + type + '_flight_departure_time').html($('#departuretime_' + flightid).html());
		$('#' + type + '_flight_arrival_time').html($('#arrivaltime_' + flightid).html());
		$('#' + type + '_flight_cost').html($('#totalcost_' + flightid).html());
		
		$('#' + type + '_airline_logo').removeAttr("class");
		$('#' + type + '_airline_logo').addClass('airline_logos');
		$('#' + type + '_airline_logo').addClass($('#airlinelogo_' + flightid).html());
	}
</script>
</head>
<body class="TwentyEighty Flights Results">
	<jsp:include page="/include/header.jsp" />
			<div id="Wrapper">
			<div class="Container">
				<div id="ContentFrame" class="clearfix">




					<div id="ModifySearchWrapper">
						<div id="SearchParams">
							<div id="SPRow">
								<div id="mod_link_wrapper">
									<a href="javascript: void(0);"
										title="Click here to make a new search" id="mod_link"
										class="toggle_closed">Modify your search</a>
								</div>
								<ul class="inline">
									<li class="no_border">${fromCity} - ${toCity}</li>
									<li><fmt:formatDate value="${SearchData.departureDate}"/>
									<c:if test="${SearchData.returnDate!=null}">, <fmt:formatDate value="${SearchData.returnDate}"/></c:if>
									<c:if test="${SearchData.noOfAdults>0}">, ${SearchData.noOfAdults} Adult</c:if>
									<c:if test="${SearchData.noOfChildren>0}">, ${SearchData.noOfChildren} Children</c:if>
									<c:if test="${SearchData.noOfInfants>0}">, ${SearchData.noOfInfants} Infants</c:if>
									</li>
								</ul>





								<div id="SalesUpsell">
									<div class="clearfix" id="SUWrapper">


										Prefer booking over the phone? <span class="channel phone">Call
											 080123456789 <span class="weak">local
												call from any phone</span>
										</span>






									</div>
								</div>


							</div>
						</div>

					</div>

				<!--  end of search params div -->


					<div class="Left">
						
						<!--  end of col div -->
					</div>
					<!--  end of left div -->

					<div class="Right clearfix">
						<div class="col clearfix">








							
							<!--
									<div id="PhoneCrossSell">
										Prefer booking on phone instead? Give us a call on <strong>1800 3000  </strong>
									</div>
									-->


							<div style="display: none;" id="warning" class="disallow">
								<span id="warning_msg">Sorry, you cannot book this
									combination. Please choose a return flight that flies out
									atleast 3 hours after you get to your destination. <a
									onclick="Form.enable($('universal_display')); new Effect.SlideUp('warning'); return false;"
									href="javascript: void(0);">Click here</a> to change your
									selection.
								</span>
							</div>

							<!-- start of universal display form -->
															<!-- show deals begins -->
								<div id="RoundtripSpecial" style="display: none;">
									<h4 style="display: none;" id="RoundtripSpecialReset">
										<a href="#">« Back to all flights</a>
									</h4>
									<h4 id="RoundtripSpecialTitle">
										<img src="/images/elements/icon_roundtrip.png"> <strong>Round
											trip discounts</strong> Get discounted fares when you book round trips
										on these airlines:
									</h4>
								</div>
								<!-- Round trip deals section ends -->
								<!-- show deals ends -->

								<!-- Fare displays begin -->

								<div id="SelectionInfo" class="clearfix" style="">
									<!-- Total price updates in cash register effect when user changes selection -->
									<div id="dynamic_price" class="dynamic_price" style="">
										<div id="RoundtripSpecialContanier" style="display: none;">
											<div id="RoundtripSpecialDiscounted" style="display: none;"></div>
										</div>
										<div id="nonDiscountedPrice" style="display: none;"></div>
										<!--[if IE]>
											<br style="clear:both;line-height:0px;" />
										<![endif]-->
										
									</div>


									<!-- Users current selection is updated here -->
									<div id="CurrentSelection" class="clearfix deal" style="">


										<form:form action="bookSelectedFlights.html" method="post" commandName="SelectedFlights">
										
										<div id="button">
											<button class="book booking" id="btnBook" type="submit"
												style="">Book</button>
										</div>


										<div id="selectedflights" style="">
											<c:if test="${SelectedFlights.selectedReturnFlight != null }">
											
											<div id="selectedreturn" class="flight clearfix">
												<form:hidden id="return_flightid" path="selectedReturnFlight.flightRouteId"/>
												<div class="airline_logos ${SelectedFlights.selectedReturnFlight.flight.airline.airlineCode}" id="return_airline_logo"></div>
												<h3><span id="return_airline_name">${SelectedFlights.selectedReturnFlight.flight.airline.airlineName}</span></h3>
												<span id="return_flight_date"><fmt:formatDate value="${SelectedFlights.selectedReturnFlight.scheduledFlightDate}"/></span><br>
												<span class="departs" id="return_flight_departure_time">${SelectedFlights.selectedReturnFlight.departureTime}</span> - <span class="arrives" id="return_flight_arrival_time">${SelectedFlights.selectedReturnFlight.arrivalTime}</span><br>
												<span id="return_flight_cost">
													<c:forEach var="cost" items="${SelectedFlights.selectedReturnFlight.flightRouteCosts}" varStatus="loopCount">
													        <c:if test="${loopCount.count eq 1}">
													         <td rowspan="1">${cost.costPerTicket}</td>
													        </c:if>
													    </c:forEach>
												</span>
											</div>
											</c:if>
											<c:if test="${SelectedFlights.selectedOutboundFlight != null }">
											<div id="selectedoutbound" class="flight clearfix">
												<form:hidden id="outbound_flightid" path="selectedOutboundFlight.flightRouteId"/>
												<div class="airline_logos  ${SelectedFlights.selectedOutboundFlight.flight.airline.airlineCode}" id="outbound_airline_logo"></div>
												<h3><span id="outbound_airline_name">${SelectedFlights.selectedOutboundFlight.flight.airline.airlineName}</span></h3>
												<span id="outbound_flight_date"><fmt:formatDate value="${SelectedFlights.selectedOutboundFlight.scheduledFlightDate}"/></span><br>
												<span class="departs" id="outbound_flight_departure_time">${SelectedFlights.selectedOutboundFlight.departureTime}</span> - 
												<span class="arrives" id="outbound_flight_arrival_time">${SelectedFlights.selectedOutboundFlight.arrivalTime}</span><br>
												<span id="outbound_flight_cost">
													<c:forEach var="cost" items="${SelectedFlights.selectedOutboundFlight.flightRouteCosts}" 
														varStatus="loopCount">
												        <c:if test="${loopCount.count eq 1}">
												         <td rowspan="1">${cost.costPerTicket}</td>
												        </c:if>
												    </c:forEach>
												</span>
											</div>
											</c:if>
											</form:form>
										</div>



									</div>
									<!-- end of current selection info -->
								</div>
								<!--  end of selection info div -->
								<div id="Notification" style="display: none">
									<span id="content"><span>Showing round trip
											specials.</span> <a href="javascript: void(0)">Back to all
											flights »</a></span>
								</div>



								<div id="update_note" class="UpdateMessage">
									<span>Updating Results...</span>
								</div>

								<div id="universalDiv" class="universal">


									<div class="sector" id="outbound_div">



										<div id="sector_info_heading" class="sector_info">



											<h2>${fromCity} to ${toCity}</h2>



										</div>
										<c:if test="${toFlights ==null || fn:length(toFlights) lt 1}">
										<div id="NoResults">
											<h2>Oops, we couldn't find any itinerary that matches
												your filter criteria.</h2>
											<p>
												Adjust the filters to the left to make them less restrictive
												or <a href="javascript: void(0);">start over without any
													filters</a>.
											</p>
										</div>
										</c:if>
										<c:if test="${toFlights !=null && fn:length(toFlights) gt 0 }">
										
										<div id="outbound">
											<table>
												<colgroup>
													<col width="21">
													<col width="36">
													<col width="17%">
													<col width="17%">
													<col width="20%">
													<col width="24%">
												</colgroup>
												<thead>
													<tr>
														<th></th>
														<th></th>
														<th>Date</th>
														<th>Departs</th>
														<th>Arrives</th>
														<th>Price</th>
													</tr>
												</thead>
												<tbody class="selected">
						
												<c:forEach var="flight" items="${toFlights}" varStatus="count">
														<c:forEach var="cost" items="${flight.flightRouteCosts}" varStatus="loopCount">
													        <c:if test="${loopCount.count eq 1}">
													        	<c:set var="totalCost" value="${cost.costPerTicket}"></c:set>
													         
													        </c:if>
													    </c:forEach>						
													<span style="display:none" id="airlinename_${flight.flightRouteId}">${flight.flight.airline.airlineName}</span>
													<span style="display:none" id="scheduleddate_${flight.flightRouteId}"><fmt:formatDate value="${flight.scheduledFlightDate}"/></span>
													<span style="display:none" id="departuretime_${flight.flightRouteId}">${flight.departureTime}</span>
													<span style="display:none" id="arrivaltime_${flight.flightRouteId}">${flight.arrivalTime}</span>	
													<span style="display:none" id="totalcost_${flight.flightRouteId}">${totalCost}</span>
													<span style="display:none" id="airlinelogo_${flight.flightRouteId}">${flight.flight.airline.airlineLogo}</span>
																																							
												<tr id="${flight.flightRouteId}">
													<td rowspan="2" class="button">
														<c:if test="${count.count eq 1 }">	
															<input type="radio"
															name="departureJourneyId" checked="checked" onclick="selectOutbound('${flight.flightRouteId}')">
														</c:if>
														<c:if test="${count.count > 1 }">
															<input type="radio"
															name="departureJourneyId" onclick="selectOutbound('${flight.flightRouteId}')">
														</c:if>
													</td>
														<td rowspan="2" class="airline_logo"><div
																class="airline_logos ${flight.flight.airline.airlineLogo}"></div></td>
														<td rowspan="2"><div>
														<fmt:formatDate value="${flight.scheduledFlightDate}"/>
														</div></td>
														
														<td>${flight.departureTime}</td>
														<td>${flight.arrivalTime}</td>
														<td rowspan="1">${totalCost}</td>
													</tr>
													<tr>
														<td colspan="2" class="noborder weak">${flight.flight.airline.airlineName}
															${flight.flight.flightName}, ${fromCity} to ${toCity}</td>
														<td class="noborder" style="" title=""></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									</c:if>
										
									<c:if test="${returnFlights !=null && fn:length(returnFlights) gt 0 }">

									<div class="sector" id="return_div">
										<div class="sector_info">

											<h2>${toCity} to ${fromCity}</h2>
										</div>
													
										<div id="return">
											<table>
												<colgroup>
													<col width="21">
													<col width="36">
													<col width="17%">
													<col width="17%">
													<col width="20%">
													<col width="24%">
												</colgroup>
												<thead>
													<tr>
														<th class="return"></th>
														<th></th>
														<th>Date</th>
														<th>Departs</th>
														<th>Arrives</th>
														<th>Price</th>
													</tr>
												</thead>
												<tbody class="selected">
									
												<c:forEach var="flight" items="${returnFlights}" varStatus="count">
														<c:forEach var="cost" items="${flight.flightRouteCosts}" varStatus="loopCount">
													        <c:if test="${loopCount.count eq 1}">
													        	<c:set var="totalCost" value="${cost.costPerTicket}"></c:set>
													         
													        </c:if>
													    </c:forEach>						
													<span style="display:none" id="airlinename_${flight.flightRouteId}">${flight.flight.airline.airlineName}</span>
													<span style="display:none" id="scheduleddate_${flight.flightRouteId}"><fmt:formatDate value="${flight.scheduledFlightDate}"/></span>
													<span style="display:none" id="departuretime_${flight.flightRouteId}">${flight.departureTime}</span>
													<span style="display:none" id="arrivaltime_${flight.flightRouteId}">${flight.arrivalTime}</span>	
													<span style="display:none" id="totalcost_${flight.flightRouteId}">${totalCost}</span>																										
													<span style="display:none" id="airlinelogo_${flight.flightRouteId}">${flight.flight.airline.airlineLogo}</span>
													
													<tr id="${flight.flightRouteId}">
														<td rowspan="2" class="return button">
														<c:if test="${count.count eq 1}">
															<input
															type="radio" checked="checked" name="arrivalJourneyId" onclick="selectReturn('${flight.flightRouteId}')">
														</c:if>
														<c:if test="${count.count > 1}">
															<input
															type="radio" name="arrivalJourneyId" onclick="selectReturn('${flight.flightRouteId}')">
														</c:if>
														</td>
														<td rowspan="2" class="airline_logo"><div
																class="airline_logos ${flight.flight.airline.airlineLogo}"></div></td>
														<td rowspan="2"><div>
														<fmt:formatDate value="${flight.scheduledFlightDate}"/>
														</div></td>
														<td>${flight.departureTime}</td>
														<td>${flight.arrivalTime}</td>
														<td rowspan="1">${totalCost}</td>
													</tr>
													<tr>
														<td colspan="2" class="noborder weak">${flight.flight.airline.airlineName}
															${flight.flight.flightName}, ${toCity} to ${fromCity}</td>
														<td class="noborder" style="" title=""></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								</c:if>
								<!-- end of universal div -->
							
							<!-- end of universal display form -->
						</div>
						<!--  end of col clearfix div -->
					</div>
					<!--  end of right clearfix div -->

				</div>
				<!--  end of content frame div -->
			</div>
		</div>
	<jsp:include page="/include/footer.jsp" />

</body>
</html>