<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DBEntities.*,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction History</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/NRBOnline/css/trans_history.css">
</head>
<body>
	<% ArrayList<AtoLTransaction> aToLList = (ArrayList<AtoLTransaction>) request.getAttribute("lList");
		ArrayList<AtoATransaction> aToAList = (ArrayList<AtoATransaction>)request.getAttribute("aList");
		%>
	<div class="container">
		<div class="title">Account to Account</div>
			<div class="sc">
				<table border="1">
					<tr>
						<th width="150px">From Account</th>
						<th width="150px">To Account</th>
						<th width="150px">Amount</th>
						<th width="150px">Date</th>
						<th width="150px">Actions</th>
					</tr>
					<%
					for(int i = 0; i<aToAList.size();i++){%>
						<tr height="30px">
						<td><%= aToAList.get(i).getHostAccount()%></td>
						<td><%= aToAList.get(i).getPayeeAccount()%></td>
						<td><%= aToAList.get(i).getAmount() %></td>
						<td><%= AtoATransaction.getTimestampformat().format(aToAList.get(i).getDate())%></td>
						<td><form action="#" method="get"><input type="submit" value="Update"></form> <form action="#" method="get"><input type="submit" value="Refund"></form>
						</td>
						</tr>
					<%}
					%>				
				</table>
			</div>
			
	</div>
	
	<div class="container1">
		<div class="title">Account to Loan</div>
			<div class="sc">
				<table border="1">
					<tr>
						<th height="40px" width="170px">From Account</th>
						<th width="170px">To Account</th>
						<th width="170px">Amount</th>
						<th width="150px">Date</th>
					</tr>
					<%
					for(int i = 0; i<aToLList.size();i++){%>
						<tr height="30px">
						<td><%= aToLList.get(i).getHostAccount()%></td>
						<td><%= aToLList.get(i).getLoanAccount()%></td>
						<td><%= aToLList.get(i).getAmount() %></td>
						<td><%= AtoLTransaction.getTimestampformat().format(aToLList.get(i).getDate())%></td>
						<td><form action="#" method="get"><input type="submit" value="Update"></form> <form action="#" method="get"><input type="submit" value="Refund"></form>
						</td>
						</tr>
					<%}
					%>
				
				</table>
			</div>
			
	</div>

</body>
</html>