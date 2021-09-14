<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.model.AJAXResponse,java.util.List,com.model.Message"%>
<%@page import="com.constants.GeneticConstants"%>


<head>
<title>Register</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sky-forms.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/errormsg.css">
		<script>
			function preventBack(){window.history.forward();}
			setTimeout("preventBack()", 0);
			window.onunload=function(){null};
		</script>
</head>

<body class="bg-cyan">
	
	<div class="isa_success">
     <i class="fa fa-check">
     If you are interested to purchase products please register.The information will be kept confidential.
     </i>
     	
     </div>


	<%
		AJAXResponse ajax = (AJAXResponse) request.getAttribute(GeneticConstants.Keys.OBJ);

		if (null == ajax) {

		} else {
			List<Message> ebErrors = ajax.getEbErrors();

			if (null == ebErrors) {

			} else {
				Message msg = ebErrors.get(0);
	%>
	<div class="isa_error">
		<%=msg.getMsg()%>
	</div>
	<%
		}
		}
	%>


	
	<div class="body body-s">
		
			<form action="<%=request.getContextPath()%>/book/registerUser.do" method="post" class="sky-form">
				<header>Registration form <a href="<%=request.getContextPath()%>/jsp/welcome.jsp" class="button">Home</a></header>
				<fieldset>					
					<section>
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" name="userId" placeholder="Username">
							<b class="tooltip tooltip-bottom-right">Enter Username</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-envelope-alt"></i>
							<input type="text" name="emailId" placeholder="Email address">
							<b class="tooltip tooltip-bottom-right">Email</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" name="userPassword" placeholder="Password">
							<b class="tooltip tooltip-bottom-right">Only latin characters and numbers</b>
						</label>
					</section>
					
					
				</fieldset>
					
				<fieldset>
					<div class="row">
						<section class="col col-6">
							<label class="input">
								<input type="text" name="firstName" placeholder="First name">
							</label>
						</section>
						<section class="col col-6">
							<label class="input">
								<input type="text" name="lastName" placeholder="Last name">
							</label>
						</section>
					</div>
					
				</fieldset>	
				<footer>
					<button type="submit" class="button">Submit</button>
				</footer>
			</form>
			
		</div>

</body>
</html>