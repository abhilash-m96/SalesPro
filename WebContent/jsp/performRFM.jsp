
<!DOCTYPE html>
<html>

<head>
<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
</script>
<style>
html {
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
}
</style>
<title></title>
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.common.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.rtl.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.silver.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.2.621/styles/kendo.mobile.all.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script src="https://kendo.cdn.telerik.com/2017.2.621/js/angular.min.js"></script>
<script
	src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script>


<style>
.automation-container {
	
}

.height_300 {
	height: 30px;
}

textarea {
	height: 300px;
	min-height: 300px;
	max-height: 300px;
}
</style>



<script>
	angular
			.module("app", [ "kendo.directives" ])
			.controller(
					"object_repository_overview_controller",
					function($scope, $http) {

						var vm = this;

						function init() {

							$scope.showSucessMsg = false;
							$scope.showFailureMsg = false;

							$scope.sucessMsg = "";
							$scope.failureMsg = "";
							$scope.showInfoMsg=false;				
			
							$scope.execute_kmeans = function() {
								clear_all_messages();
									
								$scope.infoMsg="Execution of RFM has Triggered. Please Wait";
								
								$scope.disable_identification_type=true;
								
								$scope.showInfoMsg=true;
								
									var res = $http.post(contextPath
											+ '/book/executeRFM.do');
									res
											.success(function(response, status,
													headers, config) {
												var JsonData = response;

												if (JsonData.ebErrors != null) {
													var errorObj = JsonData.ebErrors;
													for (i = 0; i < errorObj.length; i++) {
														var value = errorObj[i].msg;
														$scope.showFailureMsg = true;
														$scope.failureMsg = value;
														$scope.infoMsg='';
														$scope.showInfoMsg=false;
													}
													$scope.disable_identification_type=false;

												} else {
													var value = JsonData.message;
													$scope.showSucessMsg = true;
													$scope.sucessMsg = value;
													$scope.disable_identification_type=false;
													$scope.infoMsg='';
													$scope.showInfoMsg=false;
												}
											});

									res
											.error(function(response, status,
													headers, config) {

												var JsonData = response;

												if (JsonData.ebErrors != null) {
													var errorObj = JsonData.ebErrors;
													for (i = 0; i < errorObj.length; i++) {
														var value = errorObj[i].msg;
														$scope.showFailureMsg = true;
														$scope.failureMsg = value;
													}

													$scope.infoMsg='';
													$scope.showInfoMsg=false;
							
													$scope.disable_identification_type=false;
												} else {
													var value = JsonData.message;
													$scope.showSucessMsg = true;
													$scope.sucessMsg = value;
													$scope.disable_identification_type=false;
													$scope.infoMsg='';
													$scope.showInfoMsg=false;
												}
											});

								
							};

			
						}
						init();

						function clear_all_messages() {
							$scope.showFailureMsg = false;
							$scope.failureMsg = "";
							$scope.sucessMsg = "";
							$scope.showSucessMsg = false;

						}

						

					});
</script>



</head>
<body>

	<div ng-app="app"
		ng-controller="object_repository_overview_controller as object_repository_overview_controller">

		<div class="container">
			<div class="panel panel-primary">



				<div class="panel-heading">Perform RFM</div>

				<div class="panel-body">

					<div class="container-fluid automation-container"
						id="contentContainer">

						<div class="main-content-wrapper">

							<div class="row">
							
								<div class="col-xs-12">
									<div class="alert alert-info" ng-show="showInfoMsg">
										{{infoMsg}}
									</div>
									
								</div>
							
							</div>

							<div class="row">

								<div class="col-xs-12">

									<div class="alert alert-success" ng-show="showSucessMsg">
										{{sucessMsg}}</div>
								</div>

							</div>

							<div class="row">

								<div class="col-xs-12">

									<div class="alert alert-danger" ng-show="showFailureMsg">
										{{failureMsg}}</div>
								</div>

							</div>


					
						<div class="row">

								<div class="col-xs-12 col-lg-6 col-md-6 col-sm-12">


									<button 
									ng-disabled="disable_identification_type"
									ng-click="execute_kmeans()" class="btn btn-primary">
									RFM</button>

									<div class="col-xs-12 col-lg-6 col-md-6 col-sm-12">

										<a class="btn btn-primary"
											href="<%=request.getContextPath()%>/jsp/admin.jsp">
											Home</a>

									</div>

								</div>

							</div>



						</div>

					</div>


				</div>

			</div>

		</div>


	</div>


	</div>

	</div>

	</div>

</body>
</html>
