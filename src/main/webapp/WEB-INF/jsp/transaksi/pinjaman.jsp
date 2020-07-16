<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

<head>
<jsp:include page="../meta.jsp" /> 
<title>Daftar Pinjaman</title>

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
				<h1 class="page-header">Pinjaman</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<table class="table table-bordered table-condensed" style="font-size: 11px; margin-top: 10px;">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nama Pinjaman</th>
						<th>Kategori Pinjaman</th>
						<th>Anggota</th>
						<th>Besar Pinjaman</th>
						<th>Tanggal Pengajuan</th>
						<th>Tanggal Acc</th>
						<th>Tanggal Pinjaman</th>
						<th>Tanggal Pelunasan</th>
						<th>Keterangan</th>
						<!-- <th colspan="2">Aksi</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listPinjaman}" var="pinjaman">
						<tr>
							<td>${pinjaman.id}</td>
							<td>${pinjaman.namaPinjaman}</td>
							<td>${pinjaman.kategoriPinjaman.namaPinjaman}</td>
							<td>${pinjaman.pengguna.namaPengguna}</td>
							<td>${pinjaman.besarPinjaman}</td>
							<td>${pinjaman.tglPengajuanPinjaman}</td>
							<td>${pinjaman.tglAccPinjaman}</td>
							<td>${pinjaman.tglPinjaman}</td>
							<td>${pinjaman.tglPelunasan}</td>
							<td>${pinjaman.ket}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<hr/>
			<a href="add_pinjaman"><button type="button" id="tambah" class="btn btn-sm btn-primary">Tambah</button></a>
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