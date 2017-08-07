'use strict';

angular.module('voteApp.caisse', ['ngRoute','angularFileUpload'])

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
	        queueLimit: 1,
	        
	    });

	/**
	 * Insert a new entry function
	 */
	  
	  var tmpLine;
			
	 $scope.insert = function (line) {
		 tmpLine=line;
		 console.info('Begin inserto',  line);
		 if (uploader.queue.length==0){
			 console.info('Adding the line without a file',  tmpLine);
			 addLine(-1);
		 }
		 else{
			 console.info('Uploading a file',  tmpLine);
			 uploader.uploadAll();	 
		 }
		 uploader.queue=[];
		};
		
		function afterUpload(imageId) {		
		 console.info('Insert After upload',  tmpLine);
		 addLine(imageId); 
		 };

		function addLine(imageId) {			 
		$http.post('ws-line-caisse/' + imageId,tmpLine).
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
		}

		/**
		 * List the line in caisse
		 */		
		function list(){
				 $http.get('ws-line-caisse-summary').
			      success(function(data) {
			            $scope.linesCaisse = data.lineCaisses;
			            $scope.summary = data.caisseSummary;
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
		        console.info('onProgressItem', fileItem);
		    };
		    uploader.onProgressAll = function(progress) {
		        console.info('onProgressAll', progress);
		    };
		    uploader.onSuccessItem = function(fileItem, response, status, headers) {
		        console.info('onSuccessItem', response);
		    };
		    uploader.onErrorItem = function(fileItem, response, status, headers) {
		        console.info('onErrorItem', fileItem, response, status, headers);
		    };
		    uploader.onCancelItem = function(fileItem, response, status, headers) {
		        console.info('onCancelItem', fileItem, response, status, headers);
		    };
		    uploader.onCompleteItem = function(fileItem, response, status, headers) {
		    	 //console.info('onCompleteItem', fileItem, response, status, headers);
		    	 console.info('onCompleteItem',  response);
		    	 afterUpload(response);
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
