var secured = angular.module('secured', [ 'ui.router', 'petApp','addPetApp' ]);



secured.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$stateProvider
			// Pet =================================
			.state('secured.pet', {
				url : '/pet',
				views : {
					'main' : {
						templateUrl : 'secured/pet/pet.html',
						controller : 'PetController'
					}
				}
			}).state('secured.addPet', {
				url : '/addPet',
				views : {
					'main' : {
						templateUrl : 'secured/addPet/pet.html',
						controller : 'AddPetController'
					}
				}
			});

		} ]);
