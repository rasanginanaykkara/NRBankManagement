<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true" import="DBEntities.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Current Accounts</title>
<link rel ="stylesheet" href="http://localhost:8080/NRBOnline/css/CUmain.css">
</head>
<body>
<div class="container">
    <h1 class="main-header">Current Accounts</h1>
    <hr>
    <form id="app-form" action="">
    <fieldset class="sub-container" id="basic-details">
   <br>
   <label for="Amount" class="standard-label">
    Account Number<input class="standard-input" name="Account Number" id="Account Number" type="text" placeholder="Account Number" required>
  </label>
  <label for="Amount" class="standard-label">
  Initial Balance<input class="standard-input" name="Balance" id="Balance" type="text" placeholder="Balance" required>
  </label>
  <label for="dropdown" class="standard-label">
    Account Type
    <br>
    <select  id="dropdown" class="standard-select" required>
      <option value="select" disabled selected>Account Type</option>
      <option value="SA">Saving Account</option>
      <option value="FD">Fixed  Deposit</option>
     </select>
    
     <input type="submit" id="get-consent" value="Delete">
   
    </form>
    
  </div>
  <div class="container">
   
    <form id="app-form" action="">
    <fieldset class="sub-container" id="basic-details">
   <br>
   <label for="Amount" class="standard-label">
    Account Number<input class="standard-input" name="Account Number" id="Account Number" type="text" placeholder="Account Number" required>
  </label>
  <label for="Amount" class="standard-label">
  Initial Balance<input class="standard-input" name="Balance" id="Balance" type="text" placeholder="Balance" required>
  </label>
  <label for="dropdown" class="standard-label">
    Account Type
    <br>
    <select  id="dropdown" class="standard-select" required>
      <option value="select" disabled selected>Account Type</option>
      <option value="SA">Saving Account</option>
      <option value="FD">Fixed  Deposit</option>
     </select>
    
     <input type="submit" id="get-consent" value="Delete">
     
    </form>
    
  </div>

  <div class="container">
   
    <form id="app-form" action="">
    <fieldset class="sub-container" id="basic-details">
   <br>
   <label for="Amount" class="standard-label">
    Account Number<input class="standard-input" name="Account Number" id="Account Number" type="text" placeholder="Account Number" required>
  </label>
  <label for="Amount" class="standard-label">
  Initial Balance<input class="standard-input" name="Balance" id="Balance" type="text" placeholder="Balance" required>
  </label>
  <label for="dropdown" class="standard-label">
    Account Type
    <br>
    <select  id="dropdown" class="standard-select" required>
      <option value="select" disabled selected>Account Type</option>
      <option value="SA">Saving Account</option>
      <option value="FD">Fixed  Deposit</option>
     </select>
    
     <input type="submit" id="get-consent" value="Delete">
     
    </form>
    
 
    
  </div>
 <input type="button" id="consent" value="Deposit Money" onclick="window.open('depo.jsp','_self')">
</body>
</html>