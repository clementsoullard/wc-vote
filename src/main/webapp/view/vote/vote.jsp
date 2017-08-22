<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">


<div growl></div>
	
	<div style="background-color: white; padding: 20px">

		<h1>{{poll.name}}</h1>
		<h3 ng-if="poll.dateMaxVote">Vote until {{poll.dateMaxVote|
			date: fullDate}}</h3>

		<div class="alert alert-info" role="alert">
			<p>{{poll.description}}</p>
		</div>

		<form novalidate name="form" class="css-form">
			<div class="form-group row">
				<span class="col-sm-5"></span> <button 
					class="btn btn-success col-sm-2"
					ng-click="vote(true,poll.idr)" >YES</button>
				<span class="col-sm-1"></span> <button 
					class="btn btn-danger col-sm-2"
					ng-click="vote(false,poll.idr)"  >NO</button>
			</div>
		</form>
		<hr>
		<hc-pie-chart title="Results of Poll" data="pieData">Placeholder for pie chart</hc-pie-chart>
	</div>
	

     
</div>