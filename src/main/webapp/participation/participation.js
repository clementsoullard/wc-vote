'use strict';

angular.module('myApp.participation', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/participation', {
    templateUrl: 'participation/participation.html',
    controller: 'participationCtrl'
  });
}])

.controller('participationCtrl',  ['$scope','$http', function($scope,$http) {

   $http.get('/tvscheduler/tvstatus').
        success(function(data) {
            $scope.tvstatus = data;
        });

 $scope.tv = function (sec) {
    $http.get('/tvscheduler/credit?value='+sec).
        success(function(data) {
            $scope.tvstatus = data.status;
        })
		};
		
 $scope.update = function (user) {
    $http.post('/event-tracker/ws-participation',user).
        success(function(data) {
            $scope.updateMessage = data.message;
            $scope.error = false;
        }).
		error(function(data) {
            $scope.updateMessage  = 'Un problème a eu lieu';
			$scope.error = true;
		})
		};
			
}]);
