<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/jsp/customer.jsp"></jsp:include>
<jsp:include page="/jsp/adminmenu.jsp"></jsp:include>
	<form action="<%=request.getContextPath()%>/book/doGenetics.do" method="post">

		<table>
			<tr>
				<td>Select the Constants : C1</td>
				<td><select name="c1">
						<option value="0.1">0.1</option>
						<option value="0.2">0.2</option>
						<option value="0.3">0.3</option>
						<option value="0.4">0.4</option>
						<option value="0.5">0.5</option>
				</select></td>
			</tr>

			<tr>
				<td>Select the Constants : C2</td>
				<td><select name="c2">
						<option value="0.1">0.1</option>
						<option value="0.2">0.2</option>
						<option value="0.3">0.3</option>
						<option value="0.4">0.4</option>
						<option value="0.5">0.5</option>
						
				</select></td>
			</tr>

			<tr>
				<td>Select the Constants : C3</td>
				<td><select name="c3">
						<option value="0.1">0.1</option>
						<option value="0.2">0.2</option>
						<option value="0.3">0.3</option>
						<option value="0.4">0.4</option>
						<option value="0.5">0.5</option>
						
				</select></td>
			</tr>

			<tr>
				<td>Select the Constants : C4</td>
				<td><select name="c4">
						<option value="0.1">0.1</option>
						<option value="0.2">0.2</option>
						<option value="0.3">0.3</option>
						<option value="0.4">0.4</option>
						<option value="0.5">0.5</option>
						
				</select></td>
			</tr>

			<tr>
				<td>Select the Constants : C5</td>
				<td><select name="c5">
						<option value="0.1">0.1</option>
						<option value="0.2">0.2</option>
						<option value="0.3">0.3</option>
						<option value="0.4">0.4</option>
						<option value="0.5">0.5</option>
						
				</select></td>
			</tr>
			
			<tr>
			
			<td><input type="submit" value= "Do genetic And Push Adv"/></td>
			</tr>
			
		</table>



	</form>


</body>
</html>