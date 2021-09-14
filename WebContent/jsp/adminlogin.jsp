<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="<%=request.getContextPath()%>/images/welcome.jpg" >
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/menu.jsp"></jsp:include>


<%
	AJAXResponse ajax=(AJAXResponse)request.getAttribute(GeneticConstants.Keys.OBJ);

if(null==ajax)
{
	
}
else
{	
	List<Message> ebErrors=ajax.getEbErrors();
	
	if(null==ebErrors)
	{
		
	}
	else
	{
	Message msg=ebErrors.get(0);
%>
<div class="errMessage" >
<%=msg.getMsg()%>
</div>
<%
	}
	}


%>

<form action="<%=request.getContextPath()%>/book/login.do" method="post">
<div class="login">
<span> <strong>Login</strong></span>
<table>
	<tr>
		<td><label>Enter the User Name:</label></td>
		<td><input name="userId" id="userId" type="text"
			size="40" maxlength="40" /></td>
		</tr>
	<tr>
		<td><label>Enter the Password:</label></td>
		<td><input name="userPassword" id="userPassword" type="password" size="40"
			maxlength="40" /></td>
		</tr>
		<tr>
		<td><input type="submit" value="Login" /></td>
	</tr>

</table>
</div>
</form>
</body>
</html>