<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <link rel="stylesheet" href="../css/main.css" >
    <link rel="stylesheet" href="../css/account.css" >
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
  <h1>Add</h1>
  <form:form method="post" action="addAirline" commandName="AddAirline">


    <dl class="vertical">
      <dt><label for="airlineName" class="required email">Airline name</label></dt>
      <dd><form:input name="airlineName" path="airlineName" type="text" id="airlineName" class="required email" title="The name of the new airline" /></dd>
      <dd class="hint">This is the name of the new airline</dd>
      <dt><label class="required" for="airlineCode">Airline Code</label><form:errors path="airlineCode" cssClass="error"  /></dt>
      <dd>
        <input name="airlineCode" path="airlineCode" type="text" id="airlineCode" size="24" value="" class="required password" title="The airline code" />
      </dd>
      <dd class="submit"><button type="submit" id="signInButton" class="primary">Add</button><img src="/images/animations/spinner.gif" id="wait_spinner" style="display: none"></dd>
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
