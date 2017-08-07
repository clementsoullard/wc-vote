 <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
  <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#topFixedNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
          <a class="navbar-brand" href=".">Infosys Workcouncil</a></div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="topFixedNavbar1">
          <ul class="nav navbar-nav">
            <li ng-class="menuSelected == 'listVote'? 'active':''">
            <a href="#!/vote">Vote<span class="sr-only">(current)</span></a></li>
       		<sec:authorize access="isAuthenticated()">
       		<li ng-class="menuSelected == 'manageVote'? 'active':''"><a href="#!/manageVote">Vote management</a></li>
       		  </sec:authorize>
             </ul>
               <ul class="nav navbar-nav navbar-right">
		   	<sec:authorize access="isAnonymous()">
		   <li><a href="login"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
		   </sec:authorize>
		   	<sec:authorize access="isAuthenticated()">
		   <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
		   	</sec:authorize>
    </ul>
    </div>
  </div>
 </nav>
