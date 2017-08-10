'use strict';

angular.module('voteApp.vote')
.controller('voteCtrl',  ['$scope','$http','$routeParams','$cookies','growl',function($scope,$http,$routeParams,$cookies,growl) {	
	 /** This is to identify the event it is related to */
	
	
	 /** We use the tracer to identify if a registration has been done on the same computer */
	 var idPoll=$routeParams.idPoll;

	 var clientCookie = $cookies.get("client-autogen");
	 console.log('Cookie angular  ' + clientCookie);
	 
	 
	 if(clientCookie==null){
	 	var rndNumber=Math.floor((Math.random() * 1000000));
	 	$cookies.put("client-autogen",rndNumber);
	 }
	 else{
		 console.log('There is one cookie in the session ' + clientCookie);
	 }
	 /** This is to identify the event it is related to */

	 console.log('Poll ID  ' + idPoll);
	
	 /**
	  * 	retrieve an Poll
	  */
		function getPoll(idPoll){
			$http.get('ws/poll/'+idPoll).
			success(function(data) {
			$scope.poll = data;
			});
		};
		
		/**
		 * Insert a new entry function
		 */
		
 $scope.vote = function (voteYN,pollId) {

	 
	 var myBallot={}
	 myBallot.answer=voteYN;
	 myBallot.pollId=pollId;
	 		
	 
	 $http.post('ws-vote/'+idPoll,myBallot).
        	success(function(data) {
        		 growl.success(data.message);
      	}).
		error(function(data) {
   		 growl.error(data.message);
		})
	};

		/**
		 * Action at page loading.
		 */
		getPoll(idPoll);
		
		

        
        // Sample options for first chart
        $scope.chartOptions = {
            title: {
                text: 'Temperature data'
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },

            series: [{
                data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
            }]
        };

        // Sample data for pie chart
        $scope.pieData = [{
                name: "Microsoft Internet Explorer",
                y: 56.33
            }, {
                name: "Chrome",
                y: 24.03,
                sliced: true,
                selected: true
            }, {
                name: "Firefox",
                y: 10.38
            }, {
                name: "Safari",
                y: 4.77
            }, {
                name: "Opera",
                y: 0.91
            }, {
                name: "Proprietary or Undetectable",
                y: 0.2
        }]
    
}

]);
