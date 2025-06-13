<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head></head>
<body>
<h1><a href="./itnsimtools" >HLB Mobility Server Internet Simulator Tools(Testing)</a></h1>
<br/>
	<form action="itnsimtools"  method="POST">
	<br/>
	Correction Code : <input name="ccode" type="text"  maxlength="50"/>
	<br/>
	Mobile Number : <input name="mnumber" type="text"  maxlength="50"/>
	<br/>
	Amount : <input name="amount" type="text"  maxlength="50"/>
	<br/>
	<input  type="submit"/>
	</form>
	
	<p>${rs1}</p>
	<p>${rs2}</p>
	<p>${rsx}</p>
</body>
</html>

