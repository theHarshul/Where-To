$.get("http://ipinfo.io", function (response) {
    $("#zip").val(response.postal)
    $("#city").val(response.city)
    $("#state").val(response.region)
    $("#country").val(response.country)
}, "jsonp");

$(document).ready(function() {
	
	$("#btnPlan").click(function(){
		
		$("#link").text("Options");
		
		var budget = $('#budget').val();
        var duration = 12;
        
        var options = "http://www.kayak.com/explore/LAX/?budget=" + budget +"&duration=" + duration;
        $("#link").attr("href", options);
	});
	
});