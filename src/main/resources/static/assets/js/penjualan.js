var counter = 0;
var objBarang = "";
var total = 0;
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
			getAllBarang();
		},
		type: 'GET'
	});		
});

$("#ok").click(function(){
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
    if (Number($("#qty").val()) > Number($("#stok").val())) {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Jumlah tidak boleh melebihi stok");
		$("#qty").focus();
    	return false;
    }
    if (Number($("#diskon").val().replace(',','')) > Number($("#harjul").val().replace(',',''))) {
		$("#alert").attr("style", "display: block;");
		$("#alert").html("Diskon tidak boleh melebihi harga jual");
		$("#diskon").focus();
    	return false;
    }	      
	addRow();   
});

$("#simpan").click(function() {
	generateJson();
});


$("#jml_uang").keyup(function(){
	// hitung kembalian
	var uang = Number($("#jml_uang").val().replace(',',''));
	var kembalian = uang - total;
	$("#kembalian").val(addCommas(kembalian));
});

function getAllBarang()
{
	//var jsonBarang = '{"returnCode":0,"errorMessage":"","barang":[{"id":1,"kodeBarang":"BR000043","namaBarang":"Engkel Omi KK","satuan":"PCS","hargaPokok":15000,"hargaEceran":20000,"hargaGrosir":6000,"stok":25,"minStok":0,"kategori":"Omi"},{"id":2,"kodeBarang":"BR000044","namaBarang":"Saklar Seri Omi KK","satuan":"Butir","hargaPokok":10000,"hargaEceran":7500,"hargaGrosir":6500,"stok":5,"minStok":1,"kategori":"Omi"},{"id":3,"kodeBarang":"BR000032","namaBarang":"Saklar Engkel Visalux B","satuan":"PCS","hargaPokok":7250,"hargaEceran":10000,"hargaGrosir":7750,"stok":7,"minStok":1,"kategori":"Visalux"},{"id":4,"kodeBarang":"BR000042","namaBarang":"Saklar Arde Visalux 6L","satuan":"PCS","hargaPokok":19500,"hargaEceran":25000,"hargaGrosir":21000,"stok":5,"minStok":1,"kategori":"Visalux"},{"id":5,"kodeBarang":"BR000023","namaBarang":"Saklar Seri Sheineder B","satuan":"PCS","hargaPokok":22000,"hargaEceran":25000,"hargaGrosir":23000,"stok":2,"minStok":1,"kategori":"Sheineder"},{"id":6,"kodeBarang":"BR000024","namaBarang":"Stop Kontak Sheineder B","satuan":"PCS","hargaPokok":16000,"hargaEceran":20000,"hargaGrosir":17000,"stok":1,"minStok":1,"kategori":"Sheineder"}]}';
    //objBarang = jQuery.parseJSON(jsonBarang);
	
    var output = '';
    for (var i = 0; i < Object.keys(objBarang.barang).length; i++) {
        output += '<option value = "' + i + '">' + objBarang.barang[i].namaBarang + '</option>';
    }
    $("#nabar").html(output);
    $("#nabar").selectpicker('refresh');
    $("#nabar").on("change", function(){
    	clearField();
        var c = $(this).val();
        var obj = objBarang.barang[c];
        $('#index').val(c);
        $('#idhidden').val(obj.id);
        $('#kode_brg').val(obj.kodeBarang);
        $('#satuan').val(obj.satuan);
        $('#stok').val(obj.stok);
        $('#harjul').val(addCommas(obj.hargaEceran));
    });
}

function addRow()
{
    var harga = $("#harjul").val().replace(',','');
    var diskon = $("#diskon").val().replace(',','');
    var qty = $("#qty").val();
    var subtotal = (harga - diskon) * qty;

    var row = '<tr id="row'+counter+'">'+
    				'<input type="hidden" id="id'+counter+'" value="'+$('#idhidden').val()+'"/>'+
    				'<input type="hidden" id="index'+counter+'" value="'+$('#index').val()+'"/>'+
                    '<td id="kd'+counter+'">' + $('#kode_brg').val() +'</td>' + 
                    '<td id="nabar">'+ $('#nabar :selected').text() +'</td>' +
                    '<td id="satuan" style="text-align: center;">'+ $('#satuan').val() +'</td>' +
                    '<td id="harjul'+counter+'" style="text-align: right;">'+ $('#harjul').val() +'</td>' +
                    '<td id="diskon'+counter+'" style="text-align: right;">'+ $('#diskon').val() +'</td>' +
                    '<td id="jumlah'+counter+'" style="text-align: center;">'+ $('#qty').val() +'</td>' +
                    '<td id="subtotal'+counter+'" style="text-align: right;">'+ addCommas(subtotal) +'</td>' +
                    '<td class="batal" style="text-align: center;"><button type="button" onclick="deleteRow('+counter+','+subtotal+')" class="btn btn-warning btn-xs"><span class="fa fa-close"></span> Batal</button></td>' +
                '</tr>';

    $("#tabelPenjualan tbody").append(row);
    
    // hitung total
	total = total + subtotal;
	$("#total2").val(addCommas(total));
	$("#jml_uang").val(addCommas(total));
	
	// hitung kembalian
	var uang = Number($("#jml_uang").val().replace(',',''));
	var kembalian = uang - total;
	$("#kembalian").val(addCommas(kembalian));
	
	// hitung sisa stok
    var index_barang = $('#nabar').val();
    var obj = objBarang.barang[index_barang];
    obj.stok = obj.stok - qty;
    $('#stok').val(obj.stok);
    
    counter += 1;
}

function deleteRow(counter, subtotal)
{
	// hitung sisa stok
    var index_barang = $("#index"+counter).val();
    var obj = objBarang.barang[index_barang];
    obj.stok = obj.stok + Number($("#jumlah"+counter).text());
    if (index_barang == $('#index').val()) {
    	$("#stok").val(obj.stok);	
    }
    
	$('#row'+counter).closest('tr').remove();

    // hitung total
	total = total - subtotal;
	$("#total2").val(addCommas(total));
	$("#jml_uang").val(addCommas(total));
	
	// hitung kembalian
	var uang = Number($("#jml_uang").val().replace(',',''));
	var kembalian = uang - total;
	$("#kembalian").val(addCommas(kembalian));
}

function setObjBarang(id, kd, hj, dis, jml, tot) {
    var barang = {}
	barang["id"] = id;
    barang["kodeBarang"] = kd;
	barang["hargaJual"] = hj;
	barang["diskon"] = dis;
	barang["jumlah"] = jml;
	barang["subTotal"] = tot;
	return barang;
}

function generateJson() {	
	var jsonObj = {};
	var objBarang = []
	for (var i = 0; i <= counter; ++i) {
		if($("#row"+i).length != 0) {
			var id = $("#id"+i).val();
			var kd = $("#kd"+i).text();
			var hj = $("#harjul"+i).text().replace(',','');
			var dis = $("#diskon"+i).text().replace(',','');
			var jum = $("#jumlah"+i).text();
			var tot = $("#subtotal"+i).text().replace(',','');
			objBarang.push(setObjBarang(new Number(id), kd, new Number(hj), new Number(dis), new Number(jum), new Number(tot)));
		}
	}
	jsonObj["totalBelanja"] = new Number($("#total2").val().replace(',',''));
	jsonObj["tunai"] = new Number($("#jml_uang").val().replace(',',''));
	jsonObj["kembalian"] = new Number($("#kembalian").val().replace(',',''));
	jsonObj["barang"] = objBarang;
	var json = JSON.stringify(jsonObj);
	//alert(json);
	$.ajax({
		url: 'http://localhost:8080/tr/postpenjualan',
		data: json,		
		headers: { 'token': token },
		error: function() {
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Error koneksi saat simpan data penjualan");
		},
		success: function(data) {
			$("#output").val(data);
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Simpan data penjualan telah dilakukan");	
			
			for (var i = 0; i <= counter; ++i) {
				if($("#row"+i).length != 0) {
					$("#row"+i).remove();
				}
			}	
			$("#nabar").val('');
			$("#nabar").selectpicker('refresh');
			$("#total2").val('0');
			$("#jml_uang").val('0');
			$("#kembalian").val('0');
			
			clearField();
		},
		contentType: 'application/json',
		type: 'POST'
	});		
}

function clearField() {
	$('#diskon').val('0');
	$('#qty').val('');
    $('#index').val('');
    $('#idhidden').val('');
    $('#kode_brg').val('');
    $('#satuan').val('');
    $('#stok').val('');
    $('#harjul').val('');	
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
