/*
 * Created by William Zhang 18/02/16
 */

    define(['jquery', 'bootstrap', 'validate', 'ajaxHandler',
            'jqueryForm', 'formValidator', 'additionalMethods', 'custom', 'eventHandler',
            'jDataTables', 'jms'],
        function($, bootstrap, validate, ajaxHandler,
                 jqueryForm, formValidator, additionalMethods, custom, eventHandler,
                 jDataTables, jms) {

            "use strict";

            var position = {};

            position.View = {
            	
            	positionSelectByEmployer : function() {
	            	$('#positionTest tbody').on( 'click', 'tr', function () {
	                        if ( $(this).hasClass('selected') ) {
	                            $(this).removeClass('selected');
	                        }
	                        else {
	                        	$('#positionTest').DataTable().$('tr.selected').removeClass('selected');
	                            $(this).addClass('selected');
	                        }
	                } );
            	},
            	
            	getPositionGroupView : function(options) {
    				var positionGroupSelect = $('#positionGroupSelect');
    				$('#positionGroupSelect' + ' option').remove();

    				for (var i = 0; i < options.length; i++) {

    					var optionText = options[i].positionGroupName;
    					var optionValue = options[i].positionGroupId;

    					var option = "<option value=" + optionValue + ">"
    							+ optionText + "</option>";
    					positionGroupSelect.append(option);
    				}
        	     },
        	     positionSelectByAdmin : function() {
	        	     $('#adminPositionTest tbody').on( 'click', 'tr', function () {
                        if ( $(this).hasClass('selected') ) {
                            $(this).removeClass('selected');
                        }
                        else {
                        	$('#adminPositionTest').DataTable().$('tr.selected').removeClass('selected');
                            $(this).addClass('selected');
                        }
                     });
        	     
        	     },
        	     applicationSelectByAdmin : function() {
        	     $('#adminApplicationTest tbody').on( 'click', 'tr', function () {
                     if ( $(this).hasClass('selected') ) {
                         $(this).removeClass('selected');
                     }
                     else {
                     	 $('#adminApplicationTest').DataTable().$('tr.selected').removeClass('selected');
                         $(this).addClass('selected');
                     }
                 });			 
        	     },	
        		 positionFormSubmit : $("#positionForm_submit")
        	};

            position.Controller = {
            	getPositionGroup : function() {
        				$.ajax({
        					type : "get",
        					dataType : "json",
        					url : 'getPositionGroup',
        					success : function(data) {
        						position.View.getPositionGroupView(data);
        					}
        				});
        			},
        			 exportPositionCSV : function(){
     	                $.ajax({
     	 	                        type : "post",
     	 	                        dataType : "json",
     	 	                        url : "exportPositionCSV",
     	 	                        data:{"keyword":$("#adminPositionTest_filter").find("input").val()},
     	 	                        success : function(data) {
     	 	                            location.href = "downloadCsv?csvFileName=" + data.models.fileName;
     	 	                        }
     	 	                });
     	                  },
        			handleFormSubmit : function() {
        				$("#positionForm").submit();
        			},
        			
        			addDisplay: function(){ 
            		 var positionTable=document.getElementById("positionTable");
 				       var addForm=document.getElementById("addForm");
 				        if(positionTable.style.display=="none"){
 				        	addForm.style.display="none";
 				        	positionTable.style.display="";
 				        }else{
 				        	positionTable.style.display="none";
 				        	addForm.style.display="";
 				      }
            		 },
            		 
            		 loadPostionStatusList : function() {
            		    //var groupId = $('#employerGroup').find("option:selected").val();
                	    //var positionStatus = $('#positionStatus').find("option:selected").val();
            		 
            		 	$('#example').DataTable({
				        ajax:  {
				        	"url" : "getPostionStatusList",
				        	"type" : "post"
				        	//"data" : {"groupId" : groupId, "positionStatus" : positionStatus}
				        	//"dataSrc": "data"
				        },
				        columns: [
				            { data: "position.positionName" },
				            { data: "position.positionGroup.positionGroupName" },
				            { data: "employer.employerName" },
				            { data: "employer.employerCity" },
				            { data: null, render: 
				                function ( data, type, row ) {
					            	var result;
					            	
					            	if (data == null || data.applicationStatus == 0)
					            		result = 'New'; 
					            	else if (data.applicationStatus == 1)
					                	result = 'Requested';
					                else if (data.applicationStatus == 2)
					                	result = 'Success';
					                else if (data.applicationStatus == 3)
					                    result = 'Rejected';
					                return result;
				                }  
				            }
				            
				        ],
				        rowCallback : function(row, data) {
				        	$('td:eq(0)', row).html('<a href=position-detail?positionId='+ data.position.positionId + '>' + data.position.positionName + '</a >');
				        	$("table th").css("text-align", "center");
				        },
				        select: true
				       } );
            		 
            		 },
            		 
            		 loadPositionByEmployer : function() {
            		 
            		 	$('#positionTest').DataTable({
			             ajax:  {
			        	"url" : "showPosition",
			        	"type" : "get",
			        	//"data" : {"groupId" : groupId, "positionStatus" : positionStatus}
			        },
			        columns: [
			            { data: "positionName" },
			            { data: "positionGroup.positionGroupName" },
			            { data:null,render:function(data){
			            	function add0(m){return m<10?'0'+m:m };
			            	var time = new Date(data.updateTime);
			            	var y = time.getFullYear();
			            	var m = time.getMonth()+1;
			            	var d = time.getDate();
			            	return y+'-'+add0(m)+'-'+add0(d);
		            	}
		            },
			            { data: "unreadApplicationNum" },
			            { data: "unreadApplicationNum" },
			        ],
			        "rowCallback": function(row, data) { //data是后端返回的数据
			              $('td:eq(0)', row).html('<a href=adminPositionDetail?positionId='+ data.positionId + '>' + data.positionName + '</a>');
			              $('td:eq(3)', row).html('<a href=candidate?positionId='+ data.positionId + '>' + data.unreadApplicationNum+'/'+data.applicationNum + '</a>');
			              $('td:eq(4)', row).html('<a href=javascript:void(0) onclick=delPositionRow()>delete</a>');
			              $("table th").css("text-align", "center");
			        },
			        "order": [[ 2, "desc" ]],
			        select: true
			    } );
            		 
            		 },
            		 
            		 loadPositionByAdmin : function() {
            		 	$('#adminPositionTest').DataTable({
    			        ajax:  {
    			        	"url" : "showPosition",
    			        	"type" : "get",
    			        	//"data" : {"groupId" : groupId, "positionStatus" : positionStatus}
    			        },
    			        columns: [
    			            { data: "positionName" },
    			            { data: "positionGroup.positionGroupName" },
    			            { data: "employer.employerName" },
    			            { data:null,render:function(data){
    			            	function add0(m){return m<10?'0'+m:m };
    			            	var time = new Date(data.updateTime);
    			            	var y = time.getFullYear();
    			            	var m = time.getMonth()+1;
    			            	var d = time.getDate();
    			            	return y+'-'+add0(m)+'-'+add0(d);
    		            	}
    		            },
    		            { data: "positionId" },
    			        ],
    			        "rowCallback": function(row, data) { //data是后端返回的数据
    			              $('td:eq(0)', row).html('<a href=adminPositionDetail?positionId='+ data.positionId + '>' + data.positionName + '</a>');
    			              $('td:eq(4)', row).html('<a href=javascript:void(0) onclick=delAdminPositionRow()>delete</a>');
    			        },
    			        "order": [[ 4, "asc" ]],
    			        select: true
    			    } );
            		 
            		 
            		 },
            		 loadApplicationByAdmin : function() {
            			 $('#adminApplicationTest').DataTable({
                           	 ajax:  {
            			        	"url" : "getCandidateInfoList",
            			        	"type" : "post",
            			        	"data" : {"positionId" : 0}
            			        },
            			        columns: [
            			            { data: "candidate.lastName" },
            			            { data:  "position.positionName"},
            			            { data:  "employer.employerName"},
            			            { data: null, render:
            			                function ( data, type, row ) {
            			            	var result;
            			            	if (data.applicationStatus == 0)
            			            		result = 'New';
            			            	else if (data.applicationStatus == 1)
            			                	result = 'Unread';
            			                else if (data.applicationStatus == 2)
            			                	result = 'Accepted';
            			                else if (data.applicationStatus == 3)
            			                    result = 'Rejected';
            			                return result;
            		                	}
            			            },
            			            { data:null,render:function(data){
            			            	function add0(m){return m<10?'0'+m:m };
            			            	var time = new Date(data.updateTime);
            			            	var y = time.getFullYear();
            			            	var m = time.getMonth()+1;
            			            	var d = time.getDate();
            			            	return y+'-'+add0(m)+'-'+add0(d);
            		            	}
            		            },
            		            { data:  "candidate.firstName"},
            			        ],
            			        "rowCallback": function(row, data) { //data是后端返回的数据
            			              $('td:eq(0)', row).html( data.candidate.firstName +'&nbsp'+ data.candidate.lastName);
            			              $('td:eq(5)', row).html('<a href=javascript:void(0) onclick=delApplicationRow()>delete</a>');
            			        },
            			        select: true
            			    } );
            		 },
            			exportApplicationCSV : function(){
          	                $.ajax({
          	 	                        type : "post",
          	 	                        dataType : "json",
          	 	                        url : "exportApplicationCSV",
          	 	                        data:{"keyword":$("#adminApplicationTest_filter").find("input").val()},
          	 	                        success : function(data) {
          	 	                            location.href = "downloadCsv?csvFileName=" + data.models.fileName;
          	 	                        }
          	 	                });
          	                  }
        		};
            

            function registerEventListener() {
                /*$("#positionApplicationTab").click(function(){
                	application.Controller.loadPostionStatusList();
                });*/
                
                /*$('#searchPosition').click(function(){
                	application.Controller.loadPostionStatusList();
                });*/
                
                position.Controller.loadPostionStatusList();
                position.Controller.getPositionGroup();
                position.Controller.loadPositionByAdmin();
                position.Controller.loadPositionByEmployer();
                position.Controller.loadApplicationByAdmin();
                position.View.positionSelectByEmployer();
                position.View.positionSelectByAdmin();
                position.View.applicationSelectByAdmin();
                
                
                $("#submitPosition").click(function() {
                	if (formValidator.getPositionValidator("#positionForm").form() == true){
                		$("#myModalTrigger3").click();
                	}
    			});
                $("#confirmSubmit").click(function(){
                	position.Controller.handleFormSubmit();
                });
                $('#addDisplay').click( function () {
                	position.Controller.addDisplay();
                } );
                $('#addDisplay2').click( function () {
                	position.Controller.addDisplay();
                } );
                $('#exportPositionCSV').click(function(){
                	position.Controller.exportPositionCSV();
	            });
                $('#exportApplicationCSV').click(function(){
                	position.Controller.exportApplicationCSV();
                });
            }
                
            
            $(function() {
                registerEventListener();
            });


    jms.position = position;
    return position;

});
