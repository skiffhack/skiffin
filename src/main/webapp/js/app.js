 
   navigator.id.get(function(assertion) {
	    if (assertion) {
	        // This code will be invoked once the user has successfully
	        // selected an email address they control to sign in with.
	    	console.log("Yes");
	    	console.log(assertion);
	    	
	    	 $.ajax({
	    	      "url": "/api/verify/"+assertion, 
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
 