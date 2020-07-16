<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>

<title>Tambah Pengguna</title>

<!-- Bootstrap Core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/font-awesome.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="assets/css/4-col-portfolio.css" rel="stylesheet">
<link href="assets/dist/css/bootstrap-select.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css">
<html lang="en">

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
				<h1 class="page-header">Tambah Pengguna</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<form id="form" action="/save_pengguna" method="post">
					<table id="table" class="table table-condensed" style="font-size: 11px; margin-top: 10px;">
						<tbody class="data">
							<tr>
								<td align="right"><div style="margin-top: 5px;width: 200px;">Nama Pengguna :</div></td>
								<td>
									<div style="width: 200px;">
									<input type="text" id="namaPengguna" name="namaPengguna" class="form-control" /></div>
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Alamat :</div></td>
								<td>
									<div style="width: 200px;">
										 <input type="text" id="alamat" name="alamat" class="form-control" />
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Tanggal Lahir :</div></td>
								<td>
									<div class='input-group date' id='datepicker' style="width: 200px;">
										<input type='text' id="tglLhr" name="tglLhr" class="form-control" value="" placeholder="Tanggal..." required /> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Tempat Lahir :</div></td>
								<td>
									<div style="width: 200px;">
										<input type='text' id="tmpLhr" name="tmpLhr" class="form-control" required />
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Status :</div></td>
								<td>
									<input type="radio" id="status" name="status" value="true" /> Aktif &nbsp;
    								<input type="radio" id="status" name="status" value="false" /> Tidak Aktif
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">No. Telepon :</div></td>
								<td>
									<div style="width: 200px;">
										<input type="text" id="noTlp" name="noTlp" class="form-control" />
									</div>
								</td>
							</tr>
							<tr>
								<td align="right"><div style="margin-top: 5px;">Keterangan :</div></td>
								<td>
									<div style="width: 300px;">
										<input type="text" id="ket" name="ket" class="form-control" />
									</div>
								</td>
							</tr>

							<tr>
								<td align="right"><div style="margin-top: 5px;">Tipe Anggota :</div></td>
								<td>
									<div style="width: 300px;">
										<select id="tipeAnggota" name="tipeAnggota" class="selectpicker show-tick form-control" data-live-search="true" title="Pilih Tipe Anggota">
								    		<option value='1'>Petugas</option>
								    		<option value='2'>Anggota</option>
								    	</select>
									</div>
								</td>
							</tr>

							<tr>
								<td align="right"><div style="margin-top: 5px;">Password :</div></td>
								<td>
									<div style="width: 300px;">
										<input type="password" id="password" name="password" class="form-control"/>
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
									<a href="/pengguna"><button type="button" class="btn btn-sm btn-primary">Kembali</button></a>
								</td>
							</tr>
						</tbody>
				    </table>
			    </form>
		    </div>
    		<jsp:include page="../footer.jsp" /> 
		</div>
	</div>

    <!-- jQuery -->
	<script src="assets/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="assets/dist/js/bootstrap-select.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.price_format.min.js"></script>
	<script src="assets/js/moment.js"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
	<script src="assets/js/beli-barang.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#datepicker').datetimepicker({
				format : 'YYYY-MM-DD',
			});
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$('#harpok').priceFormat({
				prefix : '',
				//centsSeparator: '',
				centsLimit : 0,
				thousandsSeparator : ','
			});
			$('#harjul').priceFormat({
				prefix : '',
				//centsSeparator: '',
				centsLimit : 0,
				thousandsSeparator : ','
			});
		});
	</script>
    <script type="text/javascript">
    	function dateChange() {
    		var d = new Date(document.getElementById('tgl').value),
			        month = '' + (d.getMonth() + 1),
			        day = '' + d.getDate(),
			        year = d.getFullYear();
				    if (month.length < 2) 
				        month = '0' + month;
				    if (day.length < 2) 
				        day = '0' + day;
		    document.getElementById('tglLhr').value = [year, month, day].join('-') + ' 00:00:00';
    	}
    </script>
</body>
</html>