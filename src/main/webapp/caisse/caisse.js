'use strict';

angular.module('managerApp.caisse', ['ngRoute','angularFileUpload'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/caisse', {
    templateUrl: 'caisse/caisse.html',
    controller: 'caisseMgrCtrl'
  })
   ;
}])

.controller('caisseMgrCtrl',  ['$scope','$http', function($scope,$http) {

	/**
	 * Insert a new entry function
	 */
			
	 $scope.insert = function (line) {

		 $http.post('ws/line-caisse/',line).
	        	success(function(data) {
	     	  	$scope.message='Thanks for registering.';
	     	  	$scope.line={};
	         	$scope.error=false;
	            list();
	        }).
			error(function(data) {
	     	  	$scope.message='An issue occured.';
	     	  	$scope.participant={};
	     	  	$scope.error=true;
			})
		};
		/**
		 * List the entries
		 */		
		function list(){
				 $http.get('ws/line-caisse/').
			      success(function(data) {
			            $scope.linesCaisse = data.line;
			      });				 
			}


}])
