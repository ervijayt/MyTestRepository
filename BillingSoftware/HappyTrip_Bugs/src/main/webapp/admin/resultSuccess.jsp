<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/account.css">
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
									Your login attempt was not successful, try again.<br /> Caused
									: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
								</div>
							</c:if>
						</div>
						<h1>${message}</h1>
						
					</div>


				</div>
				<div class="Right">
					<div class="col clearfix">
						<img src="../images/suitcase.png" height="500" width="500"
							style="margin-left: 40px;">
					</div>
				</div>
			</div>
		</div>

		<div id="Footer">
			<div class="FooterContainer">© 2012 Happytrip Travel Services
				Private Limited</div>
		</div>
</body>
</html>
