<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="java.util.List"%>
<%@page
	import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>

<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer.css">
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
			<div class="alert alert-danger"><%=errMsg.getMsg()%></div>
			<%
				}

			}
		%>


<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Bank Input</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" action="<%=request.getContextPath()%>/book/banking.do"
		method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Account No</label>
    <div class="col-sm-6">
      <input  name="accountNo" class="form-control" id="inputEmail3" placeholder="Account No">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3"  class="col-sm-2 control-label">IPIN</label>
    <div class="col-sm-6">
      <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="IPIN">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Address</label>
     <div class="col-sm-6">
      <textarea class="form-control" name="address" ></textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-6">
      <button type="submit" class="btn btn-primary">Proceed</button>
    </div>
  </div>
</form>
  </div>
</div>
		
	

	


</body>
</html>