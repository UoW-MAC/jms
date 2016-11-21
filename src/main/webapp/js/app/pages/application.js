/**
 * Created by William Zhang 18/02/16
 */
            
require(['../main'], function () {
    require(['jquery', 'bootstrap', 'validate', 'ajaxHandler', 'jqueryForm', 'jms'],        
            
        function($, bootstrap,  validate, ajaxHandler, jqueryForm, jms) {

            "use strict";

            var application = {};
            
            application.View = {
            		getPositionGroupView : function(options) {
        				var employerGroupSelect = $('#inputAdminDetailPositionGroupSelect');
        				$('#inputAdminDetailPositionGroupSelect' + ' option').remove();
        				for (var i = 0; i < options.length; i++) {
        					var optionText = options[i].positionGroupName;
        					var optionValue = options[i].positionGroupId;
        					var option = "<option value=" + optionValue + ">"
        							+ optionText + "</option>";
        					$('#inputAdminDetailPositionGroupSelect').append(option);
        				}
        			}
            };

            application.Controller = {
            	getPositionGroup : function() {
            		$.ajax({
     					type : "get",
     					dataType : "json",
     					url : 'getPositionGroup',
     					success : function(data) {
     						application.View.getPositionGroupView(data);
        					$("#inputAdminDetailPositionGroupSelect option[value=" + $('#inputAdminDetailPositionGroupId').val() + "]").attr("selected", true);
        				}
        			});
        		},
                positionApply : function(){
                	$.ajax({
				        type: "post",
					    dataType: "json",
				        url: 'positionApply',
				        data : "positionId=" + $("#positionId").attr("value"),
				        success: function(data) {
				        	//application.Controller.loadPostionStatusList();
				        	//need load asynchronous
				        	location.href = "user-center?menu=position";
				        }
				    });
                },
                handleConfirmedSubmit: function(){
                    $("#editForm").ajaxSubmit(function(response) {
                        if (response.statusCode == 1000){
                        	$("#return").click();
                        }else{
                            return;
                        }
                    });
                }
            };

            function registerEventListener() {
            	 application.Controller.getPositionGroup();
                     	
                $("#confirmApply").click(function(){
                	application.Controller.positionApply();
                });
                
                $("#positionApply").click(function(){
                	$("#myModalTrigger").click();
                });
                
                $("#cancelApply").click(function(){
                	location.href = "user-center?menu=position";
                });
                
                $("#confirmEdit").click(function(){
                	application.Controller.handleConfirmedSubmit();
                });
                
                $("#editAdminDetailPosition").click(function(){
                	$("#myModalTrigger2").click();
                });
                
                $("#cancleAdminDetailPosition").click(function(){
                	location.href = "user-center?menu=adminPosition";
                });
            }
            
            $(function() {
                registerEventListener();
            });

    jms.application = application;
    return application;

    });
});
