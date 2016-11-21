<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="icon" href="../../favicon.ico">-->

    <title>Job Management System</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/login.css" rel="stylesheet">
    <!--<link href="css/custom.css" rel="stylesheet" type="text/css"> -->

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/lib/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container">
       <form class="form-signin" id="singleLoginForm" role="form" action="j_spring_security_check" method="POST">
            <h2 class="form-signin-heading">Log into system</h2>
            <div class="form-group">
              <input type="text" placeholder="User name" class="form-control" id="j_username" name='j_username'>
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control" id="j_password" name='j_password'>
            </div>
            <button class="btn btn-lg btn-primary btn-block" id="singleLoginFormSubmit">Log in</button>
       </form>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br>
    <jsp:include page='footer.jsp'/>
    <script data-main="js/app/pages/login" src="js/lib/require-2.1.15.min.js"></script>
  </body>
</html>
