<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page
	import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>
<%@page import="com.model.Message"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

.banking {
    border: 1px solid black;
    margin: 73px 141px;
    padding: 31px;
    height: 95px;
    width: 532px;
}

</style>
</head>

<body>
	
	<jsp:include page="customermenu.jsp"></jsp:include>

	<form action="<%=request.getContextPath()%>/book/budget.do"
		method="post">
		<%
			AJAXResponse ajax = (AJAXResponse) request
					.getAttribute(GeneticConstants.Keys.OBJ);

			if (null == ajax) {

			} else {
				
				List<Message> msg=ajax.getEbErrors();
				if(null==msg)
				{
					
				}else
				{
					Message errMsg=msg.get(0);
		%>
			<font color="red"><%=errMsg.getMsg()%></font>
			<%
				}

			}
		%>

		<div class="banking">
			<span> <strong>Monthly Budget</strong></span>
			<table>
				<tr>
					<td><label>Enter the Amount:</label></td>
					<td><input name="budget" id="budget" type="text"
						size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Apply" /></td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>