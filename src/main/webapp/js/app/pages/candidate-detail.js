/**
 * Created by Freya He 29/01/16
 */
require(['../main'], function () {
    require(['jquery', 'bootstrap', 'ajaxHandler', 'handlebars', 'jqueryForm', 'formValidator'],
        function($, bootstrap, ajaxHandler,handlebars,jqueryForm,formValidator) {

            "use strict";

            var candidateDetail = {};
            
            candidateDetail.View = {};
            candidateDetail.Controller = {
            		
            		handleConfirmedSubmit: function(){
                    	$("#candidateForm").ajaxSubmit(function(response) {
    	                        if (response.statusCode == 1000){
    	                            location.href = "user-center";
    	                        }else{
    	                            return;
    	                        }
                        });
                    },
            
            		loadCandidateProfile : function(){
            			var adminCandidateId=0;
                    	if($("#adminCandidateId").val()!=null){
                    		var adminCandidateId = $("#adminCandidateId").val();
                    	}
                    	$.ajax({
    				        type: "post",
    					    dataType: "json",
    				        url: 'getCandidateInfo',
    				        data: {"candidateId":adminCandidateId},
    				        success: function(data) {
    					        var candidateProfileSource = $("#candidateDetailProfileTable").html(),
    			                	candidateProfiletemplate = handlebars.compile(candidateProfileSource),
    			                	candidateInfoObject = null,
    			                	candidateProfileHtml = null;

    		                	if (data.models != null) {

    		                		candidateInfoObject = data.models.candidateInfo;
    		                	}

    		                	candidateProfileHtml = candidateProfiletemplate(candidateInfoObject);
    					        $("#candidateDetailInfoForm").append(candidateProfileHtml);

    					        //Notice: loading order
    					        if (data.models != null) {
    						        if (data.models.candidateInfo.semesterRegistered == 'fall') {
    		                			$('#fall').parents(".radioCustom").addClass("radioCustom-checked");
    		                			$('#fall').attr("checked","checked");
    		                		}else if (data.models.candidateInfo.semesterRegistered == 'winter'){
    		                			$('#winter').parents(".radioCustom").addClass("radioCustom-checked");
    		                			$('#winter').attr("checked","checked");
    		                		}
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
                
            };
            function registerEventListener() {
            	candidateDetail.Controller.loadCandidateProfile();
            	$("#cancelCandidate").click(function(){
            		location.href="user-center";
            	});
            	$("#candidateDetailProfile_submit").click(function () {
            		if (formValidator.getCandidateValidator("#candidateForm").form() == true){
            			$("#candidateDetailModelTrigger").click();
            		}
                });

                $("#confirmedCandidateDetailSubmit").click(function () {
                		candidateDetail.Controller.handleConfirmedSubmit();
                });
            };
            $(function() {
                registerEventListener();
            });
    });
});