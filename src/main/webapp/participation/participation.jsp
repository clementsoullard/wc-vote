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


	<div ng-repeat="event in events">
		<div style="background-color: white; padding: 10px; margin: 2px; " class="row">
				<h3 class="col-sm-5">{{event.name}}</h3>
			<span class="col-sm-5"></span> <input type="submit"
				class="btn btn-info col-sm-2" ng-click="register(event)"
				value="Register" />
			<!-- Displayed only in case the user is authenticated -->
		</div>
	</div>
</div>

