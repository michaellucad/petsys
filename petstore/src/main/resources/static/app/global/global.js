var gApp = angular.module('gApp', [ 'ui.router' ]);

gApp.controller('HeadController', [ '$scope', '$state','$location',
		function($scope, $state, $location) {
			$scope.isActive = function(viewLocation) {
				return ($location.path().indexOf(viewLocation)>-1);
			}
		} 
]);