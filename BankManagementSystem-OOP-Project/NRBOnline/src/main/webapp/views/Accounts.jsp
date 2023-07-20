<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" href="http://localhost:8080/NRBOnline/css/Accounts.css">
</head>
<body>

  <div class="container">
    <div class="title">
      <h1>ACTIONS</h1>
    </div>

      <div class="fr">
      
          <input type="button" value="Current  Accounts" style="height:50px; width:200px" onclick="window.open('CUaccounts.jsp','_self')"><br><br>
      </div>


      <div class="fr">
        
          <input type="button" value="Add Accounts" style="height:50px; width:200px" onclick="window.open('addAccount.jsp','_self')">
      
      </div>

    </div>
</body>
</html>