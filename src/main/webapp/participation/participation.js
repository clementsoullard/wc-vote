'use strict';

angular.module('myApp.participation', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/participation', {
    templateUrl: 'participation/participation.html',
    controller: 'participationCtrl'
  });
}])

.controller('participationCtrl',  ['$scope','$http', function($scope,$http) {

 
/**
 * Insert a new entry fonction
 */
		
 $scope.update = function (user) {
    $http.post('/event-tracker/ws-participation',user).
        success(function(data) {
     	  	$scope.message='Thanks for applying. You have been properly registred. You can also register husband/wife and children after closing this window.';
       	  	$scope.error=false;
            list();
        }).
		error(function(data) {
     	  	$scope.message='An issue occured';
       	  	$scope.error=true;
		})
		};
			
/**
 * List the entries
 */		
	 function list(){
		 $http.get('/event-tracker/ws-participation').
	      success(function(data) {
	        	console.log(JSON.stringify(data._embedded));
	            $scope.participations = data._embedded.participation;
	        });
		 }
	/**
	* List the entries
	*/		
	$scope.remove = function(id){ $http.delete('/event-tracker/ws-participation/'+id).
			success(function(data) {
		  	$scope.message='The entry has been removed.';
			list();
		});
	}
		
		list();		 
}]);
