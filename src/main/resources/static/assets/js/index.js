$(document).ready(function(){
	$("form").submit(function(){
		var regUsername = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$");
		if (!regUsername.test($('#nama').val())) {
			$("#alert").attr("style", "display: block;");
			$("#alert").html("Username tidak valid, format username harus berupa format email");
			$("#nama").focus();
			return false;
		}
		
//		$.ajax({
//			url : "/login",
//			type : "post",
//			success : function(response){
//				if (response.returnCode != 0){
//					alert("gagal login");
//				}
//			},
//			error : function(jqXHR){
//				alert("error koneksi");
//			}
//		})
	})
	
})