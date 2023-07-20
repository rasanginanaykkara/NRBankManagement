<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Transactions</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/NRBOnline/css/transactions.css"> 

</head>

<body>

	<div class = "container">
	
		<div class="title">
			<h1>Account Transactions</h1>	
		</div>
		
		<div class ="form">
		
			<form action="to_account.jsp" method="get">
				<input type="submit" value="Account to Account" style="height:50px; width:200px"> <br><br><br><br>	
			</form>
			<form action="to_loan.jsp" method="get">
				<input type ="submit" value="Account to Loan" style="height:50px; width:200px"> <br><br><br><br>
			</form>
			<form action="trans_history.jsp" method="get">
				<input type ="submit" value="Transaction History" style="height:50px; width:200px">
			</form>
		
			
		
		</div>

	</div>
	
</body>

</html>