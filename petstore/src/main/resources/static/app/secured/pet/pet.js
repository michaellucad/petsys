var petApp = angular.module('petApp', [ 'ui.router' ]);

petApp.controller('PetController', [ '$scope', '$http', '$state',
		'SystemService', function($scope, $http, $state, SystemService) {
			$scope.init = function() {
				if (!SystemService.checkLogin($scope, $state)) {
					return;
				}
				var data = {};
				var callback = function(response) {
					$scope.appData.pets = response.data.pets;
				};
				var req = {
					url : '/pet/searchPet',
					data : data,
					http : $http,
					state : $state,
					callback : callback
				};
				$scope.appData.menu = {
					pet : 'active'
				};
				SystemService.httpPost(req);
			}
			$scope.searchByPetId = function() {
				var data = {
						id : $scope.searchId
					};
					var callback = function(response) {
						$scope.appData.pets = response.data.pets;
					};
					var req = {
						url : '/pet/searchPet',
						data : data,
						http : $http,
						state : $state,
						callback : callback
					};
					SystemService.httpPost(req);
			}
		} ]);
petApp.controller('PetDetailsController', [ '$scope', '$http', '$state',
		'$stateParams', 'SystemService',
		function($scope, $http, $state, $stateParams, SystemService) {
			$scope.init = function() {
				var pet = $scope.selectPetById($stateParams.id);
				$scope.selectedPet = pet;
			}
			$scope.selectPetById = function(id) {
				var pets = $scope.appData.pets;
				$scope.appData.selectedPetId = id;
				for (i in pets) {
					var pet = pets[i];
					if (pet.id == id) {
						return pet;
					}
				}
			};
			$scope.deletePet = function() {
				var selectPetId = $scope.appData.selectedPetId;
				var data = {
					pet:{id : selectPetId}
				};
				var callback = function(response) {
					var data = {
							id : $scope.searchId
						};
						var callback = function(response) {
							$scope.appData.pets = response.data.pets;
						};
						var req = {
							url : '/pet/searchPet',
							data : data,
							http : $http,
							state : $state,
							callback : callback
						};
						SystemService.httpPost(req);
					$state.go("secured.pet");
				};
				var req = {
					url : '/pet/delete',
					data : data,
					http : $http,
					state : $state,
					callback : callback
				};
				SystemService.httpPost(req);
				$state.go("secured.pet");
			};
		} ]);

petApp.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$stateProvider
			// Pet =================================
			.state('secured.pet.details', {
				url : '/details/:id',
				views : {
					'details' : {
						templateUrl : 'secured/pet/details.html',
						controller : 'PetDetailsController'
					}
				}
			});

		} ]);
