'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl',  ['$scope','$http', function($scope,$http) {

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
		
 $scope.punition = function (point,rationale) {
    $http.post('/tvscheduler/punition',{'value': point, 'rationale': rationale}).
        success(function(data) {
            $scope.punitionMessage = data.message;
            $scope.error = false;
        }).
		error(function(data) {
            $scope.punitionMessage = 'Un problème a eu lieu';
			$scope.error = true;
		})
		};
			
}]);
