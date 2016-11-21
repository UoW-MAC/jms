    define(['jquery', 'bootstrap', 'handlebars', 'validate', 'ajaxHandler',
            'jqueryForm', 'formValidator', 'additionalMethods', 'jms', 'jDataTables'],
        function($, bootstrap, handlebars, validate, ajaxHandler,
                 jqueryForm, formValidator, additionalMethods, jms, jDataTables) {

            "use strict";

            var candidateProfile = {};

            candidateProfile.View = {
                candidateInfoSelectByAdmin : function() {
	            	$('#adminCandidateTest tbody').on( 'click', 'tr', function () {
	                       if ( $(this).hasClass('selected') ) {
	                           $(this).removeClass('selected');
	                       }
	                       else {
	                       	$('#adminCandidateTest').DataTable().$('tr.selected').removeClass('selected');
	                           $(this).addClass('selected');
	                       }
	                });
                },
            };

            candidateProfile.Controller = {
                handleFormSubmit: function(){
                    $("#candidateModelTrigger").click();
                },
                handleConfirmedSubmit: function(){
                	$("#candidateForm").ajaxSubmit(function(response) {
	                        if (response.statusCode == 1000){
	                            $("#candidateCancel").click();
	                        }else{
	                            $("#candidateCancel").click();
	                        }
                    });
                },
                exportCandidateCSV : function(){
  	                $.ajax({
                        type : "post",
                        dataType : "json",
                        url : "exportCandidateCSV",
                        data:{"keyword":$("#adminCandidateTest_filter").find("input").val()},
                        success : function(data) {
                            location.href = "downloadCsv?csvFileName=" + data.models.fileName;
                        }
  	 	            });
  	            },
                loadCandidateProfile : function(){
                	var a = 0;
                	$.ajax({
				        type: "post",
					    dataType: "json",
				        url: 'getCandidateInfo',
				        data: {"candidateId":a},
				        success: function(data) {
					        var candidateProfileSource = $("#candidateProfileTable").html(),
			                	candidateProfiletemplate = handlebars.compile(candidateProfileSource),
			                	candidateInfoObject = null,
			                	candidateProfileHtml = null;

		                	if (data.models != null) {

		                		candidateInfoObject = data.models.candidateInfo;
		                	}

		                	candidateProfileHtml = candidateProfiletemplate(candidateInfoObject);
					        $("#candidateInfoForm").append(candidateProfileHtml);

					        //Notice: loading order
					        if (data.models != null) {
		                		if (data.models.candidateInfo.status == '0') {
		                			$('#is').parents(".radioCustom").addClass("radioCustom-checked");
		                			$('#is').attr("checked","checked");

		                		}else if (data.models.candidateInfo.status == '1') {
		                			$('#pr').parents(".radioCustom").addClass("radioCustom-checked");
		                			$('#pr').attr("checked","checked");
		                		}
		                		if (data.models.candidateInfo.gender == 'Male') {
		                			$('#male').parents(".radioCustom").addClass("radioCustom-checked");
		                			$('#male').attr("checked","checked");

		                		}else if (data.models.candidateInfo.gender == 'Female') {
		                			$('#female').parents(".radioCustom").addClass("radioCustom-checked");
		                			$('#female').attr("checked","checked");
		                		}
					        }
				        }
				    });
                }, 
                loadCandidateProfileByAdmin : function() {
                
	                $('#adminCandidateTest').DataTable({
	   			        ajax:  {
	   			        	"url" : "adminCandidateInfo",
	   			        	"type" : "get",
	   			        	//"data" : {"groupId" : groupId, "positionStatus" : positionStatus}
	   			        },
	   			        columns: [
	   			            { data: "candidateId" },
	   			            { data: "firstName" },
	   			            {data: "lastName"},
	   			         ],
	   			         "rowCallback": function(row, data) { //data是后端返回的数据
	   			           $('td:eq(1)', row).html('<a href=getAdminCandidateInfo?candidateId='+data.candidateId+ '>' + data.firstName +'&nbsp'+ data.lastName + '</a>');
				           $('td:eq(2)', row).html('<a href=javascript:void(0) onclick=delRow()>delete</a>');
	   			         },
	   			         "order": [[ 0, "asc" ]],
	   			         select: true
	   			    });
                }

            };

            function registerEventListener() {
                $("#candidateProfile_submit").click(function () {
                	if (formValidator.getCandidateValidator("#candidateForm").form() == true){
                		candidateProfile.Controller.handleFormSubmit();
                	}
                });

                $("#confirmedSubmit").click(function () {
                    candidateProfile.Controller.handleConfirmedSubmit();
                });
                
                $('#exportCandidateCSV').click(function(){
                	   candidateProfile.Controller.exportCandidateCSV();
                });
                
                candidateProfile.Controller.loadCandidateProfile();
                candidateProfile.Controller.loadCandidateProfileByAdmin();
                
                candidateProfile.View.candidateInfoSelectByAdmin();
            }
           
           
            $(function() {
                registerEventListener();
            });

    jms.candidateProfile = candidateProfile;
    return candidateProfile;

});
