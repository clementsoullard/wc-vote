'use strict';

angular.module('managerApp.manager', ['ngRoute','angularFileUpload'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/event', {
    templateUrl: 'manager/manager_event.html',
    controller: 'eventMgrCtrl'
  })
  .when('/show', {
	    templateUrl: 'manager/manager_show.html',
	    controller: 'showsCtrl'
	  })
  ;
}])

.controller('eventMgrCtrl',  ['$scope','$http', function($scope,$http) {
/**
 * Insert a new entry fonction
 */
 $scope.update = function (event) {
    $http.post('ws/event',event).
        success(function(data) {
     	  	$scope.message='Event registered.';
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
		 $http.get('ws/event').
	      success(function(data) {
	    //    	console.log(JSON.stringify(data._embedded));
	            $scope.events = data._embedded.event;
	        });
		 }
	/**
	* List the entries
	*/		
	$scope.remove = function(id){ $http.delete('ws/event/'+id).
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
	    $http.post('ws/song',user).
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
			 $http.get('ws/song').
		      success(function(data) {
		        	console.log(JSON.stringify(data._embedded));
		            $scope.songs = data._embedded.song;
		        });
			 }
		/**
		* Remove one event
		*/		
		$scope.remove = function(id){ $http.delete('ws/song/'+id).
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
	    $http.post('ws/show',show).
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
			 $http.get('ws/show').
		      success(function(data) {
		        	console.log(JSON.stringify(data._embedded));
		            $scope.shows = data._embedded.show;
		        });
			 }
		/**
		* List the entries
		*/		
		$scope.remove = function(id){ $http.delete('ws/show/'+id).
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
