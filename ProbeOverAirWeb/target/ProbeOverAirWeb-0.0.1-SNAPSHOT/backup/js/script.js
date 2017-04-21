$(document).ready(function () {
	$('#select-device').hide();
	$('#selected-device-table').hide();
	$('#browse-scripts').hide();
	$('#selected-browse-list').hide();
	$('#showbut').hide();
	
	$('#probe-name').keypress(function () {
		$('#select-device').fadeIn(2000).show();
		//$('#select-device').css('z-index', '1000');
	})
	
	
	//Hide the login portal once,the mouse is out of the login portal.
	/* $('#select-device').keydown(function(){
		$(this).hide();
	}); */
	
	
	$('#select-device').click(function () {
		$('#selected-device-table').fadeIn(2000).show();
		$('#browse-scripts').fadeIn(2000).show();
		//$('#selected-device-table').css('z-index', '1000');
	});
	
	$('#browse-scripts').click(function () {
		
		$('#showbut').fadeIn(3000).show();
		//$('#selected-device-table').css('z-index', '1000');
	});
	$('#showbut').click(function () {
		$('#selected-browse-list').fadeIn(2000).show();
	});
	
});