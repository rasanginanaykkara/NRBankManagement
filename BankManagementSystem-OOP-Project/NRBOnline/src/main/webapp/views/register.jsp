<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="http://localhost:8080/NRBOnline/css/Styles.css">
</head>
<body>
<div class="register">
		<div class="register-Wrapper">
		<form method="post" action="../UserAdder" enctype="multipart/form-data">
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="inputEmail4">NIC</label>
	      <input type="text" name="nic" class="form-control" id="inputEmail4" placeholder="NIC" required>
	    </div>
	    <br>
	    <div class="form-group col-md-6">
	      <label for="inputEmail4">Email</label>
	      <input type="email" name="email" class="form-control" id="inputEmail4" placeholder="Email" required>
	    </div>
	    <br>
	    <div class="form-group col-md-6">
		      <label for="inputEmail4">Name</label>
	      	<input type="text" name="name" class="form-control" id="inputEmail4" placeholder="Name" required>
	    </div>
	    <br>
	    <div class="form-group col-md-6">
		<label for="inputAddress">Address</label>
		    <input type="text" name="address" class="form-control" id="inputAddress" placeholder="Address" required>
		 </div>
		 <br>
	    <div class="form-group col-md-6">
	      <label for="inputPassword4">Password</label>
	      <input type="password" name="password" class="form-control" id="inputPassword4" placeholder="Password" required>
	    </div>
	    <br>
	    <input type="file" class="form-control col-md-6" id="inputEmail4" name="file" required>
	    <br>
	  </div>
	  <br>
	  <button type="submit" class="btn btn-primary" style="--bs-btn-padding-x: 2.3rem;--bs-btn-padding-y: 0.3em;">Upload</button>
	</form>
	</div>
</div>
</body>
</html>