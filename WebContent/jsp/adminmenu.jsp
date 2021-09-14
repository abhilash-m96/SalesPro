<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/admin.jsp"><span>Home</span></a></li>
			<li>
		</ul>

		<ul>
			<li class='active '><a
				href="<%=request.getContextPath()%>/jsp/viewhabitat.jsp"><span>View
						Habitat</span></a></li>
		</ul>

		<ul>
			<li class='has-sub'><a href='#'>K Means<span></span></a>
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/jsp/performkmeans.jsp"><span>
								Perform K Means </span></a></li>
					<li><a href="<%=request.getContextPath()%>/jsp/viewkmeans.jsp"><span>
								View K Means </span></a></li>

					<li><a
						href="<%=request.getContextPath()%>/jsp/viewkmeansclassify.jsp"><span>
								K Means Classify </span></a></li>
				</ul></li>
		</ul>

		<ul>
			<li class='has-sub'><a href='#'><span>RFM</span></a>
				<ul>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/performRFM.jsp"><span>
								Perform RFM </span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewRFM.jsp"><span>
								View RFM </span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/classifyCustomer.jsp"><span>
								Classify Customers </span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewcustomerclassify.jsp"><span>
								View Customers Classification </span></a></li>
					<li class='active '><a
						href="<%=request.getContextPath()%>/jsp/viewcustomerclassifygraph.jsp"><span>
								Classify Graph </span></a></li>
				</ul>
		</ul>



		<ul>
			<li class='has-sub '><a
				href='<%=request.getContextPath()%>/book/logoutadmin.do'>Logout<span></span></a></li>
		</ul>
	</div>
</body>
</html>