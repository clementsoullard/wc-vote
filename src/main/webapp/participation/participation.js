'use strict';

angular.module('myApp.participation', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/participation', {
    templateUrl: 'participation/participation.html',
    controller: 'participationCtrl'
  }).when('/register/:idEvent', {
	    templateUrl: 'participation/register.html',
	    controller: 'registerCtrl'
	  });
}])

.controller('participationCtrl',  ['$scope','$http','$location', function($scope,$http,$location) {

 
		/**
		 * List the active events
		 */
		 function listActiveEvents(){
			 $http.get('ws-active-events').
		      success(function(data) {
		            $scope.events = data;
	        });
		 }

			/**
			 * Register 
			 */
			 $scope.register = function (event){
				 $location.path("register/"+event.idr);
			 }

		 
		listActiveEvents();
}])
.controller('registerCtrl',  ['$scope','$http','$routeParams',function($scope,$http,$routeParams) {
		
	 /** This is to identify the event it is related to */
	var user={}; 
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

	var idEvent=$routeParams.idEvent;
		console.log("Coucou register "+idEvent)

/**
 * retrieve an event
 */
		function getEvent(idEvent){
			$http.get('ws/event/'+idEvent).
			success(function(data) {
			$scope.event = data;
			});
		};
/**
 * Insert a new entry function
 */
		
 $scope.update = function (user) {
    $http.post('/event-tracker/ws-register/'+idEvent,user).
        	success(function(data) {
     	  	$scope.message='Thanks for registering.';
       	  	$scope.error=false;
            list();
        }).
		error(function(data) {
     	  	$scope.message='An issue occured.';
       	  	$scope.error=true;
		})
	};

		/**
		 * List the entries
		 */		
		function list(){
				 $http.get('ws/event/'+idEvent).
			      success(function(data) {
			        //	console.log(JSON.stringify(data._embedded));
			            $scope.participations = data.participations;
			            $scope.nbTotal = data.participations.length;
			        });
				 
		$http.get('ws-participation-stats').
			     success(function(data) {
			      //	console.log(JSON.stringify(data._embedded));
			         $scope.participationStats = data;
			     });
			}
		
		/**
		* Remove the entries
		*/		
		$scope.remove = function(email){ $http.delete('ws-unregister/'+idEvent+'/'+email).
				success(function(data) {
			  	$scope.message='The entry has been removed.';
				list();
			});
		}

		/**
		 * Action at page loading.
		 */
		list();	
		getEvent(idEvent);

}


]);
