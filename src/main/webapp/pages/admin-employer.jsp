<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--doctype html-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
        -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Position Survey</title>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="js/lib/html5shiv.min.js"></script>
        <script src="js/lib/respond.min.js"></script>
        <![endif]-->

<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/custom.css" rel="stylesheet" type="text/css">
<link href="css/validation.css" rel="stylesheet">
<!--[if IE 8]>
        <link href="css/ie8.css" rel="stylesheet" type="text/css">
        <![endif]-->
 <link href="css/jquery.dataTables.min.css" rel="stylesheet">

</head>

<body>
	<!-- CONTENT SECTION -->
	<section class="contentSection">
		<div id="adminEmployerTable">
		
			   <div class="row">
	    	     <table id="adminEmployerTest" class="display" cellspacing="0" width="100%" >
			        <thead>
			            <tr>
			                <th>Company Name</th>
			                <th>Company Type</th>		
			                <th>Publish Position Number</th>		             
			                <th>Update Time</th>
			                <th></th>
			            </tr>
			        </thead>
			     </table>
    	     </div><!--<br> <div class="row">
    	     <button type="button" id="exportEmployerCSV" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">
   					Export
 				 </button></div>-->
			</div>
	</section>
</body>
 <script type="text/javascript"  >
	function delEmployerRow () {
		 var b= $('#adminEmployerTest').DataTable().row('.selected').data();
		 if(b!=null){
	 		$('#adminEmployerTest').DataTable().row('.selected').remove().draw( false );
         $.ajax({
    				type: "get",       
    				dataType: "json",            
    				url: 'deleteEmployer',   
    				data:  b,
    				success: function() {     
    			    }      
    	        });       
		 }
      }
 </script>
</html>