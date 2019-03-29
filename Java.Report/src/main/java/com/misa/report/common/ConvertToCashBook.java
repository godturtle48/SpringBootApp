package com.misa.report.common;

import java.util.Date;

import com.misa.report.model.CACashbook;
import com.misa.report.model.PaymentReceipt;

public class ConvertToCashBook {

	public static CACashbook convert(PaymentReceipt payment) {
		
		CACashbook cashbook=new CACashbook();
		if (payment.getRef().getRefTypeID()==1) {
			cashbook.setPaymentAmount(payment.getTotalAmount());
			cashbook.setPaymentAmountOC(payment.getTotalAmountOC());
			cashbook.setSignTotalAmountOC(Double.valueOf(-1*payment.getTotalAmountOC().doubleValue()));
			cashbook.setSignTotalAmount(Double.valueOf(-1*payment.getTotalAmount().doubleValue()));
			cashbook.setRecieptAmount(Double.valueOf(0.0));
			cashbook.setRecieptAmountOC(Double.valueOf(0.0));
		}
		else if(payment.getRef().getRefTypeID()==2) {
			cashbook.setPaymentAmount(Double.valueOf(0.0));
			cashbook.setPaymentAmountOC(Double.valueOf(0.0));
			cashbook.setRecieptAmount(payment.getTotalAmount());
			cashbook.setRecieptAmountOC(payment.getTotalAmountOC());
			cashbook.setSignTotalAmountOC(Double.valueOf(1*payment.getTotalAmountOC().doubleValue()));
			cashbook.setSignTotalAmount(Double.valueOf(1*payment.getTotalAmount().doubleValue()));
		}
		cashbook.setAccountObjectAddress(payment.getAccountObjectAddress());
		cashbook.setAccountObjectID(payment.getAccountObjectID());
		cashbook.setAccountObjectName(payment.getAccountObjectName());
		Date date=new Date();
		cashbook.setCashbookPostedDate(date); //Ngày ghi chứng từ
		cashbook.setRefID(payment.getRefID());
		cashbook.setContactName(payment.getAccountObjectContactName());
		cashbook.setCreatedBy(payment.getCreatedBy());
		cashbook.setCreatedDate(payment.getCreatedDate());
		cashbook.setDescription(payment.getDescription());
		cashbook.setDocumentInclude(payment.getDocumentInclude());
		cashbook.setExchangeRate(payment.getExchangeRate());
		cashbook.setJournalMemo(payment.getJournalMemo());
		cashbook.setKeycompany(payment.getKeyCompany());
		cashbook.setModifiedBy(payment.getModifiedBy());
		cashbook.setModifiedDate(payment.getModifiedDate());
		cashbook.setPostedDate(payment.getPostedDate());
		cashbook.setPostedManagement(payment.getIsPostedFinance());
		cashbook.setRefDate(payment.getRefDate());
		cashbook.setRefNo(payment.getRefNoFinance());
		cashbook.setRefType(payment.getRef().getRefTypeID());
		cashbook.setIsPostedManagement(payment.getIsPostedFinance());
		cashbook.setKeycompany(payment.getKeyCompany());
		return cashbook;
	}
}
