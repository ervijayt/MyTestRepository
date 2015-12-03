<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to Happytrip</title>
<link rel="stylesheet" href="css/main.css"></link>
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet"></link>
<link type="text/css" href="css/nivo-slider.css" rel="stylesheet"></link>
<link rel="stylesheet" href="css/themes/default.css" type="text/css"></link>
<style>
.ui-autocomplete-loading {
	background: white url('images/ui-anim_basic_16x16.gif') right center
		no-repeat;
}
</style>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
<script type="text/javascript">
	$(function() {
		$("#errorContainer").html("");
		$("#dpt_date").datepicker({
			showOn : "button",
			buttonImage : "css/ui-lightness/images/calendar.gif",
			buttonImageOnly : true,
			minDate: 0
		});
		$("#rtn_date").datepicker({
			showOn : "button",
			buttonImage : "css/ui-lightness/images/calendar.gif",
			buttonImageOnly : true,
			minDate: 0
		});
		$('#slider').nivoSlider();
		$( "#fromCity" ).autocomplete({
		      source: "cities",
		      minLength: 2,
		      select: function( event, ui ) {
		    	  $( "#fromCity" ).val( ui.item.cityName );
		    	  return false;
		      }
		}).data( "autocomplete" )._renderItem = function( ul, item ) {
		      return $( "<li>" )
		        .data( "item.autocomplete", item )
		        .append( "<a>" + item.cityName + "</a>" )
		        .appendTo( ul );
		};
		$( "#toCity" ).autocomplete({
		      source: "cities",
		      minLength: 2,
		      select: function( event, ui ) {
		    	  $( "#toCity" ).val( ui.item.cityName );
		    	  return false;
		      }
		}).data( "autocomplete" )._renderItem = function( ul, item ) {
		      return $( "<li>" )
		        .data( "item.autocomplete", item )
		        .append( "<a>" + item.cityName + "</a>" )
		        .appendTo( ul );
		};
	});
	function showReturn(){
		$('#returnDate').show();
	}
	function hideReturn(){
		$('#returnDate').hide();
	}
	
	function checkForReturn(){
		var returnJourney = $('#return')[0].checked;
		if(returnJourney==false){
			$('#returnDate').remove();
		}
	}
	
	function showError(error){
		$("#errorContainer").append("<li>"+error+"</li>");
	}
	
	function checkForm(){
		$("#errorContainer").html("");
		var noError = true;
		var dateAvailable = true;
		var toLocation = $("#toCity").val();
		var fromLocation = $("#fromCity").val();
		var departureDate = $("#dpt_date").val();
		var returnDate = $("#rtn_date").val();
		if(toLocation == ""){
			showError("Destination Location is empty");
			noError = false;
		}
		if(fromLocation == ""){
			showError("Origin Location is empty");
			noError = false;
		}
		if(toLocation == fromLocation && toLocation != "" && fromLocation != ""){
			showError("Origin and Destination are same");
			noError = false;
		}
		if(departureDate == ""){
			showError("Departure date is required");
			dateAvailable = false;
			noError = false;
		}
		if($('#return')[0].checked && returnDate == ""){
			showError("Return date is required");
			dateAvailable = false;
			noError = false;
		}
		if($('#return')[0].checked && dateAvailable){
			if($("#dpt_date").datepicker("getDate") >= $("#rtn_date").datepicker("getDate")){
				showError("Return date is earlier than departure date");
				noError = false;
			}
		}
		
		return noError;
	}
</script>
</head>
<body>
<jsp:include page="include/header.jsp" />
<div id="Wrapper">
<div class="Container">
<div id="ContentFrame" class="clearfix">

<div class="Left">
<div class="col">
<h1>Search flights</h1>

<div id="flt_err_contiainer">
<div id="flt_err" class="error">
<ol id="errorContainer" style="color: red;"></ol>
</div>
</div>
<form:form method="get" class="search no-action-change removeSelflabels"
	commandName="SearchData" id="AirSearch" errorblockid="flt_err"
	action="flight/search.html" onsubmit="return checkForm()">
	<fieldset class="search_type">
	<table>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<td><label for="one_way" class="encaps"> <form:radiobutton
					path="returnJourney" onclick="hideReturn()" value="false" /> <strong>One
				way</strong></label></td>
				<td><label for="multi_city" class="encaps"> <form:radiobutton
					id="return" path="returnJourney" onclick="showReturn();"
					value="true" /> <strong>Return</strong></label></td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	</fieldset>

	<fieldset class="place">
	<table>
		<colgroup>
			<col width="50%">
			<col width="50%">
		</colgroup>

		<tbody>
			<tr>
				<td><label for="origin_autocomplete" class="required">From
				<span class="weak">(any worldwide city or airport)</span> </label> <form:input
					type="text" class="autocomplete required" title="From"
					path="fromCity" id="fromCity" /></td>
				<td><label for="destination_autocomplete" class="required">To
				<span class="weak">(any worldwide city or airport)</span></label> <form:input
					type="text" class="autocomplete required" title="To" path="toCity"
					id="toCity" /></td>
			</tr>
		</tbody>
	</table>
	</fieldset>

	<fieldset class="date">
	<table>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="50%">
		</colgroup>

		<tbody>
			<tr>
				<td><label for="dpt_date" class="required">Depart on</label> <span
					class="enclosedPicker"> <form:input
					class="datePicker required" type="text" path="departureDate"
					title="Departure date" id="dpt_date" size="10" /> </span></td>
				<td id="returnDate" style="display: none;"><label
					for="rtn_date" class="required" style="">Return on</label> <span
					class="enclosedPicker" style=""> <form:input
					class="datePicker second required no_autochange" type="text"
					path="returnDate" title="Return date" mindatefieldid="dpt_date"
					id="rtn_date" size="10" maxdate="7/1/2014" selflabel="dd/mm/yyyy" />
				</span></td>

			</tr>
		</tbody>
	</table>
	</fieldset>

	<fieldset class="people">
	<table>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<td><label for="adults" class="required">Adults <span
					class="weak">(above 12 yrs)</span> </label> <form:select id="adults"
					path="noOfAdults" size="1">
					<option value="1">1</option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
					<form:option value="6">6</form:option>
					<form:option value="7">7</form:option>
					<form:option value="8">8</form:option>
					<form:option value="9">9</form:option>
				</form:select></td>
				<td><label for="children">Children <span class="weak">(2-12
				yrs)</span></label> <form:select id="children" path="noOfChildren" size="1">
					<form:option value="0" selected="selected">0</form:option>
					<form:option value="1">1</form:option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
					<form:option value="6">6</form:option>
					<form:option value="7">7</form:option>
					<form:option value="8">8</form:option>
				</form:select></td>
				<td><label for="infants">Infants <span class="weak">(under
				2 yrs)</span></label> <form:select id="infants" path="noOfInfants" size="1">
					<form:option value="0" selected="selected">0</form:option>
					<form:option value="1">1</form:option>
				</form:select></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	</fieldset>
	<fieldset class="options" id="advanced_search1" style="">
	<table>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<td><label for="class">Class of travel</label> <form:select
					id="class" path="flightClass">
					<form:option value="Economy">Economy</form:option>
					<form:option value="Business">Business</form:option>
					<form:option value="First">First</form:option>
					<form:option value="Premium Economy">Premium Economy</form:option>
				</form:select></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	</fieldset>

	<fieldset class="submit">
	<table>
		<tbody>
			<tr>
				<td class="searchHotels"><label class="encaps"
					style="display: none;"><input type="checkbox" value="true"
					name="searchHotels" id="searchHotels"><span>Search
				hotels too <span class="weak">(in a separate window)</span> </span></label></td>
				<td>
				<button class="booking" title="Search" id="button_flight_search"
					onclick="checkForReturn()" type="submit">Search flights</button>
				</td>


			</tr>
		</tbody>
	</table>
	</fieldset>



</form:form></div>
</div>
<div class="Right">
<div class="col">

<div id="aside">
<div class="slider-wrapper theme-default">
<div id="slider" class="nivoSlider"><img src="images/pic1.jpg"
	data-thumb="images/pic1.jpg" alt="" /> <a href="#"><img
	src="images/pic2.jpg" data-thumb="images/pic2.jpg" alt=""
	title="Welcome to happy trip" /></a> <img src="images/pic3.jpg"
	data-thumb="images/pic3.jpg" alt="" data-transition="slideInLeft" /> <img
	src="images/pic4.jpg" data-thumb="images/pic4.jpg" alt=""
	title="#htmlcaption" /></div>
<div id="htmlcaption" class="nivo-html-caption"><strong>Fly</strong>
to <em>Paris with</em><a href="#">Happy Trip</a>.</div>
</div>
</div>
</div>
</div>
</div>

</div>
</div>
<jsp:include page="include/footer.jsp" />
</body>
</html>