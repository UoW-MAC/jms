require(['../main'], function () {

    require(['jquery', 'bootstrap', 'handlebars', 'validate', 'ajaxHandler',
            'formValidator', 'additionalMethods', 'jqueryForm',
            'eventHandler', 'candidateProfile', 'position', 'jms', 'employer','jDataTables'],
            
        function($, bootstrap, handlebars, validate, ajaxHandler,
                 formValidator, additionalMethods, jqueryForm,
                 eventHandler, candidateProfile, position, jms, employer, jDataTables) {

            "use strict";

            var userPersonalCenter = {};
            userPersonalCenter.Model = {};
            userPersonalCenter.View = {
            };

            userPersonalCenter.init = function(){
            	if ($("#menuSwitch").val() == "position") {
					$("#positionApplicationMenu").addClass("active");
                	$("#candidateProfileMenu").removeClass("active");
                	$("#positionApplicationTabPane").addClass("active");
                	$("#candidateProfileTabPane").removeClass("active");
            	}else if($("#menuSwitch").val() == "adminPosition"){
            		$("#positionManagementMenu").addClass("active");
            		$("#positionProfileMenu").addClass("active");
                	$("#jmsManagementMenu").removeClass("active");
                	$("#applicationManagementMenu").removeClass("active");
                	$("#employerManagementMenu").removeClass("active");
                	$("#employerProfileMenu").removeClass("active");
                	$("#positionManagementTabPane").addClass("active");
                	$("#positionProfileTabPane").addClass("active");
                	$("#candidateManagementTabPane").removeClass("active");
                	$("#applicationManagementTabPane").removeClass("active");
                	$("#employerManagementTabPane").removeClass("active");
                	$("#employerProfileTabPane").removeClass("active");
            	}else if($("#menuSwitch").val() == "adminEmployer"){
            		$("#employerManagementMenu").addClass("active");
            		$("#positionManagementMenu").removeClass("active");
                	$("#candidateManagementMenu").removeClass("active");
                	$("#applicationManagementMenu").removeClass("active");
                	$("#employerManagementTabPane").addClass("active");
                	$("#candidateManagementTabPane").removeClass("active");
                	$("#positionManagementTabPane").removeClass("active");
                	$("#applicationManagementTabPane").removeClass("active");
            	}else if($("#menuSwitch").val() == "employerCandidate"){
            		$("#positionProfileMenu").addClass("active");
            		$("#employerProfileMenu").removeClass("active");
                	$("#positionProfileTabPane").addClass("active");
                	$("#employerProfileTabPane").removeClass("active");
            	}
            }

            $(function() {
                eventHandler.registerEventListener();
                userPersonalCenter.init();
            })

            jms.userPersonalCenter = userPersonalCenter;
            return userPersonalCenter;
        });
});
