<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
<<<<<<< HEAD
    pageEncoding="ISO-8859-1" session="true" import="DBEntities.*"%>
=======
    pageEncoding="ISO-8859-1" session="true" %>
    
>>>>>>> 67af7bf80746f7aa785b370e037d33084ff318f2
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Account</title>
<link rel ="stylesheet" href="http://localhost:8080/NRBOnline/css/add.css">
</head>
<body>

	<%if((boolean)(session.getAttribute("isLoggedIn"))){ %>
		<form action="../accountAdder" method="get">
			<input type="text" name="type" placeholder="acc type">
			<input type="text" name="initial" placeholder="initial">
			<input type="text" name="uid" value="<%=session.getAttribute("userID") %>">
			<input type="text" name="typeCode" placeholder="type code for savings">
			<input type="text" name="months" placeholder="months">
			<input type="submit" value="add">
			
		</form>
	<%} %>
	
	<div class="container">
    <h1 class="main-header">Account Application Form.</h1>
    <hr>
    <form id="app-form" method="post" action="../accountAdder">
    <fieldset class="sub-container" id="basic-details">
      <h2 class="sub-heading">Basic Details</h2>
      <hr>
        <label for="dropdown" class="standard-label">
          What type of account are you applying for?<span class="required">*</span>
          <br>
          <select  id="dropdown" class="standard-select" required name="type">
            <option value="select" disabled selected>Select Type</option>
            <option value="<%= Account.SAVINGS%>">Saving Account</option>
            <option value="<%= Account.FIXED%>">Fixed  Deposit</option>
           </select>
           <br>
           <label for="Amount"  class="standard-label">
            Amount<span class="required">*</span>
            <input class="standard-input" name="initial" id="Amount" type="text" placeholder="Amount..." required>
          </label>
        </label>
       
        <label for="SAVINGaccounttype" class="standard-label">
          What type of saving account are you applying for?<span class="required">*</span>
          <br>
          <input type="hidden" name="uid" value="<%=session.getAttribute("userID") %>">
          <select  name="typeCode" id="account-select" class="standard-select" required name="typeCode">
            <option value="select" disabled selected>Choose Account</option>
            <option value="ran kekulu">ran kekulu</option>
          </select>
        </label>
        <label for="SAVINGaccounttype" class="standard-label">
          What are the Months for Fixed Deposit<span class="required">*</span>
          <br>
          <select  id="account-select" class="standard-select" name="months" required>
            <option value="select" disabled selected>Months</option>
            <option value="6">06</option>
            <option value="12">12</option>
            <option value="18">18</option>
            <option value="24">24</option>
          </option>

          </select>
        </label>
        <fieldset class="sub-container" id="your-consent">
          <h2 class="sub-heading">Your Consent</h2>
          <hr>
          <label class="standard-label" id="credit-search" for="credit-search">
            Tick the box below to allow the bank to use the details you have provided to conduct a credit search if necessary.
            <br>
          <input type="checkbox" id="credit-click" name="credit-search"><br>
          </label>
          <label class="standard-label" for="info-request" id="info-request">
            Tick the box below to allow the bank to contact you via email if further information is needed to progress your application.
            <br>
            <input type="checkbox" id="info-click" name="info-request"><br>
          </label>
          <button type="submit" id="get-consent">SUBMIT</button>
        </fieldset>
      </form>
    


</body>
</html>