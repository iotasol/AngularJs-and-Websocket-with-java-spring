<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE>

<html>
<head>
<title>Welcome Page</title>
<%@ include file="common-css.jsp"%>
</head>
<body data-ng-app="common-app">
	<div class="container-fluid no-padding">
		<div class="container-fluid no-padding">
			<nav class="navbar navbar-default no-margin-bottom">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<h4 class="app_name">
							<b>Demo Application</b>
						</h4>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
						</ul>
					</div>
				</div>
			</nav>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
		<div class="container-fluid no-padding login-form-container">
			<div data-login-page-directive=""></div>
		</div>
		<div class="clearfix"></div>
		<div class="container-fluid no-padding fotter-container">
			<div class="col-md-12 no-padding">
				<div class="col-md-5 col-md-offset-1">
					<i class="fa fa-copyright"></i> 2014-2015
				</div>
				<div class="col-md-5 text-right">
					Manage By <a href="">Iotasol Pvt Ltd</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="common-script.jsp"%>
</body>
</html>
