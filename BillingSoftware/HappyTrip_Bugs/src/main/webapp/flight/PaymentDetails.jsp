<html>
<head>
<title>Credit Card Paymet Details</title>
</head>
<body>
	<h1>Welcome to payment GateWay please enter credit card details </h1>

<hr/>

	<form action="PaymentConfirmation">
		Enter creditcard Number: <input type="text" name="creditcardNum">
		Confirm credit card Number: <input type="text" name="confirmcreditcardNum">
		Credit Card Expire Month and year(DD/YYYY): <input type="text" name="expiryDate">
		Total Payment: <input type="text" name="totaPayment">
		<input type="hidden" name="name" value="sucess">
		
		<input type="submit" value="Submit">
	</form>

</body>
</html>