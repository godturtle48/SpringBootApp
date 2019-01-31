/**

 * 
 */

$(document).ready(function(){
	if(localStorage.getItem("authenCookie") != "" && localStorage.getItem("authenCookie") != null){
		$.ajax({
			method: "GET",
			url:MISA.Config.loginUrl+"/api/home",
			beforeSend: function(xhr) {
			      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
			},
			success: function(data, status, xhr){
				$('#user-info').text((data.email).split("@")[0]);
				//ajax goi company
				$.ajax({
					method:"get",
					url:MISA.Config.loginUrl+"/getCompanyUser",
					beforeSend: function(xhr){
						xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
					},
					success: function(data){
						console.log(data);
						appendCompany(data);
						initGoToWorkspaceOncClickEvent();
						
					},
					error: function(err){
						console.log("chua co cong ty nao");
					}
				})
			},
			error: function(err, stt, xhr){
				window.location.href="/login.html";
			}
		})	
	}
	else{
		window.location.href="/login.html";
	}
	
})

//add more company
//lúc ấn vào button thêm sẽ gửi về server. 
$('#addBtn').click(function(){
	var comData = {"name": $('#companyName').val(),
					"address":$('#companyAddress').val(),
					"companyTaxNumber":$('#companyTaxNumber').val()};
	console.log(comData.name);
	$.ajax({
		method: "post",
		url: MISA.Config.loginUrl+"/addCompany",
		contentType:"application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("authorization", localStorage.getItem("authenCookie"));
		},
		data: JSON.stringify(comData),
		success: function(data, txtStatus, xhr){
			console.log(xhr.status);
			if(xhr.status == 200){
				noticeAlert(1, "Thêm công ty làm việc thành công!");
				var totalCompany = localStorage.getItem("totalCompany");
				$('#tb-company tbody').append(
	                    '<tr>'    
	                        +'<td>' + totalCompany + '</td>'
	                        +'<td>' + comData.name + '</td>'
	                        +'<td style="display: none;">' + comData.companyTaxNumber + '</td>'
	                        +'<td>'
	                            +'<button type="button" class="btn btn-primary goToWorkspace">'
	                                +'Đi đến không gian làm việc'
	                            +'</button>'
	                            +'<button type="button" class="btn btn-warning" id="editCompany">'
	                                +'Chỉnh sửa thông tin'
	                            +'</button>'
	                        +'</td>'
	                    +'</tr>')
	              initGoToWorkspaceOncClickEvent();
	              localStorage.setItem("totalCompany", totalCompany + 1);
			}
		},
		error: function(data, txtStatus, xhr){
			if(data.status == 406){
				noticeAlert(1, "Cá nhân đã làm cho công ty này!");
			}
			if(data.status == 200){
				noticeAlert(1, "xảy ra lỗi khi thêm chứng từ!");
			}
		}
	})
})


//go to work space

//init  event for goToWorkspace
function initGoToWorkspaceOncClickEvent(){
		$('.goToWorkspace').on('click', function(){
			var workCompanyId = $(this).parents('tr').find("td:eq(2)").text();
			console.log(workCompanyId);
			localStorage.setItem("workCompanyID", workCompanyId);
			console.log(localStorage.getItem("workCompanyID"));
			window.location.href="/rae.html";
		})
}

//logout
$('#btnLogout').click(function(){
	localStorage.setItem("authenCookie","");
	window.location.href="/login.html";
})

//add company popup
//$('#btnAdd').click(function(){
//	$('#popupAdd').show();
//})

$(document).ready(function () {
    $(document).click(function () {
        var target = event.target;
        if (!$(target).hasClass('hide-if-outside')) {
            $('.hide-if-outside').hide();
        }
    })
});

$('#btnAdd').click(function(){
	$('.add-section').show();
	$('#btnAdd').hide();
})

$('#cancelBtn').click(function(){
	$('.add-section').hide();
	$('#btnAdd').show();
})
//alert

//append function

function appendCompany(data){
	var i=0;
	while(data[i] != null){
		$('#tb-company tbody').append(
                    '<tr>'    
                        +'<td>' + i + '</td>'
                        +'<td>' + data[i].name + '</td>'
                        +'<td style="display: none;">' + data[i].companyTaxNumber + '</td>'
                        +'<td>'
                            +'<button type="button" class="btn btn-primary goToWorkspace">'
                                +'Đi đến không gian làm việc'
                            +'</button>'
                            +'<button type="button" class="btn btn-warning" id="editCompany">'
                                +'Chỉnh sửa thông tin'
                            +'</button>'
                        +'</td>'
                    +'</tr>')
		i = i + 1;
	}
	localStorage.setItem("totalCompany", i);
}


function noticeAlert(stt, mes){
	//1 = success, 2 = error
	if(stt == 0) {
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","red");
		$('#statusField').text(mes);
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 2000);
	}
	else if(stt == 1){
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","green");
		$('#statusField').text(mes);
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 2000);	
	}
}
