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
       <div id="positionTable">
		   <div class="row">
    	       <table id="positionTest" class="display" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Position Name</th>
		                <th>Group Name</th>
		                <th>Update Time</th>
		                <th>Unread/AllApp</th>
		                <th></th>
		            </tr>
		        </thead>
		       </table>
	        </div><br>
	         <div class="row">
			     <button type="button" id="addDisplay" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">
   					Add Position
 				 </button>
 			 </div>
       </div>
				<div id="addForm" style="display:none">
				<form class="positionForm col-sm-9 rightBorder equalCol" id="positionForm" role="form" action="makePosition" method="post">
						<div class="row">
							<aside class="userFormCol-1">Name</aside>
							<aside class="userFormCol-right">
								<input type="text" class="width-330" id="positionName" name="positionName">
							</aside>
						</div>
						<div class="row">
							<aside class="userFormCol-1">Responsibilities</aside>
							<aside class="userFormCol-right">
								<textarea style="background-color:#f7f9fa" row="3" cols="62" class="width-330" id="responsibilities"
									name="responsibilities"></textarea>
							</aside>
						</div>
						<div class="row">
							<aside class="userFormCol-1">Requirements</aside>
							<aside class="userFormCol-right">
								<textarea style="background-color:#f7f9fa" row="3" cols="62" id="requirements"
									name="requirements"></textarea>
							</aside>
						</div>
						<div class="row">
							<aside class="userFormCol-1">Salary</aside>
							<aside class="userFormCol-right">
								<input type="text" class="width-330" id="salary"
									name="salary">
							</aside>
						</div>
						<div class="row">
							<aside class="userFormCol-1">Description</aside>
							<aside class="userFormCol-right">
								<textarea style="background-color:#f7f9fa" row="3" cols="62" class="width-330" id="positionDescription"
									name="positionDescription"></textarea>
							</aside>
						</div>
						<div class="row">
							<aside class="userFormCol-1">Position Group</aside>
							<aside class="userFormCol-right">								
              					<select id="positionGroupSelect" name="positionGroup.positionGroupId">
             				 	</select>
							</aside>
						</div><br>
						<div class="row">
							<input type="button" id="submitPosition" value="Submit"/>
							<input type="button" value="cancel" class="btn2" id = "addDisplay2"/>
						</div>
					</form>
					</div>
	</section>
	<!-- /CONTENT SECTION -->
<!-- Button trigger modal -->
      <button type="button" id="myModalTrigger3" class="btn btn-primary" data-toggle="modal" data-target="#myModal3" style="display:none">Submit</button>
      <div class="modal fade in" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                     <h4 class="modal-title" id="myModalLabel2">Tip</h4>
                 </div>
                 <div class="modal-body">Are you sure to submit this position?</div>
                  <div class="buttonsRow">
                     <button id="return" type="button" class="white-bt" data-dismiss="modal">Cancel</button>
                     <button type="submit" id="confirmSubmit" class="orange-bt meta-event-source" meta-event-handler="position:confirmSubmit">Confirm</button>
                  </div>
            </div>
        </div>
      </div>
</body>
	 <script type="text/javascript"  >
	function delPositionRow () {
		var a= $('#positionTest').DataTable().row('.selected').data();
		if(a!=null){
		$('#positionTest').DataTable().row('.selected').remove().draw( false );
         $.ajax({
    				type: "get",       
    				dataType: "json",            
    				url: 'deletePosition',   
    				data:  a,
    				success: function() {     
    			    }      
    	        });       
		 }
      }
 </script>
</html>