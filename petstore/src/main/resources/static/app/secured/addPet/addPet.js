var addPetApp = angular.module('addPetApp', [ 'ui.router' ]);

addPetApp.controller('AddPetController', [ '$scope', '$http', '$state','flash',
		'SystemService', function($scope, $http, $state, flash, SystemService) {
			$scope.init = function() {
			}
			$scope.addPet = function() {
				var data = {
					pet : {
						name : $scope.name,
						tags : $scope.tags,
						category : $scope.category,
						photoUrls : $scope.photoUrls,
						status : $scope.status
					}
				};
				var callback = function(response) {
					$scope.appData.res = response;
					flash.setMessage('Pet added');
					$state.go('secured.addPet');
				};
				var req = {
					url : '/pet/add',
					data : data,
					http : $http,
					state : $state,
					callback : callback
				};
				SystemService.httpPost(req);
				$state.go('secured.addPet');
			}
		} ]);