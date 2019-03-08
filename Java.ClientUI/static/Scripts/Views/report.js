$(document).ready(function(){
    $('#printBtn').click(function(){
        window.print();
    })
})
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
        getData();
        this.buildDataIntoTable(dataReport);
    }
    getData() {
        dataReport = [];
        var size = 100;
        commonJS.showMask($('#tblReport'));
        $.ajax({
            method : "GET",
            url : MediaSource.Config.paymentUrl + "/getAllPage_Size:" + size,
            beforeSend: function(xhr) {
                xhr.setRequestHeader('authorization', localStorage.getItem('authenCookie'));
                xhr.setRequestHeader('keycompany', localStorage.getItem('workcompany'));
            },
            async: false,
            success : function(result, txtStatus) {
                setTimeout(function () {
                    commonJS.hideMask($('.#tblReport'));
                }, 300);
                var report = result.data;
                sessionStorage.setItem('detailReport', JSON.stringify(report));
                for (var i = 0; i < report.length; i++) {
                    dataReport.push({
                        ID: report[i].refID,
                        PostedDate : convertDate(report[i].postedDate),                    
                        RefDate : convertDate(report[i].refDate),
                        RefNo : report[i].refNoFinance,
                        JournalMemo : report[i].journalMemo,                               
                        RefTypeName : report[i].ref.refTypeName,
                        TotalAmount : report[i].totalAmountOC,                             
                        CashBookPostedDate : convertDate(payment[i].createdDate),
                        EmployeeName : report[i].modifiedBy,
                    })
                }
            },
            error: function() {
                commonJS.hideMask($('#tblReport'));
            }
        })
    }

    buildDataIntoTable(data) {
        var table = $('#tbodyReport');
        table.html('');
        var column = $('#tblReport tr th');
        var rowTemplate = [];
        var fieldData = [];
        rowTemplate.push('<tr class="{0}">');
        column.each(function (index, item) {
            fieldData.push($(item).attr('fieldData'));
        })
        data.forEach(function (item, index) {
            var htmlItem = [];
            htmlItem.push('<tr indexRef="{0}" class="{1}" refTypeName={2}  >'.format(index, index % 2 === 0 ? '' : 'row-highlight', item.RefTypeName));
            fieldData.forEach(function (valueField, indexField) {
                switch (indexField) {
                    case 0:
                        htmlpush
                }
                if (indexField === 0 || indexField === 1) 
                    htmlItem.push('<td class="{1}">{0}</td>'.format(item[valueField],"text-center"));
                else if (indexField === 5 || indexField === 6 || indexField === 7) 
                    htmlItem.push('<td class="{1}">{0}</td>'.format(Number(item[valueField]).formatMoney(),"text-right"));
                else if (indexField == 2) {
                    if (item.RefTypeName == "Thu") {
                        htmlItem.push('<td>{0}</td>'.format(item[valueField]));
                    } else htmlItem.push('<td>{0}</td>'.format(''));
                }
                else if (indexField == 3){
                    if (item.RefTypeName == "Chi") {
                        htmlItem.push('<td>{0}</td>'.format(item[valueField]));
                    } else htmlItem.push('<td>{0}</td>'.format(''));
                }
                else  htmlItem.push('<td>{0}</td>'.format(item[valueField]));
            })
            htmlItem.push('</tr>');
            table.append(htmlItem.join(""));
        });

    }
}