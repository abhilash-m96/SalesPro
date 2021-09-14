<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>

<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>
<%@page import="com.model.BookRating"%>
<%@page import="com.model.BookPackModel"%>
<%@page import="com.constants.GeneticConstants"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>

.tableCls{
 border: 1px solid black;
    margin: 73px 141px;
    padding: 31px;
}

.tableCls td{
border: 1px solid blue;
}
</style>


</head>
<body>
<jsp:include page="/jsp/customermenu.jsp"></jsp:include>

<%
	AJAXResponse ajaxResponse = (AJAXResponse) request
	.getAttribute(GeneticConstants.Keys.OBJ);
	if (null == ajaxResponse) {

	} else {
		boolean status = ajaxResponse.isStatus();
%>
<%
	if (!status) {
			List<Message> msg = ajaxResponse.getEbErrors();
%>
<%
	for (int i = 0; i < msg.size(); i++) {
				Message tempMsg = msg.get(i);
%>

<div class="errMsg"><%=tempMsg.getMsg()%></div>

<%
	}

		}
	}

	List<BookPackModel> rankedList = (List<BookPackModel>) ajaxResponse
			.getModel();

	if (null == rankedList) {
%>
<font color="red">Ranking of Books Could not be Done
at this point of Time</font>
<%
	} else {
%>
<table class="tableCls">
	<tr>
	<td>Product Name</td>
	<td>Author</td>
	<td>Publisher</td>
	<td>Product Overview</td>
	<td>Edition</td>
	</tr>
	<%
		for (BookPackModel bookModel : rankedList) {
	%>
	<tr>
		<td><span><%=bookModel.getBookName()%></span></td>
		<td><span><%=bookModel.getAuthor()%></span></td>
		<td><span><%=bookModel.getPublisher()%></span></td>
		<td><span><%=bookModel.getBookOverview()%></span></td>
		<td><span><%=bookModel.getEdition()%></span></td>
		<td><img src="<%=request.getContextPath()%>/images/<%=bookModel.getBookLoc()%>" width="250px" height="250px"></img></td>
	</tr>

	<%
		}
	%>
</table>
<%
	}
%>


</body>
</html>