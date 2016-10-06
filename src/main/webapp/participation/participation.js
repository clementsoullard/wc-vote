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
 * Insert a new entry function
 */
		
 $scope.update = function (user) {
	 /** This is to identify the event it is related to */
	 
	 user.event='infyplay2016';
	 
	 /** We use the tracer to identify if a registration has been done on the same computer */
	 var x = document.cookie;
	 
	 if(!x){
		 var d = new Date();
	 	d.setTime(d.getTime() + (30*24*60*60*1000));
	 	var expires = "expires="+ d.toUTCString();
	 	var rndNumber=Math.floor((Math.random() * 1000000) + 1);
	 	document.cookie =  "tracer="+rndNumber+" ; " + expires;
	 	x=document.cookie;
	 }
	 else{
		 console.log('There is one cookie in the session ' + x.substring(7));
	 }
	 /** This is to identify the event it is related to */

	 user.tracer=x.substring(7);
	 
	 
	 
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
		 $http.get('/event-tracker/ws-participation?size=100').
	      success(function(data) {
	        //	console.log(JSON.stringify(data._embedded));
	            $scope.participations = data._embedded.participation;
	            $scope.nbTotal = data.page.totalElements;
	        });
		 
		 $http.get('/event-tracker/ws-participation-stats').
	      success(function(data) {
	        //	console.log(JSON.stringify(data._embedded));
	            $scope.participationStats = data;
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
