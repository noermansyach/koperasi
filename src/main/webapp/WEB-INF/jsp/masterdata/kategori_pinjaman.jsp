<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

<head>
<jsp:include page="../meta.jsp" /> 
<title>Daftar Kategori Pinjaman</title>

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
				<h1 class="page-header">Daftar Kategori Pinjaman</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<table class="table table-bordered table-condensed" style="font-size: 11px; margin-top: 10px;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nama</th>
						<th colspan="2">Aksi</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listKategoriPinjaman}" var="kategori">
						<tr>
							<td>${kategori.id}</td>
							<td>${kategori.namaPinjaman}</td>
					        <td>
					        	<a href="/edit_kategori_pinjaman?id=${kategori.id}">Edit</a>
					        </td>
					        <td>
					        	<a href="/delete_kategori_pinjaman?id=${kategori.id}" onclick="return confirm('Yakin menghapus data kategori pinjaman ?')">Hapus</a>
					        </td>  									
						</tr>
				</tbody>
				</c:forEach>
			</table>
			<hr/>
			<a href="/add_kategori_pinjaman"><button type="button" id="tambah" class="btn btn-sm btn-primary">Tambah</button></a>
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