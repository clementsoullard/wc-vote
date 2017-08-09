'use strict';

// Declare app level module which depends on views, and components
angular.module('voteApp', [
  'ngRoute',
  'ngCookies',
   'voteApp.vote',
  'voteApp.voteManager',
  'voteApp.version',
  'ngAnimate',
  'ngMaterial',
  'angularFileUpload',
  'ng-fusioncharts',
  'angular-growl'
  ]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  
  $routeProvider.when('/managePoll/:id', {
	    templateUrl: 'managePoll/manager_poll.jsp',
	    controller: 'voteMgrCtrl'
	  })
	  .when('/managePoll', {
	    templateUrl: 'managePoll/manager_poll.jsp',
	    controller: 'voteMgrCtrl'
	  })
	  .otherwise({redirectTo: '/vote'});
}]);
