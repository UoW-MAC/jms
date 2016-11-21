<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <link href="css/ie.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
 <nav class="navbar page-title withOutTitle navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="home">Job Management System</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" id="loginForm" role="form" action="j_spring_security_check" method="POST">
            <sec:authorize ifNotGranted="ROLE_CANDIDATE,ROLE_ADMIN,ROLE_ORGANIZATION">
	            <div class="form-group">
	              <input type="text" placeholder="User name" class="form-control" id="j_username" name='j_username'>
	            </div>
	            <div class="form-group">
	              <input type="password" placeholder="Password" class="form-control" id="j_password" name='j_password'>
	            </div>
	            <button type="button" class="btn btn-success" id="loginFormSubmit">log In</button>
            </sec:authorize>
          </form>
          <form class="navbar-brand navbar-right">
           <sec:authorize access="(hasRole('ROLE_CANDIDATE') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ORGANIZATION')) and isAuthenticated()">
            <div class="logged">
               <li><sec:authentication property="name"></sec:authentication></li>
               <li><a class="logout" href="j_spring_security_logout"> | Log out</a></li>
            </div>
            </sec:authorize>
          </form>
        </div>
      </div>
 </nav>
</header>

</body>
<script src="js/app/modules/ie.js"></script>
</html>