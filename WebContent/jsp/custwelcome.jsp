<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>
<%@page import="com.model.LicenseInfoForUserUI"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/customer.css" type="text/css">
</head>
<body>

	<jsp:include page="customermenu.jsp"></jsp:include>

	<div class="fullContent">

		<%
		AJAXResponse ajaxResponse = (AJAXResponse)request.getAttribute(GeneticConstants.Keys.OBJ);
	
		
		
		if(null==ajaxResponse){
			
		}else{
			
		
				
		%>


		<%		
				
			
				
			
			%>

		<a class="partialBlock sportsImg"
			href="<%=request.getContextPath()%>/book/specBook.do?id=1"> </a>
		<%
				
				
	%>
		<%
			
			%>

		<a class="partialBlock policticsImg"
			href="<%=request.getContextPath()%>/book/specBook.do?id=2"> </a>
		<%
				
				
	%>

		<%
	%>
		<a class="partialBlock bollywoodImg"
			href="<%=request.getContextPath()%>/book/specBook.do?id=3"> </a>
		<%
		
	
	
	%>
		<a class="partialBlock cloudImg"
			href="<%=request.getContextPath()%>/book/specBook.do?id=4"> </a>
		<%
		%>
		<a class="partialBlock programmingImg"
			href="<%=request.getContextPath()%>/book/specBook.do?id=5"> </a>
		<%
			%>
		<a class="partialBlock romanceImg"
			href="<%=request.getContextPath()%>/book/specBook.do?id=6"> </a>

		<%
			}
	
	%>




	</div>
</body>
</html>