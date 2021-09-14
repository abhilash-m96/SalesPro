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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/directrating.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script>
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};
</script>

<style>

.testUp{
	margin:1px;
	height:25px;
}

a:link.myNav {
    text-decoration: none;
    text-align: center;
    padding-top: 10px;
    padding: 5px;
    margin-top: 10px;
    border-bottom: #3371FF 7px solid;
}

a:hover.myNav{
	background-color:#83FF33;
}

</style>


</head>
<body>

	<div class="testUp">
	
	</div>

	<div>
		<a class="myNav" href="<%=request.getContextPath()%>/jsp/welcome.jsp"><span>Home</span></a>
		<a class="myNav" href="<%=request.getContextPath()%>/jsp/directrating.jsp"><span>Direct Rating</span></a>
		<a class="myNav" href="<%=request.getContextPath()%>/jsp/directratinggraph.jsp"><span>Rating Graph</span></a>
	</div>
	
	<div class="testUp">
	
	</div>

<div id="content">


<div id="confirmationMessage" class="alert alert-sucess">

</div>
<div id="ErrorDiv" />
<div id="buttonDiv"/>

<div id="contentDiv" />
<div id="AddDiv" />
<div id="contentPanel">

</div>
</div>
</body>
</html>