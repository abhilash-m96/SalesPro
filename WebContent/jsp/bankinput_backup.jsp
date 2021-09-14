<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page
	import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body background="<%=request.getContextPath()%>/images/background.jpg">
	<jsp:include page="/jsp/customer.jsp"></jsp:include>
	<jsp:include page="/jsp/customermenu.jsp"></jsp:include>

	<form action="<%=request.getContextPath()%>/book/banking.do"
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
			<span> <strong>Banking</strong></span>
			<table>
				<tr>
					<td><label>Enter the Account Number:</label></td>
					<td><input name="accountNo" id="accountNo" type="text"
						size="40" maxlength="40" /></td>
				</tr>
				<tr>
					<td><label>Enter the Password:</label></td>
					<td><input name="password" id="password" type="password" size="40"
						maxlength="40" /></td>
				</tr>
				
				<tr>
				
				<td> <label>Address for Delivery</label>
				<td>
				<textarea name="address" rows="10" cols="40"></textarea>
				</td>
				
				
				<tr>
					<td><input type="submit" value="Apply" /></td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>