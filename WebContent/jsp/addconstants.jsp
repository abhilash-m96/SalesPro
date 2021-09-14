<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>

<%@page import="com.constants.GeneticConstants"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Constant</title>
<script type="text/javascript">

validatestopword=function()
{
	var stopWord=document.getElementById('locatorValue').value;

	if(stopWord<=0)
	{
		alert("Please Enter a Value for Locator");
		return false;
	}
}
</script>
</head>
<body>
<body background="<%=request.getContextPath()%>/images/background.jpg">
<jsp:include page="/jsp/adminmenu.jsp"></jsp:include>

<%
AJAXResponse ajaxResponse=(AJAXResponse) request.getAttribute(GeneticConstants.Keys.OBJ);
if(null==ajaxResponse)
{
	
}
else
{
	boolean status=ajaxResponse.isStatus();
%>
<%
	if(!status)
	{
		List<Message> msg=ajaxResponse.getEbErrors();
%>
<%
		for(int i=0;i<msg.size();i++)
		{
			Message tempMsg=msg.get(i);
	%>
	
	<div class="errMsg"><%= tempMsg.getMsg()%></div>
	
<%		}
	
	}
}
%>


<form action="<%=request.getContextPath()%>/dia/addConstants.do" method="post"  >


	<table>
		<tr>
			<td><label>Enter Constant:</label> </td>
			<td><input name="locatorValue" id="locatorValue" type="text"  size="500" maxlength="500" /></td>
		</tr>
			<tr>
			<td><input  type="submit" value="Add Constant" onclick="validatestopword()"/></td>
		</tr>
			
	</table>
</form>
</body>
</html>