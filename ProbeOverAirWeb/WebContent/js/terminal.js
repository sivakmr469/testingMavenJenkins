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
		     
		  // Duplicate current input and append to output section.
		     var line = this.parentNode.parentNode.cloneNode(true);
		     line.removeAttribute('id')
		     line.classList.add('line');
		     var input = line.querySelector('input.cmdline');
		     input.autofocus = false;
		     input.readOnly = true;
		     output_.appendChild(line);
		     /*var args=[];
		     if (this.value && this.value.trim()) {
		          args = this.value.split(' ').filter(function(val, i) {
		           return val;
		         });
		      
		       }*/
		 	  
		     var cmd = this.value; 
				 if(cmd.length>0){
					 for(var i=0;i<jsondata.items.length;i++){
						 var chk = jsondata.items[i].command;						 
						 //var re = chk.indexOf(args[1]);
						 if(cmd.includes(chk)){
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
	var localdname = localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);
	document.title= localdname;
	//document.getElementById("localdname").innerHTML= 'Deviece Name:';
	
function fune(cmd){

	 dname =localStorage.getItem('demoPrefix.localStoragedevicename').slice(1,-1);

	console.log(dname);
	 msg= cmd;//document.querySelector(".cmdline").value;
	//console.log(comm);
	//var msg=comm;//ps -ef
	 output= dname+'/output';//"output";
	console.log(output);
	error=dname+'/error';
	 command=dname+'/command';
	console.log(command);
	//return msg;
	
   
}
var countOfCmd = 0;

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
      htmlstring = message.payloadString.replace(/(↵|↵↵|\|\r\n|\n|\r)/g, '<br/>');//replace(/↵/g, '<br/>');
      document.getElementById("result").innerHTML= htmlstring;
     $('#container').closest("div").find('#result').attr("id", "resultold");
      //$('#container').closest("#input-line").find('input').attr("id", "cmdlineold");       
     //output_.appendChild(line);
   	  client.disconnect();
	  
     //console.log(htmlstring);
     // console.log(message.payloadString);
    };
    var options = {
      timeout: 3,
      onSuccess: function () {
        console.log("mqtt connected");
        // Connection succeeded; subscribe to our topic, you can add multile lines of these
        client.subscribe(output, {qos: 0});//device name/output
        client.subscribe(error, {qos: 0});//device name/error
    
        //use the below if you want to publish to a topic on connect
        message = new Paho.MQTT.Message(msg);
        message.destinationName = command;//device name/command
        client.send(message);
  
      },
      onFailure: function (message) {
        console.log("Connection failed: " + message.errorMessage);
      }
    };
 
  function init(cmd) {
	  fune(cmd);
      client.connect(options);
      //fune();
      //output_ = container_.querySelector('output');
     }