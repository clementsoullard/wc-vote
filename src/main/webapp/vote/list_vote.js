'use strict';

angular.module('voteApp.vote', ['ngRoute','ngCookies'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/vote', {
    templateUrl: 'vote/list_vote.jsp',
    controller: 'listVoteCtrl'
  })
  .when('/vote/:idPoll', {
	    templateUrl: 'vote/vote.jsp',
	    controller: 'voteCtrl'
	  });
}])
.controller('listVoteCtrl',  ['$scope','$http','$location', function($scope,$http,$location) {
	console.log('Passage dans le menu de liste des votes');
	$scope.menuSelected = "listVote";
	
		/**
		 * List the active polls
		 */
		 function listActivePolls(){
			 $http.get('ws-active-polls').
		      success(function(data) {
		            $scope.polls  = data;
	        });
		 }
			/**
			 * Vote for a poll
			 */
			 $scope.vote = function (poll){
				 $location.path("vote/" + poll.idr);
			 }
		listActivePolls();
}])

.controller('voteCtrl',  ['$scope','$http','$routeParams','$cookies','growl',function($scope,$http,$routeParams,$cookies,growl) {	
	 /** This is to identify the event it is related to */
	
	 
	
	 /** We use the tracer to identify if a registration has been done on the same computer */
	 var idPoll=$routeParams.idPoll;

	 var clientCookie = $cookies.get("client-autogen");
	 console.log('Cookie angular  ' + clientCookie);
	 
	 
	 if(clientCookie==null){
	 	var rndNumber=Math.floor((Math.random() * 1000000));
	 	$cookies.put("client-autogen",rndNumber);
	 }
	 else{
		 console.log('There is one cookie in the session ' + clientCookie);
	 }
	 /** This is to identify the event it is related to */

	 console.log('Poll ID  ' + idPoll);
	
	 /**
	  * 	retrieve an Poll
	  */
		function getPoll(idPoll){
			$http.get('ws/poll/'+idPoll).
			success(function(data) {
			$scope.poll = data;
			});
		};
		
		/**
		 * Insert a new entry function
		 */
		
 $scope.vote = function (voteYN,pollId) {



	 
	 var myBallot={}
	 myBallot.answer=voteYN;
	 myBallot.pollId=pollId;
	 		
	 
	 $http.post('ws-vote/'+idPoll,myBallot).
        	success(function(data) {
        		 growl.success(data.message);
      	}).
		error(function(data) {
   		 growl.error(data.message);
		})
	};

		/**
		 * Action at page loading.
		 */
		getPoll(idPoll);
}

]);
