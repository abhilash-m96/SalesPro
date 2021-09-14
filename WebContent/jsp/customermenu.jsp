<%@page import="com.model.LicenseInfoForUserUI"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.constants.GeneticConstants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/customer.css" type="text/css">
</head>

<div>
	<a class="myNav"
		href="<%=request.getContextPath()%>/book/custwelcome.do"><span>Home</span></a>

	<%
		AJAXResponse ajaxResponse = (AJAXResponse)request.getAttribute(GeneticConstants.Keys.OBJ);
	
		
		
		if(null==ajaxResponse){
			
		}else{
			
		
				
		%>
	<a class="myNav" href="<%=request.getContextPath()%>/book/budgetNav.do"><span>Budget</span></a>
	<a class="myNav" href="<%=request.getContextPath()%>/book/advRecommend.do"><span>Recommendations</span></a>

	<%
		}
	%>

	<a class="myNav" href="<%=request.getContextPath()%>/book/logout.do"><span>Logout</span></a>
	
	
	
</div>

</html>