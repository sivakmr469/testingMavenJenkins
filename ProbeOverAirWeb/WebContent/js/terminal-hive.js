$(document).ready(function(){	
	
	   var jsondata='';
	    
		 $.getJSON('js/command_restric.json', function (data) {
	    	 jsondata=data;
	    	 /*console.log('jsondata');
	 	    console.log(jsondata);*/
	 	  
	 	  });
	
var container_ = document.getElementById('container');
container_.insertAdjacentHTML('beforeEnd',
	      ['<output></output>',
	       '<div id="input-line" class="input-line">',
	       '<div class="prompt">$&gt;</div><div><input class="cmdline" autofocus /></div>',
	       '</div>'].join(''));

var cmdLine_ = container_.querySelector('#input-line .cmdline');
var output_ = container_.querySelector('output');
var interlace_ = document.querySelector('.interlace');

cmdLine_.addEventListener('keydown', processNewCommand_, false);
function processNewCommand_(e) {
	  if ( e.keyCode == 13 ) {
		     //console.log(event);
		     
		     //appendfun();
		  // Duplicate current input and append to output section.
		     var line = this.parentNode.parentNode.cloneNode(true);
		     line.removeAttribute('id')
		     line.classList.add('line');
		     var input = line.querySelector('input.cmdline');
		     input.autofocus = false;
		     input.readOnly = true;
		     output_.appendChild(line);
		     var args=[];
		     if (this.value && this.value.trim()) {
		          args = this.value.split(' ').filter(function(val, i) {
		           return val;
		         });
		        // var cmd = args[0].toLowerCase();
		        // args = args.splice(1); // Remove cmd from arg list.
		        
		       }
		 	  
		     var cmd = this.value; //.toLowerCase();
		    // testinput(cmd);
		     
		    
		    	 
		    	// var midstring;
				 if(cmd.includes('sudo') && args.length>1){
					 for(var i=0;i<jsondata.items.length;i++){
						 var chk = jsondata.items[i].command;						 
						 //var re = chk.indexOf(args[1]);
						 if(chk==args[1]){
							 output('<div id="result">You have entered a restricted command.</div>');
							 $('#container').closest("div").find('#result').attr("id", "resultold");
							 this.value = ''; 
							 return true;
						}
						
					 }
					}
					
				 else{
					 for(var i=0;i<jsondata.items.length;i++){
						 var chk = jsondata.items[i].command;						 
						// var re = chk.indexOf(args[0]);
						 if(chk==args[0]){
							 output('<div id="result">You have entered a restricted command.</div>');
							 $('#container').closest("div").find('#result').attr("id", "resultold");
							 this.value = ''; 
							 return true;
						}
					 }
				 }	
				 
			
		 	  switch (cmd) {
		 
		 	  default:
		           if (cmd) {
		             output('<div id="result"></div>');
					 this.value = '';
		             init(cmd);
		           }
				   
		       };
		     
		       this.value = ''; // Clear/setup line for next input. 
	  }
      function output(html) {
    	  //$(cmdLine_).before(html);
    	    output_.insertAdjacentHTML('beforeEnd', html);
    	    //output_.scrollIntoView();
    	    cmdLine_.scrollIntoView();
    	  }
  }

});

	window.dname="";
	window.msg="";
	window.output="";
	window.command="";
	window.error="";
	//var localdname = localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);
	//document.title= localdname;
	//document.getElementById("localdname").innerHTML= 'Deviece Name:';

	
function fune(cmd){

	 dname = localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);

	console.log(dname);
	 msg= cmd;//document.querySelector(".cmdline").value;
	//console.log(comm);
	//var msg=comm;//ps -ef
	output= dname+'/output';//"output";
	//console.log(output);
	//error=dname+'/error';
	command=dname+'/command';
	//console.log(command);
	//return msg;
	
   
}
//var countOfCmd = 0;

var wsbroker = "52.10.165.197";  //mqtt websocket enabled broker 52.10.165.197
var wsport = 18083 ;// port for above   8083 

// Create a client instance
client = new Paho.MQTT.Client(wsbroker, Number(wsport), "clientId");
 
// set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;
 


 
// called when the client connects
function onConnect() {
  // Once a connection has been made, make a subscription and send a message.
  var subscribeOptions = {
    qos: 0,  // QoS
   // invocationContext: {foo: true},  // Passed to success / failure callback
    //onSuccess: onSuccessCallback,
    //onFailure: onFailureCallback,
   // timeout: 10
};
  
  console.log("onConnect");
  client.subscribe(output, subscribeOptions);//"encyclopedia1"
  message = new Paho.MQTT.Message(msg);
  message.destinationName = command;//"Anunay";
  client.send(message);
}
 
// called when the client loses its connection
function onConnectionLost(responseObject) {
  if (responseObject.errorCode !== 0) {
    console.log("onConnectionLost:"+responseObject.errorMessage);
  }
}
 
// called when a message arrives
function onMessageArrived(message) {
  console.log("onMessageArrived:"+message.payloadString);
  //console.log("Message Arrived: " + message.payloadString);
  htmlstring = message.payloadString.replace(/(↵|↵↵|\|\r\n|\n|\r)/g, '<br/>');//replace(/↵/g, '<br/>');
  document.getElementById("result").innerHTML= htmlstring;
 $('#container').closest("div").find('#result').attr("id", "resultold");
  //$('#container').closest("#input-line").find('input').attr("id", "cmdlineold");       
 //output_.appendChild(line);
  client.disconnect();
}



 function init(cmd) {
	  fune(cmd);
	  // connect the client
	  client.connect({onSuccess:onConnect});
	  
      //client.connect(options);
      //fune();
      //output_ = container_.querySelector('output');
     }