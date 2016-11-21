    define(['jquery', 'bootstrap', 'handlebars', 'validate', 'ajaxHandler',
            'jqueryForm', 'formValidator', 'additionalMethods','jDataTables'],
        function($, bootstrap, handlebars, validate, ajaxHandler,
                 jqueryForm, formValidator, additionalMethods, jDataTables) {

            "use strict";

            var employerForm = {};
            
            var employerShow = {};
            
            employerForm.View = {
                employerFormSubmit: $("#employerForm_submit"),
                enableSubmitButton: function() {
                    employerForm.View.candidateApplicationFormSubmit.removeClass("disabled");
                },
                disableSubmitButton: function() {
                    employerForm.View.employerFormSubmit.addClass("disabled");
                },
                
                getEmployerGroupView : function(options) {
    				var employerGroupSelect = $('#employerGroupSelect');
    				$('#employerGroupSelect' + ' option').remove();
    				for (var i = 0; i < options.length; i++) {
    					var optionText = options[i].employerGroupType;
    					var optionValue = options[i].employerGroupId;
    					var option = "<option value=" + optionValue + ">"
    							+ optionText + "</option>";
    					$('#employerGroupSelect').append(option);

    				}

    			},
            };
            employerShow.View = {
            		showEmployerInfo: function(data){
            			var employerProfiletemplate = handlebars.compile($("#employer-list").html());
            			var employerInfoObject = null;
            			var employerProfileHtml = null;
            			if (data!= null) {
            				employerInfoObject = data.employer;
            			}
            			employerProfileHtml = employerProfiletemplate(employerInfoObject);
				        $("#employerForm").append(employerProfileHtml);
            		},
            		employerSelectByAdmin:function(){
            			 $('#adminEmployerTest tbody').on( 'click', 'tr', function () {
                             if ( $(this).hasClass('selected') ) {
                                 $(this).removeClass('selected');
                             }
                             else {
                             	$('#adminEmployerTest').DataTable().$('tr.selected').removeClass('selected');
                                 $(this).addClass('selected');
                             }
                         } );
            		}
            };
            employerForm.Controller = {
            		getEmployerGroup : function() {
        				$.ajax({
        					type : "get",
        					dataType : "json",
        					url : 'getEmployerGroup',
        					success : function(data) {
        						employerForm.View.getEmployerGroupView(data);
        						if(data!=null){
        					        $("#employerGroupSelect option[value=" + $('#employerGroupId').val() + "]").attr("selected", true);
        						}
        					}
        				});
        			},
        			 exportEmployerCSV : function(){
      	                $.ajax({
      	 	                        type : "post",
      	 	                        dataType : "json",
      	 	                        url : "exportEmployerCSV",
      	 	                        data:{"keyword":$("#adminEmployerTest_filter").find("input").val()},
      	 	                        success : function(data) {
      	 	                            location.href = "downloadCsv?csvFileName=" + data.models.fileName;
      	 	                        }
      	 	                });
      	                  },
                handleFormSubmit: function(){
                    if ($("#employerForm_submit").hasClass("disabled") == true)
                    { 
                        return;
                    }
                    $("#employerModelTrigger").click();
                },
                handleConfirmedSubmit: function(){
                	$("#employerForm").ajaxSubmit(function(response) {
                        if (response.statusCode == 1000){
                            location.href = "user-center";
                        }else{
                            return;
                        }
                });
                }
               
               
            };
            employerShow.Controller = {
            		showEmployer: function(){ 
            			$.ajax({
            				type: "get",       
            				dataType: "json",            
            				url: 'showEmployer',   
            				success: function(data) {     
            					employerShow.View.showEmployerInfo(data);
            					 employerForm.Controller.getEmployerGroup();
            			    }      
            	        });       
            	},
            	loadEmployerByAdmin:function(){
            		 $('#adminEmployerTest').DataTable({
     			        ajax:  {
     			        	"url" : "showAdminEmployer",
     			        	"type" : "get",
     			        	//"data" : {"groupId" : groupId, "positionStatus" : positionStatus}
     			        },
     			        columns: [
     			            { data: "employerName" },
     			            { data: "employerGroup.employerGroupType" },
     			            { data: "allPositionNum" },
     			            { data:null,render:function(data){
     			            	function add0(m){return m<10?'0'+m:m };
     			            	var time = new Date(data.updateTime);
     			            	var y = time.getFullYear();
     			            	var m = time.getMonth()+1;
     			            	var d = time.getDate();
     			            	return y+'-'+add0(m)+'-'+add0(d);
     		            	}
     		            },
     		            { data: "employerId" },
     			        ],
     			        "rowCallback": function(row, data) { //data是后端返回的数据
     			        	  $("table th").css("text-align", "center");
     			              $('td:eq(0)', row).html('<a href=employerDetail?employerId='+ data.employerId + '>' + data.employerName + '</a>');
     			              $('td:eq(4)', row).html('<a href=javascript:void(0) onclick=delEmployerRow()>delete</a>');
     			        },
     			        "order": [[ 4, "asc" ]],
     			        select: true
     			    } );
            	}
            };
            function registerEventListener() {
                $("#employerForm_submit").click(function () {
                	if (formValidator.getEmployerValidator("#employerForm").form() == true){
                		employerForm.Controller.handleFormSubmit();
                	}
                });

                $("#confirmedSubmit").click(function () {
                    employerForm.Controller.handleConfirmedSubmit();
                });
                employerShow.Controller.showEmployer();
                $('#exportEmployerCSV').click(function(){
                	employerForm.Controller.exportEmployerCSV();
                 });
                employerShow.Controller.loadEmployerByAdmin();
                employerShow.View.employerSelectByAdmin();
            }

            $(function() {
                registerEventListener();
                
            });

});
