'use strict';

var commonDirectives = angular.module('common-directives');

commonDirectives.directive('loginPageDirective',function() {
	return {
		restrict : 'EA',
		templateUrl : "resources/scripts/internal/angularjs/common/directives/templates/login-page-template.html",
		link : function(scope, element, attrs) {
		},
		scope : {},
		controller : function($scope, $timeout,$rootScope,$http) {
			
			$scope.onClickValidate=false;
			$scope.loginForm=false;
			$scope.username="user";
			$scope.password="user";
			
			//Login Submit function
			$scope.loginFunction=function(bol){
				$scope.isError=false;
				$scope.onClickValidate=!bol;
				if(!bol){
					return;
				}
				var data ="j_username=" + $scope.username.toLowerCase() +"&j_password=" + $scope.password +"&_spring_security_remember_me=false&submit=Login";
				$http.post('app/authentication', data, {
                 // Set Header
                  headers: {
                     // Set Content-Type in header
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    // Set ignoreAuthModule
                    ignoreAuthModule: 'ignoreAuthModule'
                })
                // Success Function
                .success(function (data, status, headers, config) {
                	window.location.href="user";
                })
                //Error Function 
                .error(function (data, status, headers, config) {
                	$scope.data="Please use user as username and password for testing.";
                	$scope.isError=true;
                	$timeout(function(){
                		$scope.isError=false;
                	},5000);
                });
			};
		}
	};
});
commonDirectives.directive('navBarDirective',function() {
	return {
		restrict : 'EA',
		templateUrl : "resources/scripts/internal/angularjs/common/directives/templates/nav-bar-template.html",
		link : function(scope, element, attrs) {
		},
		scope : {},
		controller : function($scope, $timeout,$rootScope,$http,AuthenticationSharedService) {
			//Method to logout
			$scope.logoutFunction=function(){
				AuthenticationSharedService.logout();
			};
		}
	};
});



