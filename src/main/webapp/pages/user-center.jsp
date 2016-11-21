<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="images/favicon.png">
    <!--
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    -->
    <title>Job Management System</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/lib/html5shiv.min.js"></script>
    <script src="js/lib/respond.min.js"></script>
    <![endif]-->


    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css" rel="stylesheet" type="text/css">
    <link href="css/validation.css" rel="stylesheet" type="text/css">
    <!--[if IE 8]>
    <link href="css/ie8.css" rel="stylesheet" type="text/css">
    <![endif]-->
</head>

<body>
<jsp:include page='header.jsp'/>

<!-- PAGE TITLE -->
<div class="page-title withOutTitle">
</div>
<!-- /PAGE TITLE -->

<!-- CONTENT SECTION -->
<div class="containerDiv">
    <section class="contentSection">
        <div class="container">
            <div class="row">
                <span class="col-sm-2 sidebar equalCol">
                    <h3>Menu</h3>
                    <ul class="nav nav-tabs-justified" role="tablist">
                        <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                            <li id="candidateManagementMenu" class="active"><a id="candidateManagementTab" href="#candidateManagementTabPane"  role="tab" data-toggle="tab">candidate Management<span></span></a></li>
                            <li id="employerManagementMenu"><a id="employerManagementTab" href="#employerManagementTabPane"  role="tab" data-toggle="tab">Employer Management<span></span></a></li>
                            <li id="positionManagementMenu"><a id="positionManagementTab" href="#positionManagementTabPane"  role="tab" data-toggle="tab">Position Management<span></span></a></li>
                            <li id="applicationManagementMenu"><a id="applicationManagementTab" href="#applicationManagementTabPane"  role="tab" data-toggle="tab">Application Management<span></span></a></li>
                        </sec:authorize>
                        
                        <sec:authorize access="hasRole('ROLE_ORGANIZATION') and isAuthenticated()">
                            <li id="employerProfileMenu" class="active"><a id="employerProfileTab" href="#employerProfileTabPane" role="tab" data-toggle="tab">Employer Information<span></span></a></li>
                            <li id="positionProfileMenu"><a id="positionProfileTab" href="#positionProfileTabPane" role="tab" data-toggle="tab">Position Information<span></span></a></li>
                        </sec:authorize>
                        
                        <sec:authorize access="hasRole('ROLE_CANDIDATE') and isAuthenticated()">
                            <li id="positionApplicationMenu"><a id="positionApplicationTab" href="#positionApplicationTabPane" role="tab" data-toggle="tab">Position Application<span></span></a></li>
                            <li class="active" id="candidateProfileMenu"><a id="candidateProfileTab" href="#candidateProfileTabPane" role="tab" data-toggle="tab">Profile<span></span></a></li>
                        </sec:authorize>
                    </ul>
                </span>

                <span class="col-sm-10 leftBorder equalCol umList">
                    <div class="tab-content">
                        <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                            <div id="candidateManagementTabPane" role="tabpane" class="tab-pane active">
                                <jsp:include page='admin-candidate.jsp'/>
                            </div>
                               <div id="employerManagementTabPane" role="tabpane" class="tab-pane">
                                <jsp:include page='admin-employer.jsp'/>
                            </div> 
                               <div id="positionManagementTabPane" role="tabpane" class="tab-pane">
                                <jsp:include page='admin-position.jsp'/>
                            </div>
                             <div id="applicationManagementTabPane" role="tabpane" class="tab-pane">
                                <jsp:include page='admin-application.jsp'/>
                            </div>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_CANDIDATE') and isAuthenticated()">
                            <div id="candidateProfileTabPane" role="tabpane" class="tab-pane active">
                                <jsp:include page='candidate.jsp'/>
                            </div>
                            <div id="positionApplicationTabPane" role="tabpane" class="tab-pane">
                                <jsp:include page='application.jsp'/>
                            </div>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ORGANIZATION') and isAuthenticated()">
                            <div id="employerProfileTabPane" role="tabpane" class="tab-pane active">
                                <jsp:include page='employer.jsp'/>
                            </div>
                            <div id="positionProfileTabPane" role="tabpane" class="tab-pane">
                                <jsp:include page='position.jsp'/>
                            </div>
                        </sec:authorize>
                    </div>
                </span>
            </div>
        </div>
    </section>
</div>
<!-- /CONTENT SECTION -->
<input type="hidden" id="menuSwitch" value="${menu}" />  

<jsp:include page='footer.jsp'/>

<script data-main="js/app/pages/user-center" src="js/lib/require-2.1.15.min.js"></script>
</body>
</html>
