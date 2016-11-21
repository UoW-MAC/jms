/**
 * Created by William Zhang on 16/3/16.
 */

require(['../main'], function () {
    require(['jquery', 'bootstrap', 'formValidator', 'jqueryForm', 'ajaxHandler'],
	    function($, bootstrap, formValidator, jqueryForm, ajaxHandler) {
	
	        "use strict";
	
	        var login = {};
	        login.View = {};
	
	        login.Controller = {
	        
		        handleLoginFormSubmit: function () {
		        	if (formValidator.getLoginValidator("#singleLoginForm").form() == true){
		                ajaxHandler.sendRequest({
	                        type: "POST",
	                        url: "j_spring_security_check",
	                        dataType: "json",
	                        data: {
	                            j_password: $("#j_password").val(),
	                            j_username: $("#j_username").val()
	                            //_spring_security_remember_me: $("#_spring_security_remember_me").prop("checked")
	
	                        },
	                        success: function (response) {
	                            if (response.statusCode < 2000) {
	                                window.location.href = response.models.redirect;
	                            } else {
	                                return;
	                            }
	                        },
	                        fail: function (xhr, status, error) {
	                        	alert(error);
	                            return;
	                        }
                       });
		        	}
	       	    }
	        
	        }
	        
	        function loginEventListener() {
	            $("#singleLoginFormSubmit").click(function () {
	            	login.Controller.handleLoginFormSubmit();
	            });
	        }
	        
	        
	        $(function() {
	            loginEventListener();
	        });
	
	        jms.Login = login;
	        return login;
	
		});
});
