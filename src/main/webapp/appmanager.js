'use strict';

// Declare app level module which depends on views, and components
angular.module('managerApp', [
  'ngRoute',
  'managerApp.manager',
  'ng-fusioncharts',
  'ngAnimate',
  'ngMaterial',
  'angularFileUpload'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/manager'});
}]);

angular.module('datepickerBasicUsage',
	    ['ngMaterial', 'ngMessages']).controller('AppCtrl', function($scope) {
	  $scope.myDate = new Date();

	  $scope.minDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth() - 2,
	      $scope.myDate.getDate());

	  $scope.maxDate = new Date(
	      $scope.myDate.getFullYear(),
	      $scope.myDate.getMonth() + 2,
	      $scope.myDate.getDate());

	  $scope.onlyWeekendsPredicate = function(date) {
	    var day = date.getDay();
	    return day === 0 || day === 6;
	  };
	});
