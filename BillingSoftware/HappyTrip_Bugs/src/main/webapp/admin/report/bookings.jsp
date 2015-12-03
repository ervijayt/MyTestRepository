<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to Happytrip</title>
<link rel="stylesheet" href="<c:url value="/css/main.css"/>"></link>
<link rel="stylesheet" href="<c:url value="/css/flight_seats.css"/>"></llink>
</head>
<body class="TwentyEighty Flights Results">
	<jsp:include page="/include/adminHeader.jsp" />
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

											<h2>List of Booked Trips</h2>
										</div>

										<div id="return">
											<table>
												<colgroup>
													<col width="21">
													<col width="36">
													<col width="17%">
													
												</colgroup>
												<thead>
													<tr>
														<th>Cost Per Ticket</th>
														<th>Date Of Journey</th>
														<th>Number Of Seats</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach items="${report}" var="report" varStatus="status">
													<tr>
														<td>${report.costPerTicket }</td>
														<td rowspan="1">${report.dateOfJourney}</td>
														<td>${report.numberOfSeats }</td>
													</tr>
													
												</c:forEach>		
												</tbody>
																						
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
</body>
</html>