var hargaTotal = 0;
var counter = 0;

var objAnggota = "";
var objBarang = "";

var token;

function setToken(t) {
	token = t;
}

$(document).ready(function() {	
	$.ajax({
		url: 'http://localhost:8080/getallpengguna',
		data: {
			
		},		
		error: function() {
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Error koneksi mengambil data anggota");
		},
		success: function(data) {
			objAnggota = data;
			if (objAnggota.returnCode != 0) {
				$("#alert").attr("style", "display: block;");
				$("#alert").html(objAnggota.errorMessage);
			}
			getAnggotaList();
		},
		type: 'GET'
	});
});

$("#ok").click(function() {  	
	$("#alert").attr("style", "display: none;");
	if ($("#nabar").val() == "") {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Barang harus dipilih");
		$("#nabar").focus();
		return false;
	}
	if ($("#harpok").val() == "") {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Harga pokok harus diisi");
		$("#harpok").focus();
		return false;
	}
	if ($("#harjul").val() == "") {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Harga jual harus diisi");
		$("#harjul").focus();
		return false;
	}	
	if ($("#jumlah").val() == "" || Number($("#jumlah").val()) < 1) {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Jumlah harus diisi");
		$("#jumlah").focus();
		return false;
	}	
    var jumlah = new RegExp("^[0-9]+$");
    if (!jumlah.test($("#jumlah").val())) {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Jumlah tidak valid");
		$("#jumlah").focus();
    	return false;
    }	
	addRow();
});

//$("#tgl").keydown(function(event) {event.preventDefault();event.stopPropagation();});

$("#simpan").click(function() {
	$("#alert").attr("style", "display: none;");
	if ($("#nofak").val() == "") {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Nomor faktur harus diisi");
		$("#nofak").focus();
		return false;
	}
	if ($("#tgl").val() == "") {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Tanggal harus diisi");
		$("#tgl").focus();
		return false;
	}	
	if ($("#supplier").val() == "") {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Suplier harus dipilih");
		$("#supplier").focus();
		return false;
	}	
	generateJson();
});


function getAnggotaList() {
	var output = '';
	for (var i = 0; i < Object.keys(objAnggota.pengguna).length; ++i) {
		output += '<option value="' + i + '" >' + objAnggota.pengguna[i].namaPengguna + '</option>';
	}
	$("#namaPengguna").html(output);	
	$("#namaPengguna").selectpicker('refresh');
	$("#namaPengguna").on("change", function () { 
		clearField();
		$('#totalSimpanan').val(objAnggota.pengguna[$(this).val()].totalSimpanan);
		$('#pengguna').val(objAnggota.pengguna[$(this).val()].id);
		$('#simpananSekarang').val(objAnggota.pengguna[$(this).val()].totalSimpanan);
	});
}

function getBarangList(){
	//var jsonBarang = '{"returnCode":0,"errorMessage":"","barang":[{"id":1,"kodeBarang":"BR000043","namaBarang":"Engkel Omi KK","satuan":"PCS","hargaPokok":15000,"hargaEceran":20000,"hargaGrosir":6000,"stok":25,"minStok":0,"kategori":"Omi"},{"id":2,"kodeBarang":"BR000044","namaBarang":"Saklar Seri Omi KK","satuan":"Butir","hargaPokok":10000,"hargaEceran":7500,"hargaGrosir":6500,"stok":5,"minStok":1,"kategori":"Omi"},{"id":3,"kodeBarang":"BR000032","namaBarang":"Saklar Engkel Visalux B","satuan":"PCS","hargaPokok":7250,"hargaEceran":10000,"hargaGrosir":7750,"stok":7,"minStok":1,"kategori":"Visalux"},{"id":4,"kodeBarang":"BR000042","namaBarang":"Saklar Arde Visalux 6L","satuan":"PCS","hargaPokok":19500,"hargaEceran":25000,"hargaGrosir":21000,"stok":-5,"minStok":1,"kategori":"Visalux"},{"id":5,"kodeBarang":"BR000023","namaBarang":"Saklar Seri Sheineder B","satuan":"PCS","hargaPokok":22000,"hargaEceran":25000,"hargaGrosir":23000,"stok":-2,"minStok":1,"kategori":"Sheineder"},{"id":6,"kodeBarang":"BR000024","namaBarang":"Stop Kontak Sheineder B","satuan":"PCS","hargaPokok":16000,"hargaEceran":20000,"hargaGrosir":17000,"stok":1,"minStok":1,"kategori":"Sheineder"}]}';
	//var objBarang = jQuery.parseJSON(jsonBarang); 
	var output = '';
	for (var i = 0; i < Object.keys(objBarang.barang).length; ++i) {
		output += '<option value="'+i+'" >' + objBarang.barang[i].namaBarang + '</option>';
	}
	$("#nabar").html(output);
	$("#nabar").selectpicker('refresh');
	$("#nabar").on("change", function () { 
		clearField();
		var value = $(this).val();
		var obj = objBarang.barang[value];
		$('#idhidden').val(obj.id);
		$('#kode_brg').val(obj.kodeBarang);
		$('#satuan').val(obj.satuan);
	});
}

function addRow() {
	var subTotal = $('#harpok').val().replace(',','')*$('#jumlah').val();
	counter = counter + 1;
	var rows = '<tr id="row'+counter+'">'+
				'<input type="hidden" id="id'+counter+'" value="'+$('#idhidden').val()+'"/>'+
				'<td id="kd'+counter+'">'+$('#kode_brg').val()+'</td>'+
				'<td>'+$('#nabar option:selected').text()+'</td>'+
				'<td style="text-align: center;">'+$('#satuan').val()+'</td>'+
				'<td id="harpok'+counter+'" style="text-align: right;">'+$('#harpok').val()+'</td>'+
				'<td id="harjul'+counter+'" style="text-align: right;">'+$('#harjul').val()+'</td>'+
				'<td id="jumlah'+counter+'" style="text-align: center;">'+$('#jumlah').val()+'</td>'+
				'<td style="text-align: right;">'+addCommas(subTotal)+'</td>'+
				'<td style="text-align: center;"><button class="btn btn-warning btn-xs" onclick="deleteRow('+counter+','+subTotal+')"><span class="fa fa-close"></span> Batal</button></td></tr>';
	$("#dataBeli tbody").append(rows);
	hargaTotal = hargaTotal + subTotal;
	$("#tdtotal").html("Rp. " + addCommas(hargaTotal));
}

function deleteRow(counter, subTotal) {
	hargaTotal = hargaTotal - subTotal;
	$('#row'+counter).closest('tr').remove();
	$("#tdtotal").html("Rp. " + addCommas(hargaTotal));
}

function setObjBarang(id, kd, hp, hj, jml) {
    var barang = {}
	barang["id"] = id;
	barang["kodeBarang"] = kd;
	barang["hargaPokok"] = hp;
	barang["hargaJual"] = hj;
	barang["jumlah"] = jml;
	return barang;
}

function generateJson() {	
	var jsonObj = {};
	var objBarang = []
	for (var i = 0; i <= counter; ++i) {
		if($("#row"+i).length != 0) {
			var id = $("#id"+i).val();
			var kd = $("#kd"+i).text();
			var hp = $("#harpok"+i).text().replace(',','');
			var hj = $("#harjul"+i).text().replace(',','');
			var j = $("#jumlah"+i).text();
			objBarang.push(setObjBarang(new Number(id), kd, new Number(hp), new Number(hj), new Number(j)));
		}
	}
	jsonObj["supplier"] = $("#supplier").val();
	jsonObj["nomorFaktur"] = $("#nofak").val();
	jsonObj["tanggal"] = $("#tgl").val();
	jsonObj["barang"] = objBarang;
	var json = JSON.stringify(jsonObj);
	//alert(json);
	$.ajax({
		url: 'http://localhost:8080/tr/postbelibarang',
		data: json,		
		headers: { 'token': token },
		error: function() {
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Error koneksi saat simpan data pembelian");
		},
		success: function(data) {
			$("#output").val(data);
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Simpan data pembelian telah dilakukan");	
			
			for (var i = 0; i <= counter; ++i) {
				if($("#row"+i).length != 0) {
					$("#row"+i).remove();
				}
			}	
			$("#nabar").val('');
			$("#supplier").val('');
			$("#nofak").val('');
			$("#tgl").val('');
			
			$("#nabar").selectpicker('refresh');
			$("#supplier").selectpicker('refresh');
			
			$("#tdtotal").html("Rp. -");
			
			clearField();
		},
		contentType: 'application/json',
		type: 'POST'
	});			
}

function clearField() {
	$('#totalSimpanan').val('');
	$('#pengguna').val('');
}

function addCommas(nStr)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}
