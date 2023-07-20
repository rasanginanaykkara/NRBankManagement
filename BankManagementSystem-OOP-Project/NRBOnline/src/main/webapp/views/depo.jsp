<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit Amount</title>
<link rel ="stylesheet" href="http://localhost:8080/NRBOnline/css/depo.css">
</head>
<body>
 <div class="container">
    <h1 class="main-header">Deposit Amount</h1>
    <hr>
    <form id="app-form" action="">
    <fieldset class="sub-container" id="basic-details">
   <br>
   <label for="Amount" class="standard-label">
    Account Number<input class="standard-input" name="Account Number" id="Account Number" type="text" placeholder="Account Number" required>
  </label>
  <br>
  <label for="Amount" class="standard-label">
    Deposit Amount<input class="standard-input" name="Deposit Amount" id="Deposit Amount" type="text" placeholder="Deposit Amount" required>
  </label>
</form>
 
  <input type="submit" id="get-consent">
</fieldset>
    
 
</div>
</body>
</html>