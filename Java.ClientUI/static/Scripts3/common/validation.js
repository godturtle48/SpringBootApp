﻿$(document).ready(function () {
    $('.text-required').on('blur', validationJS.requiredValidation);
})

var validationJS = Object.create({
    requiredValidation: function (sender, e) {
        if (!$(this).val()) {
            $(this).addClass('required-border');
            $(this).parent().attr('title', "Thông tin này không được để trống");
            $(this).parent().addClass('wrap-control');
            var nextElement = $(this).next();
            if (!$(nextElement).hasClass('error-box')) {
                $(this).after('<div class="error-box"></div>');
            }
            return false;
        } else {
            $(this).removeClass('required-border');
            $(this).next('.error-box').remove();
            $(this).parent().removeAttr('title');
            return true;
        }
        
    }
})