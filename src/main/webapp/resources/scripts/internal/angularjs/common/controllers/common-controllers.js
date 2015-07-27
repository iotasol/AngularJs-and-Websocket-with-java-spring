'use strict';

var commonControllers = angular.module('common-controllers');

// Product page controller
commonControllers.controller('ProductController', function($scope, $location,
		$timeout, $window, ProductService, ChatService) {

	// Init Method
	function init() {

		$scope.messages = [];
		$scope.message = "";
		$scope.max = 140;

		$scope.addMessage = function() {
			ChatService.send($scope.message);
			$scope.message = "";
		};

		ChatService.receive().then(null, null, function(message) {
			$scope.messages.push(message);
		});
	}
	// Call init Method
	init();

});

// About page controller
commonControllers.controller('AboutController', function($scope, $location,
		$timeout, $window) {

});
