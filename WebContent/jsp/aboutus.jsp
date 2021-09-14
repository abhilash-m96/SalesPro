<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World</title>
</head>
<body background="<%=request.getContextPath()%>/images/welcome.jpg" >
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>


<div>
</div>

<div class="aboutus">

<h1>Frequency </h1> 
<p>This module is used to compute the frequency of the page</p>
<h1>Bytes Computation </h1>
This module is used to compute the bytes to be downloaded
<h1>Time of Stay</h1>
<p>This module is used to compute the time of stay</p> 
</div>



</body>
</html>