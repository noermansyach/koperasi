<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<jsp:include page="../meta.jsp" /> 
<title>Edit Kategori Barang</title>

<!-- Bootstrap Core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/font-awesome.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="assets/css/4-col-portfolio.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="../menu.jsp" />  
	<div class="container">
		<div id="alert" <c:if test="${empty message}">
		    style="display: none;"
		</c:if> <c:if test="${not empty message}">
		    style="display: block;"
		</c:if> class="alert alert-danger">${message}</div>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Edit Kategori Barang</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<form id="form" action="/update_kategori_pinjaman?id=${kategoriPinjaman.id}" method="post">
					<table id="table" class="table table-bordered table-condensed" style="font-size: 11px; margin-top: 10px;">
						<tbody class="data">
							<tr>
								<input type="hidden" id="token" name="token" value="${token}" />
								<td>Nama Kategori Pinjaman</td>
								<td><input type="text" id="namaPinjaman" name="namaPinjaman" class="form-control input-sm"  value="${kategoriPinjaman.namaPinjaman}" /></td>
							</tr>
							<tr>
								<td colspan="2">
									<button id="submit" type="submit" class="btn btn-info btn-sm">
										<span class="fa fa-save"></span> Simpan
									</button>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a href="/kategori_pinjaman"><button type="button" class="btn btn-sm btn-primary">Kembali</button></a>
								</td>
							</tr>
						</tbody>
				    </table>
			    </form>
    		</div>
    		<jsp:include page="../footer.jsp" /> 
		</div>
	</div>
	<!-- /.container -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.js"></script>
</body>
</html>