$(document).ready(function(){
    $('#printBtn .btn').click(function(){
        window.print();
    })
})
var dataReport=[];
class ReportJS {
    constructor() {
        this.initEvents();
        this.loadData();
        this.me = this;
    }
    initEvents() {

    }
    /*-----------------------------------------
     * Thực hiện load dữ liệu
     * Created by: NVLAM (08/03/2019)
     */
    loadData() {
        dataReport = [];
        this.getData();
        this.buildDataIntoTable(dataReport);
    }
    getData() {
        dataReport = [];
        var size = 100;
        commonJS.showMask($('#tblReport'));
        // $.ajax({
        //     method : "GET",
        //     url : MediaSource.Config.paymentUrl + "/getAllPage_Size:" + size,
        //     beforeSend: function(xhr) {
        //         xhr.setRequestHeader('authorization', localStorage.getItem('authenCookie'));
        //         xhr.setRequestHeader('keycompany', localStorage.getItem('workcompany'));
        //     },
        //     async: false,
        //     success : function(result, txtStatus) {
        //         setTimeout(function () {
        //             commonJS.hideMask($('.#tblReport'));
        //         }, 300);
        //         var report = result.data;
        //         sessionStorage.setItem('detailReport', JSON.stringify(report));
        //         for (var i = 0; i < report.length; i++) {
        //             dataReport.push({
        //                 ID: report[i].refID,
        //                 PostedDate : convertDate(report[i].postedDate),                    
        //                 RefDate : convertDate(report[i].refDate),
        //                 RefNo : report[i].refNoFinance,
        //                 JournalMemo : report[i].journalMemo,                               
        //                 RefTypeName : report[i].ref.refTypeName,
        //                 TotalAmount : report[i].totalAmountOC,                             
        //                 CashBookPostedDate : convertDate(payment[i].createdDate),
        //                 EmployeeName : report[i].modifiedBy,
        //             })
        //         }
        //     },
        //     error: function() {
        //         commonJS.hideMask($('#tblReport'));
        //     }
        // })
        for (var i=0; i<10; i++) {
            dataReport.push({
                ID: '123123123',
                PostedDate : '08/03/2019',                    
                RefDate : '08/03/2019',
                RefNo : 'PT' + i,
                JournalMemo : "Phiếu thu nhân viên",                               
                RefTypeName : "Phiếu thu",
                TotalAmount : "123456",                             
                CashBookPostedDate : '08/03/2019',
                EmployeeName : 'Nguyễn Văn Lâm',
            })
        }
    }

    buildDataIntoTable(data) {
        var table = $('#tbodyReport');
        table.html('');
        var column = $('#tblReport tr th');
        var rowTemplate = [];
        var fieldData = [];
        var totalAmount = Number(data[0].TotalAmount);
        rowTemplate.push('<tr class="{0}">');
        // column.each(function (index, item) {
        //     fieldData.push($(item).attr('fieldData'));
        // })
        $.each(data, function (key, value) {
            $('#tbodyReport').append('<tr>'
                + '<td class="text-center">{0}</td>'.format(data[key].PostedDate)
                + '<td class="text-center">{0}</td>'.format(data[key].RefDate)
                + '<td>{0}</td>'.format(data[key].RefNo)
                + '<td></td>'
                + '<td>{0}</td>'.format(data[key].JournalMemo)
                + '<td class="text-right">{0}</td>'.format(Number(data[key].TotalAmount).formatMoney())
                + '<td class="text-right"></td>'
                + '<td class="text-right">{0}</td>'.format(Number(totalAmount).formatMoney())
                + '<td>{0}</td>'.format(data[key].EmployeeName)
                + '</tr>');
            totalAmount += new Number(value.TotalAmount);
        });
        totalAmount -= Number(data[0].TotalAmount);
        $('#tbodyReport').append('<tr id = "sum">'
            + '<td colspan="5" class="bold-font text-right">Tổng cộng:</td>'
            + '<td class="bold-font text-right">' + Number(totalAmount).formatMoney() + '</td>'
            + '<td class="bold-font text-right"></td>'
            + '<td class="bold-font text-right">' + Number(totalAmount).formatMoney() + '</td>'
            + '<td></td>'
        + '</tr>');
    }
}
var reportJS = new ReportJS();