<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<jsp:include page="meta.jsp" /> 
<title>Masuk</title>
<!-- Bootstrap -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/stylesl.css" rel="stylesheet">
</head>
<body class="login-bg">
	<div id="alert" 
	<c:if test="${empty message}">
	    style="display: none;"
	</c:if>
	<c:if test="${not empty message}">
	    style="display: block;"
	</c:if>	
	 class="alert alert-danger">${message}</div>

	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
					<div class="box">
						<div class="content-wrap">
							<h4 style="color: white; text-align: center; background-color: #509090; padding: 20px 20px 20px 20px; border-radius: 5px;font-family: 'Verdana, sans-serif';">Login Koperasi</h4>
							<form action="/login" method="post" id="form-login">
								<input class="form-control" type="text" name="namaPengguna" id="namaPengguna" value="" placeholder="Nama"> 
								<input class="form-control" type="password" name="password" id="password" value="" placeholder="Password">
								<button type="submit" id="submit" class="btn btn-lg btn-success" style="width: 310px; margin-top: 0px;">Login</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.js"></script>
    <script>
	    $(document).ready(function(){
	    	$("form").submit(function(){
	    		// var regUsername = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$");
	    		// if (!regUsername.test($('#nama').val())) {
	    		// 	$("#alert").attr("style", "display: block;");
	    		// 	$("#alert").html("Nama tidak boleh kosong dan harus dalam bentuk email");
	    		// 	$("#nama").focus();
	    		// 	return false;
	    		// }
	    	})
	    })    
    </script>
</body>
</html>