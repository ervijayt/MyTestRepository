<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passenger Details</title>
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/flight_seats.css">
<link type="text/css" href="../css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
 <script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
 <script type="text/javascript" src="../js/jquery-ui-1.8.16.custom.min.js"></script>
 <script type="text/javascript">
    $(function() {
        $( "input[name^='dateOfBirth']" ).datepicker({
            showOn: "button",
            buttonImage: "../css/ui-lightness/images/calendar.gif",
            buttonImageOnly: true
        });
    });

    </script>
</head>
<body class="TwentyEighty Flights Results">
	<jsp:include page="/include/header.jsp" />
	<div id="Wrapper">
			<div class="Container">
				<div id="ContentFrame" class="clearfix">

sadasdasda${FlightsSelected}

					<div id="ModifySearchWrapper">
						<div id="SearchParams">
							<div id="SPRow">
								<div id="mod_link_wrapper">
									<a href="javascript: void(0);"
										title="Click here to make a new search" id="mod_link"
										class="toggle_closed">Modify your search</a>
								</div>
								<ul class="inline">
									<li class="no_border">${SearchData.fromCity} - ${SearchData.toCity},</li>
									<li>${SearchData.departureDate}, 
									<c:if test="${SearchData.noOfAdults>0}">
										${SearchData.noOfAdults} Adult
									</c:if>
									<c:if test="${SearchData.noOfChildren>0}">
										,${SearchData.noOfChildren} Children
									</c:if>
									<c:if test="${SearchData.noOfInfants>0}">
										,${SearchData.noOfInfants} Infants
									</c:if>
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
						<div class="col">

                                                    <img src="images/passenger.jpg" alt="" />
	
						</div>
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








								<!-- TMPL_VAR book-it-inputs -->


							
								<!-- show deals begins -->
								<div id="RoundtripSpecial" style="display: none;">
									<h4 style="display: none;" id="RoundtripSpecialReset">
										<a href="#">« Back to all flights</a>
									</h4>
									<h4 id="RoundtripSpecialTitle">
										<img src="../images/elements/icon_roundtrip.png"> <strong>Round
											trip discounts</strong> Get discounted fares when you book round trips
										on these airlines:
									</h4>
								</div>
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
                                                                    <div class="infoBlock relative" style="visibility: visible;">
                                                                        <div class="row">
                                                                            <div class="colZero ten">
                                                                            <!-- Form Here -->
                                                                            <form:form method="get" action="showBookingSummary.html" commandName="PassengerList" 
                                                                            id="travellerDetails" errorblkid="TravellerBlockError">
                                                                                <input type="hidden" name="booking_id" value="">
                                                                                
                                                                                <!-- Single Traveller block -->
                                                                                <c:forEach begin="0" end="${SearchData.noOfAdults+SearchData.noOfChildren+SearchData.noOfInfants-1}" varStatus="status">
                                                                                <div id="intADDAD1" style="display: block;" class="blockOptInBG clearFix active">
                                                                                    <div class="blockOptInPAD clearFix active">
                                                                                        <div class="blockOptIn clearFix active" style="padding: 1.5em 0px 1em;">
                                                                                            <dl class="horizontal travellers row">
                                                                                                <dt style="text-align: right;"><label class="" id="AdultOne" style="margin: 0;padding: 0;border: 0;font-weight: bold;font-style: normal;font-size: 120%;line-height: 1;font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;">Traveller ${status.count}</label> </dt>
                                                                                                <dd id="addAD1">
                                                                                                    <form:select path="passengers[${status.count-1}].title" id="AdultTitle1" class="required travellerTitle title span one">
                                                                                                      <option value="0">Title</option>
                                                                                                      <option value="Mr">Mr</option>
                                                                                                      <option value="Ms">Ms</option>
                                                                                                      <option value="Mrs">Mrs</option>
                                                                                                    </form:select>
                                                                                                    <form:input type="text" path="passengers[${status.count-1}].name" id="AdultFname1" maxlength="18" class="required travellerFName name span four placeholder" minchars="1" title="Name" placeholder="Name"/>
                                                                                                    <form:select path="passengers[${status.count-1}].gender" id="Gender1" class="required travellerTitle title span one" title="Adult 1's gender">
												      													<option value="0">Choose Gender</option>
                                                                                                      	<option value="Male">Male</option>
                                                                                                      	<option value="Female">Female</option>
                                                                                                    </form:select>
												    <form:input class="datePicker required" type="text" path="passengers[${status.count-1}].dateOfBirth" title="Departure date" id="dateOfBirth1" size="10" placeholder="Date of Birth "/>  <small style="display: inline;line-height: 2em;margin-top: 5px;font-size: 100.61%;color: #999;">[format:mm/dd/yyyy]</small>
                                                                                                    <small class="selFromList" style="display: none;"> &nbsp;<a onclick="$('#addAD1').hide();$('#selAD1').show();" class="weak">(Select from a list)</a></small> 
                                                                                                </dd>
                                                                                                
                                                                                            </dl>     
                                                                                        </div>
                                                                                    </div>      
                                                                                </div>
                                                                                </c:forEach>
                                                                                
                                                                                <dl class="horizontal travellers row last">
                                                                                    <dt style="text-align: right;"><label style="margin: 0;padding: 0;border: 0;font-weight: bold;font-style: normal;font-size: 120%;line-height: 1;font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;">Mobile no.</label></dt>
                                                                                    <dd>
                                                                                        <form:input type="text" maxlength="17" placeholder="Enter your mobile number" etitle="Mobile Number" id="mobileNumber" onkeypress="return AllowNumeric(event);" validate="true" path="mobileNumber" class="required mb span four placeholder" autocomplete="off"/>
                                                                                        <small style="display: block;line-height: 1.3em;margin-top: 5px;font-size: 84.61%;color: #999;">Please provide the country code as applicable</small>
                                                                                    </dd>
                                                                                </dl>
                                                                                <dl class="horizontal travellers row">  
                                                                                    <dt>&nbsp;</dt>
                                                                                    <dd id="NewTCheck" style="display: none">
                                                                                        <input type="checkbox" id="chkRememberContactDetails" disabled="&quot;disabled&quot;" checked="&quot;checked&quot;" name="chkRememberContactDetails">
                                                                                        <label for="chkRememberContactDetails">Save this information in my profile &amp; prefill it for me next time</label>
                                                                                    </dd>
                                                                                    <dd>
                                                                                        <button class="book booking" id="btnBook" type="submit" style="">Continue</button>
                                                                                        <span id="travellerLoaderMess" class="transitionMsg"></span> 
                                                                                        
                                                                                    </dd>
                                                                                </dl>
                                                                            
                                                                                
                                                                            </form:form> 
                                                                            <!-- Form End Here -->
                                                                            </div>
                                                                            <aside class="col one">
                                                                                
                                                                            </aside>
                                                                        </div>
                                                                    </div>

								</div>
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