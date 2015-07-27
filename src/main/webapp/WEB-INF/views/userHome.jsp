<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE>

<html>
<head>
<title>User Cash Baord</title>
<%@ include file="common-css.jsp"%>


</head>
<body data-ng-app="common-app">
	<div class="container-fluid no-padding">
		<div class="container-fluid no-padding">
			<div data-nav-bar-directive=""></div>
		</div>
		<div class="clearfix"></div>
		<div class="container-fluid no-padding login-form-container">
			<div data-ng-view=""></div>
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
