<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.model.FullBookModelForUserId"%>
<%@page import="com.model.BookPackModel"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>

<%@page import="com.constants.GeneticConstants"%>
<%@page import="com.model.AJAXResponse"%>
<%@page import="com.model.Message"%>
<%@page import="com.model.BookRating"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/reset.css">
<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/customer.css">

<script>
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};
</script>
</head>
<body>

	<div class="testUp">
	
	</div>

	<div>
		<a class="myNav" href="<%=request.getContextPath()%>/book/custwelcome.do"><span>Home</span></a>
		<a class="myNav" href="<%=request.getContextPath()%>/book/logout.do">Logout<span></span></a>
	</div>
	
	<div class="testUp">
	
	</div>

	<%
		AJAXResponse ajaxResponse = (AJAXResponse) request.getAttribute(GeneticConstants.Keys.OBJ);
		if (null == ajaxResponse) {

		} else {
			boolean status = ajaxResponse.isStatus();
	%>
	<%
		if (!status) {
				List<Message> msg = ajaxResponse.getEbErrors();
				if (msg != null & !msg.isEmpty()) {
	%>
	<%
		for (int i = 0; i < msg.size(); i++) {
						Message tempMsg = msg.get(i);
	%>

	<div class="errMsg"><%=tempMsg.getMsg()%></div>

	<%
		}
				}

			} else {

				FullBookModelForUserId full = (FullBookModelForUserId) ajaxResponse.getModel();

				if (null == full) {

				} else {

					List<BookPackModel> rankedList = full.getBooksList();

					if (null == rankedList) {
	%>
	<font color="red">Books Could not Be Found</font>
	<%
		} else {
	%>
	<%
		for (BookPackModel bookModel : rankedList) {
	%>
	
	
	<div class="globalDiv">
		<div class="common leftCls">
			<h4><b><%=bookModel.getBookName()%></b>,<%=bookModel.getAuthor()%>
				<i><%=bookModel.getEdition()%></i>
			</h4>
			<p>
				<%=bookModel.getBookOverview()%>
			</p>
			<br> <span> Price :<%=bookModel.getBookPrice()%></span>
		</div>
		<div class="common middleCls">
			<img
				src='<%=request.getContextPath()%>/images/<%=bookModel.getBookLoc()%>' />
		</div>
		<div class="common rightCls">
			<form action="<%=request.getContextPath()%>/book/buyBook.do"
				method="post">
				<input type="hidden" name="bookId"
					value="<%=bookModel.getBookId()%>" /> <input type="hidden"
					name="noOfBooks" value="1" />
				<input type="hidden"
					name="bookPrice" value="<%=bookModel.getBookPrice()%>" />
				<button type="submit">Buy</button>
			</form>

		</div>
	</div>


	<%
		}
					}

				}
			}
		}
	%>


</body>
</html>