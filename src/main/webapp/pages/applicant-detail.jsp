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
        <!--[if IE 8]>
        <link href="css/ie8.css" rel="stylesheet" type="text/css">
        <![endif]-->


	</head>

	<body>
	<jsp:include page='header.jsp'/>
	
    <!-- CONTENT SECTION -->
    <section class="contentSection position-detail">
    	<div class="container">
    	
                <div class="row">
                <input type="hidden" id = "applicationId" value="${applicationId}">
                <input type="hidden" id = "positionId" value="${positionId}">
                 </div>
                    	<div class="row">
                        	<aside class="userFormCol-1">Gender:</aside>
                        	<aside class="userFormCol-right">${candidateDetail.gender}</aside>
                        </div>
	                        	<div class="row">
	                            	<aside class="userFormCol-1">Full Name</aside>
	                            	<aside class="userFormCol-right"> ${candidateDetail.firstName} ${candidateDetail.middleName} ${candidateDetail.lastName}</aside>
	                        	</div>
	                         	<div class="row">
	                            	<aside class="userFormCol-1">Email</aside>
	                            	<aside class="userFormCol-right"> ${candidateDetail.email}</aside>
	                        	</div>
		                        <div class="row">
		                            <aside class="userFormCol-1">Telephone</aside>
		                            <aside class="userFormCol-right"> ${candidateDetail.telephone}</aside>
		                        </div><br>
	          					<div class="table">
					                 <table width="750px" border="1" cellspacing="0" cellpadding="0">
					                     <tr>
					                     <td></td>
					                     <td style="text-align:center"><div>Major</div></td>
					                     <td style="text-align:center"><div>GPA</div></td>
					                     <td style="text-align:center"><div>University/Organization</div></td>
					                     <td style="text-align:center"><div>Country</div></td>
					                     <td style="text-align:center"><div>MM/YY</div></td>
					                     </tr>
					                     <tr>
					                     <td>${candidateDetail.educationList[0].degree}</td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[0].major}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[0].gpa}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.educationList[0].institution}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[0].country} </aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[0].graduationDate}</aside></td>
					                     </tr>
					                     
					                     <tr>
					                     <td>${candidateDetail.educationList[1].degree}</td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[1].major}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[1].gpa}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.educationList[1].institution}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[1].country}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[1].graduationDate}</aside></td>
					                     </tr>
					                     <tr>
					                     <td><aside class="userFormCol-5">${candidateDetail.educationList[2].degree}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[2].major}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[2].gpa}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.educationList[2].institution}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[2].country}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[2].graduationDate}</aside></td>
					                     </tr>
					                     <tr>
					                     <td>${candidateDetail.educationList[3].degree}</td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[3].major}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[3].gpa}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.educationList[3].institution}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[3].country}</aside></td>
					                     <td><aside class="userFormCol-1">${candidateDetail.educationList[3].graduationDate}</aside></td>
					                     </tr>
					                </table>
	                            </div>
				                <div class="table">
					                 <table width="750px" border="1" cellspacing="0" cellpadding="0">
					                     <tr>
					                     <td><div style="text-align:center">Company</div></td>
					                     <td><div style="text-align:center">Dates(MM/YY-MM/YY)</div></td>
					                     <td><div style="text-align:center">Title</div></td>
					                     <td><div style="text-align:center">Duties</div></td>
					                     </tr>
					                     <tr>                    
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[0].company}</aside></td>
					                     <td><aside class="userFormCol-4">${candidateDetail.workList[0].dates}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[0].title}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[0].duties}</aside></td>
					                     </tr>
					                     
					                     <tr>           
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[1].company}</aside></td>
					                     <td><aside class="userFormCol-4">${candidateDetail.workList[1].dates}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[1].title}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[1].duties}</aside></td>                                     
					                     </tr>
					                     
					                     <tr>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[2].company}</aside></td>
					                     <td><aside class="userFormCol-4">${candidateDetail.workList[2].dates}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[2].title}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[2].duties}</aside></td>                    
					                     </tr>
					                     <tr>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[3].company}</aside></td>
					                     <td><aside class="userFormCol-4">${candidateDetail.workList[3].dates}</aside></td>                   
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[3].title}</aside></td>
					                     <td><aside class="userFormCol-2">${candidateDetail.workList[3].duties}</aside></td>
					                     </tr>
					                 </table>
				                </div>
          		    <div class="row">
                	<input type="button" id="candidateAccept" value="Accept"/>
                	<input type="button" id="candidateReject" value="Reject"/>
                	<input type="button" class="white-bt" id="candidateDetailPageCancel" value="Cancel"/>
                    </div>
           </div>
    </section>

    
    <!-- Button trigger modal -->
      <button type="button" id="myAcceptTrigger" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="display:none">Submit</button>
      <div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                     <h4 class="modal-title" id="myModalLabel">Tip</h4>
                 </div>
                 <div class="modal-body">Are you sure to accept this candidate?</div>
                  <div class="buttonsRow">
                     <button id="return" type="button" class="white-bt" data-dismiss="modal">Cancel</button>
                     <button type="submit" id="confirmAccept" class="orange-bt meta-event-source" meta-event-handler="candidate:confirmApply">Confirm</button>
                  </div>
            </div>
        </div>
      </div>
        <button type="button" id="myRejectTrigger" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" style="display:none">Submit</button>
      <div class="modal fade in" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                     <h4 class="modal-title" id="myModalLabel2">Tip</h4>
                 </div>
                 <div class="modal-body">Are you sure to reject this candidate?</div>
                  <div class="buttonsRow">
                     <button id="return" type="button" class="white-bt" data-dismiss="modal">Cancel</button>
                     <button type="submit" id="confirmReject" class="orange-bt meta-event-source" meta-event-handler="candidate:confirmReject">Confirm</button>
                  </div>
            </div>
        </div>
      </div>
 </body>
 <script data-main="js/app/pages/candidate" src="js/lib/require-2.1.15.min.js"></script>
</html>