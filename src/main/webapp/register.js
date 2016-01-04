$(document).ready(function() {
	
    $("#btnCreateAccount").click(function(){
        var email = $('#email').val();
        var password = $('#password').val();
        
        var user = new Object();
        user.email = email;
        user.password = password;
        
        var jsonUser = JSON.stringify(user);
        
        
        $.ajax({
        	  url:"http://localhost:8080/WhereTo/webapi/user",
        	  type:"POST",
        	  data: jsonUser,
        	  contentType:"application/json; charset=utf-8",
        	  dataType:"json",
        	  success: function(){
        		  alert("success");
        	  }
        	});
       
        
    }); 
    
    $("#btnVerify").click(function(){
    	
    	var email = $('#email').val();
        var password = $('#password').val();
        
        $.ajax({
      	  url:"http://localhost:8080/WhereTo/webapi/user/authenticate?email=" + email +"&password=" +password,
      	  type:"GET",
      	  success: function(){
      		window.location.replace("PlanTrip.html");
      	  },
          error: function(){
        	 // alert("Invalid");
        	  $("#invalidLogin").text("Invalid!");
          }
      	});
    	
    
    }); 
    
   
    
    
});