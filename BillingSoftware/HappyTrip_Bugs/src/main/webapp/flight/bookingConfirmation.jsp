<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Confirm Booking</title>
<link rel="stylesheet" href="../css/main.css"/>
<link rel="stylesheet" href="../css/flight_seats.css"/>
    <style>
        .detailRow {
            font-family:arial,helvetica,verdana,sans-serif;font-size: 14px;color: #333;
        }
        
        .marginText {
            margin: 15px 0 15px 0;
        }
    </style>
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
									<li class="no_border">E - Ticket</li>
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
						<div class="col">

                                                            <img src="../images/trips.jpg" alt="" />
							<!--  end of search tools div -->
						</div>
						<!--  end of col div -->
					</div>
					<!--  end of left div -->

					<div class="Right clearfix">
						<div class="col clearfix">









							<form id="universal_display" action="initiate-booking"
								method="post" name="results">
								<!-- TMPL_VAR book-it-inputs -->


								
								<!-- Round trip deals section ends -->
								<!-- show deals ends -->

								<!-- Fare displays begin -->

								
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



									<div class="sector" style="width:100%" id="return_div">
										<div class="sector_info" style="font-size: 13px;">
											<jsp:useBean id="now" class="java.util.Date" />
											<div class="marginText"><b>Name : </b>${PrimaryPassenger.name}</div>
                                            <div class="marginText"><b>Date of birth : </b><fmt:formatDate value="${PrimaryPassenger.dateOfBirth}"/></div>
                                            <div class="marginText"><b>Date of booking : </b><fmt:formatDate value="${now}"/></div>
										</div>
										<div class="sector_info" style="font-size: 13px;">
											<div class="marginText"><b>Reference Number : </b>${Bookings.outboundFlightBooking.booking.bookingReferenceNo}</div>
										</div>
										<div id="return">
											<table>
												<colgroup>
													<col width="21"/>
													<col width="36"/>
													<col width="17%"/>
													<col width="17%"/>
													<col width="20%"/>
													<col width="24%"/>
												</colgroup>
												<thead>
													<tr>
														<th style="text-align: center">Date</th>
														<th style="text-align: center">Departure</th>
														<th>Flight</th>
														<th>Class</th>
														<th>Route</th>
														<th>Price</th>
													</tr>
												</thead>
												
												<tbody class="selected detailRow">
													<tr>
														<td rowspan="2" class="return button"><fmt:formatDate value="${SelectedFlights.selectedOutboundFlight.scheduledFlightDate}"/></td>
														<td rowspan="2" class="airline_logo">${SelectedFlights.selectedOutboundFlight.departureTime} hrs</td>
														<td>${SelectedFlights.selectedOutboundFlight.flightNumber}</td>
														<td>${SearchData.flightClass}</td>
														<td rowspan="1">${SearchData.fromCity}-${SearchData.toCity}</td>
														<td><c:forEach var="cost" items="${SelectedFlights.selectedOutboundFlight.flightRouteCosts}" 
															varStatus="loopCount"><c:if test="${loopCount.count eq 1}">Rs ${cost.costPerTicket}
														        </c:if>
														    </c:forEach></td>
													</tr>
												</tbody>
												<c:if test="${SelectedFlights.selectedReturnFlight != null}">
												<tbody class="selected detailRow">
													<tr>
														<td rowspan="2" class="return button"><fmt:formatDate value="${SelectedFlights.selectedReturnFlight.scheduledFlightDate}"/></td>
														<td rowspan="2" class="airline_logo">${SelectedFlights.selectedReturnFlight.departureTime} hrs</td>
														<td>${SelectedFlights.selectedReturnFlight.flightNumber}</td>
														<td>${SearchData.flightClass}</td>
														<td rowspan="1">${SearchData.toCity}-${SearchData.fromCity}</td>
														<td>Rs <c:forEach var="cost" items="${SelectedFlights.selectedReturnFlight.flightRouteCosts}" 
															varStatus="loopCount">
														        <c:if test="${loopCount.count eq 1}">
														         	${cost.costPerTicket}
														        </c:if>
														    </c:forEach>
														</td>
													</tr>											
												</tbody>	
												</c:if>
											</table>
											<span class="sector_info" style="font-size: 13px;"><strong>List of Passengers</strong></span>
											<table>
												<colgroup>
													<col width="21"/>
													<col width="36"/>
													<col width="17%"/>
													<col width="17%"/>
													<col width="20%"/>
													<col width="24%"/>
												</colgroup>
												<thead>
													<tr>
														<th style="text-align: center">Title</th>
														<th style="text-align: center">Name</th>
														<th>Gender</th>
														<th>Date of birth</th>
													</tr>
												</thead>
												
												<tbody class="selected detailRow">
													<c:forEach var="passenger" items="${PassengerList.passengers}">
													<tr>
														<td rowspan="2" class="return button">${passenger.title}</td>
														<td rowspan="2" class="airline_logo">${passenger.name}</td>
														<td>${passenger.gender}</td>
														<td><fmt:formatDate value="${passenger.dateOfBirth}"/></td>
													</tr>
													</c:forEach>
													
												</tbody>
												
											</table>
											<c:if test="${SelectedFlights.selectedReturnFlight != null}">
                                                <div class="detailRow marginText"><b>Total Price : </b>Rs ${Bookings.outboundFlightBooking.booking.totalCost +  Bookings.returnFlightBooking.booking.totalCost} </div>
                                            </c:if>
                                            <c:if test="${SelectedFlights.selectedReturnFlight == null}">
                                            	<div class="detailRow marginText"><b>Total Price : </b>Rs ${Bookings.outboundFlightBooking.booking.totalCost} </div>
                                            </c:if>
										</div>
									</div>

								</div>
								<!-- end of universal div -->
							</form>
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