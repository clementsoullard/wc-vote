'use strict';

angular.module('managerApp.manager', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/manager', {
    templateUrl: 'manager/manager_forfeit.html',
    controller: 'forfeitCtrl'
  })
  .when('/song', {
	    templateUrl: 'manager/manager_song.html',
	    controller: 'songCtrl'
	  })
  .when('/show', {
	    templateUrl: 'manager/manager_show.html',
	    controller: 'showsCtrl'
	  })
  ;
}])

.controller('forfeitCtrl',  ['$scope','$http', function($scope,$http) {
/**
 * Insert a new entry fonction
 */
 $scope.update = function (forfeit) {
    $http.post('/event-tracker/ws/forfeit',forfeit).
        success(function(data) {
     	  	$scope.message='Thanks for submitting the idea.';
       	  	$scope.error=false;
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
		 $http.get('/event-tracker/ws/forfeit').
	      success(function(data) {
	        	console.log(JSON.stringify(data._embedded));
	            $scope.forfeits = data._embedded.forfeit;
	        });
		 }
	/**
	* List the entries
	*/		
	$scope.remove = function(id){ $http.delete('/event-tracker/ws/forfeit/'+id).
			success(function(data) {
		  	$scope.message='The entry has been removed.';
			list();
		});
	}
		
		list();		 
}])

.controller('songCtrl',  ['$scope','$http', function($scope,$http) {

	 
	/**
	 * Insert a new entry fonction
	 */
			
	 $scope.update = function (user) {
	    $http.post('/event-tracker/ws/song',user).
	        success(function(data) {
	     	  	$scope.message='Thanks for applying. You have been properly registred. You can also register husband/wife and children after closing this window.';
	       	  	$scope.error=false;
	            list();
	        }).
			error(function(data) {
	     	  	$scope.message='An issue occured';
	       	  	$scope.error=false;
			})
			};
				
	/**
	 * List the entries
	 */		
		 function list(){
			 $http.get('/event-tracker/ws/song').
		      success(function(data) {
		        	console.log(JSON.stringify(data._embedded));
		            $scope.songs = data._embedded.song;
		        });
			 }
		/**
		* List the entries
		*/		
		$scope.remove = function(id){ $http.delete('/event-tracker/ws/song/'+id).
				success(function(data) {
			  	$scope.message='The entry has been removed.';
				list();
			});
		}
			
			list();		 
	}])
	
/**
 * 
 * The controller for the show
 */	
	
	.controller('showsCtrl',  ['$scope','$http', function($scope,$http) {
	/**
	 * Insert a new entry fonction
	 */
			
	 $scope.update = function (show) {
	    $http.post('/event-tracker/ws/show',user).
	        success(function(data) {
	     	  	$scope.message='Thanks for applying. You have been properly registred. You can also register husband/wife and children after closing this window.';
	       	  	$scope.error=false;
	            list();
	        }).
			error(function(data) {
	     	  	$scope.message='An issue occured';
	       	  	$scope.error=false;
			})
			};
				
	/**
	 * List the entries
	 */		
		 function list(){
			 $http.get('/event-tracker/ws/show').
		      success(function(data) {
		        	console.log(JSON.stringify(data._embedded));
		            $scope.songs = data._embedded.song;
		        });
			 }
		/**
		* List the entries
		*/		
		$scope.remove = function(id){ $http.delete('/event-tracker/ws/show/'+id).
				success(function(data) {
			  	$scope.message='The entry has been removed.';
				list();
			});
		}
			
			list();		 
	}])
	
	;
