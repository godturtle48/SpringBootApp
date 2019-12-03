package com.example.demo.common;

import org.json.simple.JSONObject;

import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.query.model.PaymentReceiptView;

public class ConvertModelCommandToView {

	public static PaymentReceiptView convert(PaymentReceiptCommand paymentCommand) {
	
		JSONObject obj=new JSONObject();
		
		
	
		PaymentReceiptView payment=new PaymentReceiptView();
		payment.setAccountObjectAddress(paymentCommand.getAccountObjectAddress());
		payment.setAccountObjectContactName(paymentCommand.getAccountObjectContactName());
		payment.setAccountObjectID(paymentCommand.getAccountObjectID());
		payment.setAccountObjectName(paymentCommand.getAccountObjectName());
		payment.setCreatedBy(paymentCommand.getCreatedBy());
		payment.setCreatedDate(paymentCommand.getCreatedDate());
		payment.setDescription(paymentCommand.getDescription());
		payment.setDocumentInclude(paymentCommand.getDocumentInclude());
		payment.setEditVersion(paymentCommand.getEditVersion());
		payment.setExchangeRate(paymentCommand.getExchangeRate());
		payment.setInvoices(paymentCommand.getInvoices());
		payment.setIsPostedFinance(paymentCommand.getIsPostedFinance());
		payment.setJournalMemo(paymentCommand.getJournalMemo());
		payment.setKeyCompany(paymentCommand.getKeyCompany());
		payment.setModifiedBy(paymentCommand.getModifiedBy());
		payment.setModifiedDate(paymentCommand.getModifiedDate());
		payment.setPostedDate(paymentCommand.getPostedDate());
		payment.setRef(paymentCommand.getRef());
		payment.setRefDate(paymentCommand.getRefDate());
		payment.setRefID(paymentCommand.getRefID());
		payment.setRefNoFinance(paymentCommand.getRefNoFinance());
		payment.setRefOrdef(paymentCommand.getRefOrdef());
		payment.setTotalAmount(paymentCommand.getTotalAmount());
		payment.setTotalAmountOC(paymentCommand.getTotalAmountOC());
		return payment;
	}
}
