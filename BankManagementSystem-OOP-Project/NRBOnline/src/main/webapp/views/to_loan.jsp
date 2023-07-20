<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account to Loan Transactions</title>

	<link rel="stylesheet" type="text/css" href="http://localhost:8080/NRBOnline/css/to_loan.css"> 
	
	<script src="../js/to_loan.js"></script>

</head>
<body>
	
	<div class="container">
	
		<div class="fr">
		
			<form action="AtoLtransactions" method="post" onsubmit="return ValidationEvent()">
			
				<div class="title"><label class="title">TRANSFERS BETWEEN ACCOUNT AND LOAN </label></div><br><br><br>
				
					From Account : <input id="faccount" name="faccount" type="number" min="1" placeholder="Type from account number" required="required"><br><br>
					Loan Number: <input id="lnumber" name="lnumber" type="number" min="1" placeholder="Type loan number" required="required"><br><br><br><br><br><br>
					
					<input type="submit" value="Pay one Installment" style="height:30px; width:150px"><br><br>
			</form>
			
		</div>
		
	</div>
	
	
</body>
</html>