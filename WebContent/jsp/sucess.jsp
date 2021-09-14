<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.model.AJAXResponse"%>
<%@page import="com.constants.GeneticConstants"%>
<%@page import="java.util.List"%>


<%@page import="com.model.Message"%><html>
<head>
<meta charset="utf-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer.css">
<script>
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};
</script>
</head>
<body>

<div class="testUp">
	
	</div>

	<div>
		<a class="myNav" href="<%=request.getContextPath()%>/book/custwelcome.do"><span>Home</span></a>
		<a class="myNav" href="<%=request.getContextPath()%>/book/logout.do">Logout<span></span></a>
	</div>
	
	<div class="testUp">
	
	</div>

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Message Information</h3>
  </div>
  <div class="panel-body">
<%
	AJAXResponse ajax=(AJAXResponse)request.getAttribute(GeneticConstants.Keys.OBJ);
if(null==ajax)
{
	
}
else
{
	
	List<Message> errMsgList=ajax.getEbErrors();
	if(null==errMsgList)
	{
		
	}
	else
	{
		Message m=errMsgList.get(0);
%>

<div class="alert alert-error">
<%=m.getMsg()%>
</div>

<% 		
		
	}
	
	if(ajax.getMessage()!=null)
	{
		%>
		<div class="alert alert-sucess">
		<%=ajax.getMessage()%>
		</div>
		<%
	}
	
}
%>
</div>
</div>
</body>
</html>