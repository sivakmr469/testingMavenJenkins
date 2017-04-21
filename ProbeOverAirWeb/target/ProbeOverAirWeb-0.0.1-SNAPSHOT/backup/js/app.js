var app = angular.module('app1', ['ngTable','angularjs-dropdown-multiselect','ui.bootstrap']);
var app1 = angular.module('app', ['ngTable','LocalStorageModule']);

/* ------------  */
/* CONTROLLER-I */
/* ------------  */

app.controller('MainCtrl', ['$scope', '$http',function ($scope,$http) {
	//$scope.sortOption = "device_name";
	//$scope.sortType = "device_name";
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
  $scope.filedate = function(){
	           var filenamer = [];
				// alert("hi");
				    var inp = document.getElementById('fileElementId');
					for (var i = 0; i < inp.files.length; ++i) {
				    var name = inp.files.item(i).name;
					filenamer[i] = name;
				    //alert("here is a file name: " + name);
					//alert("filename"+filenamer);
				}
				//alert("filename"+filenamer[1]);
				//return(i);
				$scope.goloop(i,filenamer);
				
			}; 

  
  $scope.goloop = function(p,fl){
	  //alert("filename"+fl[1]);
  //for (var i = 0; i < p; ++i) {
   //$scope.addRow();//alert("hi");
   //alert("filename"+fl[i]);
   $scope.fileContent = fl;
	//}
};
  
/*   $scope.addRow = function() {
  $scope.rows.push({

				  });

				};
				
		$scope.fun=function(person)	{
			console.log(person);
		};	 */
								
 $scope.product= [{
    item_code: 1001,
    uom: "Nos.",
    product_mrp: 12,
    item_description: "CHOCO FILL 250GM"
  },{
    item_code: 1002,
    uom: "Nos.",
    product_mrp: 13,
    item_description: "CHOCO FILL 250GM"
  }]				
 $scope.removeItem1 = function(index) {
			$scope.fileContent.splice(index, 1);
			    }
  
  
 var req = $http({
    method : "GET",
    url : "data.json"
  }).then(function mySucces(response) {
      $scope.myWelcome = response.data;
	   }, function myError(response) {
      $scope.myWelcome = response.statusText;
  });
   $scope.changedValue1 = function(ro) {
	  
	//  alert(ro);
	 // alert(req);
  }
// alert(req);
  $scope.changedValue3 = function (selec) {
	 // alert(req);
                var diviceId = selec;	
				alert(diviceId);
                var diviceName = $.grep(req, function (selec) {
					alert(req+"j");
                    return divice.Id == diviceId;
                })[0].device_name;
                alert("Selected Value: " + diviceId + "\nSelected Text: " + diviceName);
            }
  
 /*  $scope.getProductDetails = function(row) {
        angular.forEach($scope.product, function(p) {
            if (p.item_code == row.item_code) {
                row.uom = p.uom;
                row.product_mrp = p.product_mrp;              
              
            }
        })
    } */
  
/* Another adding and deleting atom  */					
	
	 $scope.devicename = [
		{dname: 'Raspberry',value: 'device_name'}, 
		{dname: 'Raspberry-I',value: 'device_name'}, 
		{dname: 'Raspberry-II',value: 'device_name'}, 
		{dname: 'Raspberry-III',value: 'device_name'}, 
		{dname: 'Raspberry-IV',value: 'device_name'}, 
		{dname: 'Raspberry',value: 'device_name'}		
		]; 
		$scope.selectdname =  $scope.devicename[0];
		$scope.selectItems = [
		{
		text: 'Device Name',	
		value: 'device_name'		
		}, 
		{   text: 'Status',
			value: 'status'			
		},
		{   text: 'Location',
			value: 'location'		
		}
		];   
	// $scope.selectview =  $scope.selectItems[0];
	// $scope.selectview1 =  true;
	 //$scope.whatt = 'Device Name';
   $scope.reverse = false;
	$scope.workorder = {
	        items: [{
	        	probename: 'item',
	            select_device: 'yes',
	            priority:1,
	            //file_url:""
				selectdnamer:[{dname: 'Raspberry'},
				{dname: 'Raspberry-I'},
				{dname: 'Raspberry-II'},
				{dname: 'Raspberry-III'},
				{dname: 'Raspberry-IV'}]
				
	            }]
	    };
	    $scope.addItem = function() {
	        $scope.workorder.items.push({
	        	probename: '',
	            select_device: 'yes',
	            priority:'',
				selectdnamer:[{dname: ''}]
				
	            //file_url:""
	        });
	    },
	    $scope.removeItem = function(index) {
			$scope.workorder.items.splice(index, 1);

			    }

	
	$scope.example8model = [];
	$scope.example8data = [
		{id: 1, label: "Raspberry Pi"},
		{id: 2, label: "Kapparock"},
		{id: 3, label: "Intel DK 300"},
		{id: 4, label: "Alomond"}
		];

		/* $scope.selection=[];
		// toggle selection for a given employee by name
		$scope.toggleSelection = function toggleSelection(example8dataId,example8dataLabel) {
	    var idx = $scope.selection.indexOf(example8dataId);
          alert(example8dataId);
          alert(idx);
	    // is currently selected
	    if (idx > -1) {
	      $scope.selection.splice(idx, 1); alert("slide");
	    }

	    // is newly selected
	    else {
	      $scope.selection.push(example8dataId,example8dataLabel);
	    }
	  }; */
	
}]);

/* ------------  */
/* CONTROLLER-II */
/* ------------  */

app1.config(function(localStorageServiceProvider){
  localStorageServiceProvider.setPrefix('demoPrefix');
  // localStorageServiceProvider.setStorageCookieDomain('example.com');
  // localStorageServiceProvider.setStorageType('sessionStorage');
})
app1.controller('MainCtrl-1', ['$scope', '$http','ngTableParams','localStorageService',  function ($scope, $http,ngTableParams,localStorageService) {
	$scope.fun=function(person)	{
			
			$scope.probeclickname = person.probe_name;						
	
	console.log($scope.probeclickname);
	};
	console.log("hi"+$scope.probeclickname);
	
	$scope.localStorageDemo = $scope.probeclickname;//localStorageService.get('person.probe_name');
	
    $scope.$watch('localStorageDemo', function(value){
      localStorageService.set('localStorageDemo',value);
      $scope.localStorageDemoValue = localStorageService.get('localStorageDemo');
    });

    $scope.storageType = 'Local storage';

    if (localStorageService.getStorageType().indexOf('session') >= 0) {
      $scope.storageType = 'Session storage';
    }

    if (!localStorageService.isSupported) {
      $scope.storageType = 'Cookie';
    }

    $scope.$watch(function(){
      return localStorageService.get('localStorageDemo');
    }, function(value){
      $scope.localStorageDemo = value;
    });
	
	/* $scope.save = function() {
                    $localStorage.message = "Hello World";
                }
 
                $scope.load = function() {
                    $scope.data = $localStorage.message;
					console.log($scope.data);
                } */
	 		
    //Table configuration
	 var tableData = [];	
    $scope.tableParams = new ngTableParams({
        page: 1,
        count: 6
    },{
        total:tableData.length,
      
        getData : function($defer,params){
            $http.get('dashboard.json').then(function(response) {
                tableData = response.data.testing_dash;
				
                $defer.resolve(tableData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                params.total(tableData.length);
            });
        }
    });
	
	var tableData2 = [];	
	 //Dashboard Table configuration
    $scope.tableParams2 = new ngTableParams({
        page: 1,
        count: 6
    },{
        total:tableData2.length,
        //Returns the data for rendering
        getData : function($defer,params){
            $http.get('dashboarddetail.json').then(function(response) {
                tableData2 = response.data.testing_detail;
				//alert(tableData2);
				//$scope.selec = response.data.testing_dash;
                $defer.resolve(tableData2.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                params.total(tableData2.length);
            });
        }
    });


	//Dashboard script Table configuration
	var tableData3 = [];	
    $scope.tableParams3 = new ngTableParams({
        page: 1,
        count: 6
    },{
        total:tableData3.length,
        //Returns the data for rendering
        getData : function($defer,params){
            $http.get('dashboardscript.json').then(function(response) {
                tableData3 = response.data.testing_script;
				//alert(tableData3);
				//$scope.selec = response.data.testing_dash;
                $defer.resolve(tableData3.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                params.total(tableData3.length);
            });
        }
    });
	
}]);

/* Xml code*/




/* 
function WriteTest()
        {
            try
            {
                var XML=new XMLWriter();
                XML.BeginNode("Example");
                XML.Attrib("SomeAttribute", "And Some Value");
                XML.Attrib("AnotherAttrib", "...");
                XML.WriteString("This is an example of the JS XML WriteString method.");
                XML.Node("Name", "Value");
                XML.BeginNode("SubNode");
                XML.BeginNode("SubNode2");
                XML.EndNode();
                XML.BeginNode("SubNode3");
                XML.WriteString("Blah blah.");
                XML.EndNode();
                XML.Close(); // Takes care of unended tags.
                // The replace in the following line are only for making the XML look prettier in the textarea.
                document.getElementById("ExampleOutput").value=XML.ToString().replace(/</g,"\n<");
            }
            catch(Err)
            {
                alert("Error: " + Err.description);
            }
            return false;
        }

        function WriteForm(e)
        {
            try
            {
                var Frm=Settings.SrcElement(e);
                var XML=new XMLWriter();
                XML.BeginNode(Frm.name);
                XML.Attrib("Example", "Attribute & Value");
                var Nodes=Frm.elements;
                for (var i=0;i<Nodes.length;i++)
                    XML.Node(Nodes[i].name, Nodes[i].value);
                XML.EndNode();
                XML.Close();
				alert( XML);
                document.getElementById("ExampleOutput").value=XML.ToString().replace(/</g,"\n<");
            }
            catch(Err)
            {
                alert("Error: " + Err.description);
            }
            return false;
        } */