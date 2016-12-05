'use strict';

angular.module('myApp.participation', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/participation', {
    templateUrl: 'participation/participation.html',
    controller: 'participationCtrl'
  }).when('/register/:idEvent', {
	    templateUrl: 'participation/register.jsp',
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
	var participant={forEmployeeOnly: false, employee: null}; 
	 
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
	//	 console.log('There is one cookie in the session ' + x.substring(7));
	 }
	 /** This is to identify the event it is related to */

	 participant.tracer=x.substring(7);

	var idEvent=$routeParams.idEvent;
	
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
    $http.post('ws-register/'+idEvent,user).
        	success(function(data) {
     	  	$scope.message='Thanks for registering.';
     	  	$scope.participant={};
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
				 $http.get('ws-event/'+idEvent).
			      success(function(data) {
			            $scope.participations = data.participations;
			            if(data.participations != null){
			            $scope.nbTotal = data.participations.length;
			            }
			            else{
			            	$scope.nbTotal = 0;
			            }
			        });
				 
		$http.get('ws-participation-stats').
			     success(function(data) {
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
		* Register paiement
		*/		
		$scope.pay = function(participation,event){ 
			 console.log('Paiement for participation ' + participation.idr + " valeur "+participation.paid + ", eventId="+event.idr);
			$http.get('ws-pay/'+idEvent+'/'+participation.idr+'/'+participation.paid).
				success(function(data) {
			});
		}

		/**
		 * Action at page loading.
		 */
		list();	
		getEvent(idEvent);

}


]);
