<%@page import="com.accenture.pota.dal.bean.DalDeviceDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"  ng-app="app1">
<head>
	<title>Accenture</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://silviomoreto.github.io/bootstrap-select/dist/css/bootstrap-select.min.css"> 	
	<link rel="stylesheet" href="css/component.css">    
	<link rel="stylesheet" href="css/BootSideMenu.css">  
	<link rel="stylesheet" href="css/BootSideMenu.css">  
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular-route.min.js"></script> -->
	<script src="js/app.js"></script>
	<script src="js/script.js"></script>
	<script src="js/ng-table.min.js"></script> 
<!-- 	<script src="js/ExampleCtrl.js"></script>  -->
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.10.0/ui-bootstrap-tpls.js"></script>

    <!-- <script type="text/javascript" src="https://rawgit.com/dotansimha/angularjs-dropdown-multiselect/master/src/angularjs-dropdown-multiselect.js"></script> -->
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/lodash.js/2.4.1/lodash.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js" /></script>
	
	<script src="https://rawgit.com/nghuuphuoc/bootstrapvalidator/master/dist/js/bootstrapValidator.min.js"></script>
	
	<script src="js/jquery-validate.bootstrap-tooltip.min.js"></script>
   <script src="js/angularjs-dropdown-multiselect.js"></script>
	<script src="js/BootSideMenu.js"></script>
	<!-- <script>
$(document).ready(function () {
$('input[type=file]').change(function () {
var val = $(this).val().toLowerCase();
var regex = new RegExp("(.*?)\.(py)$");
 if(!(regex.test(val))) {
$(this).val('');
alert('Please select correct file format(ie .py)');
} }); });
</script> -->
<script>
$(document).ready(function (e) {
    $('#selectId1').click(function(){
        console.log('Dropdown list 1 losing focus...');
        $('#selectId').toggle();
    });
	$('#selectId').click(function(){
        console.log('Dropdown list 1 losing focus...');
        $(this).show();
    });
});
</script>
		 <script type="text/javascript">
			  $(document).ready(function(){
			  
			  $('#contact_form').bootstrapValidator({
                live: 'enabled',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
				fields: {
					 browsee: {
                        validators: {
                            file: {
                                extension: 'py',
                                maxSize: 5 * 1024 * 1024, // 5 MB
                                message: 'The selected file is not valid, it should be (py) and 5 MB at maximum.'
                            }
                        }
                    },
                'priority[]': {
                    validators: {
                        notEmpty: {
                            message: 'Please specify at least one Device Name'
                        }
                    }
                },
				}
			  });
			  
			  /*$('#contact_form').bootstrapValidator({
                live: 'enabled',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
				submitHandler: function(validator, form, submitButton) {
					alert("hidden");
					var strconfirm = confirm("Are you sure you want to Send?");
					if (strconfirm == true)
								{
									return true;
								}
				},
                fields: {
					probename: {
                    validators: {
                        notEmpty: {
                            message: 'The probename name is required'
                        }
                    }
                },
					 devicevalue: {
                    validators: {
                        notEmpty: {
                            message: 'The device names are required'
                        }
                    }
                },
                    browsee: {
                        validators: {
                            file: {
                                extension: 'py',
                                maxSize: 5 * 1024 * 1024, // 5 MB
                                message: 'The selected file is not valid, it should be (py) and 5 MB at maximum.'
                            }
                        }
                    },
				priority: {
                    validators: {
                        digits: {
                            message: 'priority number is not valid'
                        }
                    }
                },
				mobilePhone: {
                    validators: {
                        notEmpty: {
                            message: 'The mobile phone number is required'
                        },
                        digits: {
                            message: 'The mobile phone number is not valid'
                        }
                    }
                }
				
                }
            }); */
			  
				  $('#menuleft').BootSideMenu({side:"left", autoClose:true});
				  
				 // $( ".checkboxInput" ).attr( "name", "selectvalue" );
				 /*function validateForm()
					{ alert("byi"); 
						var z = document.forms["form1"]["priority"].value;
						if(!z.match(/^\d+/))
						{
						alert("Please only enter numeric characters only for your Age! (Allowed input:0-9)")
						};
					} */
			
			$("#contact_form").validate({
				
				rules: {
					probename:{
						required: true
						
					},
					browsee:{
						required: true,
											
						},
					priority:{
						required: true,
						 digits: true,
						 minlenght:2
					},
					devicevalue:{
						required: true
					}
				},
				messages: {
					probename: "Please enter the Probe Name",
					browsee: "Please browse python the files(ie .py)",
					priority: "Please enter Priority of file",
					devicevalue: "Please enter Priority of file"
					
				},
				tooltip_options: {
					 
					probename: {
						placement: 'top',
						html: true,
						trigger: 'focus'
					},
					browsee: {
						placement: 'top',
						html: true,
						trigger: 'focus'
					},
					priority: {
						placement: 'right',
						html: true,
						trigger: 'focus'
					},
					devicevalue: {
						placement: 'right',
						html: true,
						trigger: 'focus'
					}
				},
				submitHandler: function(form) {
					alert("hidden");
					
					
					 var strconfirm = confirm("Are you sure you want to Send?");
					if (strconfirm == true)
								{
									return true;
								}
								
								
				},
				 invalidHandler: function(form, validator) {
					$("#validity_label").html('<div class="alert alert-danger">There be ' + validator.numberOfInvalids() + ' error' + (validator.numberOfInvalids() > 1 ? 's' : '') + ' here.  OH NOES!!!!!</div>');
				}
	
			});
				
				  
				  });
				 
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
        	<li><a href="#"> User Name &nbsp;&nbsp;<img src="images/user-icon.png"/>  </a></li>
        	<li><a href="#">Logout &nbsp; &nbsp;<img src="images/log-out-icon.png"/></a></li>
      	</ul>
    </div>
 	</div>
</nav>
<div class="col-md-12 text-center header-heading">
  <h1 class="pad1">Probe Creation</h1>
</div> 
 
	<div id="menuleft">	
		<div class="list-group">
		  <h2  class="text-center">Dashboard</h2>
		  <a href="/ProbeOverAirWeb/dashboarddetails" class="list-group-item" target="_self" ><img src="images/Add-New-Test-Porbes.png"/>Home</a>
		  <a href="/ProbeOverAirWeb/createprobe" class="list-group-item active"><img src="images/add-new-test-porbes.png"/>Add New Test Probes</a>
		  <a href="#" class="list-group-item"><img src="images/device-inventory.png"/>Device Inventory</a>
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
		<form class="well form-horizontal" action="UploadServlet" method="post"  id="contact_form" name="form1" enctype="multipart/form-data">
			<div id="validity_label"></div>
			<div class="form-group" >
			  <label class="col-md-4 control-label">Probe Name</label>  
				<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-tags"></i></span>
			  <input name="probename"  class="form-control" type="text" id="probe-name">
				</div>
			  </div>
			</div>

			<!-- Text input-->
			 
			<div class="form-group" id="select-device">
			  <label class="col-md-4 control-label">Select Devices</label>  
				<div class="col-md-5 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-hdd"></i></span>	
         <span class="btn btn-default btn-default1 bor-rad">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="selectId1">
					Select <span class="caret">
				</a> 
				<ul id="selectId" class="dropdown-menu" multiple >
					<table class="table table-condensed">
						<thead>
						  <tr>
							<th>Device Name</th>
							<th>Location</th>
							<th>Status</th>
						  </tr>
						</thead>
						<%List<DalDeviceDetail> deviceList=(List<DalDeviceDetail>)request.getSession().getAttribute("deviceList"); 
						for(DalDeviceDetail device:deviceList){
						%>
						<tbody>
						  <tr>
							<td><input type="checkbox" value="<%=device.getDeviceName() %>" name="devicelist">&nbsp;<%=device.getDeviceName() %></td>
							<td><%=device.getLocation() %></td>
							<td><%=device.getDeviceStatus() %></td>
						  </tr>
						   
						</tbody>
						<%} %>
					  </table>
				</ul>
			
		 
		 </span>					
			
				<!-- <div ng-dropdown-multiselect options="example8data"  selected-model="example8model" checkboxes="true"  events="yourEvents" ></div> -->
				<!--   <div ng-dropdown-multiselect="" extra-settings="dropdownSetting" 
                     options="Categories" selected-model="CategoriesSelected" checkboxes="true"  events="yourEvents" ></div> -->
				<!-- ng-checked="selection.indexOf(example8data.id) > -1" ng-click="toggleSelection(example8data.id,example8data.label)" id="{{example8data.id}}" 
			  </div> -->
			  <!-- <small>{{one}}</small> -->
			 
				</div>
			  </div>
			</div>
			
			<div class="form-group" id="selected-device-table">
			  <label class="col-md-4 control-label">Selected Devices</label>  
				<div class="col-md-4 inputGroupContainer" ng-if="SubmittedCategories.length > 0">
				<table class="table table-condensed" frame="box" rules="all" > 
					<thead>
					   <tr>
                        <th>Sr. No</th>
                        <th>Name</th>
                        <th>status</th>
                    </tr>
					</thead>
					<tbody>	
					 <tr ng-repeat="cat in SubmittedCategories| orderBy:'id' ">
                        <td>{{$index+1}}</td>
                        <td>{{cat.name}}</td>
                        <td>{{cat.status}}</td>
                    </tr>
		
					<!-- <tr ng-repeat="name in selection">
						<td>{{name}}</td>
					</tr> -->
					<!-- <tr>
						<td>{{example1model|json}}</td>
					</tr>
					<tr>
						<td>{{one}}</td>
					</tr> -->
					<!--  <tr>
						<td>Raspberry Pi</td>
						<td>Pune</td>
						<td>Active</td>
						<td><span class="input-group-addon addon1"><i class="glyphicon glyphicon-remove"></i></span></td>
					 </tr>
					 <tr>
						<td>Intel DK 300</td>
						<td>Bangalore</td>
						<td>Active</td>
						<td><span class="input-group-addon addon1"><i class="glyphicon glyphicon-remove"></i></span></td>
					 </tr>
					 <tr>
						<td>Kapparock</td>
						<td>Pune</td>
						<td>Active</td>
						<td><span class="input-group-addon addon1"><i class="glyphicon glyphicon-remove"></i></span></td>
					 </tr>
					  <tr>
						<td>Intel DK 300</td>
						<td>Bangalore</td>
						<td>Active</td>
						<td><span class="input-group-addon addon1"><i class="glyphicon glyphicon-remove"></i></span></td>
					 </tr> -->
					 <!--<tr>
						<td>Raspberry Pi</td>
						<td>Pune</td>
						<td>Active</td>
						<td><span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span></td>
					 </tr>
					 <tr>
						<td>Alomond</td>
						<td>Bangalore</td>
						<td>Active</td>
						<td><span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span></td>
					 </tr> -->
					</tbody>	
				</table>	

			  </div>
			</div>
			
			<div class="form-group" id="browse-scripts">
			  <label class="col-md-4 control-label">Test Scripts</label>  
				<div class="col-md-3 inputGroupContainer">
				<div class="input-group" id="pyt">
					<span class="input-group-addon"><i class="glyphicon glyphicon-folder-open"></i></span>
					<span class="btn btn-default btn-default1 btn-file bor-rad">
						Browse <input type="file" name="browsee" multiple id="fileElementId" required>
					</span>
				 </div>
			  </div>
			  <div class="col-md-2"><button type="button" class="btn btn-primary btn-sm" ng-click="filedate()" id="showbut"><i class="glyphicon glyphicon-refresh"></i>&nbsp;&nbsp;ADD</button></div>
			</div>
			
			<div class="form-group"id="selected-browse-list">
			  <label class="col-md-4 control-label">Selected Test Script List</label>  
				<div class="col-md-4 inputGroupContainer">
					<table class="table table-condensed" frame="box" rules="all" > 
						<thead>
						  <tr>
							<th>Script File</th>
							<th>Priority</th>
							<th>Close</th>
						  </tr>	
						</thead>
				<tbody>	
				<tr ng-repeat="r in fileContent">				
					<td>{{r}}</td>
					<td>
					
						 <input type="number" class="form-control" name="priority" placeholder="Priority" ng-model="products" value="{{$index+1}}" required />
					
					 </td>
					<td><a href=""><span class="input-group-addon addon1" ng-click="removeItem1($index)"><i class="glyphicon glyphicon-remove"></i></span></a></td>
				</tr>	
				
					<!--
						 <tr>
							<td>ConformanceCoAP.py</td>
							<td><input type="text" class="form-control"/></td>
							<td><span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span></td>
						 </tr>
						 <tr>
							<td>Functional.py</td>
							<td><input type="text" class="form-control"/></td>
							<td><span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span></td>
						 </tr> 
						 <tr>
							<td>ConformanceCoAP.py</td>
							<td><input type="text" class="form-control"/></td>
							<td><span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span></td>
						 </tr> 
						 <tr>
							<td>ConformanceCoAP.py</td>
							<td><input type="text" class="form-control"/></td>
							<td><span class="input-group-addon"><i class="glyphicon glyphicon-remove"></i></span></td>
						 </tr>
						-->
						</tbody>	
					</table>	
			  </div>
			  
			</div>
			
			<span class="visitestee" >
				<span id="visit">
					
				</span>
			</span>
			
			<button type="button" class="btn btn-danger"><a href="device_avail.html" style="color:#fff;text-decoration:none;">Cancel</a></button>&nbsp;
			<!-- <button type="button" class="btn btn-primary" ng-click="save()">Save</button>&nbsp; -->
			<button type="submit" class="btn btn-success"  >Submit</button>&nbsp;
			
		</form>
	</div>
			
	<div class="col-md-2"></div>   
			  
</div>
    <footer class="footer">
      <div class="container">
        <p class="text-muted">Copyright<sup style="font-size:12px;">&#9400;</sup> | Accenture India</p>
      </div>
    </footer>

</body>
</html>
