var app=angular.module('om2mInterface', []);

app.directive("fileread", [function () {
    return {
        scope: false,
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                scope.$apply(function () {
				console.log(changeEvent.target.files[0]);	
				var reader = new FileReader();
				reader.onload = function (e) {
				scope.fileBytes = new Int8Array(e.target.result);
				console.log(scope.fileBytes);
    };
    reader.onerror = function (e) {
        console.error(e);
    };
    reader.readAsArrayBuffer(changeEvent.target.files[0]);
					
					
				
				 
                });
            });
        }
    }
}]);

app.controller('om2mController', function($scope, $http, $window){
	
	
	var req = {
 method: 'GET',
 data: '',
 url: 'http://localhost:8080/ProbeOverAirWeb/rest/workorder/retrieve/details',
 headers: {
   'Content-Type': 'application/json'
 }
 
}

$http(req).then(function(response){
	
	$scope.status=response.status;
    $scope.data = response.data.workorderList;
	$scope.statusArr=["null","Pending", "Inprogress", "Completed"];
	
});

$scope.uploadWorkorder=function(clickEvent){
	console.log($scope.devicesTowo);
	if($scope.fileBytes){
	 var dataObj={"fileName": "TestSuit.zip", "description": $scope.woDescription, "name":$scope.woName, "deviceList": [{'deviceName':$scope.devicesTowo}],"fileByte":Array.prototype.slice.call($scope.fileBytes)};
	 	var req = {
 method: 'POST',
 data: dataObj,
 url: 'http://localhost:8080/ProbeOverAirWeb/rest/workorder/create',
 headers: {
   'Content-Type': 'application/json'
 }
 
}


$http(req).then(function(response){
	
	$scope.status=response.status;
	if($scope.status==200){
		alert("Successfully uploaded");
	}
});
	}
	else{
		alert("Please select a file to upload");
	}
	
		
}



	
	
});