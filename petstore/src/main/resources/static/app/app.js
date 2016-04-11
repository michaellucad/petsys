var testWeb = angular.module('testWeb', [ 'ui.router', 'ui.utils', 'loginApp',
		'secured', 'gApp' ]);

testWeb.service('SystemService', function() {
	this.handlerHttpError = function($scope, error, status) {
		// TODO
	}
	this.checkLogin = function($scope, $state) {
		if ($scope.appData == undefined
				|| $scope.appData.loginForm == undefined
				|| $scope.appData.loginForm.loginId == undefined) {
			$state.go('login');
			return false;
		}
		return true;
	}
	this.httpPost = function(req) {
		var url = req.url;
		var data = req.data;
		var http = req.http;
		var state = req.state;
		var callback = req.callback;

		http.post(url, data).success(function(data, status, headers, config) {
			var response = {
				data : data,
				status : status,
				headers : headers,
				config : config,
				redirected : false
			};
			if (data.errors) {
				for (i in data.errors) {
					var error = data.errors[i];
					// alert('1'+error.code);
					if (error.code == 'notLogin') {
						response.redirected = true;
						state.go('login');
					}
				}
			}
			callback(response);
		}).error(function(data, status, headers, config) {
			var response = {
				data : data,
				status : status,
				headers : headers,
				config : config,
				redirected : false
			};
			if (status == 401) {
				response.redirected = true;
				state.go('login');
			}
			callback(response);
		});
	}
});

testWeb.controller('MessageController', [ '$scope', '$http', '$state', 'flash',
		'SystemService', function($scope, $http, $state, flash, SystemService) {
	$scope.getMessage=flash.getMessage;
		} ]);

testWeb.run([ '$rootScope', function($rootScope) {
	$rootScope.appData = {};
} ]);
testWeb.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('/');
			$stateProvider
			// Login ========================================
			.state('login', {
				url : '/',
				views : {
					'top' : {
						templateUrl : 'login/login.html',
						controller : 'LoginController'
					},
					'header' : {
						templateUrl : 'global/header-public.html'
					},
					'messages' : {
						templateUrl : 'global/message.html',
						controller : "MessageController"
					},
					'footer' : {
						templateUrl : 'global/footer-public.html'
					}
				}

			})
			// secured =================================
			.state('secured', {
				abstract : true,
				url : '/secured',
				views : {
					'menus' : {
						templateUrl : 'global/menu.html',
						controller : 'PetController'
					},
					'top' : {
						templateUrl : 'secured/secured.html',
						controller : 'PetController'
					},
					'header' : {
						templateUrl : 'global/header-public.html',
						controller : 'HeadController'
					},
					'messages' : {
						templateUrl : 'global/message.html',
						controller : "MessageController"
					},
					'footer' : {
						templateUrl : 'global/footer-public.html'
					}

				}
			})
		} ]);

testWeb.factory("flash", function($rootScope) {
	var currentMessage = "";
	$rootScope.$on("$stateChangeSuccess", function() {
		currentMessage = "";
	});
	return {
		setMessage : function(message) {
			currentMessage = message;
		},
		getMessage : function() {
			return currentMessage;
		},
		hasMessage : function() {
			return currentMessage != "";
		}
	}
});
