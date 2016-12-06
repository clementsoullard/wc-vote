<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">

	<div class="alert alert-danger" ng-if="message&&error">
		<a class="close" data-dismiss="alert" aria-label="close">&times;</a>
		{{message}}
	</div>
	<div class="alert alert-success" ng-if="message&&!error">
		<a class="close" data-dismiss="alert" aria-label="close">&times;</a>
		{{message}}

	</div>

	<div style="background-color: white; padding: 20px">

		<h1>{{event.name}} on {{event.date | date: fullDate}}</h1>
		<h3 ng-if="event.dateMaxRegistration">Registration until
			{{event.dateMaxRegistration| date: fullDate}}</h3>
		<h3 ng-if="event.maxParticipant">Open to {{event.maxParticipant}}
			persons max.</h3>

		<div class="alert alert-info" role="alert">
			<p>{{event.description}}</p>
			<p>{{event.childInfo}}</p>
		</div>

		<!-- Displayed only in case the user is authenticated -->
		<sec:authorize access="isAuthenticated()">
			<a href="ws-download/{{event.idr}}.csv" class="btn btn-info"
				aria-label="Left Align"> Download CSV <span
				class="glyphicon glyphicon glyphicon-download" aria-hidden="true"></span>
			</a>
		</sec:authorize>
		<form novalidate name="form" class="css-form">
			<div class="row">
				<label for="inputFirstName" class="col-sm-2 col-form-label">First
					Name</label>
				<div class="col-sm-2">
					<input type="text" ng-model="participant.firstName" />
				</div>
				<label for="inputFirstName" class="col-sm-2 col-form-label">Last
					Name</label>
				<div class="col-sm-2">
					<input type="text" ng-model="participant.lastName" />
				</div>
			</div>
			<div ng-show="!event.forEmployeeOnly" class="form-group row">
				<label class="col-sm-2 col-form-label">Infosys Employee: </label><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.employee" ng-value="true" ng-required="!participant.employee" />Employee</span><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.employee" ng-value="false" ng-required="!participant.employee" />Relative</span>
			</div>
			<div ng-show="participant.employee==true"></div>
			<div
				ng-if="participant.employee && participant.employee != null || event.forEmployeeOnly"
				class="form-group row">
				<label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="email" ng-model="participant.email" size="50" required />
				</div>
			</div>

			<div ng-show="needGenderInfo" class="form-group row">
				<label class="col-sm-2 col-form-label">Gender:</label> <span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.gender" value="male" />Male </span><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.gender" value="female" />Female</span>
			</div>
			<div ng-show="needVegetarianInfo" class="form-group row">
				<label class="col-sm-2 col-form-label">Veg: </label><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.vegetarian" ng-value="true" />Vegetarian </span><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.vegetarian" ng-value="false" />Non Veg</span>
			</div>


			<div ng-show="!participant.employee && participant.employee != null"
				class="form-group row">
				<label class="col-sm-2 col-form-label">Children: </label><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.child" ng-value="true" />Child </span><span
					class="col-sm-2 col-form-label"><input type="radio"
					ng-model="participant.child" ng-value="false" />Adult</span>
			</div>

			<div
				ng-show="!participant.employee && participant.employee != null && participant.child && participant.child != null"
				class="form-group row">
				<label class="col-sm-2 col-form-label">Age: </label><span
					class="col-sm-2 col-form-label"><input type="text"
					ng-model="participant.age" /></span>
			</div>
			<div class="form-group row">
				<span class="col-sm-5"></span> <input type="submit"
					class="btn btn-success col-sm-2" ng-click="update(participant,event,form)"
					value="Register" />
			</div>
		</form>
		<hr>
	</div>
	
	<!-- 
	Liste des participants
	 -->
	
	<div style="background-color: white; padding: 20px">
		<h2>Participants list</h2>
		<div class="row">
			<label class="col-sm-2 col-form-label">Nb participants: </label><span
				class="col-sm-2 col-form-label">{{nbTotal}}</span> <label
				ng-if="needVegetarianInfo" class="col-sm-2 col-form-label">Nb
				vegs: </label><span ng-if="needVegetarianInfo"
				class="col-sm-2 col-form-label">{{participationStats.nbVegetarian}}</span>
			<label ng-if="needChildInfo" class="col-sm-2 col-form-label">Nb
				Childs: </label><span ng-if="needChildInfo" class="col-sm-2 col-form-label">{{participationStats.nbChild}}</span>
		</div>
		<div class="row" style="background-color: white; padding: 30px;">
			<ul class="list-group" ng-repeat="participation in participations">
				<li class="list-group-item col-sm-6" style="height:60px">
				<div>
				<span 	ng-class="{redInfamed: participation.infamed}" class="col-sm-9 gras">
						{{participation.firstName}} {{participation.lastName}}<br>
						 {{participation.email}} </span>
					 <sec:authorize
						access="isAuthenticated()">
						<span ng-if="event.payingEvent" class="col-sm-2">Paid&nbsp;<input type="checkbox"
							ng-model="participation.paid" ng-change="pay(participation,event)">
					</span>
					</sec:authorize> 
					<sec:authorize access="isAnonymous()">
						<span ng-if="event.payingEvent" ng-class="(participation.paid==true) ? 'green' : 'redInfamed'" class="gras col-sm-2" >{{participation.paid?'Paid':'To pay'}}</span>
					</sec:authorize> <span class="pull-right"><a class="close"
						data-dismiss="alert" aria-label="close"
						ng-click="remove(participation.idr)">&times;</a> </span>
						</div>
				</li>		
			</ul>
		</div>
	</div>