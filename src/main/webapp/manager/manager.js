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

.directive("fileread", [function () {
    return {
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
            	scope.tmpImgId = undefined;
                console.log("Changement");
                var xhr = new XMLHttpRequest();                 
                var FD  = new FormData();
                FD.append('file', changeEvent.target.files[0]);
                xhr.onreadystatechange = function () {
                   console.log("Callback");
                	if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                		console.log("Id generé" + xhr.response);
                		scope.setTempImage(xhr.response);
                     }
                   }
                xhr.open('POST', 'upload/', true);
                xhr.send(FD);
             });
        }
    }
}])

.controller('eventMgrCtrl',  ['$scope','$http','$routeParams','$location', function($scope,$http,$routeParams,$location) {
	
	 var eventId = $routeParams.id;
	 
	 var fichier;
	 $scope.fichier=fichier;
	 
	 console.log("Evenement "+eventId);
	 
	 /**
	  * Function to read an event
	  */
	 function get(eventId){
		    $http.get('ws/event/'+eventId).
		        success(function(data) {
		        // Converting the JSON format to the the Java Script date format.
		        	
	       	  	data.dateEventDp=new Date(data.date);
	       	  	data.dateMaxRegistrationDp=new Date(data.dateMaxRegistration);
	       	  	console.log('Date'+data.dateMaxRegistrationDp);

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
	 * Insert a new entry fonction or update (if the id have been specified)
	 */
	 $scope.update = function (event) {
     	event.date=event.dateEventDp;
     	event.tmpImgId=$scope.tmpImgId;
    	event.dateMaxRegistration=event.dateMaxRegistrationDp;
    	$http.post('ws-event',event).
	        success(function(data) {
	     	  	$scope.message='Event registered.';
	       	  	$scope.error=false;
	       	  	// Converting the date to a datepicker format (after the post is done the event is reloaded from server side
	      	  	data.dateEventDp=new Date(data.date);
	       	  	data.dateMaxRegistrationDp=new Date(data.dateMaxRegistration);

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
	 * Create a new optional question
	*/
	 $scope.addQuestion = function (question) {
		 var questions=$scope.event.additionalQuestions;
		 if(questions == undefined){
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
	* Remove an event
	*/		
	$scope.remove = function(id){ $http.delete('ws/event/'+id).
			success(function(data) {
		  	$scope.message='The entry has been removed.';
			list();
		});
	}
	/**
	* Upload a file
	*/		
	$scope.setTempImage = function(tmpImgId){ 
		 console.log("Set Temporary Image "+tmpImgId);
		 $scope.tmpImgId=tmpImgId;
	}
	
	list();		 
}]);