'use strict';

angular.module('managerApp.caisse', ['ngRoute','angularFileUpload'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/caisse', {
    templateUrl: 'caisse/caisse.html',
    controller: 'caisseMgrCtrl'
  })
   ;
}])

.controller('caisseMgrCtrl',  ['$scope','$http', 'FileUploader', function($scope,$http,FileUploader) {

	  var uploader = $scope.uploader = new FileUploader({
	        url: 'upload',
	        queueLimit: 1
	    });

	/**
	 * Insert a new entry function
	 */
			
	 $scope.insert = function (line) {
		 uploader.uploadAll();
		 uploader.queue=[];
		 
		 $http.post('ws/line-caisse/',line).
	        	success(function(data) {
	     	  	$scope.message='Line saved.';
	     	  	$scope.line={};
	     	   $('#bill-file').val('');
	         	$scope.error=false;
	            list();
	        }).
			error(function(data) {
	     	  	$scope.message='An issue occured.';
	     	  	$scope.error=true;
			})
		};
		/**
		 * List the line in caisse
		 */		
		function list(){
				 $http.get('ws/line-caisse/').
			      success(function(data) {
			            $scope.linesCaisse = data._embedded.line;
			      });				 
			}
		
		
		/**
		* Remove one line caisse
		*/		
		$scope.remove = function(id){ $http.delete('ws/line-caisse/'+id).
				success(function(data) {
			  	$scope.message='The entry has been removed.';
				list();
			});
		}


		    // FILTERS

		    uploader.filters.push({
		        name: 'imageFilter',
		        fn: function(item /*{File|FileLikeObject}*/, options) {
		            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
		            return '|jpg|png|jpeg|bmp|gif|pdf|'.indexOf(type) !== -1;
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
		
		/**
		 * Listing the entries
		 */
		list();


}])
