'use strict';

angular.module('voteApp.vote')
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
		
		var pieData= [{
            name: "Yes",
            y: 56.33
        }, {
            name: "No",
            y: 24.03,
        }];
		
		 /**
		  * 	retrieve the result of the Poll
		  */
			function getResults(idPoll){
				$http.get('ws-poll-result/'+idPoll).
				success(function(data) {
					console.log("Ouanegene ");
					$scope.pieData = data;
					console.log("JSON "+JSON.stringify($scope.pieData));
					
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
		
        // Sample data for pie chart
        $scope.pieData = pieData;

    	getResults(idPoll);
       
}

]);
