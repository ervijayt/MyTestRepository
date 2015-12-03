<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="css/main.css" >
    <link rel="stylesheet" href="css/register.css" >
    <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
 	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
 	<script type="text/javascript">
    $(function() {
        $( "input[name^='dateOfBirth']" ).datepicker({
            showOn: "button",
            buttonImage: "css/ui-lightness/images/calendar.gif",
            buttonImageOnly: true
        });
    });
    
    function checkPassword(){
    	if($("#passwordError")){
    		$("#passwordError").remove();
    	}
    	var password1 = $("#password").val();
    	var password2 = $("#password_confirmation").val();
    	if(password1 != password2){
    		$("label[for='password']").after($("<span id='passwordError' class='error'>Password Mismatch</span>"));
    		return false;
    	} else {
    		return true;
    	}
    }

    </script>
</head>
<body class="FullWidth Welcome ">

	<jsp:include page="include/header.jsp" />
	<div id="Wrapper">
		<div class="Container">
			<div id="ContentFrame" class="clearfix">

				<div class="Left">
					<div class="col" id="stepContainer">

						<div id="Step1" class="step" style="display: block;height:600px;">
							<div id="ProgressStatus">
								<ul class="clearfix">
									<li class="step1"><span></span>Personal info</li>
								</ul>
							</div>
							<h1>Welcome to your Happytrip Account</h1>
							<div id="step1_errors" class="errors clearfix">
								<span>Oops, you'll need to fix these issues before we can
									confirm your account</span>
								<ol></ol>
							</div>
							<form:form method="post" action="registerprocess" commandName="UserRegister" id="signin_details" onsubmit="return checkPassword()">
								<fieldset class="primary">
									<h3 class="legend">Set a password to get started</h3>
									<dl class="vertical">
										<dt>
											<label for="username">Your email address</label><form:errors path="email" cssClass="error"/>
										</dt>

										<dd>
											<form:input id="email" path="email" type="text" size="32" /><br>
          <span class="hint">This will be the username for your Happytrip Account</span>
        </dd>
        <dt>
											<label class="required" for="password">Your password</label><form:errors
							path="password" cssClass="error"  />
										</dt>
        <dd>
											<form:input class="required" path="password" type="password"
												id="password" size="24" title="Your account password" />
										</dd>
        <dt>
											<label class="required" for="retype_password">Re-type your password</label>
										</dt>
        <dd>
											<input class="required" name="retype_password"
												type="password" id="password_confirmation" size="24"
												title="Password verification" />
										</dd>
        <dt>
											<label for="title">Your name</label><form:errors
							path="fullName" cssClass="error"  />
										</dt>
        <dd>
          
          <form:input type="text" path="fullName"
												id="fullName" selflabel="First name"
												class="required selflabel" title="Your name" />        
										</dd>
        <dt>
											<label class="required" for="gender">Gender</label><form:errors
							path="gender" cssClass="error"  />
										</dt>
        <dd>
											<form:select id="gender" title="Your gender"
												class="required" path="gender">
            <form:option value="male" label="Male" />
            <form:option value="female" label="Female" />
          </form:select>
										</dd>
		<dt><label class="required" for="dateOfBirth">Date of Birth</label><form:errors
							path="dateOfBirth" cssClass="error"  /></dt>								
		<dd><form:input path="dateOfBirth" class="datePicker required" type="text" name="dateOfBirth" title="Departure date" id="dateOfBirth" size="10" placeholder="Date of Birth" /></dd>
      </dl>
      
    </fieldset>
    <fieldset class="stepControls">
      <button type="submit" class="next blue">Submit</button>
      <img style="display: none;" id="wait_spinner"
										src="/images/animations/spinner.gif">
    
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
