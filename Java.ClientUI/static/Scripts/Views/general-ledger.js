$(document).ready(function(){
    $('#start-date').click(function(){
        $('#start-date').datepicker({dateFormat:"dd/mm/yy"}).datepicker();
    })
    $('#end-date').click(function(){
        $('#end-date').datepicker({dateFormat:"dd/mm/yy"}).datepicker();
    })
})