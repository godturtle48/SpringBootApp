﻿var AccountObjectData = [];
var Reason = [];
var dataResource = Object.create({
    AccountObject: [
        //COMBOBOX CHO PHIEU CHI
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY MISA",
            AccountObjectName: "Công ty cổ phần MISA",
            AccountObjectType: 2,
            Address: "Tòa nhà Technosoft, Phố Duy Tân, Dịch Vọng Hậu, Cầu Giấy, Hà Nội",
            ContactName: "Nguyễn Kim Ngọc"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY DIENLUC",
            AccountObjectName: "Công ty điện lực Hà Nội",
            AccountObjectType: 2,
            Address: "1078 Nguyễn Trãi, Thanh Xuân, Hà Nội",
            ContactName: "Nguyễn Hữu Thắng"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY MISA",
            AccountObjectName: "Công ty cổ phần MISA",
            AccountObjectType: 2,
            Address: "Tòa nhà Technosoft, Phố Duy Tân, Dịch Vọng Hậu, Cầu Giấy, Hà Nội",
            ContactName: "Nguyễn Kim Ngọc"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY HONGHA",
            AccountObjectName: "Công ty TNHH Hồng Hà",
            AccountObjectType: 2,
            Address: "1078 Phố Huế, Hoàn Kiếm, Hà Nội",
            ContactName: "Nguyễn Phan Mỹ Linh"                
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY LANTAN",
            AccountObjectName: "Công ty TNHH Lan Tân",
            AccountObjectType: 2,
            Address: "1254 Lê Lai, Ba Đình, Hà Nội",
            ContactName: "Phạm Thị Mai Phương"                 
        },
        //COMBOBOX CHO PHIEU THU
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY MINHANH",
            AccountObjectName:  "Công ty TNHH Minh Anh",
            AccountObjectType: 1,
            Address: "1251 Nguyễn Khánh Toàn, Cầu Giấy, Hà Nội",
            ContactName: "Hà Thị Hằng"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY MINHHUONG",
            AccountObjectName: "Công ty TNHH Minh Hương",
            AccountObjectType: 1,
            Address:  "3021 Kim Mã, Ba Đình, Hà Nội",
            ContactName: "Phạm Thục Trinh"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY PHUVINH",
            AccountObjectName: "Công ty TNHH Phú Vinh",
            AccountObjectType: 1,
            Address: "2548 Tràng Thi, Hoàn Kiếm, Hà Nội",
            ContactName: "Hà Đức Phú"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY SONGCONG",
            AccountObjectName: "Công ty vận tải Sông Công",
            AccountObjectType: 1,
            Address: "214 Nguyễn Văn Cừ, Long Biên, Hà Nội",
            ContactName: "Vũ Ngọc Mai"                 
        },
        {
            AccountObjectNumber: 1,
            AccountObjectCode: "CTY THIENTAN",
            AccountObjectName: "Công ty cổ phần Thiên Tân",
            AccountObjectType: 1,
            Address: "2310 Láng Hạ, Đống Đa, Hà Nội",
            ContactName: "Phạm Thị Mai Phương"                 
        },
       
    ],
    Reason: [
        //LY DO CHO PHIEU CHI
        {
            ReasonID: 1,
            RefType: 2,
            ReasonName: "Chi tiền điện",
            EmployeeName: "Nguyễn Đình Quân"
        },
        {
            ReasonID: 2,
            RefType: 2,
            ReasonName: "Mua thiết bị mới",
            EmployeeName: "Nguyễn Công Thành"
        },
        {
            ReasonID: 3,
            RefType: 2,
            ReasonName: "Mua máy tính đào tạo",
            EmployeeName: "Đoàn Văn Quân"
        },
        {
            ReasonID: 4,
            RefType: 2,
            ReasonName: "Du lịch cho fresher",
            EmployeeName: "Nguyễn Văn Lâm"
        },
        {
            ReasonID: 5,
            RefType: 2,
            ReasonName: "Chi khác",
            EmployeeName: "Trần Thị Huyền"
        },
        //LY DO CHO PHIEU THU
        {
            ReasonID: 6,
            RefType: 1,
            ReasonName: "Thu phí đào tạo phần mềm",
            EmployeeName: "Nguyễn Thế Chí Dũng"
        },
        {
            ReasonID: 7,
            RefType: 1,
            ReasonName: "Thu tiền gửi",
            EmployeeName: "Nguyễn Công Thành"
        },
        {
            ReasonID: 8,
            RefType: 1,
            ReasonName: "Thu tiền nước",
            EmployeeName: "Đoàn Văn Quân"
        },
        {
            ReasonID: 9,
            RefType: 1,
            ReasonName: "Thu thuế GTGT",
            EmployeeName: "Nguyễn Văn Lâm"
        },
        {
            ReasonID: 10,
            RefType: 1,
            ReasonName: "Thu khác",
            EmployeeName: "Trần Thị Huyền"
        },
    ]

})





//get data for conbobox
var getCustomerDetail = function(){
    dataResource.AccountObject = [];
    $.ajax({
        method: 'get',
        url: MISA.Config.paymentUrl + "/getCustomerDetail:2",
        beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', localStorage
                    .getItem("authenCookie"));
            xhr.setRequestHeader("keycompany", localStorage.getItem("workCompanyID"));
        },
        success: function(res){
            console.log(res);
            for (var i = 0; i < res.length; i++) {
                 if(res[i] == null) break;                    
                (function () {                        
                    dataResource.AccountObject.push({                            
                        AccountObjectNumber: 1,
                        AccountObjectCode: res[i].accountObjectID,
                        AccountObjectName: res[i].accountObjectName,
                        AccountObjectType:2,
                        Address: res[i].accountObjectAddress,
                        ContactName:res[i].accountObjectContactName                       
                    });   
                     dataResource.Reason.push({                            
                            ReasonID: res[i].journalMemo,
                            RefType: 2,
                            ReasonName: res[i].description,
                            EmployeeName: res[i].employeeName                      
                    });                   
                })(i)                 
            }
                        
        },
        error: function(){

        }
    });
    $.ajax({
        method: 'get',
        url: MISA.Config.paymentUrl + "/getCustomerDetail:1",
        beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', localStorage
                    .getItem("authenCookie"));
            xhr.setRequestHeader("keycompany", localStorage.getItem("workCompanyID"));
        },
        success: function(res){
            AccountObjectData = res;
            // console.log(AccountObjectData);
            for (var i = 0; i < AccountObjectData.length; i++) {                     
                (function () {                        
                    dataResource.AccountObject.push({                            
                        AccountObjectNumber: 1,
                        AccountObjectCode: res[i].accountObjectID,
                        AccountObjectName: res[i].accountObjectName,
                        AccountObjectType:1,
                        Address: res[i].accountObjectAddress,
                        ContactName:res[i].accountObjectContactName                       
                    });    
                    dataResource.Reason.push({                            
                        ReasonID: res[i].journalMemo,
                        RefType: 1,
                        ReasonName: res[i].description,
                        EmployeeName: res[i].employeeName                    
                    });                   
                })(i)                 
            }            
        },
        error: function(){

        }
    });
}


