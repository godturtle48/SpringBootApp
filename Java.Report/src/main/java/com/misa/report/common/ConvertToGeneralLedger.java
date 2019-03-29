package com.misa.report.common;

import java.util.Date;

import com.misa.report.model.GeneralLedger;
import com.misa.report.model.PaymentReceipt;

public class ConvertToGeneralLedger {
	public static GeneralLedger convert(PaymentReceipt payment) {
		
		GeneralLedger general=new GeneralLedger();
	
		general.setContactName(payment.getAccountObjectContactName());
	
		general.setDescription(payment.getDescription());
		general.setDescription(payment.getDescription());
		general.setPostedDate(payment.getPostedDate());
		general.setExchangeRate(payment.getExchangeRate());
		general.setKeycompany(payment.getKeyCompany());
		general.setRefDate(payment.getRefDate());
		general.setRefID(payment.getRefID());
		general.setRefNo(payment.getRefNoFinance());
		general.setRefType(payment.getRef().getRefTypeID());
		return general;
	}
}
