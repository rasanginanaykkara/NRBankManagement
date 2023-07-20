<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="DBEntities.*,java.io.File" session="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="http://localhost:8080/NRBOnline/css/Styles.css">

<title>Login page</title>
<style>
	.Logins{
		background-color: #5985ba;
	}
	.Login-wrapper{
		width: 427px;
	    height: 338px;
	    display: flex;
	    flex-direction: column;
	    flex-wrap: nowrap;
	    align-items: center;
	    justify-content: center;
	    background-color: rgba(0,0,0,0.4);
	    border-radius: 43px;
	    color: antiquewhite;
	}
</style>
</head>
<body>

	<div class="Logins" style="height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;">
    
    <div class="Login-wrapper"> 
	<form action="../Login" method="post">
		
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputEmail4">NIC</label>
	      <input type="text" class="form-control" id="inputEmail4" name="nic" placeholder="NIC" required>
	    </div>
	    <br>
	    <div class="form-group col-md-6">
	      <label for="inputPassword4">Password</label>
	      <input type="password" class="form-control" id="inputPassword4" name="password" placeholder="Password" required>
	    </div>
	  </div> 
	  <br>
	  <button type="submit" class="btn btn-primary" style="--bs-btn-padding-x: 2.3rem;--bs-btn-padding-y: 0.4rem;">login</button>
	  
	</form>
	</div>
	</div>
	
</body>
</html>