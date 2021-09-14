<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Energy Panel</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/extjs41/resources/css/ext-all.css" />
<script type="text/javascript" >
var contextPath='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/extjs41/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/applyreview.js"></script>


</head>
<body background="<%=request.getContextPath()%>/images/etourback.jpg">
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/customermenu.jsp"></jsp:include>


 

<div id="content">

<div id="confirmationMessage" ></div>
<div id="ErrorDiv" />
<div id="buttonDiv"/>

<div id="contentPanel" />
<div id="AddDiv" />
</div>
</body>
</html>