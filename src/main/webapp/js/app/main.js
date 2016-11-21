/**
 * Created by William Zhang 18/01/16
 */
requirejs.config({
    baseUrl : 'js',
    paths: {
        "jquery" : "lib/jquery-1.11.1.min",
        "bootstrap" : "lib/bootstrap-3.0.3.min",
        "handlebars": "lib/handlebars-v2.0.0",
        "pagination": "lib/jquery-pagination",
        "validate" : "lib/jquery-validate-1.13.1",
        "methods": "lib/additional-methods-1.13.1",
        "message_zh": "lib/message_zh.1.13.1",
        "jqueryForm": "lib/jquery-form",
        "jms" : "app/app",
        "ajaxHandler" : "app/modules/ajax-handler",
        "formValidator": "app/modules/form-validator",
        "additionalMethods": "app/modules/additional-methods",
        "login": "app/modules/login",
        "eventHandler": "app/modules/event-handler",
        "userManagement": "app/modules/user-management",
        "userApplicationForm": "app/modules/user-application-form",
        "candidateProfile": "app/modules/candidate-profile",
        "custom": "app/modules/custom",
        "slider": "app/modules/slider",
        "html5shiv": "lib/html5shiv.min",
        "jcarouselResponsive": "lib/jcarousel.responsive",
        "jqueryJcarousel": "lib/jquery.jcarousel.min",
        "npm": "lib/npm",
        "owlCarousel": "lib/owl.carousel",
        "respond": "lib/respond.min",
        "ie10bug": "ie10-viewport-bug-workaround.js",
        "position": "app/modules/position",
        "employer": "app/modules/employer",
        "jDataTables": "app/modules/jquery.dataTables.min"
    },
    shim: {
        'bootstrap' : {
            deps : ['jquery'],
            exports : "bootstrap"
        },
        'handlebars' : {
            deps : ['jquery'],
            exports : "handlebars"
        },
        "pagination": {
            deps :['jquery'],
            exports : "pagination"
        },
        "additionalMethods": {
            deps :['jquery'],
            exports : "additionalMethods"
        },
        "validate": {
            deps : ['jquery'],
            exports : "validate"
        },
        "methods": {
            deps : ['jquery'],
            exports : "methods"
        },
        "ajaxHandler": {
            deps : ['jquery'],
            exports : "ajaxHandler"
        },
        "applicants": {
            deps : ['jquery'],
            exports : "questionnaire"
        },
        "jqueryForm": {
        	deps :['jquery'],
            exports : "jqueryForm"
        },
        "formValidator": {
            deps :['jquery', 'validate'],
            exports : "formValidator"
        },
        "login": {
            deps :['jquery'],
            exports : "login"
        },
        "eventHandler": {
            deps :['jquery'],
            exports : "eventHandler"
        },
        "userManagement": {
            deps :['jquery'],
            exports : "userManagement"
        },
        "userApplicationForm": {
            deps :['jquery'],
            exports : "userApplicationForm"
        },
        "candidateProfile": {
            deps :['jquery'],
            exports : "candidateProfile"
        },
        "application": {
            deps :['jquery'],
            exports : "application"
        },
        "custom": {
            deps :['jquery'],
            exports : "custom"
        },
        "slider": {
            deps :['jquery'],
            exports : "slider"
        },
        "html5shiv": {
            deps :['jquery'],
            exports : "html5shiv"
        },
        "jcarouselResponsive": {
            deps :['jquery'],
            exports : "jcarouselResponsive"
        },
        "jqueryJcarousel": {
            deps :['jquery'],
            exports : "jqueryJcarousel"
        },
        "npm": {
            deps :['jquery'],
            exports : "npm"
        },
        "owlCarousel": {
            deps :['jquery'],
            exports : "owlCarousel"
        },
        "respond": {
            deps :['jquery'],
            exports : "respond"
        },
        "position": {
        	deps :['jquery'],
        	exports : "position"
        },
        "employer": {
        	deps :['jquery'],
        	exports : "employer"
        },
        "ie10bug": {
        	deps :['jquery'],
        	exports : "ie10bug"
        },
        "jDataTables": {
        	deps :['jquery'],
        	exports : "jDataTables"
        }

    }

});
