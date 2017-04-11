var app = angular.module('app1', ['ngTable','angularjs-dropdown-multiselect','ui.bootstrap']);
var app1 = angular.module('app', ['ngTable','LocalStorageModule']);

/* ------------  */
/* CONTROLLER-I */
/* ------------  */

app.controller('MainCtrl', ['$scope', '$http','$window',function ($scope,$http,$window) {

	$scope.onloadFun = function(e) {
		//console.log("validateUser");
		 // $window.location.href = "/webprobelatest/login.html";
		 var request = {
	             method: 'GET',
	             url: '/ProbeOverAirWeb/rest/workorder/validateUser',
	            headers: {
					'Content-Type': 'application/json',
	            	'Access-Control-Allow-Origin': '*'
	             }
	         };

	         // SEND THE FILES.
	        $http(request)
	             .success(function (d) {
					 if(d==false){
						$window.location.href = "/ProbeOverAirWeb/login.html";
					 }
					 else{
						 console.log("your in window");;
						 }
	                // console.log("successfully uploaded files");
	             })
	             .error(function () {
	             });
   }
	$scope.selectdname = "Raspberry";
	$scope.selectview = "device_name";
				
	/* Another adding and deleting atom  */	
 $scope.save = function(){
	alert("Successfully Saved !!!"); 	 
 };
 $scope.submit = function(){
		alert("Successfully Submitted to Server !!!"); 	 	 
 }
	
  $scope.rows = [{}];
  $scope.nrows = [];	
  $('.pnm').keypress(function(e) {
	  if (e.which < 48 || 
			    (e.which > 57 && e.which < 65) || 
			    (e.which > 90 && e.which < 97) ||
			    e.which > 122) return false;
	});
  $scope.probenamefun = function(){
	 //if(!$scope.probename.length)
	 $scope.prt=false;
	 $scope.probeshow=false;
	 $('#probe-name').removeClass('borerror');$('#probe-name1').removeClass('borerror');
	// $('#probe-name').tooltip('destroy');
	
  }
  
  $scope.brow=[];
  $scope.filesize=0;
  $scope.totalfilesize=0;
  $('#fileElementId').change(function() {
	//  var filesArr=[];elems
			var inp = document.getElementById('fileElementId');
			$scope.extes=inp;
			
			for(var i=0;i<inp.files.length;i++){
				var fsize =inp.files[i].size;
				$scope.filesize=$scope.filesize+fsize;
			}
			
			 
			if(inp.files.length!==0 && $scope.filesize<5242880){
				
				$scope.browsefile=false;
				$('#brow').removeClass('borerror');$('#brow1').removeClass('borerror');
				var b=0;var firsttime=true;
				for(var i=0;i<inp.files.length;i++)		{	
					var ext = this.files.item(i).name.match(/\.(.+)$/);
					var  fsize=inp.files[i].size;
					if(ext!=null){ext=ext[1];}
					switch (ext) {
						case 'sh':
						case 'py':       
							$scope.browse=false;  
							$scope.flag=false;
							$scope.totalfilesize=$scope.totalfilesize+fsize;
							break;
						default:
							
							//this.value = '';
							$scope.browse=true;
							$scope.browsefile=true;
							$scope.flag=true;
							$scope.brow[b]=this.files.item(i).name;
							//$scope.brow =$scope.brow.substring(1,-1);
							b++;
							$('#brow').addClass('borerror');
							$('#brow1').addClass('borerror');
							/*if(firsttime){
								//alert('This is not an allowed file type. It should be .py or .sh');
								firsttime=false;
								$('#brow').tooltip({title: "This is not an allowed file type. It should be .py or .sh only", placement: "top",trigger:"click",offset:'0 0'})
																
							}*/
							break;
					}
				}
				setTimeout(Deletefile, 2000);
			function Deletefile() {
			if($scope.brow.length!=0){
					   alert('Please Delete\n'+ $scope.brow.join(',\n'));	
					   $scope.browse=true;
						$scope.browsefile=true;
						console.log("Jio");
			}};

					
				//$scope.checkfiletype(inp,this);
				$scope.onuploadfiles(inp);
				$scope.prt=false;
				$scope.flag=false;
				
		$scope.filedate();
		
  }

else{
		
		$scope.browsefile=true;
		$('#brow').addClass('borerror');$('#brow1').addClass('borerror');
		inp.value='';
		$scope.filesize=0;
		$scope.$apply();
	}
			
			$('#selected-browse-list').fadeIn(2000).show();	
			$('.numr').keypress(function(key) {
				if(key.charCode < 48 || key.charCode > 57) return false;
			});
  
	    });
  
  $scope.filenamer = [];
 // $scope.mainfilesize=[];
  $scope.filedate = function(){
	  
				    var inp = document.getElementById('fileElementId');
				    var vlen = $scope.filenamer.length; 
				    //alert(vlen);
				    		    
				    if (vlen==0) {
				    // alert("file equal to 0 ");
				    //	$scope.fileconteners = inp.files;
				    	
					for (var i = 0; i < inp.files.length; ++i) {
						
				    var name = inp.files.item(i).name;
				    $scope.filenamer[i] = name;
				    //$scope.mainfilesize[i] = inp.files[i].size;
				   // alert("here is a file name: " + name);
					
				}
			}
					
				if (vlen>0) {//alert("file greater 0 ");
						for (var i = 0; i<inp.files.length; ++i) {
							//$scope.fileconteners[vlen]=inp.files[i];
						    var name = inp.files.item(i).name;
						    $scope.filenamer[vlen] = name;	
						    //$scope.mainfilesize[vlen] = inp.files[i].size;
						     ++vlen;
						}						
					}
				
				 $scope.goloop(i,$scope.filenamer);
				$scope.visit = "visible";
				
			}; 

  
  $scope.goloop = function(p,fl){

   $scope.fileContent = fl;
   $scope.fileContentflag=true;
      $scope.$apply();
	
};


/* for cheking the file type */

/*$scope.checkfiletype = function(inp,this){
	
	

};*/
/*End for cheking the file type */
		
 $scope.removeItem1 = function(index,filename) {
	 		
	 		
			$scope.fileContent.splice(index, 1);
			var filepathe = document.getElementById('fileElementId');
			if($scope.fileContent.length==0){
				filepathe.value = "";
			}
			$scope.priorty_valold.splice(index, 1);
			if($scope.priorty_valold.length>index){
				for(var i=index;i<$scope.priorty_valold.length;i++){
					$scope.priorty_valold[i]=$scope.priorty_valold[i]-1;
				}
			}
			
			if($scope.brow.length!=0){
				for(var j=0;j<$scope.brow.length;j++){
					if($scope.brow[j]==filename){
						$scope.brow.splice(j, 1);
					}
				}
			}
			if($scope.brow.length==0){$scope.browse=false;$scope.browsefile=false;$('#brow').removeClass('borerror');$('#brow1').removeClass('borerror');$('#submitty').attr("disabled", false);}
			/* Remove size */
//			var remsize=$scope.mainfilesize[index];
//			$scope.totalfilesize=$scope.totalfilesize-remsize;
//			$scope.mainfilesize.splice(index, 1);
			/* end Remove size */
			
			
			var request = {
		             method: 'POST',
		             url: '/ProbeOverAirWeb/FirstServlet?remove=true&filename='+filename,		             
		             headers: {
		                 'Content-Type': undefined
		             }
		         };

		         // SEND THE FILES.
		         $http(request)
		             .success(function (d) {
		                 console.log("sucessfully removed");
		             })
		             .error(function () {
		             });
			
			
			    }
  
 /* start upload files   */
 
 $scope.onuploadfiles=function(inp){
	 $scope.filecontener=[];
	 for (var i = 0; i < inp.files.length; i++) {
	        $scope.filecontener.push(inp.files[i]);
	    }	
		var fdata = new FormData();
		 for (var i in $scope.filecontener) {
			 fdata.append("uploadfile", $scope.filecontener[i]);
	     }
		 var request = {
	             method: 'POST',
	             url: '/ProbeOverAirWeb/FirstServlet?upload=true',
	             data: fdata,
	             headers: {
	                 'Content-Type': undefined
	             }
	         };

	         // SEND THE FILES.
	         $http(request)
	             .success(function (d) {
	                 console.log("successfully uploaded files");
	             })
	             .error(function () {
	             });
	         
 }
 
 /* end upload files   */
 
	/*    start check priority    */ 
 
     $scope.priorty_val = [];
     $scope.priorty_valold = [];
   
    $scope.priortyy = function(key){
	   $scope.priorty_val[key]= key+1;
    }
   $scope.priorty_valold = $scope.priorty_val; 
    $scope.prior = function(prt,p){
	    $scope.priorty_valold[p-1]=prt[p-1]; 
	    $scope.flag=false;  
	    $scope.prt=false;  
   }; 
  $scope.priorty_val=$scope.priorty_valold; 
  $scope.ckpriorty = [];
  $scope.fileContentflag=false;
  $scope.checkpriorty = function(e){
	 
	   var prtl = $scope.priorty_valold.length;
	   if($scope.probename==undefined||$scope.probename.length==0){$scope.prt=true;$scope.probeshow=true;$('#probe-name').addClass('borerror');$('#probe-name1').addClass('borerror');}//$('#probe-name').tooltip({title: "Please Select Probe Name !!", placement: "right",trigger:"manuals"}).tooltip('show');
	   if($scope.checkcount==undefined||$scope.checkcount==0){$scope.prt=true;$scope.devicename=true;$('#brosebor').addClass('borerror');$('#brosebor1').addClass('borerror');}
	   if($scope.fileContentflag==false){$scope.browsefile=true;$scope.flag=true;$('#brow').addClass('borerror');$('#brow1').addClass('borerror');}
	   else if($scope.fileContent.length==0||$scope.fileContent==undefined){$scope.prt=true;$scope.browsefile=true;$('#brow').addClass('borerror');$('#brow1').addClass('borerror');} 
	   for (var i = 0; i<prtl; ++i) {
	   for (var j = i+1; j<prtl; ++j) {
			  if($scope.priorty_valold[i]==$scope.priorty_valold[j]) 
			  { $scope.flag=true;
			  alert("Please check Priority!! Should not be same");$scope.prt=true;e.preventDefault();}
		  }
	  
	  }
	   
//	   $scope.prt=false;
//	   $scope.flag=false;
//	   console.log($scope.prt);
//	   console.log($scope.browsefile);
//	   console.log($scope.flag);
	   if($scope.totalfilesize<5242880){
		   $scope.browsefile==true;
	   }
	   if($scope.prt==true || $scope.flag==true || $scope.browsefile==true)
	   { $scope.flag==true;
	   	$('#submitty').attr("disabled", true);
	   }
	   
	   
	   if($scope.prt==false && $scope.flag==false && $scope.browsefile==false)
		   {$( "#contact_form" ).submit();}
	   
    } 
  
  /*  end check priority      */
	
   $scope.reverse = false;
	  //define object 
    $scope.CategoriesSelected = [];
    $scope.Categories = [];
    $scope.detail = [];
	$scope.dropdownSetting = {
        scrollable: true,
        scrollableHeight : '200px'
    }
	
  //Table configuration
  $scope.dropdowns = [];
	   var httpRequest = $http({
            method: 'GET',
            url: '/ProbeOverAirWeb/rest/device/retriveDeviceList'          
        }).success(function(data, status) {
             $scope.dropdowns = data.deviceList;  
		//console.log(data);			 
        }).
        error(function (data) {
           console.log("Unsuccessfull get");
       });

$scope.SubmittedCategories =[];
$scope.ival=0;
$scope.checkcount=0;
    $scope.funer = function(dname,bok){
    	$scope.devicename=false;
		$('#brosebor').removeClass('borerror');$('#brosebor1').removeClass('borerror');
    	if (bok==true) {//alert("true");
    		$scope.checkcount=$scope.checkcount+1;
    		console.log($scope.checkcount);
    		$scope.prt=false;    		
            $scope.CategoriesSelected[$scope.ival] = dname; 
            $scope.SubmittedCategories =  $scope.CategoriesSelected; 
            $scope.ival++; 
            
            } 
                            
            if (bok==false||bok===undefined) { 
            //alert("false"); 
            	$scope.checkcount=$scope.checkcount-1;
            	console.log($scope.checkcount);
	            var catlen =$scope.CategoriesSelected.length; 
	            for(var i=0;i<catlen;i++){ 
	                    if( dname.deviceId==$scope.CategoriesSelected[i].deviceId){ 
					            $scope.CategoriesSelected[i] =[]; 
					            $scope.SubmittedCategories =  $scope.CategoriesSelected; 
	                    } 
	            } 
            }

	}
    
    /* Login */
	$scope.uspascheck = function(username,userpassword){
		
	 var request = {
	             method: 'GET',
	             url: '/ProbeOverAirWeb/rest/workorder/login/'+username+'/'+userpassword,
				 headers: {
					'Content-Type': 'application/json',
					'Access-Control-Allow-Origin': '*'
	             }
	         };

	         // SEND THE FILES.
	         $http(request)
	             .success(function (d) {
					 if(d==true){
						$window.location.href = "/ProbeOverAirWeb/device_avail.html";
					 }
					 else{
						 alert("You have entered wrong username and password");
					 }
	                 //console.log("successfully uploaded files");
	             })
	             .error(function () {
	             });
	}
	/* end Login */

    
    
    
}]);

/* ------------  */
/* CONTROLLER-II */
/* ------------  */

app1.config(function(localStorageServiceProvider){
  localStorageServiceProvider.setPrefix('demoPrefix');

})
app1.controller('MainCtrl-1', ['$scope', '$http','ngTableParams','localStorageService','$rootScope','$filter','$window', function ($scope, $http,ngTableParams,localStorageService,$rootScope,$filter,$window) {
	
	$scope.onloadFun = function(e) {
		
		 // $window.location.href = "/webprobelatest/login.html";
		 var request = {
	             method: 'GET',
	             url: '/ProbeOverAirWeb/rest/workorder/validateUser',
	            headers: {
					'Content-Type': 'application/json',
	            	'Access-Control-Allow-Origin': '*'
	             }
	         };

	         // SEND THE FILES.
	         $http(request)
	             .success(function (d) {
					 if(d==false){
						$window.location.href = "/ProbeOverAirWeb/login.html";
					 }
					 else{
						 console.log("your in window");
						 }
	                 //console.log("successfully uploaded files");
	             })
	             .error(function () {
	             });
		
   }
	
	$scope.fun=function(workorder)	{
			
	//localStorageService.set('localStorageDemo',person.probe_name);
	localStorageService.set('localStorageDemo',workorder.workorderName);
	localStorageService.set('localStorageworkid',workorder.workorderId);
     
	};
	
	$scope.fun1=function(devname)	{
		
		//localStorageService.set('localStorageDemo',person.probe_name);
		localStorageService.set('localStoragedevicename',devname);
	     
		};
	
	$scope.initThisPage = function (){
		$scope.localStorageDemoValue = localStorageService.get('localStorageDemo');
		$scope.localStorageDemoworkid = localStorageService.get('localStorageworkid');
		
		//console.log($scope.localStorageDemoworkid);		
		 var params1 = {"workorderID":$scope.localStorageDemoworkid};	    
		    $http({
		        url: '/ProbeOverAirWeb/rest/workorder/retrieve/devicedetails',
		        dataType: 'json',
		        method: 'POST',
		        data: params1,
		        headers: {
		            "Content-Type": "application/json"
		        }
		    }).success(function(response){
		        $scope.resp2 = response.deviceDetailsList;
		        $scope.respdevi=response.deviceDetailsList.deviceName;
		        //console.log("Alert");
		       //console.log($scope.resp2);
		    }).error(function(error){
		        $scope.error = error;
		    });
		    
		    //var params1 = {"workorderID":$scope.localStorageDemoworkid};	    
		    $http({
		        url: '/ProbeOverAirWeb/rest/workorder/retrievescriptlist',
		        dataType: 'json',
		        method: 'POST',
		        data: params1,
		        headers: {
		            "Content-Type": "application/json"
		        }
		    }).success(function(response){
		        $scope.scptlist = response.scriptList;		       
		    }).error(function(error){
		        $scope.error = error;
		    });
			
		    
	};
	 		
    //dashboard page Table configuration
	 $scope.filter = {
		        workorderName: undefined,
				uploadedBy:undefined,
				uploadTime:undefined
		        //failed: undefined
		    };
			 var tableData = [];	
		    $scope.tableParams = new ngTableParams(
			{
		        page: 1,
		        count: 6,
				 filter: $scope.filter,
				 sorting: {
		        uploadTime: 'desc'
		    }
		    },{
				debugMode: true,
		        total:tableData.length,
		      
		        getData : function($defer,params){
		            $http.get('/ProbeOverAirWeb/rest/workorder/retrieveworkorderlist').then(function(response) {
		                tableData = response.data.retrieveworkorderList;
						 var orderedData = params.sorting() ? $filter('orderBy')(tableData, params.orderBy()) : data;
						orderedData	= $filter('filter')(orderedData, params.filter());
						params.total(orderedData.length);
						$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));						
						
		            });
		        }
		    }
			);  
			
	//Device Inventory Table configuration
	var tableData4 = [];	
    $scope.tableDInventory = new ngTableParams({
        page: 1,
        count: 6
    },{
        total:tableData4.length,
        //Returns the data for rendering
        getData : function($defer,params){
            $http.get('/ProbeOverAirWeb/rest/device/retriveDeviceList').then(function(response) {
                tableData4 = response.data.deviceList;
				//console.log(tableData4);
				//alert(tableData3);
				//$scope.selec = response.data.testing_dash;
                $defer.resolve(tableData4.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                params.total(tableData4.length);
            });
        }
    });
}]);
	