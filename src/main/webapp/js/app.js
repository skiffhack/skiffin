 
$(function() {

	// BROWSER ID -------------------------------------------------------
	
	function login() {
	   navigator.id.get(function(assertion) {
		    if (assertion) {
		        // This code will be invoked once the user has successfully
		        // selected an email address they control to sign in with.
		    	
		    	 $.ajax({
		    	      "url": "/api/v1/login/"+assertion, 
		    	      "type": "GET",
		    	      "contentType": "application/json; charset=utf-8",
		    	      "processData": false,
		    	      "dataType": "json",
		    	      "success": function(person, status, jqXhr) {
		    	        if (person) showStatusToggle(person);
		    	      }
		    	    }); 
		    	
		    	
		    } else {
		    	console.log("something went wrong!  the user isn't logged in");
		    }
		});
	}

	$("#sign-in").delegate("#sign-in-button", "click", login);	

	function showStatusToggle(person) {
		var control = $("#status-control");
		$("#sign-in").hide();
		$("#email").html(person.email);
		$("#person").show();
		control.data("email", person.email);
		control.checked = person.in;
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

// iOS-style Checkboxes -----------------------------------------------

function recordState(status) {
	
	var email = $("#status-control").data("email");
	
	$.ajax({
	      "url": "/api/v1/"+email, 
	      "type": "POST",
	      "contentType": "application/json; charset=utf-8",
	      "processData": false,
	      "data": JSON.stringify( {"in": status} ),
	      "dataType": "json",
	      "success": function(person, status, jqXhr) {
	    	  //console.log(person);
	       } 
	    }); 
	
	
}

// http://awardwinningfjords.com/2009/06/16/iphone-style-checkboxes.html
$(document).ready(function() {
	$(':checkbox').iphoneStyle({
		  checkedLabel: 'IN',
		  uncheckedLabel: 'OUT',
		  onChange: function(elem, value) { 
		     recordState(value);
		    }
		});
	$("#person").hide();
});

