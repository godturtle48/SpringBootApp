package com.example.demo.query.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.query.model.CustomerDetails;
import com.example.demo.query.model.PaymentReceiptView;
import com.example.demo.query.repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
	@Autowired
	CustomerDetailsService customerService; 
	
	@Autowired
	CustomerDetailsRepository customerRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	String[] accountObjectID = { "CTY MISA", "CTY DIENLUC", "CTY HONGHA", "CTY LANTAN", "CTY MINHANH",
			"CTY MINHHUONG", "CTY PHUVINH", "CTY SONGCONG", "CTY THIENTAN", "HNMAI" };
	String[] accountObjectAddress = { "Tòa nhà Technosoft, Phố Duy Tân, Dịch Vọng Hậu, Cầu Giấy, Hà Nội",
			"1078 Nguyễn Trãi, Thanh Xuân, Hà Nội", "1078 Phố Huế, Hoàn Kiếm, Hà Nội", "1254 Lê Lai, Ba Đình, Hà Nội",
			"1251 Nguyễn Khánh Toàn, Cầu Giấy, Hà Nội", "3021 Kim Mã, Ba Đình, Hà Nội",
			"2548 Tràng Thi, Hoàn Kiếm, Hà Nội", "2014 Nguyễn Văn Cừ, Long Biên, Hà Nội",
			"2310 Láng Hạ, Đống Đa, Hà Nội", "900 Láng Hạ, Đống Đa, Hà Nội" };
	String[] accountObjectContactNames = { "Nguyễn Kim Ngọc", "Nguyễn Hữu Thắng", "Nguyễn Hữu Thắng", "Hà Đức Phú",
			"Nguyễn Phan Mỹ Linh", "Phạm Thị Mai Phương", "Phạm Thị Mai Phương", "Hà Thị Hằng", "Phạm Thục Trinh",
			"Vũ Ngọc Mai" };

	String[] accountObjectNames = { "Công ty cổ phần MISA", "Công ty điện lực Hà Nội", "Công ty TNHH Hồng Hà",
			"Công ty TNHH Lan Tân", "Công ty TNHH Minh Anh", "Công ty TNHH Minh Hương", "Công ty TNHH Phú Vinh",
			"Công ty vận tải Sông Công", "Công ty cổ phần Thiên Tân", "Hoàng Ngọc Mai" };
	String[] employeeName = { "Nguyễn Đình Quân", "Nguyễn Công Thành", "Nguyễn Công Thành", "Đoàn Văn Quân",
			"Đoàn Văn Quân", "Nguyễn Văn Lâm", "Trần Thị Huyền", "Trần Thị Huyền", "Nguyễn Thế Chí Dũng",
			"Nguyễn Thế Chí Dũng" };
	int[] reasonTypeID= {1, 2, 3,4,5,6,7,8,9};
	String[] journalMemo1 = {"Thu phí đào tạo phần mềm", "Thu tiền gửi", "thu thuế", "Thu tiền điện", "Thu phí gửi xe", 
			"thu phí mặt bằng", "thu tiền nước", "thu thuế GTGT", "Thu khác"};
	String[] journalMemo2 = {"Chi tiền điện", "Mua thiết bị mới", "Mua máy tính đào tạo", "Suất ăn trưa", "Chi tiền điện",
			"Lì xì nhân viên", "Tạm ứng tháng lương", "Du lịch cho fresher", "Chi khác"};
	String[] description1 = {"Thu phí đào tạo phần mềm", "Thu tiền gửi ngân hàng", "thu thuế", "Thu tiền điện", "Thu phí gửi xe", 
			"thu phí mặt bằng", "thu tiền nước", "thu thuế GTGT", "Thu các khoản khác"};
	String[] description2 = {"Chi tiền điện", "Mua thiết bị mới", "Mua máy tính đào tạo", "Suất ăn trưa", "Chi tiền điện",
			"Lì xì nhân viên", "Tạm ứng tháng lương", "Du lịch cho fresher", "Chi các khoản khác"};
	public int generateCustomerDetail(String keyCompany) {
		try {
			CustomerDetails customerDetails = new CustomerDetails();
			for(int i = 0; i < 9; i++) {
				customerDetails.setAccountObjectAddress(accountObjectAddress[i]);
				customerDetails.setAccountObjectID(accountObjectID[i]);
				customerDetails.setAccountObjectContactName(accountObjectContactNames[i]);
				customerDetails.setEmployeeName(employeeName[i]);
				customerDetails.setKeyCompany(keyCompany);
				customerDetails.setAccountObjectName(accountObjectNames[i]);
				customerDetails.setRefTypeID(1);
				customerDetails.setJournalMemo(journalMemo1[i]);
				customerDetails.setDescription(description1[i]);
				customerRepo.insert(customerDetails);
			}
			for(int i = 0; i < 9; i++) {
				customerDetails.setAccountObjectAddress(accountObjectAddress[i]);
				customerDetails.setAccountObjectID(accountObjectID[i]);
				customerDetails.setAccountObjectContactName(accountObjectContactNames[i]);
				customerDetails.setEmployeeName(employeeName[i]);
				customerDetails.setKeyCompany(keyCompany);
				customerDetails.setAccountObjectName(accountObjectNames[i]);
				customerDetails.setRefTypeID(2);
				customerDetails.setJournalMemo(journalMemo2[i]);
				customerDetails.setDescription(description2[i]);
				customerRepo.insert(customerDetails);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	
	public void insertCustomerDetails(PaymentReceiptView payment) {
		int result = 0;
		//copy data
		CustomerDetails customerDetails = new CustomerDetails();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("keyCompany").is(payment.getKeyCompany()));
			query.addCriteria(Criteria.where("accountObjectID").is(payment.getAccountObjectID()));
			query.addCriteria(Criteria.where("refTypeID").is(payment.getRef().getRefTypeID()));
			customerDetails = mongoTemplate.findOne(query, CustomerDetails.class);
			if(customerDetails != null) {
				customerDetails.setAccountObjectAddress(payment.getAccountObjectAddress());
				customerDetails.setAccountObjectContactName(payment.getAccountObjectContactName());
				customerDetails.setAccountObjectID(payment.getAccountObjectID());
				customerDetails.setAccountObjectName(payment.getAccountObjectName());
				customerDetails.setEmployeeName(payment.getCreatedBy());
				customerDetails.setJournalMemo(payment.getJournalMemo());
				customerDetails.setDescription(payment.getDescription());
				customerDetails.setRefTypeID(payment.getRef().getRefTypeID());
				customerDetails.setReasonTypeID(2);
				customerRepo.save(customerDetails);
			}
			else {
				customerDetails.setAccountObjectAddress(payment.getAccountObjectAddress());
				customerDetails.setAccountObjectContactName(payment.getAccountObjectContactName());
				customerDetails.setAccountObjectID(payment.getAccountObjectID());
				customerDetails.setAccountObjectName(payment.getAccountObjectName());
				customerDetails.setEmployeeName(payment.getCreatedBy());
				customerDetails.setJournalMemo(payment.getJournalMemo());
				customerDetails.setKeyCompany(payment.getKeyCompany());
				customerDetails.setReasonTypeID(2);
				customerDetails.setRefTypeID(payment.getRef().getRefTypeID());
				customerDetails.setDescription(payment.getDescription());
				customerRepo.insert(customerDetails);
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//detail for combobox
	public List<CustomerDetails> getGeneralDetails(String keyCompany, int refTypeID){
		Query query = new Query();
		query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
		query.addCriteria(Criteria.where("refTypeID").is(refTypeID));
		query.limit(6);
		List<CustomerDetails> cd = new ArrayList<>();
		try {
			cd = mongoTemplate.find(query, CustomerDetails.class);
			return cd;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<CustomerDetails> getGeneralDetailByInput(String keyCompany, String input, String refTypeID){
		Query query = new Query();
		query.addCriteria(Criteria.where("keyCompany").is(keyCompany));
		query.addCriteria(Criteria.where("refTypeID").is(refTypeID));
		query.addCriteria(Criteria.where("accountObjectID").regex("^" + input));
		query.limit(6);
		List<CustomerDetails> cd = null;
		try {
			cd = mongoTemplate.find(query, CustomerDetails.class);
		}
		catch(Exception e){
			
		}
		return cd;
	}
}
