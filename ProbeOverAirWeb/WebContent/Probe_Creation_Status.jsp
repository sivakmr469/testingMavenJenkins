<!DOCTYPE html>
<html lang="en"  ng-app="app1">
<head>
	<title>Accenture</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/component.css">    
	<link rel="stylesheet" href="css/BootSideMenu.css">
	<script src="js/jquery.min.js"></script>
	<script src="js/BootSideMenu.js"></script>
		 <script type="text/javascript">
			  $(document).ready(function(){
				  $('#menuleft').BootSideMenu({side:"left", autoClose:true}); });
				 
		 </script>

</head>
<body ng-controller="MainCtrl">
<nav class="navbar navbar-inverse">
	<div class="container-fluid cw-header">
    <div class="navbar-header">
		<button type="button" class="navbar-toggle" >
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>                        
      	</button>
      	<a class="navbar-brand" href="index.html">Accenture</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">		
		<ul class="nav navbar-nav navbar-right">
        	<li><a href="#"> User Name&nbsp; &nbsp;<img src="images/user-icon.png"/>  </a></li>
        	<li><a href="#">Logout &nbsp; &nbsp;<img src="images/log-out-icon.png"/></a></li>
      	</ul>
    </div>
 	</div>
</nav>
<div class="col-md-12 text-center header-heading">
  <h1>Probe Creation</h1>
</div> 
 
	<div id="menuleft">	
		<div class="list-group">
		  <h2  class="text-center">Dashboard</h2>
		  <a href="device_avail.html" class="list-group-item" target="_self" ><img src="images/add-new-test-porbes.png"/>Home</a>
		  <a href="script.html" class="list-group-item active"><img src="images/add-new-test-porbes.png"/>Add New Test Probes</a>
		  <a href="device_inventory.html" class="list-group-item"><img src="images/device-inventory.png"/>Device Inventory</a>
		  <a href="#" class="list-group-item"><img src="images/standard-test-libraries.png"/>Standard Test Libraries</a>
		  <a href="#" class="list-group-item"><img src="images/reports.png"/>Reports</a>
		  <a href="#" class="list-group-item"><img src="images/advanced-search.png"/>Advanced Search</a>		 
		</div>
    </div>
		<div class="meny-arrow"></div>
		
  <div class="container contents">
  <div class="col-md-2 text-center"  ></div>  

	<div class="col-md-2"></div>
	<!-- <form><textarea id="ExampleOutput" style="width:100%" rows="10"></textarea></form> -->
</div>

<div class="container">
<!-- <div class="row">
		 <div class="col-md-12 text-center">
		  <h3>Probe Creation</h3>
		 </div> <br clear="all"/> <br clear="all"/> 
	</div>	 -->
<div class="col-md-2"></div>      

<!-- Text input-->
	<div class="col-md-8">   
		<!-- <form class="well form-horizontal" action=" " method="post"  id="contact_form"> -->
	
		<form class="well form-horizontal" action="/ProbeOverAirWeb/device_avail.html" method="post"  id="contact_form" >
		
		
		<h3><%=session.getAttribute("status")%></h3><br/>
		<button type="button" class="btn btn-success btn-lg"><a href="device_avail.html" style="color:#fff;text-decoration:none;">Home</a></button>&nbsp; 
		<!-- <button type="submit" class="btn btn-success"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HOME</button>&nbsp; -->
				
		</form>
	</div>
			
	<div class="col-md-2"></div>   

</div>
    <footer class="footer">
      <div class="container">
        <p class="text-muted">Copyright<sup style="font-size:12px;">&copy;</sup> | Accenture India</p>
      </div>
    </footer>

</body>
</html>
