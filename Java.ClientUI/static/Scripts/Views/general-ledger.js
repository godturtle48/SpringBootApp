$(document).ready(function(){
    if(localStorage.getItem("authenCookie") != "" && localStorage.getItem("authenCookie") != null){
        $.ajax({
            method: "GET",
            url:MISA.Config.loginUrl+"/api/home",
            beforeSend: function(xhr) {
                  xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
            },
            success: function(data, status, xhr){
                $('.user-info').text((data.email).split("@")[0]);
                //ajax goi company
            },
            error: function(err, stt, xhr){
                window.location.href="/";
            }
        })  
    }
    else{
            window.location.href="/";
        }
        
    })
$(document).ready(function(){   
        $('#start-date').datepicker({dateFormat:"dd/mm/yy"}).datepicker();
        $('#end-date').datepicker({dateFormat:"dd/mm/yy"}).datepicker();  
})
var dataReport=[];

class GeneralLedgerJS {
    constructor() {
        this.initEvents();
        this.loadData();
        this.me = this;
    }
    initEvents() {
    }
    /*-----------------------------------------
     * Thực hiện load dữ liệu
     * Created by: NVLAM (22/03/2019)
     */
    loadData() {
        this.getData();
        this.buildDataIntoTable(dataReport);
    }
    getData() {
        dataReport = [];
        // var startDate = $('#start-date').val();
        // var endDate = $('#end-date').val();
        var raeDate = new DateControll();
        
        var startDate = new Date('2018-06-01');
        var endDate = new Date('2018-12-25');
        var data={
            "fromDate":startDate,
            "toDate":endDate,
        };
        commonJS.showMask($('#tblReport'));
        console.log('abc');
        $.ajax({
            method : "POST",
            url : MISA.Config.reportUrl + "/report",
            beforeSend: function(xhr) {
                xhr.setRequestHeader('authorization', localStorage.getItem('authenCookie'));
                xhr.setRequestHeader('keycompany', localStorage.getItem("workCompanyID"));

            },
            data: JSON.stringify(data),
            contentType:"application/json; charset:utf-8;",
            async: false,
            success : function(result, txtStatus) {
                setTimeout(function () {
                    commonJS.hideMask($('#tblReport'));
                }, 300);
                debugger;
                var report = result.data;
                for (var i = 0; i < report.length; i++) {
                    dataReport.push({
                        ID: report[i].refID,
                        PostedDate : raeDate.convertDate(report[i].postedDate),                    
                        RefDate : raeDate.convertDate(report[i].refDate),
                        RefNo : report[i].refNo,
                        JournalMemo : report[i].description,                               
                        // RefTypeName : report[i].ref.refTypeName,
                        TotalAmount : report[i].totalAmountOC,                             
                        CashBookPostedDate : raeDate.convertDate(report[i].postedDate),
                        EmployeeName : report[i].contactName
                    })
                }
                // generalLedgerJS.buildDataIntoTable(dataReport);                
            },
            error: function() {
                debugger;
                commonJS.hideMask($('#tblReport'));
            }
        })
        // for (var i=0; i<100; i++) {
        //     dataReport.push({
        //         ID: '123123123',
        //         PostedDate : '08/03/2019',                    
        //         RefDate : '08/03/2019',
        //         RefNo : 'PT' + i,
        //         JournalMemo : "Phiếu thu nhân viên",                               
        //         RefTypeName : "Phiếu thu",
        //         TotalAmount : "123456",                             
        //         CashBookPostedDate : '08/03/2019',
        //         EmployeeName : 'Nguyễn Văn Lâm',
        //     })
        // }
    }

    buildDataIntoTable(data) {
        var table = $('#tbodyRAE');
        table.html('');
        var column = $('#tbodyRAE tr th');
        var rowTemplate = [];
        var fieldData = [];
        rowTemplate.push('<tr class="{0}">');
        // column.each(function (index, item) {
        //     fieldData.push($(item).attr('fieldData'));
        // })
        table.append('<tr>'
        + '<td class="width-150 no-border-left no-border-top "></td>'
        + '<td class="width-150 no-border-top"></td>'
        + '<td class="width-100 no-border-top"></td>'
        + '<td class="width-100 no-border-top"></td>'
        + '<td class="text-left font-weight-bold no-border-top" fieldData="JournalMemo">Số tồn đầu kì:</td>'
        + '<td class="width-100 no-border-top"></td>'
        + '<td class="width-100 no-border-top"></td>'
        + '<td class="text-right width-150 no-border-top"></td>'.format()
        + '<td class="text-right width-150 no-border-top"></td>'.format()
        + '<td class="text-right width-150 no-border-top">{0}</td>'.format(Number(0))
        + '<td class="width-200  no-border-top"></td>');
        $.each(data, function (key, value) {
            table.append('<tr>'
                + '<td class="text-center no-border-left">{0}</td>'.format(data[key].PostedDate)
                + '<td class="text-center">{0}</td>'.format(data[key].RefDate)
                + '<td>{0}</td>'.format(data[key].RefNo)
                + '<td></td>'
                + '<td>{0}</td>'.format(data[key].JournalMemo)
                + '<td></td>'
                + '<td></td>'
                + '<td class="text-right">{0}</td>'.format(Number(data[key].TotalAmount).formatMoney())
                + '<td class="text-right"></td>'
                + '<td class="text-right">{0}</td>'.format(Number(data[key].totalAmount).formatMoney())
                + '<td>{0}</td>'.format(data[key].EmployeeName)
                + '</tr>');
        });  
    }
}
var generalLedgerJS = new GeneralLedgerJS();
