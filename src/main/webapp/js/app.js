 
$(function() {

	// BROWSER ID -------------------------------------------------------
	
	function login() {
	   navigator.id.get(function(assertion) {
		    if (assertion) {
		        // This code will be invoked once the user has successfully
		        // selected an email address they control to sign in with.
		    	console.log(assertion);
		    	
		    	 $.ajax({
		    	      "url": "/api/v1/login/"+assertion, 
		    	      "type": "GET",
		    	      "contentType": "application/json; charset=utf-8",
		    	      "processData": false,
		    	      "dataType": "json",
		    	      "success": function(data, status, jqXhr) {
		    	        // do nothing
		    	      }
		    	    }); 
		    	
		    	
		    } else {
		        // something went wrong!  the user isn't logged in.
		    	console.log("No-one logged in");
		    }
		});
	}

	
});
 

function formatStatus(p) {
	return p.in ? "in" : "out";
}

function formatPerson(p) {
	return "<li class='person'>" +
				"<span class='email'>"+p.email+"</span>" +
				"<span class='status' data-status-in='"+formatStatus(p)+"'>"+p.in+"</span>" +
			"</li>";
}

function everyone() {
	
	
	$.ajax({
	      "url": "/api/v1/people", 
	      "type": "GET",
	      "contentType": "application/json; charset=utf-8",
	      "processData": false,
	      "dataType": "json",
	      "success": function(data, status, jqXhr) {
	        var lis = _.map(data.people, function(p) {
	        	return formatPerson(p);
	        });
	        $('#people').html("<ul>"+lis.join("")+"</ul>");
	      }
	    }); 
}


everyone();