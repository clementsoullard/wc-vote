<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div ng-include= "'menu.jsp'"></div>

<div class="container">
	<div class="alert alert-danger" ng-if="message&&error">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		{{message}}
	</div>
	<div class="alert alert-success" ng-if="message&&!error">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		{{message}}
	</div>

	<div>
		<h2>Vote</h2>
		<form novalidate class="simple-form">
			<div class="form-group row">
				<label for="inputFirstName" class="col-sm-2 col-form-label">Title</label>
				<div class="col-sm-6">
					<input  class="form-control" size="60" type="text" ng-model="event.name" />
				</div>

				
			<label class="col-sm-2" for="date" class="col-sm-2 col-form-label">Date max vote</label>
			
			<div flex-gt-xs>
					<md-datepicker md-placeholder="Enter date" ng-model="event.dateMaxRegistrationDp"></md-datepicker>
				</div>
			</div>

			<div class="row">
				<label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
				<textarea ng-model="event.description" class="form-control" style="min-width: 100%">
				</textarea>
			</div>


	

</div>

