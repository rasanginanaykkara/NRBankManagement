<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account to Account Transactions</title>

	<link rel="stylesheet" type="text/css" href="http://localhost:8080/NRBOnline/css/to_account.css"> 
	
	<script src="../js/to_account.js"></script>
	
</head>

<body>
	<div class="container">
	
		<div class="fr">
		
			<form action="../AtoAtransactions" method="post" onsubmit="return ValidationEvent()">
			
				<div class="title"><label class="title">TRANSFERS BETWEEN ACCOUNTS </label></div><br><br><br>
				
				From Account : <input id="faccount" name="faccount" type="number" min="1" placeholder="Type from account number" required><br><br>
				To Account : <input id="taccount" name="taccount" type="number" min="1" placeholder="Type to account number"  required><br><br>
				Amount : <input name="amount" type="number" min="1" placeholder="Type amount to tranfer" required><br><br><br><br><br><br>
				
				
				<input type="submit" value="Transfer" style="height:30px; width:100px"><br><br>
				
			</form>
			
		</div>
		
	</div>
	
	
</body>

</html>