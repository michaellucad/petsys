var loginApp = angular.module('loginApp', [ 'ui.router' ]);

loginApp
		.controller(
				'LoginController',
				[
						'$scope',
						'$http',
						'$state',
						'flash',
						'SystemService',
						function($scope, $http, $state, flash, SystemService) {
							$scope.init = function() {
								$scope.appData.loginForm = {};
							}
							$scope.login = function() {
								var data = {
									loginId : $scope.appData.loginForm.loginId,
									password : $scope.appData.loginForm.password
								};
								var callback = function(response) {
									$scope.appData.res = response;
									if (!response.redirected
											&& (response.data.errors == null || response.data.errors.length == 0)) {
										$scope.appData.loginForm.password = '';
										$scope.appData.loginForm.logined = true;
										$scope.appData.roles = response.data.roles;
										$state.go('secured.pet');
									} else {
										flash.setMessage("invalid td/pass");
										$scope.init();
									}
								};
								var req = {
									url : '/login/login',
									data : data,
									http : $http,
									state : $state,
									callback : callback
								};
								SystemService.httpPost(req);
							}
						} ]);