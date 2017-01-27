'use strict';

angular.module('managerApp.manager', ['ngRoute','angularFileUpload'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/event/:id', {
    templateUrl: 'manager/manager_event.html',
    controller: 'eventMgrCtrl'
  })
  .when('/event', {
    templateUrl: 'manager/manager_event.html',
    controller: 'eventMgrCtrl'
  })
  .when('/show', {
	    templateUrl: 'manager/manager_show.html',
	    controller: 'showsCtrl'
	  });
}])

.controller('eventMgrCtrl',  ['$scope','$http','$routeParams','$location', function($scope,$http,$routeParams,$location) {
	
	 var eventId = $routeParams.id;
	 
	 console.log(eventId);
	 
	 /**
	  * Function to read an event
	  */
	 function get(eventId){

		    $http.get('ws/event/'+eventId).
		        success(function(data) {
	       	  	$scope.event=data;
	       	  	console.log("Chargement de l'event "+eventId);

		        }).
				error(function(data) {
				})			
	 }
	 
	 if(eventId!=null){
		 get(eventId);
	 }
	/**
	 * Insert a new entry fonction
	 */
	 $scope.update = function (event) {
	    $http.post('ws/event',event).
	        success(function(data) {
	     	  	$scope.message='Event registered.';
	       	  	$scope.error=false;
	       	  	$scope.event=data;
	       	  	$location.path('/event/'+data.idr);
	            list();
	        }).
			error(function(data) {
	     	  	$scope.message='An issue occured';
	       	  	$scope.error=true;
			})
			};
			
	/**
	 * Insert a new entry fonction
	*/
	 $scope.addQuestion = function (question) {
		 var questions=$scope.event.additionalQuestions;
		 if(questions == undefined){
			 console.log("Creation du tableau");
			 questions=[];
		 }
		 questions.push(question);
		 $scope.newQuestion={};
		 $scope.event.additionalQuestions=questions;
	 };
						
/**
 * List the entries
 */		
	 function list(){
		 $http.get('ws/event').
	      success(function(data) {
	    //    	console.log(JSON.stringify(data._embedded));
	            $scope.events = data._embedded.event;
	        });
		 }
	/**
	* List the entries
	*/		
	$scope.remove = function(id){ $http.delete('ws/event/'+id).
			success(function(data) {
		  	$scope.message='The entry has been removed.';
			list();
		});
	}
		
		list();		 
}]);