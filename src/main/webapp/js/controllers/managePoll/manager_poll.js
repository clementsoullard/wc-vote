'use strict';

angular.module('voteApp.voteManager', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/poll/:id', {
    templateUrl: 'view/managePoll/manager_poll.jsp',
    controller: 'voteMgrCtrl'
  })
  .when('/poll', {
    templateUrl: 'view/managePoll/manager_poll.jsp',
    controller: 'voteMgrCtrl'
  })
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

.controller('voteMgrCtrl',  ['$scope','$http','$routeParams','$location', function($scope,$http,$routeParams,$location) {
	
	$scope.menuSelected = "manageVote";
	 var voteId = $routeParams.id;
	 
	 var fichier;
	 $scope.fichier=fichier;
	 
	 console.log("Vote "+voteId);
	 
	 /**
	  * Function to read an event
	  */
	 function get(voteId){
		    $http.get('ws/poll/'+voteId).
		        success(function(data) {
		        // Converting the JSON format to the the Java Script date format.
	       	  	data.dateMaxVoteDp=new Date(data.dateMaxVote);
	       	  	console.log('Date'+data.dateMaxVoteDp);

	       	  	$scope.poll=data;
	       	  	console.log("Chargement du Poll "+voteId);

		        }).
				error(function(data) {
				})			
	 }
	 
	 if(voteId!=null){
		 get(voteId);
	 }
	 
	/**
	 * Insert a new entry fonction or update (if the id have been specified)
	 */
	 $scope.update = function (poll) {
     	poll.dateMaxVote=poll.dateMaxVoteDp;
   	  	console.log('Date'+poll.dateMaxVoteDp);

     	
    	$http.post('ws-poll',poll).
	        success(function(data) {
	     	  	$scope.message='Poll registered.';
	       	  	$scope.error=false;
	       	  	// Converting the date to a datepicker format (after the post is done the event is reloaded from server side
	       	  	data.dateMaxVoteDp=new Date(data.dateMaxVote);

	       	  	$scope.poll=data;
	       	  	$location.path('/poll/'+data.idr);
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
		 $http.get('ws/poll').
	      success(function(data) {
	    //    	console.log(JSON.stringify(data._embedded));
	            $scope.events = data._embedded.poll;
	        });
		 }
	/**
	* Remove an event
	*/		
	$scope.remove = function(id){ $http.delete('ws/poll/'+id).
			success(function(data) {
		  	$scope.message='The entry has been removed.';
			list();
		});
	}
	
	list();		 
}]);