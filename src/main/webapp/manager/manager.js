'use strict';

angular.module('managerApp.manager', ['ngRoute','angularFileUpload'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/manager', {
    templateUrl: 'manager/manager_forfeit.html',
    controller: 'forfeitCtrl'
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
	
	.controller('showsCtrl',  ['$scope','$http', 'FileUploader', function($scope,$http,FileUploader) {
	/**
	 * Insert a new entry fonction
	 */
			
	 $scope.update = function (show) {
	    $http.post('/event-tracker/ws/show',show).
	        success(function(data) {
	     	  	$scope.message='Show registered.';
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
		            $scope.shows = data._embedded.show;
		        });
			 }
		/**
		* List the entries
		*/		
		$scope.remove = function(id){ $http.delete('/event-tracker/ws/show/'+id).
				success(function(data) {
				list();
			});
		}
	list();		 
	
    var uploader = $scope.uploader = new FileUploader({
        url: 'upload'
    });

    // FILTERS

    uploader.filters.push({
        name: 'imageFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
    };

console.info('uploader', uploader);
	}])
	
	;
