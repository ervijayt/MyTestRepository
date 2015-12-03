<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <link rel="stylesheet" href="css/main.css" >
    <link rel="stylesheet" href="css/account.css" >
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
</head>
<body class="Signin">
	<jsp:include page="include/header.jsp" />
    <div id="Wrapper">
     <div class="clearfix leftPanel">
 <blockquote><h5 class="legend" style="color: #555;">  ${advertisementcontent}</h5></blockquote>
 </div>
    
      <div class="Container">
        <div id="ContentFrame" class="clearfix">
        <h2 style="margin-left: 20px;">About us</h2>
      <blockquote><h3 class="legend" style="color: #555;">  ${aboutuscontent}</h3></blockquote>	
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
