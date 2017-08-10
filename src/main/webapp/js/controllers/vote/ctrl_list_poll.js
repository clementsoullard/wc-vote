'use strict';

angular.module('voteApp.vote')
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/vote', {
    templateUrl: 'view/vote/list_vote.jsp',
    controller: 'listVoteCtrl'
  })
  .when('/vote/:idPoll', {
	    templateUrl: 'view/vote/vote.jsp',
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
}]);
