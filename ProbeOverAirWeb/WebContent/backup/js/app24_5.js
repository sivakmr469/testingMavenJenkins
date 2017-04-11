var app = angular.module('app', ['ngTable']);
app.controller('MainCtrl', ['$scope', '$http','ngTableParams' ,
    function ($scope, $http, ngTableParams) {
	//$scope.sortOption = "device_name";
	$scope.sortType = "device_name";
	$scope.selectdname = "Raspberry";
	//$scope.selectview = "device_name";
	
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
    var tableData = [];$scope.reverse = false;
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
    //Table configuration
    $scope.tableParams = new ngTableParams({
        page: 1,
        count: 6
    },{
        total:tableData.length,
        //Returns the data for rendering
        getData : function($defer,params){
            $http.get('data.json').then(function(response) {
                tableData = response.data.person;
                $defer.resolve(tableData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                params.total(tableData.length);
            });
        }
    });
}]);

/*app.controller('probeOverAirController',['$scope',function($scope){ l
  alert("hi");
  $scope.workorder = {
	        items: [{
	           
	        	probename: 'item',
	            execution: 'yes',
	            priority:1,
	            //file_url:""
	            }]
	    };
	    $scope.addItem = function() {alert("hi");
	        $scope.workorder.items.push({
	        	probename: '',
	            execution: '',
	            priority:''
	            //file_url:""
	        });
	    },
	    $scope.removeItem = function(index) {
			alert("hi");
	        $scope.workorder.items.splice(index, 1);
	    }

}]);*/