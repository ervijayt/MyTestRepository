<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to Happytrip</title>
<link rel="stylesheet" href="css/main.css"></link>
<link rel="stylesheet" href="css/flight_seats.css">
</head>
<body class="TwentyEighty Flights Results">
	<jsp:include page="/include/header.jsp" />
<div id="Wrapper">
			<div class="Container">
				<div id="ContentFrame" class="clearfix">




					<div id="ModifySearchWrapper">
						<div id="SearchParams">
							<div id="SPRow">
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

                                                            <img src="images/trips.jpg" alt="" />
							<!--  end of search tools div -->
						</div>
						<!--  end of col div -->
					</div>
					<!--  end of left div -->

					<div class="Right clearfix">
						<div class="col clearfix">

							<!-- start of universal display form -->
							<form id="universal_display" action="initiate-booking"
								method="post" name="results">
								<!-- TMPL_VAR book-it-inputs -->


								
								<!-- Round trip deals section ends -->
								<!-- show deals ends -->

								<!-- Fare displays begin -->

								

								<div id="universalDiv" class="universal">



									<div class="sector" style="width:100%" id="return_div">
										<div class="sector_info">

											<h2>Booked Trips</h2>
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
														<th style="text-align: center">Booking ID</th>
														<th>Type</th>
														<th>Date</th>
														<th>Price</th>
														<th></th>
													</tr>
												</thead>
												<c:forEach items="${flightBookings }" var="flightBooking" varStatus="status">
												<tbody class="<c:out value="${status.count % 2 eq 0 ? 'selected': ''}"/>">
													<tr>
														<td rowspan="2" class="return button"></td>
														<td rowspan="2" class="airline_logo"><a href="#">${flightBooking.booking.bookingReferenceNo }</a></td>
														<td>${flightBooking.flightRoute.flight.airline.airlineName}</td>
														<td><fmt:formatDate value="${flightBooking.dateOfJourney }"/></td>
														<td rowspan="1">Rs. ${flightBooking.booking.totalCost }</td>
														<td>${flightBooking.flightClass.classType }</td>
													</tr>
													<tr>
														<td colspan="2" class="noborder weak">
															${flightBooking.flightRoute.route.fromCity.cityName } to ${flightBooking.flightRoute.route.toCity.cityName }</td>
														<td class="noborder" style="" title=""></td>
														<td class=""></td>
													</tr>
													
												</tbody>
												</c:forEach>											
											</table>
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
		<!--  end of wrapper div -->
	<jsp:include page="/include/footer.jsp" />
</body>
</html>