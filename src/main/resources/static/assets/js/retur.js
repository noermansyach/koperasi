var counter = 0;
var objBarang = "";
var token;

function setToken(t) {
	token = t;
}

$(document).ready(function() {		
	$.ajax({
		url: 'http://localhost:8080/tr/getallbarang',
		data: {
			
		},		
		error: function() {
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Error koneksi mengambil data barang");
		},
		success: function(data) {
			objBarang = data;
			if (objBarang.returnCode != 0) {
				$("#alert").attr("style", "display: block;");
				$("#alert").html(objBarang.errorMessage);
			}
			getBarangList();
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
	if ($("#qty").val() == "" || Number($("#qty").val()) < 1) {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Jumlah harus diisi");
		$("#qty").focus();
		return false;
	}	
    var jumlah = new RegExp("^[0-9]+$");
    if (!jumlah.test($("#qty").val())) {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Jumlah tidak valid");
		$("#qty").focus();
    	return false;
    }		
	addRow();
});

$("#simpan").click(function() {
	generateJson();
});

function getBarangList(){
	//var jsonBarang = '{"returnCode":0,"errorMessage":"","barang":[{"id":1,"kodeBarang":"BR000043","namaBarang":"Engkel Omi KK","satuan":"PCS","hargaPokok":15000,"hargaEceran":20000,"hargaGrosir":6000,"stok":25,"minStok":0,"kategori":"Omi"},{"id":2,"kodeBarang":"BR000044","namaBarang":"Saklar Seri Omi KK","satuan":"Butir","hargaPokok":10000,"hargaEceran":7500,"hargaGrosir":6500,"stok":5,"minStok":1,"kategori":"Omi"},{"id":3,"kodeBarang":"BR000032","namaBarang":"Saklar Engkel Visalux B","satuan":"PCS","hargaPokok":7250,"hargaEceran":10000,"hargaGrosir":7750,"stok":7,"minStok":1,"kategori":"Visalux"},{"id":4,"kodeBarang":"BR000042","namaBarang":"Saklar Arde Visalux 6L","satuan":"PCS","hargaPokok":19500,"hargaEceran":25000,"hargaGrosir":21000,"stok":-5,"minStok":1,"kategori":"Visalux"},{"id":5,"kodeBarang":"BR000023","namaBarang":"Saklar Seri Sheineder B","satuan":"PCS","hargaPokok":22000,"hargaEceran":25000,"hargaGrosir":23000,"stok":-2,"minStok":1,"kategori":"Sheineder"},{"id":6,"kodeBarang":"BR000024","namaBarang":"Stop Kontak Sheineder B","satuan":"PCS","hargaPokok":16000,"hargaEceran":20000,"hargaGrosir":17000,"stok":1,"minStok":1,"kategori":"Sheineder"}]}';
	//var objBarang = jQuery.parseJSON(jsonBarang); 
	var output = '';
	for (var i = 0; i < Object.keys(objBarang.barang).length; ++i) {
		output += '<option value="' +i+'" >' + objBarang.barang[i].namaBarang + '</option>';
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
		$('#harpok').val(addCommas(obj.hargaPokok));
	});
}

function addRow() {
	var d = new Date();
	var mm = d.getMonth() + "";
	if (mm.length == 1) {
		mm = "0" + mm;
	}
	var dd = d.getDate() + "";
	if (dd.length == 1) {
		dd = "0" + dd;
	}
	var dn =  d.getFullYear() + '-' + mm + '-' + dd;
	counter = counter + 1;
	var subTotal = $('#harpok').val().replace(',','')*$('#qty').val();
	var rows = '<tr id="row'+counter+'">'+
				'<input type="hidden" id="id'+counter+'" value="'+$('#idhidden').val()+'"/>'+
			  	'<td id="dn'+counter+'">'+dn+'</td>'+
			  	'<td id="kd'+counter+'">'+$('#kode_brg').val()+'</td>'+
				'<td>'+$('#nabar option:selected').text()+'</td>'+
				'<td style="text-align: center;">'+$('#satuan').val()+'</td>'+
				'<td id="harpok'+counter+'" style="text-align: right;">'+$('#harpok').val()+'</td>'+
				'<td id="qty'+counter+'" style="text-align: center;">'+$('#qty').val()+'</td>'+
				'<td style="text-align: right;">'+addCommas(subTotal)+'</td>'+
				'<td id="ket'+counter+'" style="text-align: center;">'+$('#keterangan').val()+'</td>'+
				'<td style="text-align: center;"><button class="btn btn-warning btn-xs" onclick="deleteRow('+counter+','+subTotal+')"><span class="fa fa-close"></span> Batal</button></td></tr>';
	$("#dataRetur tbody").append(rows);
}

function setObjBarang(id, tg, kd, ket, hj, jml) {
    var barang = {}
	barang["id"] = id;
    barang["tanggal"] = tg;
    barang["kodeBarang"] = kd;
	barang["hargaJual"] = hj;
	barang["jumlah"] = jml;
	barang["keterangan"] = ket;
	return barang;
}

function generateJson() {	
	var jsonObj = {};
	var objBarang = []
	for (var i = 0; i <= counter; ++i) {
		if($("#row"+i).length != 0) {
			var id = $("#id"+i).val();
			var tg = $("#dn"+i).text();
			var kd = $("#kd"+i).text();
			var hj = $("#harpok"+i).text().replace(',','');
			var ket = $("#ket"+i).text();
			var j = $("#qty"+i).text();
			objBarang.push(setObjBarang(new Number(id), tg, kd, ket, new Number(hj), new Number(j)));
		}
	}
	jsonObj["barang"] = objBarang;
	var json = JSON.stringify(jsonObj);
	//alert(json);
	$.ajax({
		url: 'http://localhost:8080/tr/postretur',
		data: json,		
		headers: { 'token': token },
		error: function() {
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Error koneksi saat simpan data retur");
		},
		success: function(data) {
			$("#output").val(data);
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Simpan data retur telah dilakukan");	
			
			for (var i = 0; i <= counter; ++i) {
				if($("#row"+i).length != 0) {
					$("#row"+i).remove();
				}
			}	
			$("#nabar").val('');
			$("#nabar").selectpicker('refresh');
			
			clearField();
		},
		contentType: 'application/json',
		type: 'POST'
	});			
}

function deleteRow(counter, subTotal) {
	$('#row'+counter).closest('tr').remove();
}

function clearField() {
	$('#idhidden').val('');
	$('#nabarhidden').val('');
	$('#kode_brg').val('');
	$('#satuan').val('');
	$('#harpok').val('');
	$('#qty').val('');
	$('#keterangan').val('');
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