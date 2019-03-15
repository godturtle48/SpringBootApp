package com.example.demo.common;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.model.RefTypeCommand;
import com.example.demo.command.service.InvoiceDetailCommandService;
import com.example.demo.command.service.PaymentReceiptCommandService;
import com.example.demo.command.service.RefTypeCommandService;



@RestController
public class GenerateData {

	@Autowired
	RefTypeCommandService refTypeService;
	@Autowired
	PaymentReceiptCommandService paymentService;
	@Autowired
	InvoiceDetailCommandService invoiceService;
	@RequestMapping("/ref")
	public String ref(HttpServletRequest httpServletRequest) {
		String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
		RefTypeCommand ref=new RefTypeCommand(1,"Thu");
		RefTypeCommand ref2=new RefTypeCommand(2,"Chi");
		refTypeService.Save(ref,keydatabase);
		refTypeService.Save(ref2,keydatabase);
		return "ref";
	}
	
	static int randNumber;
	static String[] accountObjectID = {"CTY MISA", 
										"CTY DIENLUC", 
										"CTY HONGHA", 
										"CTY LANTAN", 
										"CTY LANTAN",
										"CTY MINHHUONG",
										"CTY PHUVINH",
										"CTY SONGCONG",
										"CTY THIENTAN",
										"HNMAI"};
	
	@RequestMapping("/payment")
	public String paymnet(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		
		String keydatabase=(String) httpServletRequest.getAttribute("keydatabase");
		String keyCompany= httpServletRequest.getHeader("keycompany");
		List<RefTypeCommand> ref=refTypeService.findAll(keydatabase);
		Random rand=new Random();
		
		String[]	accountObjectAddresss= {"Tòa nhà Technosoft, Phố Duy Tân, Dịch Vọng Hậu, Cầu Giấy, Hà Nội", 
											"1078 Nguyễn Trãi, Thanh Xuân, Hà Nội", 
											"1078 Phố Huế, Hoàn Kiếm, Hà Nội", 
											"1254 Lê Lai, Ba Đình, Hà Nội", 
											"1251 Nguyễn Khánh Toàn, Cầu Giấy, Hà Nội",
											"3021 Kim Mã, Ba Đình, Hà Nội",
											"2548 Tràng Thi, Hoàn Kiếm, Hà Nội",
											"2014 Nguyễn Văn Cừ, Long Biên, Hà Nội",
											"2310 Láng Hạ, Đống Đa, Hà Nội",
											"1234 Đại Cồ Việt, Hai Bà Trưng, Hà Nội"};	
		String[]	accountObjectContactNames= {"Nguyễn Kim Ngọc",
												"Nguyễn Hữu Thắng",
												"Hà Đức Phú",
												"Nguyễn Phan Mỹ Linh",
												"Phạm Thị Mai Phương",
												"Hà Thị Hằng",
												"Phạm Thục Trinh",
												"Vũ Ngọc Mai"};
		
		String[]	accountObjectNames= {"Công ty cổ phần MISA", 
										"Công ty điện lực Hà Nội", 
										"Công ty TNHH Hồng Hà", 
										"Công ty TNHH Lan Tân", 
										"Công ty TNHH Minh Anh",
										"Công ty TNHH Minh Hương",
										"Công ty TNHH Phú Vinh",
										"Công ty vận tải Sông Công",
										"Công ty cổ phần Thiên Tân",
										"Hoàng Ngọc Mai"};	
		String[]	createdBys= {"Nguyễn Đình Quân",
								"Nguyễn Công Thành",
								"Đoàn Văn Quân",
								"Nguyễn Văn Lâm",
								"Trần Thị Huyền",
								"Nguyễn Thế Chí Dũng"};	
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
		String[]	jornalMemoArrPayment= {"Chi thu tiền điện",
									"Chi phí đào tạo",
									"Mua thiết bị mới",
									"Lì xì Dev",
									"Mua cà phê",
									"Suất ăn trưa",
									"Tạm ứng cho nhân viên",
									"Gửi tiền vào ngân hàng",
									"Chi khác"};
		String[]	jornalMemoArrReceipt= {"Rút tiền gửi về nộp quỹ",
											"Thu hoàn thuế GTGT",
											"Thu hoàn ứng sau khi quyết toán tạm ứng nhân viên",
											"Thu khác"};
		String[]	descriptionArr= {"Chi thu tiền điện",
				"Chi phí đào tạo",
				"Mua thiết bị mới",
				"Lì xì Dev",
				"Mua cà phê",
				"Suất ăn trưa",
				"Tạm ứng cho nhân viên",
				"Gửi tiền vào ngân hàng",
				"Chi khác"};
             
			int length = GenerateData.accountObjectID.length;
			for(int i=1;i<100;++i) {
				try {
					int tmpIndex = rand.nextInt(length);
				
					PaymentReceiptCommand payment=new PaymentReceiptCommand();
					List<InvoiceDetailCommand> invoices=new ArrayList<>();
					for (int j = 1; j <= 5; j++) {
						InvoiceDetailCommand invoice=new InvoiceDetailCommand();
//						invoice.setAccountObjectID(GenerateData.accountObjectID[tmpIndex]);
						invoice.setAmount(Double.valueOf(1000+i/100));
						invoice.setAmountOC(Double.valueOf(1000+i/1000));
						invoice.setDiscription(jornalMemoArrPayment[rand.nextInt(jornalMemoArrPayment.length)]);
						invoice.setPayment(payment);
						invoice.setSortOrder(i);
						invoices.add(invoice);
					}
					payment.setAccountObjectAddress(accountObjectAddresss[tmpIndex]);
					payment.setAccountObjectContactName(accountObjectContactNames[rand.nextInt(accountObjectContactNames.length)]);
					payment.setAccountObjectID(GenerateData.accountObjectID[tmpIndex]);
					payment.setAccountObjectName(accountObjectNames[tmpIndex]);
					payment.setCreatedBy(createdBys[rand.nextInt(createdBys.length)]);
					payment.setCreatedDate(date.get(rand.nextInt(date.size())));
					payment.setDocumentInclude("documentInclude"+i%100+".doc");
					payment.setEditVersion(date.get(rand.nextInt(date.size())));
					payment.setJournalMemo(jornalMemoArrPayment[rand.nextInt(jornalMemoArrPayment.length)]);
					payment.setDescription(descriptionArr[rand.nextInt(descriptionArr.length)]);
					payment.setKeyCompany(keyCompany);
					payment.setModifiedBy(createdBys[rand.nextInt(createdBys.length)]);
					payment.setModifiedDate(date.get(rand.nextInt(date.size())));
					payment.setPostedDate(date.get(rand.nextInt(date.size())));
					payment.setRefDate(date.get(rand.nextInt(date.size())));
					payment.setRefNoFinance("CT"+i);
					payment.setRefOrdef(i);
					payment.setTotalAmount(Double.valueOf(0.0));
					payment.setTotalAmountOC(Double.valueOf(2000.0 + rand.nextInt(1000)));
					payment.setRef(ref.get(i%2));
					payment.setPostedFinance(Integer.valueOf(1));
					payment.setInvoices(invoices);
					payment.setVersion(Integer.valueOf(0));
					paymentService.save(payment,keydatabase);
					
					
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return "payment";

	}
	
}
