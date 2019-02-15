var AccountObjectData = [];
var Reason = [];

$(document).ready(function(){
    $.ajax({
        method: 'get',
        url: MISA.Config.paymentUrlServer + "/loadComboboxAddPayment",
        success: function(res){
            // AccountObjectData = res;
            // console.log(res);
            for (var i = 0; i < res.length; i++) {                     
                (function () {                        
                    dataResource.AccountObject.push({                            
                        AccountObjectNumber: 1,
                        AccountObjectCode: res[i][0],
                        AccountObjectName: res[i][1],
                        AccountObjectType:2,
                        Address: res[i][2],
                        ContactName:res[i][3]                       
                    });   
                     dataResource.Reason.push({                            
                            ReasonID: res[i][4],
                            RefType: 2,
                            ReasonName: res[i][5],
                            // Description: res[i][5],
                            EmployeeName: res[i][3]                      
                    });                   
                })(i)                 
            }            
        },
        error: function(){

        }
    });
    $.ajax({
        method: 'get',
        url: MISA.Config.paymentUrlServer + "/loadComboboxAddReceipt",
        success: function(res){
            AccountObjectData = res;
            // console.log(AccountObjectData);
            for (var i = 0; i < AccountObjectData.length; i++) {                     
                (function () {                        
                    dataResource.AccountObject.push({                            
                        AccountObjectNumber: 1,
                        AccountObjectCode: res[i][0],
                        AccountObjectName: res[i][1],
                        AccountObjectType:1,
                        Address: res[i][2],
                        ContactName:res[i][3]                       
                    });    
                    dataResource.Reason.push({                            
                        ReasonID: res[i][4],
                        RefType: 1,
                        ReasonName: res[i][5],
                        // Description: res[i][5],
                        EmployeeName: res[i][3]                      
                    });                   
                })(i)                 
            }            
        },
        error: function(){

        }
    })


})


var dataResource = Object.create({
    AccountObject: [

       
    ],
    Reason: [
    ]

})