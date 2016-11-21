<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>Candidate Center</title>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="js/lib/html5shiv.min.js"></script>
        <script src="js/lib/respond.min.js"></script>
        <![endif]-->
    
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/custom.css" rel="stylesheet" type="text/css">
        <link href="css/validation.css" rel="stylesheet">
        
        <link href="css/jquery.dataTables.min.css" rel="stylesheet">
        
        <!--[if IE 8]>
        <link href="css/ie8.css" rel="stylesheet" type="text/css">
        <![endif]-->

	</head>

	<body>
    <!-- CONTENT SECTION -->
    <section class="contentSection">
    	    <div class="row" style="display:none">
	    		 <select id="employerGroup">
	    		     <option value="0">Employer Group</option> 
				     <option value="1">Company</option> 
				     <option value="2">Startup Company</option> 
				     <option value="3">Research Project</option> 
				     <option value="4">MAC Project</option> 
				     <option value="5">Other</option> 
				 </select>
		         
		         <select id="positionStatus">
	    		     <option value="0">ALL</option> 
				     <option value="1">Requested</option> 
				     <option value="2">Confirmed</option> 
				     <option value="3">Rejected</option> 
				 </select>
		        
		         <button type="button" id="searchPosition" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">
  					Search
				 </button>
            </div>
    	    
    	     <div class="row">
	    	     <table id="example" class="display" cellspacing="0" width="100%">
			        <thead>
			            <tr>
			                <th>Position</th>
			                <th>Position Group</th>
			                <th>Organization</th>
			                <th>Work Place</th>
			                <th>Status</th>
			            </tr>
			        </thead>
			     </table>
    	     </div>
    	     
    </section>
    <input type="hidden" id="positionId"/> 
 </body>
</html>