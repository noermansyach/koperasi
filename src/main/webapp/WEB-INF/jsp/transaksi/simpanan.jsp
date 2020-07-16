<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

<head>
<jsp:include page="../meta.jsp" /> 
<title>Daftar Simpanan</title>

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
				<h1 class="page-header">Simpanan</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<table class="table table-bordered table-condensed" style="font-size: 11px; margin-top: 10px;">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tanggal</th>
						<th>Nama Pengguna</th>
						<th>Nama Simpanan</th>
						<th>Keterangan</th>
						<th>Jumlah Simpanan</th>
						<!-- <th colspan="2">Aksi</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listSimpanan}" var="simpanan">
						<tr>
							<td>${simpanan.id}</td>
							<td>${simpanan.tglSimpanan}</td>
							<td>${simpanan.pengguna.namaPengguna}</td>
							<td>${simpanan.namaSimpanan}</td>
							<td>${simpanan.ket}</td>
							<td>${simpanan.jumlahTambahan}</td>
							
					        <!-- <td>
					        	<a href="/edit_kategori_pinjaman?id=${simpanan.id}">Edit</a>
					        </td>
					        <td>
					        	<a href="/delete_kategori_pinjaman?id=${simpanan.id}" onclick="return confirm('Yakin menghapus data kategori pinjaman ?')">Hapus</a>
					        </td> -->
						</tr>
					</c:forEach>
						<c:if test = "${sessionScope.tipePengguna == '2'}">
						<tr>
							<td colspan="5" align="right">Total Simpanan : </td>
							<td>
								${totalSimpanan}
							</td>
						</tr>
						</c:if>
				</tbody>
				
			</table>
			<hr/>
			<a <c:if test = "${sessionScope.tipePengguna == '1'}"> href="/add_simpanan"</c:if>><c:if test = "${sessionScope.tipePengguna == '1'}"><button type="button" id="tambah" class="btn btn-sm btn-primary">Tambah</button></c:if></a>
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