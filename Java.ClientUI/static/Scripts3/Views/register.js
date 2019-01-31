$(document).ready(function () {
    $('#btnRegister').on('click', loginJS.doRegister);

    //Check Enter key is pressed
    $('#formRegister').keypress(function (e) {
        if (e.which == 13 || e.keyCode == 13)  // the enter key code
        {
            $('#btnRegister').click();
            return false;
        }
    });
})

/**
 * Object JS phục vụ cho trang Login
 */
var loginJS = Object.create({
    /*
     * Hàm xử lý khi nhấn Button Đăng ký
     * Created by: NVMANH (28/12/2018) 
     * */
    doRegister: function () {
        //Check if is valid
            var jsondata = {contactMobile: $('#txtContactMobile').val(), 
                    contactEmail: $('#txtContactEmail').val(), 
                    password: $('#txtPassword').val()};
            $.ajax({
                method:"POST",
                url: MISA.Config.loginUrl+"/api/register",
                contentType:"application/json",
                data: JSON.stringify(jsondata),                     
                success: function(data, textStatus, xhr){
                    $('#register-error').hide();
                    if(textStatus=="success") {
                        commonJS.showSuccessMsg("Đăng kí thành công!Chuyển tới đăng nhập!")
                        setTimeout(function(){
                            window.location.href="/";
                        }, 2000)
                        
                    }
                },
                error: function(data, txtStatus, xhr){
                    if(data.status == 409){
                        $('#register-error').html("<li>Tài khoản đã tồn tại!</li>");
                        $('#register-error').addClass('red-color');
                    }    
                    else {
                        $('#register-error').html("<li>Lỗi đăng kí. Vui lòng thử lại</li>");
                        $('#register-error').addClass('red-color');
                    }
                        
                }
                        
            });
    }
})

