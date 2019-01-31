package com.example.demo.controller;



import java.sql.Date;
import java.sql.Ref;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InvoiceDetailService;
import com.example.demo.Service.PaymentReceiptService;
import com.example.demo.Service.RefTypeService;
import com.example.demo.configAuthen.Authenticate;
import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;

@RestController
public class GenerateDataController {

	
	@Autowired
	RefTypeService refService;
	@Autowired
	PaymentReceiptService payService;
	@Autowired
	InvoiceDetailService invoiceService;
	@RequestMapping("/ref")
	public String ref() {
		RefType ref=new RefType(1,"thu");
		RefType ref2=new RefType(2,"chi");
		refService.Save(ref);
		refService.Save(ref2);
		return "ref";
	}

	
	@RequestMapping("/payment:{keyCompany}")
	public String paymnet(@PathVariable String keyCompany,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		List<RefType> ref=refService.findAll();
		Random rand=new Random();
		String[]	accountObjectAddresss= {"CTCP MISA","tap doan FPT","CT MSR","CT MSE","CT SME","CT DARR"};	
		String[]	accountObjectContactNames= {"misa@Gmail.com","group@Gmail.com","contact@Gmail.com","hoa@Gmail.com","manager@Gmail.com","stresy@Gmail.com"};	
		
		String[]	accountObjectNames= {"C MISA","FPT","MSR","SME","DARR","DARR"};	
		String[]	createdBys= {"Hoàng","Hà","Nam","Hiếu","Hùng","Ngọc"};	
		//year
		ArrayList<Integer> year = new ArrayList<Integer>();
		year.add(2017);
		year.add(2018);
		year.add(2019);
		//month
		ArrayList<Date> date = new ArrayList<>();
		Date date1=new Date( new GregorianCalendar(2018, 12, 03).getTime().getTime());
		Date date2=new Date( new GregorianCalendar(2018, 10, 03).getTime().getTime());
		Date date3=new Date( new GregorianCalendar(2018, 6, 03).getTime().getTime());
		Date date4=new Date( new GregorianCalendar(2018, 5, 03).getTime().getTime());
		Date date5=new Date( new GregorianCalendar(2018, 2, 03).getTime().getTime());
		
		date.add(date1);
		date.add(date2);
		date.add(date3);
		date.add(date4);
		date.add(date5);
		String[]	jornalMemoArr= {"Chi thu tiền điện","Chi phí đào tạo",
										"Mua thiết bị mới",
										"Lì xì Dev",
										"Mua cà phê",
										"Suất ăn trưa"};
			for(int i=1;i<1000;i++) {
				PaymentReceipt payment=new PaymentReceipt();
				payment.setAccountObjectAddress(accountObjectAddresss[rand.nextInt(5)]);
				payment.setAccountObjectContactName(accountObjectContactNames[rand.nextInt(5)]);
				payment.setAccountObjectID("accountObjectID"+i%10);
				payment.setAccountObjectName(accountObjectNames[rand.nextInt(5)]);
				payment.setCreatedBy(createdBys[rand.nextInt(6)]);
				payment.setCreatedDate(date.get(rand.nextInt(5)));
				payment.setDocumentInclude("documentInclude"+i%100+".doc");
				payment.setEditVersion(date.get(rand.nextInt(5)));
				payment.setJournalMemo(jornalMemoArr[rand.nextInt(6)]);
				payment.setKeyCompany(keyCompany);
				payment.setModifiedBy(createdBys[rand.nextInt(6)]);
				payment.setModifiedDate(date.get(rand.nextInt(5)));
				payment.setPostedDate(date.get(rand.nextInt(5)));
				payment.setRefDate(date.get(rand.nextInt(5)));
				payment.setRefNoFinance("CT"+i);
				payment.setRefOrdef(i);
				payment.setTotalAmount(Double.valueOf(0.0));
				payment.setTotalAmountOC(Double.valueOf(2000.0 + rand.nextInt(1000)));
				payment.setRef(ref.get(i%2));
				payment.setPostedFinance(Integer.valueOf(1));
				payService.save(payment);
			}
		return "payment";

	}
	
	
	@GetMapping("/getref")
	public List<RefType> getref(){
		return refService.findAll();
	}
	
	@RequestMapping("/invoice:{keyCompany}")
	public String invoice(@PathVariable String keyCompany,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		
		
		Random rand=new Random();
		String[]	accountObjectAddresss= {"CTCP MISA","tap doan FPT","CT MSR","CT MSE","CT SME","CT DARR"};	
		String[]	accountObjectContactNames= {"misa@Gmail.com","group@Gmail.com","contact@Gmail.com","hoa@Gmail.com","manager@Gmail.com","stresy@Gmail.com"};	
		
		String[]	accountObjectNames= {"C MISA","FPT","MSR","SME","DARR","DARR"};	
		String[]	createdBys= {"Hoàng","Hà","Nam","Hiếu","Hùng","Ngọc"};	
		List<PaymentReceipt> payments=payService.getPaymentReceiptOfCompany("company1");
		if(payments==null) {
			return "fails";
		}
		for(int i=0;i<1000;i++) {
			InvoiceDetail invoice=new InvoiceDetail();
			invoice.setAccountObjectID("accountObjectID"+i%10);
			invoice.setAmount(Double.valueOf(1000+i/100));
			invoice.setAmountOC(Double.valueOf(1000+i/1000));
			invoice.setDiscription("Trả lương nhân viên tháng"+(i%12+1));
			invoice.setPayment(payments.get(rand.nextInt(payments.size())));
			invoice.setSortOrder(i);
		invoiceService.save(invoice);
		}
		return "invoice";
	}
}
