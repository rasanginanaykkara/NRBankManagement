<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/Styles.css">
    <link rel="stylesheet" href="../css/usercontrol.css">
<title>Insert title here</title>
</head>
<body>
	
    <section>
        <div class="header-wrapper">
            <header>
                <div class="login-wrapper">
                    <div class="user-img-wrapper">
                        <img src="../images/User.png" alt="User">
                    </div>
                    <button type="button" class="btn btn-outline-primary">Login</button>  
                </div>
            </header>
        </div>
        <div class="nav-Wrapper">
            <div class="nav-seciton">
                <nav>				
                    <ul id="menutab" >
                        <li><a class="nav-buttons smooth Active" onclick="hideShowmenu();find('a');">Home</a></li>
                        <li><a class="nav-buttons smooth" onclick="hideShowmenu();find('f');">Account</a></li>
                        <li><a class="nav-buttons smooth" href="Loan.html" onclick="hideShowmenu();find('t');">Loan</a></li>
                        <li><a class="nav-buttons smooth" href="#" onclick="hideShowmenu();">Transaction</a></li>
                       
                    </ul>
                </nav>
            </div>
        </div>
        <div class="user-card">
            <table>
                <tr class="card-row">
                    <td>
                        <img src="../images/123.jpg" alt="" class="user-card-dp" style="object-fit:cover;">
                        
                    </td>
                    <td>
                        <span class="name-tag">siripala</span>
                        
                    </td>
                    <td>
                        <span class="nic-tag">199902700422</span>
                    </td>
                    <td>
                        <form action="#">
                            <button>delete account</button>                
                        </form>
                    </td>
                    <td>
                        <form action="#">
                            <button>reset password</button>
                        </form>
                    </td>
                </tr>
            </table>            
        </div>
        
    </section>
    

</body>
</html>