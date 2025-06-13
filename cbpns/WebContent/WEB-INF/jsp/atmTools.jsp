<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head></head>
<body>
<h1><a href="./atmsimtools" >HLB Mobility Server ATM Simulator Tools(Testing)</a></h1>
<br/>
<form action="atmsimtools" method="POST">
Pin : <input name="pin" type="text"  maxlength="50"/>
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
