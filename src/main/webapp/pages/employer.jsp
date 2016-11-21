<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--doctype html-->
<html>
	<head>
		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Employer Center</title>
	</head>

	<body>
    <!-- CONTENT SECTION -->
    <section class="contentSection">
    	<div class="container">
    	    <div class="row">
    	        <span class="col-sm-9 rightBorder equalCol">
                    <form class="employerForm" id="employerForm" role="form" action="makeEmployer" method="post">
                    	 <div id="employerTable" style="position:relative"class="row" >
                     	     <script id="employer-list" type="text/x-handlebars-template">
				         	     <div class="row">
		                     	 	<aside class="userFormCol-1">Company Name:</aside>
		                     	 	<aside class="userFormCol-right"><input type="text" class="width-330" id="employer_name" name="employerName" value="{{employerName}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Address:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-200" id="address" name="employerAddress" value="{{employerAddress}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">City:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id="city" name="employerCity" value="{{employerCity}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Post Code:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id="post_code" name="employerPostCode" value="{{employerPostCode}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Country:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id="country" name="employerCountry" value="{{employerCountry}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Telephone:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id="telephone" name="employerTelephone" value="{{employerTelephone}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Email:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id="email" name="employerEmail" value="{{employerEmail}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Website:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id="website" name="employerWebsite" value="{{employerWebsite}}"></aside>
		                         </div>
		                         <div class="row">
		                            <aside class="userFormCol-1">Notes:</aside>
		                            <aside class="userFormCol-right"><input type="text" class="width-330" id=notes name="employerNotes" value="{{employerNotes}}"></aside>
		                         </div>
								 <input type ="hidden" id="employerId" name = "employerId" value ="{{employerId}}"/>
								 <input type ="hidden" id="employerGroupId" name = "employerGroupId" value ="{{employerGroup.employerGroupId}}"/>
								 <div class="row">
									<aside class="userFormCol-1">Employer Group:</aside>
									<aside class="userFormCol-right">				
		              					<select id="employerGroupSelect" name="employerGroup.employerGroupId">
		             				 	</select>
									</aside>
								 </div>	
 					         </script>
 				         </div>
			 			 <div id="abc" style="position:absolute; bottom:-50px;left:20px">
			 			     <div class="row"><input type="button" id="employerForm_submit" value="submit"/></div>
			 			 </div>  
                     </form>
                 </span>
                  <!-- Button trigger modal -->
	                <button type="button" id="employerModelTrigger" data-toggle="modal" data-target="#employerModel" style="display:none"></button>
	                <!-- Project Application Page-Confirm Modal -->
	                <div class="modal fade" id="employerModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	                    <div class="modal-dialog">
	                        <div class="modal-content">
	                            <div class="modal-header">
	                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
	                                <h4 class="modal-title" id="myModalLabel">please....</h4>
	                            </div>
	                            <div class="modal-body">
	                                Do you want to submit?
	                            </div>
	                            <div class="buttonsRow">
	                                <button type="submit" class="orange-bt" id="confirmedSubmit">submit</button>
	                                <button type="button" class="white-bt" data-dismiss="modal">cancel</button>
	                            </div>
	                        </div>
	                    </div>
	                </div>
             </div>
	     </div>
     </section>
     </body>
</html>