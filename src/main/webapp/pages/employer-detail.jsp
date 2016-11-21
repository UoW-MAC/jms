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
            <link rel="icon" href="images/favicon.png">
		<title>Employer Detail</title>
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


	</head>

	<body>
	<jsp:include page='header.jsp'/>

    <!-- CONTENT SECTION -->
    <section class="contentSection position-detail">
    	<div class="container">
               <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                  <div id="updateEmployerForm">
							<form  id="editEmployerForm" class ="col-sm-9 rightBorder equalCol" role="form" action="makeEmployer" method="post">
								 <div class="row">
                            <aside class="userFormCol-1">Company Name:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="inputemployer_name" name="employerName" value="${employerDetail.employerName}"></aside>
                        </div>
                         <div class="row">
                            <aside class="userFormCol-1">Address:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-200" id="employeraddress" name="employerAddress" value=" ${employerDetail.employerAddress}"></aside>
                        </div>
                         <div class="row">
                            <aside class="userFormCol-1">City:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employercity" name="employerCity" value="${employerDetail.employerCity}"></aside>
                        </div>
                          <div class="row">
                            <aside class="userFormCol-1">Post Code:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employerpost_code" name="employerPostCode" value="${employerDetail.employerPostCode}"></aside>
                        </div>
                          <div class="row">
                            <aside class="userFormCol-1">Country</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employercountry" name="employerCountry" value="${employerDetail.employerCountry}"></aside>
                        </div>
                         <div class="row">
                            <aside class="userFormCol-1">First Name:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employercontactor_first_name" name="contactorFirstName" value="${employerDetail.contactorFirstName}"></aside>
                        </div>
                        <div class="row">
                            <aside class="userFormCol-1">Last Name:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employercontactor_last_name" name="contactorLastName" value="${employerDetail.contactorLastName}"></aside>
                        </div>
                        <div class="row">
                            <aside class="userFormCol-1">Contacts Position:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employercontactor_position" name="contactorPosition" value="${employerDetail.contactorPosition}"></aside>
                        </div>
                         <div class="row">
                            <aside class="userFormCol-1">Telephone:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employertelephone" name="employerTelephone" value="${employerDetail.employerTelephone}"></aside>
                        </div>
                          <div class="row">
                            <aside class="userFormCol-1">Email:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employeremail" name="employerEmail" value="${employerDetail.employerEmail}"></aside>
                        </div>
                         <div class="row">
                            <aside class="userFormCol-1">Website:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employerwebsite" name="employerWebsite" value="${employerDetail.employerWebsite}"></aside>
                        </div>
                        <div class="row">
                            <aside class="userFormCol-1">Notes:</aside>
                            <aside class="userFormCol-right"><input type="text" class="width-330" id="employernotes" name="employerNotes" value=" ${employerDetail.employerNotes}"></aside>
                        </div>
                        <div class="row">
							<aside class="userFormCol-1">Employer Group:</aside>
							<aside class="userFormCol-right">				
              					<select id="inputemployerGroupSelect" name="employerGroup.employerGroupId">
             				 	</select>
							</aside>
						</div>	<br>
						<div class="row">
							<input type ="hidden" id="employer_Id" name = "employerId" value ="${employerDetail.employerId}"/>
							<input type ="hidden" id="inputemployerGroupId" value ="${employerDetail.employerGroup.employerGroupId}"/>
							<input type="button" id="editEmployer" value="Edit"/>
							<input type="button" class="white-bt" id="cancelEmployer" value="Cancel" />
						</div>
					</form>
				</div>
               </sec:authorize>
            </div>
    </section>
      <!-- Button trigger modal -->
      <button type="button" id="myModalTrigger6" class="btn btn-primary" data-toggle="modal" data-target="#myModal6" style="display:none">Submit</button>
      <div class="modal fade in" id="myModal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel6" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                     <h4 class="modal-title" id="myModalLabel6">Tip</h4>
                 </div>
                 <div class="modal-body">Are you sure to edit this Employer?</div>
                  <div class="buttonsRow">
                     <button id="return" type="button" class="white-bt" data-dismiss="modal">Cancel</button>
                     <button type="submit" id="confirmEmployerEdit" class="orange-bt meta-event-source" meta-event-handler="application:confirmEdit">Confirm</button>
                  </div>
            </div>
        </div>
      </div>
      <jsp:include page='footer.jsp'/>
 </body>
 <script data-main="js/app/pages/employer-detail" src="js/lib/require-2.1.15.min.js"></script>
</html>
