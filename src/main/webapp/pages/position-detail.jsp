<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
		<title>Position Detail</title>
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="js/lib/html5shiv.min.js"></script>
        <script src="js/lib/respond.min.js"></script>
        <![endif]-->

        <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/custom.css" rel="stylesheet" type="text/css">
        <link href="css/validation.css" rel="stylesheet">
        
        <link rel="icon" href="images/favicon.png">
        
        <!--[if IE 8]>
        <link href="css/ie8.css" rel="stylesheet" type="text/css">
        <![endif]-->


	</head>

	<body>
	<jsp:include page='header.jsp'/>

    <!-- CONTENT SECTION -->
    <section class="contentSection position-detail">
    	<div class="container">
    	<div id="positionEditTable">
            <div class="row">
               <div id="positionId" value="${positionDetail.positionId}"></div>
                </div>
                <div class="row positionName">
                	${positionDetail.positionName}
                </div>
                <br>
                <div class="row positionGroupName" id="positionGroupId" value="${positionDetail.positionGroup.positionGroupId}">Position Group -</div>
                <br>
                <div class="row">
                    ${positionDetail.positionGroup.positionGroupName}
                </div>
                <br>
                <div class="row positionDescription">Position Description -</div>
                <br>
                <div class="row">
                    ${positionDetail.positionDescription}
                </div>
                <br>
                <div class="row responsibilities">Position Responsibilities -</div>
                <br>
                <div class="row">
                    ${positionDetail.responsibilities}
                </div>
                <br>
                <div class="row requirements">Position Requirements -</div>
                <br>
                <div class="row">
                    ${positionDetail.requirements}
                </div>
                <br>
                <div class="row salary">Salary -</div>
                <br>
                <div class="row">
                    ${positionDetail.salary}
                </div>
                <br>
                 <sec:authorize access="hasRole('ROLE_CANDIDATE') and isAuthenticated()">
                <div class="row">
                	<input type="button" id="positionApply" value="Apply"/>
                	<input type="button" id="cancelApply" value="Cancel"/>
                </div>
                  </sec:authorize>
              </div>
            </div>
    </section>
 
    <!-- Button trigger modal -->
      <button type="button" id="myModalTrigger" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="display:none">Submit</button>
      <div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                     <h4 class="modal-title" id="myModalLabel">Tip</h4>
                 </div>
                 <div class="modal-body">Are you sure applying this position?</div>
                  <div class="buttonsRow">
                     <button id="return" type="button" class="white-bt" data-dismiss="modal">Cancel</button>
                     <button type="submit" id="confirmApply" class="orange-bt meta-event-source" meta-event-handler="application:confirmApply">Confirm</button>
                  </div>
            </div>
        </div>
      </div>
      
      <jsp:include page='footer.jsp'/>
 </body>
 <script data-main="js/app/pages/application" src="js/lib/require-2.1.15.min.js"></script>
</html>
