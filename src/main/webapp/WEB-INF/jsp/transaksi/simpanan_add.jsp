<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<jsp:include page="../meta.jsp" /> 
<title>Tambah Simpanan</title>

<!-- Bootstrap Core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/font-awesome.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="assets/css/4-col-portfolio.css" rel="stylesheet">
<link href="assets/dist/css/bootstrap-select.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css">
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
				<h1 class="page-header">Tambah Simpanan</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<form id="form" action="/simpanan_add" method="post">
					<table id="table" class="table table-condensed" style="font-size: 11px; margin-top: 10px;">
						<tbody class="data">
							<tr>
								<td align="right"><div style="margin-top: 5px;width: 200px;">Nama Anggota :</div></td>
								<td>
									<div style="width: 200px;">
									<select name="pengguna" id="pengguna" class="selectpicker show-tick form-control" data-live-search="true" title="Pilih Anggota" data-width="100%" required>
									
									<c:forEach items="${listAnggota}" var="anggota">
										<option value="${anggota.id}">${anggota.namaPengguna} - ${anggota.noTlp}</option>
									</c:forEach>
									
									</select>
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Nama Simpanan :</div></td>
								<td>
									<div style="width: 300px;">
										<input type="text" id="namaSimpanan" name="namaSimpanan" class="form-control input-sm" />
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Tanggal Simpanan :</div></td>
								<td>
									<div class='input-group date' id='datepicker' style="width: 200px;">
										<input type='text' id="tglSimpanan" name="tglSimpanan" class="form-control" value="" placeholder="Tanggal..." required /> 
										<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Tambah simpanan :</div></td>
								<td>
									<div style="width: 200px;">
										<input type="number" id="jumlahTambahan" name="jumlahTambahan" class="form-control input-sm" />
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Keterangan :</div></td>
								<td>
									<div style="width: 300px;">
										<input type="text" id="ket" name="ket" class="form-control input-sm" />
									</div>
								</td>
							</tr>
							<tr>
								<td>
								</td>
								<td>
									<button id="submit" type="submit" class="btn btn-info btn-sm">
										<span class="fa fa-plus"></span> Tambah
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

	<!-- jQuery -->
	<script src="assets/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="assets/dist/js/bootstrap-select.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.price_format.min.js"></script>
	<script src="assets/js/moment.js"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
	<!-- <script src="assets/js/simpanan.js"></script> -->
	<script type="text/javascript">
		$(function() {
			$('#datepicker').datetimepicker({
				format : 'YYYY-MM-DD',
			});
		});
	</script>
</body>
</html>