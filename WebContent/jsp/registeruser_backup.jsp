<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body background="<%=request.getContextPath()%>/images/register.jpg" >
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


<div class="register">
<form action="<%=request.getContextPath()%>/book/registerUser.do" method="post" >

<table>
<tr>
<td><span>Enter the First Name:</span></td>
<td><input type="text" name="firstName" maxlength="40" ></input></td>
</tr>

<tr>
<td><span>Enter the Last Name:</span></td>
<td><input type="text" name="lastName" maxlength="40" ></input></td>
</tr>

<tr>
<td><span>Enter the Desired User Name:</span></td>
<td><input type="text" name="userId" maxlength="40" ></input></td>
</tr>

<tr>
<td><span>Enter the Password:</span></td>
<td><input type="password" name="userPassword" maxlength="40" ></input></td>
</tr>
<tr>
<td><span>Enter the Email ID:</span></td>
<td><input type="text" name="emailId" maxlength="40" ></input></td>
</tr>
<tr>
<td></td>
<td>
<input  type="submit" name="Register" value="Register" ></input>
</td>
<td></td>
</tr>
</table>





</form>

</div>

</body>
</html>