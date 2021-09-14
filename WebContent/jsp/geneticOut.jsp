<%@page import="java.util.ArrayList"%>
<%@page import="com.model.CrossOverObj"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page
	import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>
<%@page import="com.model.AdvitisementModel"%>
<%@page import="com.model.PageInfo"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/jsp/customer.jsp"></jsp:include>
	<jsp:include page="/jsp/adminmenu.jsp"></jsp:include>

	<%
		AJAXResponse ajaxResponse = (AJAXResponse) request
				.getAttribute(GeneticConstants.Keys.OBJ);
		if (null == ajaxResponse) {

		} else {
			boolean status = ajaxResponse.isStatus();
	%>
	<%
		if (!status) {
	%>

	<div class="errMsg"><%=ajaxResponse.getMessage()%></div>
	<%
		} else {

				Object obj = (Object) ajaxResponse.getModel();

				ArrayList<AdvitisementModel> list = (ArrayList<AdvitisementModel>) obj;

				if (null == list) {

				} else {
					for (AdvitisementModel advitisementModelTemp : list) {
	%>
	<div class="genetic">
		UserId:<%=advitisementModelTemp.getUserId()%>
	</div>

	<div class="pageInfoTable" border="true">
		<table>
			<tr>
				<td>Page ID</td>
				<td>Cost</td>
			</tr>
			<%
				List<PageInfo> allPageInformation = advitisementModelTemp
										.getAllPageInformation();

								if (allPageInformation != null
										&& !allPageInformation.isEmpty()) {

									for (PageInfo pInfo : allPageInformation) {
			%>
			<tr>
				<td><%=pInfo.getPageName()%></td>
				<td><%=pInfo.getBestCost()%></td>
			</tr>
			<%
				}
								}

								if (advitisementModelTemp.isTwoPathPossible()) {

									List<PageInfo> pageInfo = advitisementModelTemp
											.getPageInfoList();

									if (pageInfo != null && !pageInfo.isEmpty()) {
			%>

			<div class="pageInfo">
				<span>Best Two Paths Information</span>
				<table border="1">
					<tr>
						<td>Page ID</td>
						<td>Best Costs</td>
					</tr>
					<%
						for (PageInfo pageInfoTemp : pageInfo) {
					%>

					<tr>
						<td><%=pageInfoTemp.getPageName()%></td>
						<td><%=pageInfoTemp.getBestCost()%></td>
					</tr>

					<%
						}
					%>
				</table>
			</div>
			<div class="crossOver">
				<span>Cross Over Information</span>
				<%
					}

										CrossOverObj crossOverObj = advitisementModelTemp
												.getCrossOverObj();
										if (crossOverObj != null) {
											List<String> originalPaths = crossOverObj
													.getOrginalPaths();

											if (originalPaths != null
													&& !originalPaths.isEmpty()) {
				%>
				<table border="1">
					<tr>
						<td>Original Paths</td>
					</tr>
					<%
						for (String path : originalPaths) {
					%>
					<tr>
						<td><%=path%></td>
					</tr>


					<%
						}
					%>>
				</table>

				<table border="1">

					<%
						List<String> crossOverPaths = crossOverObj
															.getFourPaths();
					%>
					<tr>
						<td>Cross Over Paths</td>
					<tr>
						<%
							if (crossOverPaths != null
																&& !crossOverPaths.isEmpty()) {

															for (String path : crossOverPaths) {
						%>
					
					<tr>
						<td><%=path%></td>
					</tr>

					<%
						}

													}
					%>
				</table>


				<%
					List<Integer> countOfOnesFourPaths = crossOverObj
														.getCountOfOnesFourPaths();
												if (countOfOnesFourPaths != null
														&& !countOfOnesFourPaths
																.isEmpty()) {
				%>
				<table border="1">
					<tr>
						<td>Count Of Ones Four Paths</td>
					</tr>

					<%
						for (Integer count : countOfOnesFourPaths) {
					%>

					<tr>
						<td><%=count%></td>
					</tr>
					<%
						}
													}
					%>
				</table>



				<%
					List<Double> distance = crossOverObj
														.getDistances();
				%>

				<%
					if (distance != null
														&& !distance.isEmpty()) {
				%>
				<table border="1">
					<tr>
						<td>Distances</td>
					</tr>
					<%
						for (Double distanceTemp : distance) {
					%>
					<tr>
						<td><%=distanceTemp%></td>
					</tr>


					<%
						}
													}
					%>
				</table>

				<%
					if (crossOverObj.getMutatedPath() != null
														&& crossOverObj
																.getMutatedPathCost() > 0) {
				%>
				<table border="1">
					<tr>
						<td>Mutated Path is :</td>



						<td><%=crossOverObj.getMutatedPath()%></td>



					</tr>
					<tr>
						<td>Mutated Path Cost is :</td>
						<td><%=crossOverObj.getMutatedPathCost()%></td>
					</tr>


				</table>
				<%
					}
				%>

				<table border="1">
					<tr>
						<td>Best Page is :</td>
						<td><%=crossOverObj.getBestPage()%></td>
					</tr>

				</table>

				<%
					}

										}
				%>
			</div>

			<%
				}
			%>
		</table>
	</div>

	<%
		}
				}

			}
		}
		
	%>

	<div class="advitise">
	<form action="<%=request.getContextPath()%>/book/pushAdvitiseMent.do">
			<table>
			<tr>
			<td><label>Enter the Advertisement Information to be Pushed</label></td>
			<td><textarea type="textarea" name="advitisement" rows="5" cols="10"> </textarea></td>
			<td><input type="submit" value="PUSH Advertisement" /></td>
			</tr>
			</table>
		</form>
	</div>



</body>
</html>