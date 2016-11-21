/**
 * Created by Freya He 29/01/16
 */
require(['../main'], function () {
    require(['jquery', 'bootstrap', 'ajaxHandler', 'jqueryForm'],
        function($, bootstrap, ajaxHandler, jqueryForm) {

            "use strict";

            var employerDetail = {};
            
            employerDetail.View = {
                getEmployerGroupView : function(options) {
    				var employerGroupSelect = $('#inputemployerGroupSelect');
    				$('#inputemployerGroupSelect' + ' option').remove();
    				for (var i = 0; i < options.length; i++) {
    					var optionText = options[i].employerGroupType;
    					var optionValue = options[i].employerGroupId;
    					var option = "<option value=" + optionValue + ">"
    							+ optionText + "</option>";
    					$('#inputemployerGroupSelect').append(option);
    				}
    			}
    		 };
            employerDetail.Controller = {
            		getEmployerGroup : function() {
        				$.ajax({
        					type : "get",
        					dataType : "json",
        					url : 'getEmployerGroup',
        					success : function(data) {
        						employerDetail.View.getEmployerGroupView(data);
        						$("#inputemployerGroupSelect option[value=" + $('#inputemployerGroupId').val() + "]").attr("selected", true);
        					}
        				});
        			},
                
                handleAdminEmployerConfirmedSubmit: function(){
                    $("#editEmployerForm").ajaxSubmit(function(response) {
                        if (response.statusCode == 1000){
                        	$("#return").click();
                        }else{
                            return;
                        }
                    });
                }
               
            };
            function registerEventListener() {
                employerDetail.Controller.getEmployerGroup();
                $("#editEmployer").click(function(){
                	$("#myModalTrigger6").click();
                });
                $("#confirmEmployerEdit").click(function(){
                	employerDetail.Controller.handleAdminEmployerConfirmedSubmit();
                });
            }
            $("#cancelEmployer").click(function(){
            	location.href = "user-center?menu=adminEmployer";
            });
            $(function() {
                registerEventListener();
            });
    });
});