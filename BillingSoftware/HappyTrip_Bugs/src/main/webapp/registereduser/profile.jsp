<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>User Profile</title>
	 <link rel="stylesheet" href="css/register.css" >
    <link rel="stylesheet" href="css/main.css" >

    <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet"></link>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript">
    $(function() {
        $( "#date" ).datepicker({
            showOn: "button",
            buttonImage: "css/ui-lightness/images/calendar.gif",
            buttonImageOnly: true,
            setDate : "<c:out value="${user.dateOfBirth}"/>"
        });
    });

    </script>
</head>
<body class="FullWidth Welcome ">

    <jsp:include page="/include/header.jsp" />

    <div id="Wrapper">
      <div class="Container">
        <div id="ContentFrame" class="clearfix">

          <div class="Left">
            <div class="col" id="stepContainer">
              <script type="text/javascript">
  var key = '9o8NsPcNOaf4rdhV'
</script>
<div id="Step1" class="step" style="display:block; height: 750px;">
  <div id="ProgressStatus">
    <ul class="clearfix">
      <li class="step1"><span></span>Personal info</li>
    </ul>
  </div>
  <h1>Welcome to your Happytrip Account</h1>
  <div id="step1_errors" class="errors clearfix">
    <span>Oops, you'll need to fix these issues before we can confirm your account</span>
    <ol></ol>
  </div>
  <form:form id="signin_details" action="saveProfile" method="post" commandName="UserProfile">
    <fieldset class="primary">
      <h3 class="legend">Profile Update</h3>
      <dl class="vertical">
        <dt><label for="username">Your email address</label></dt>
		<form:hidden path="userId"/>
		<form:hidden path="userContact.userId"/>
        <dd><form:input path="email" name="email" id="username" type="text" size="28" value="${user.email}" />
          <span class="hint">This will be the username for login</span>
        </dd>
        <dt><label for="fullName">Your name</label></dt>
        <dd>
    
          <form:input path="fullName" type="text" name="fullName" id="fullName" selflabel="Full Name" class="required selflabel" title="Your name" value="${user.fullName}" />

        </dd>
        <dt><label for="phone_number">Phone number</label></dt>
        <dd>

          <form:input path="userContact.mobileNo" type="text" name="phone_number[phone_number_value]" id="contactPhoneNumber1" value="${user.userContact.mobileNo}" />
          <select path="" class="TwentyWidth" id="contactPhoneNumber1Type" name="phone_number[category]">
            <option value="mobile">Mobile</option>
			<option value="landline">Land-line</option>
          </select>
          <p class="hint">Give us a mobile number to get customer service <abbr title="Short Messaging Service">SMS</abbr> messages</p>

        </dd>
        <dt><label class="required" for="gender">Gender</label></dt>
        <dd><form:select path="gender" name="gender" id="gender" title="Your gender" class="required">
            	<form:option value="male" label="Male" />
            	<form:option value="female" label="Female" />
          </form:select></dd>
        <dt><label class="required" for="address1">Date Of Birth</label></dt>
        <dd><form:input path="dateOfBirth" class="datePicker required" type="text" name="dateOfBirth" title="Departure date" id="date" /></dd>
        <dt><label class="required" for="address1">Address</label></dt>
        <dd><form:input class="required" path="userContact.address" type="text" id="address1" size="70" title="Your address line 1" value="${user.userContact.address}" /></dd>
        <dt><label class="required" for="city">City</label></dt>
        <dd><form:input path="userContact.city.cityName" name="city" id="ccity" title="Your city" class="required" value="${user.userContact.city.cityName}" /></dd>
        <dt><label class="required" for="pin">Pin Code</label></dt>
        <dd><form:input path="userContact.pinCode" class="required" name="pin" type="text" id="pin" size="10" title="Your pin code" value="${user.userContact.pinCode}" /></dd>
      </dl>
      
    </fieldset>
    <fieldset class="stepControls">
      <button type="submit" class="next blue">Submit</button>
      <img style="display: none;" id="wait_spinner" src="/images/animations/spinner.gif"/>
    </fieldset>
  </form:form>

</div>

            </div>

          </div>
        </div>

      </div>
    </div>

    <div id="Footer">
      <div class="FooterContainer">
        © 2013 Happytrip Travel Services Private Limited
      </div>
    </div>

  
</body>

</html>