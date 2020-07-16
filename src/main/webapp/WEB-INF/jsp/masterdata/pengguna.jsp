<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<jsp:include page="../meta.jsp" /> 
<title>Daftar Pengguna</title>

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
				<h1 class="page-header">Daftar Pengguna</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<table class="table table-bordered table-condensed" style="font-size: 11px; margin-top: 10px;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nama</th>
						<th>Status</th>
						<th>Tipe Anggota</th>
						<th>No. Telepon</th>
						<th colspan="2">Aksi</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listPengguna}" var="pengguna">
						<tr>
							<td>${pengguna.id}</td>
							<td>${pengguna.namaPengguna}</td>
							<td>${pengguna.status}</td>
							<td>${pengguna.tipeAnggota}</td>
							<td>${pengguna.noTlp}</td>
					        <td>
					        	<a href="/edit_pengguna?id=${pengguna.id}">Edit</a>
					        </td>
					        <td>
					        	<a href="/delete_pengguna?id=${pengguna.id}" onclick="return confirm('Yakin menghapus data pengguna ?')">Hapus</a>
					        </td>  									
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a id="tambah" class="btn-sm btn-primary" href="/add_pengguna">Tambah</a>
		</div>
		<hr>
	<jsp:include page="../footer.jsp" /> 
	</div>
	<!-- /.container -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.js"></script>
</body>
</html>