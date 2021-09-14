<%@page import="com.model.MontlyBudget"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.model.AJAXResponse"%>
<%@page import="java.util.List"%>
<%@page import="com.constants.GeneticConstants"%>
<%@page import="com.model.Message"%>
<%@page import="com.model.MontlyBudget"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>

.warning {
    border: 1px solid black;
    margin: 50px 50px;
    padding: 50px 332px;
}



</style>


</head>
<body>
	<jsp:include page="/jsp/customermenu.jsp"></jsp:include>

	<%
		AJAXResponse ajax = (AJAXResponse) request
				.getAttribute(GeneticConstants.Keys.OBJ);
		if (null == ajax) {

		} else {

			List<Message> errMsgList = ajax.getEbErrors();
			if (null == errMsgList) {

			} else {
				Message m = errMsgList.get(0);
	%>
	<div class="sucess">

		<%=m.getMsg()%>
	</div>

	<%
		}

			if (ajax.getMessage() != null) {
	%>
	<%=ajax.getMessage()%>
	<%
		}

			if (ajax.getModel() != null) {

				MontlyBudget montlyBudget = (MontlyBudget) ajax.getModel();

				double budget = montlyBudget.getBudget();

				double usedBudget = montlyBudget.getUsedBudget();

				double costOfProduct = montlyBudget.getCostProduct();
	%>
	
	<div class="warning">
	<table>
		<tr>
			<td> <Strong>Budget</Strong></td>
			<td><%=budget%></td>
		</tr>
		<tr>
			<td><Strong>Used Budget</Strong></td>
			<td><%=usedBudget%></td>
		</tr>
		<tr>
			<td><strong>Cost of Product</strong></td>
			<td><%=costOfProduct%></td>
		</tr>
	</table>

	
		<table>
			<form action="<%=request.getContextPath()%>/book/budgetMove.do">
				<tr>
					<td>Do you want to proceed Future?</td>
					<td><select name="decision">
							<option value="YES">YES</option>
							<option value="NO">NO</option>
					</select></td>
				</tr>
				<tr>
				<td><input type="submit" name="SUBMIT"></td>
				</tr>
			</form>
		</table>
	</div>

	<%
		}

		}
	%>
</body>
</html>