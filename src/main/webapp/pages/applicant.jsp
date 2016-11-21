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
    	<link rel="icon" href="images/favicon.png">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/custom.css" rel="stylesheet" type="text/css">
        <link href="css/validation.css" rel="stylesheet">
        <link href="css/jquery.dataTables.min.css" rel="stylesheet">
        <!--[if IE 8]>
        <link href="css/ie8.css" rel="stylesheet" type="text/css">
        <![endif]-->


	</head>

	<body>
	<jsp:include page='header.jsp'/>
	
    <!-- CONTENT SECTION -->
    <section class="contentSection position-detail">
    	<div class="container">
	    	<input type="hidden" id = "positionId" value="${positionId}"/>
	    	<table id="candidateList" class="display" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Candidate Id</th>
		                <th>Candidate Name</th>
		                <th>Application Status</th>
		                <th>Application Time</th>
		            </tr>
		        </thead>
		    </table>
		    <input type="button" class="white-bt" id="cancelCandidate" value="cancel" />
        </div>
    </section>
    
 </body>
 <script data-main="js/app/pages/candidate" src="js/lib/require-2.1.15.min.js"></script>
</html>