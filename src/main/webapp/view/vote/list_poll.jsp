<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div ng-include="'menu.jsp'"></div>


<div class="container">

	<div class="alert alert-danger" ng-if="message&&error">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		{{message}}
	</div>
	
	<div class="alert alert-success" ng-if="message&&!error">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		{{message}} <br> <a href="#" aria-label="close">Enter some
			other peoples (relatives or colleagues)</a>
	</div>

	<div ng-repeat="poll in polls">
		<div style="background-color: white; padding: 10px; margin: 2px; " class="row">
				<h3 class="col-sm-5">{{poll.name}}</h3>
				
				<h3 class="col-sm-3"></h3>
		<sec:authorize	access="isAuthenticated()">
						 <a href="#!/poll/{{poll.idr}}"><button 
				class="btn btn-info col-sm-2" 
				>Modify</button> 
				</a>
				
			</sec:authorize>
		
	
	
		<sec:authorize	access="isAnonymous()">
		
		Vote pour: {{poll.voteFor}}
		
		Vote contre: {{poll.voteAgainst}}
		
			 <input type="submit"
				class="btn btn-info col-sm-2" ng-disabled="!poll.active" ng-click="vote(poll)"
				value="Vote" />
			<!-- Displayed only in case the user is authenticated -->
		</sec:authorize>
				
		</div>
	</div>
</div>

