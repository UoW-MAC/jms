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
               <sec:authorize access="hasAnyRole('ROLE_ORGANIZATION,ROLE_ADMIN') and isAuthenticated()">
                  <div id="updateForm">
						<form class="col-sm-9 rightBorder equalCol" id="editForm" role="form" action="updatePosition" method="post">
							<div class="row">
                            	<aside class="userFormCol-1">Position Name:</aside>
                            	<aside class="userFormCol-right"><input type="text" id="inputAdminDetailPositionName" name="positionName" value="${positionDetail.positionName}"/></aside>
                           	</div>
                           	<div class="row">
                            	<aside class="userFormCol-1">Responsibilities:</aside>
                           		<aside class="userFormCol-right"><textarea row="3" cols="82" id="inputAdminDetailResponsibilities" name="responsibilities">${positionDetail.responsibilities}</textarea></aside>
                           	</div>
                           	<div class="row">
                            	<aside class="userFormCol-1">Requirements:</aside>
                           		<aside class="userFormCol-right"><textarea row="3" cols="82" id="inputAdminDetailRequirements" name="requirements">${positionDetail.requirements}</textarea></aside>
                           	</div>
                           	<div class="row">
                            	<aside class="userFormCol-1">Salary:</aside>
                           		<aside class="userFormCol-right"><input type="text" id="inputAdminDetailSalary" name="salary" value=" ${positionDetail.salary}"/></aside>
                           	</div>
                           	<div class="row">
                            	<aside class="userFormCol-1">Description:</aside>
                           		<aside class="userFormCol-right"><textarea row="3" cols="82" id="inputAdminDetailPositionDescription" name="positionDescription">${positionDetail.positionDescription}</textarea></aside>
                           	</div>
							<div class="row">
								<aside class="userFormCol-1">Position Type:</aside>
								<aside class="userFormCol-right">				
              						<select id="inputAdminDetailPositionGroupSelect" name="positionGroup.positionGroupId"></select>
								</aside>
							</div>	<br>
							<div class="row">
								<input type ="hidden" id="inputAdminDetailPositionId" name = "positionId" value ="${positionDetail.positionId}"/>
								<input type ="hidden" id="inputAdminDetailPositionGroupId"  value ="${positionDetail.positionGroup.positionGroupId}"/>
								<input type="button" id="editAdminDetailPosition" value="Edit"/>
								<input type="button" class="white-bt" id="cancleAdminDetailPosition" value="Cancel" />
							</div>
						</form>
					</div>
                  </sec:authorize>
            </div>
    </section>
    
      <!-- Button trigger modal -->
      <button type="button" id="myModalTrigger2" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" style="display:none">Submit</button>
      <div class="modal fade in" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                     <h4 class="modal-title" id="myModalLabel2">Tip</h4>
                 </div>
                 <div class="modal-body">Are you sure to edit this position?</div>
                  <div class="buttonsRow">
                     <button id="return" type="button" class="white-bt" data-dismiss="modal">Cancel</button>
                     <button type="submit" id="confirmEdit" class="orange-bt meta-event-source" meta-event-handler="application:confirmEdit">Confirm</button>
                  </div>
            </div>
        </div>
      </div>
      
      <jsp:include page='footer.jsp'/>
 </body>
 <script data-main="js/app/pages/application" src="js/lib/require-2.1.15.min.js"></script>
</html>
