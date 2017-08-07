'use strict';

// Declare app level module which depends on views, and components
angular.module('voteApp', [
  'ngRoute',
  'voteApp.vote',
  'voteApp.voteManager',
  'voteApp.version',
  'ngAnimate',
  'ngMaterial',
  'angularFileUpload',
  'ng-fusioncharts'
  ]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  
  $routeProvider.when('/manageVote/:id', {
	    templateUrl: 'manageVote/manager_vote.jsp',
	    controller: 'voteMgrCtrl'
	  })
	  .when('/manageVote', {
	    templateUrl: 'manageVote/manager_vote.jsp',
	    controller: 'voteMgrCtrl'
	  })
	  .otherwise({redirectTo: '/vote'});
}]);
