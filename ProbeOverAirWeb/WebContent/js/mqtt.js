//sample HTML/JS script that will publish/subscribe to topics in the Google Chrome Console
    //by Matthew Bordignon @bordignon on twitter.
//function fune(){
//	alert("hi");
//	var dname = localStorage.getItem("demoPrefix.localStoragedevicename");
//	alert(dname);
//	var comm=document.querySelector(".cmdline").value;
//	console.log(comm);
//}   //var comm=document.getElementByName("getvalue");
//	var dname = localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);
//
//	console.log(dname);
//	var comm=document.querySelector(".cmdline").value;
//	console.log(comm);
//	var msg=comm;//ps -ef
//	var output= dname+'/output';//"output";
//	console.log(output);
//	// var error="error";
//	var command=dname+'/command';
//	console.log(command);
	window.dname="";
	window.msg="";
	window.output="";
	window.command="";
	window.error="";
	var localdname = localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);
	document.title= localdname;
	//document.getElementById("locald").innerHTML= 'Deviece Name:';
	
	$(document).ready(function(){
		$("#locald").append("Device Name:"+localdname);
	});
function fune(){

	 dname = localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);

	console.log(dname);
	 msg=document.querySelector(".cmdline").value;
	//console.log(comm);
	//var msg=comm;//ps -ef
	 output= dname+'/output';//"output";
	console.log(output);
	error=dname+'/error';
	 command=dname+'/command';
	console.log(command);
	//return msg;
	
   
}


var wsbroker = "52.10.165.197";  //mqtt websocket enabled broker
var wsport = 8083 // port for above    
var client = new Paho.MQTT.Client(wsbroker, wsport,
        "myclientid_" + parseInt(Math.random() * 100, 10));
    client.onConnectionLost = function (responseObject) {
      console.log("connection lost: " + responseObject.errorMessage);
    };
    client.onMessageArrived = function (message) {
      console.log(message.destinationName, ' -- ', message.payloadString);
     // document.getElementById("output").innerHTML= message.payloadString;
      htmlstring = message.payloadString.replace(/(\r\n|\n|\r)/gm, "<br>");
      document.getElementById("output").innerHTML= htmlstring;
      client.disconnect();
     //console.log(htmlstring);
     // console.log(message.payloadString);
    };
    var options = {
      timeout: 3,
      onSuccess: function () {
        console.log("mqtt connected");
        // Connection succeeded; subscribe to our topic, you can add multile lines of these
        client.subscribe(output, {qos: 1});//device name/output
        client.subscribe(error, {qos: 1});//device name/error
    
        //use the below if you want to publish to a topic on connect
        message = new Paho.MQTT.Message(msg);
        message.destinationName = command;//device name/command
        client.send(message);
  
      },
      onFailure: function (message) {
        console.log("Connection failed: " + message.errorMessage);
      }
    };
 
  function init() {
	  fune();
      client.connect(options);
      //fune();
      
  }