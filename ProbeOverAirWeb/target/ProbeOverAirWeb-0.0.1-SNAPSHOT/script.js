$(document).ready(function () {
	
	   
	$("#trig_file").bind("click", function (e) {
	      
	  	console.log("bind");
	  	 $("input[type='file']").click();
	  	//$("input[type='file']").off(e);
//	  	 e.stopImmediatePropagation() ;
	  });
	    
//	 $('#myId').on("click",function(){
//	 	   // console.log('click');
//	 	 //   $('#fileElementId').trigger('click');
//	$("input[type='file']").click();
//	 	});
//	var myEl = document.getElementById('trig_file');
//	
//		  myEl.addEventListener('click', function() {
//		      console.log('Hello world');
//		  $("input[type='file']").click();
//		  }, false);
	
	$('#select-device').hide();
	$('#selected-device-table').hide();
	$('.browse-scripts').hide();
	$('#selected-browse-list').hide();
	$('#showbut').hide();
	//$('.visitestee').css("visibility","hidden");
	//$('#fileElementId').trigger('click');		
	$('#probe-name').on("click",function () {
		$('#select-device').fadeIn(1000).show();
		//$('#select-device').css('z-index', '1000');
	});
	
	$('#select-device').on("click",function () {
		$('#selected-device-table').fadeIn(2000).show();
		$('.browse-scripts').fadeIn(2000).show();
		//$('#selected-device-table').css('z-index', '1000');
	});
	
	/*$('#browse-scripts').on("click",function () {
		$('#showbut').fadeIn(3000).show();
		//$('#selected-device-table').css('z-index', '1000');
	});*/
	$('#showbut').on("click",function () {
		//$('#selected-browse-list').fadeIn(2000).show();
		$('#visit').hide();
	});
	$('#selectId1').on("click",function(){       
        $('#selectId').slideToggle(function(){
        	$(this).toggleClass("abc");
        	
        });
    });
	$('#selectId').on("click",function(){
               	$(this).show();
    });
	
	$(document).on('click', function (e) {
	    if ($(e.target).closest("#selectId").length === 0) {
	        $("#selectId").slideUp();
	    }
	    //console.log(e.target);
	});
	
	//$('#selectId').hide();
    
	
});

