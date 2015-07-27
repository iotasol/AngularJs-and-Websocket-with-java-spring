'use strict';

var commonServices = angular.module('common-services');

commonServices.factory('AuthenticationSharedService', [
		'$rootScope',
		'$http',
		function($rootScope, $http) {
			return {
				// GET:- Check authenticate Method
				authenticate : function() {
					// get Call to check authenticate
					$http.get('api/user/authenticate')
					// Success function
					.success(function(data, status, headers, config) {
						$rootScope.data = data;
						// if Data is Empty
						if (data.username == '') {
							// call when authenticate Need
							$rootScope.$broadcast('event:auth-loginRequired');
						} else {
							// call when authenticate is Not Need
							$rootScope.$broadcast('event:auth-authConfirmed');
						}
					});
				},
				logout : function() {
					$http.get('app/logout').success(
							function(data, status, headers, config) {
								window.location.href = "/";
							});
				}
			};
		} ]);
commonServices.factory('Account', function($resource) {
	return $resource('api/user/account', {}, {
		'get' : {
			method : 'GET',
			params : {},
			isArray : false
		},
	});
});

/* Product Service */
commonServices.factory('ProductService', function($resource) {
	return $resource('api/product/:id', {
		id : '@id'
	}, {
		'fetchAll' : {
			method : 'GET',
			params : {},
			isArray : true
		},
		'fetch' : {
			method : 'GET',
			params : {},
			isArray : false
		},
		'save' : {
			method : 'POST',
			params : {},
			isArray : false
		},
		'update' : {
			method : 'PUT',
			params : {},
			isArray : false
		},
		'remove' : {
			method : 'DELETE',
			params : {},
			isArray : false
		},
	});
});

commonServices.service("ChatService", function($q, $timeout) {

	var service = {};
	var listener = $q.defer();
	var socket = {
		client : null,
		stomp : null
	};

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/hello";
	service.CHAT_TOPIC = "/topic/greetings";
	service.CHAT_BROKER = "/app/hello";

	service.receive = function() {
		return listener.promise;
	};

	service.send = function(message) {
		var id = Math.floor(Math.random() * 1000000);
		socket.stomp.send(service.CHAT_BROKER, {
			priority : 9
		}, JSON.stringify({
			message : message,
			id : id
		}));
	};

	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getMessage = function(data) {
		var message = JSON.parse(data), out = {};
		out.message = message.message;
		out.date = new Date();
		return out;
	};

	var startListener = function() {
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
			listener.notify(getMessage(data.body));
		});
	};

	var initialize = function() {
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	initialize();
	return service;
});