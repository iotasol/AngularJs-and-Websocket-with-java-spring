angular.module('common-directives', ['common-services']);
angular.module('common-controllers', ['ngResource','common-services']);
angular.module('common-services', []);
angular.module('common-filters', []);
angular.module('common-app', ['ngResource',
                           'ngRoute',
                           'common-controllers',
                           'common-services',
                           'common-directives',
                           'common-filters',
                           'angularUtils.directives.dirPagination'
                                  ])
                              .config(['$routeProvider','$locationProvider', '$httpProvider', function($routeProvider,$locationProvider, $httpProvider) {
                              	 $routeProvider.
                              	  	when('/', {templateUrl: 'resources/scripts/internal/angularjs/common/partials/index.html',   controller: 'ProductController'}).
                              	    when('/about', {templateUrl: 'resources/scripts/internal/angularjs/common/partials/about.html',   controller: 'AboutController'}).
                              	    otherwise({redirectTo: '/'});
                              }])
                              .run(['$rootScope', '$location','AuthenticationSharedService','Account',
                                    function($rootScope, $location,AuthenticationSharedService,Account) {
                            	  
                            	    //Get Current Login User
	                    	  		Account.get({},function(data){
	                    	  			$rootScope.user=data;
	                    	  			$rootScope.authenticated = true;
	                    	  		});
                              }]);

