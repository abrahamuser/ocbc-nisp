<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
 <body>
<h1>Stat Info</h1>


<c:forEach items="${rsParam}" var="element">    
    <p>${element.gnCodeDescription}</p>
</c:forEach>
<br/>

<p><span>Total Login Session : </span>${totalRecords}</p>
<button id="callapi" >Test Connection</button> 
<pre id="rs"></pre>
<%-- <h2>Proxy : ${name}</h2>
 <h2>Result : ${rs}</h2>--%>



 
 <script src="js/jquery.min.js"></script>
  <script src="js/jquery-1.4.2.min.js"></script>
 <script type="text/javascript">
 
 $(document).ready(function(){

 	$("#callapi").click(function(){
 		
 		$("#rs").html('Connecting...')
 		setTimeout(function myFunction() {

	 		$.ajax({
	            url: 'retrieveAccessInfo',
	            type: 'post',
	            data : '{}',
	            dataType: 'json',
	            contentType: "application/json; charset=utf-8",
	            success: function (data, status, jqXHR) {
	            	$("#rs").html(JSON.stringify(data, undefined, 2));
	            }
	        });
 		},1000);
 	
	    
	});
 	
 });
 
 

 
 </script>
</body>
</html>