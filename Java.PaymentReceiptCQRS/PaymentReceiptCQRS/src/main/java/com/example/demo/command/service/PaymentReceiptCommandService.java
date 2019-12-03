package com.example.demo.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.command.model.GeneralDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.repository.PaymentReceiptCommandRepository;

@Service
public class PaymentReceiptCommandService {

	@Autowired
	PaymentReceiptCommandRepository paymentReceiptRepository;
	
	public int save(PaymentReceiptCommand paymentReceipt,String keydatabase) {
		return paymentReceiptRepository.save(paymentReceipt, keydatabase);
	}
	public int delete(PaymentReceiptCommand paymentReceipt,String keydatabase) {
		return paymentReceiptRepository.delete(paymentReceipt, keydatabase);
	}
//	public List<PaymentReceipt> getPaymentReceiptOfCompany(String keyCompany){
//		return paymentReceiptRepository.getPaymentReceiptOfCompany(keyCompany);
//	}
//	
//	public List<PaymentReceipt> getPaymentReceiptOfCompanyPage(String keyCompany,long index,int size){
//		return paymentReceiptRepository.getPaymentReceiptOfCompanyPage(keyCompany, index, size);
//	}
//	public List<PaymentReceipt> getAlL(){
//		return paymentReceiptRepository.getAll();
//				}
//	public long count(String keyCompany) {
//		return paymentReceiptRepository.count(keyCompany);
//	}
	
	public int update(PaymentReceiptCommand paymentReceipt,String keydatabase) {
		System.out.println("updateing");
		return paymentReceiptRepository.update(paymentReceipt, keydatabase);
	}
	
	public PaymentReceiptCommand findByID(String id,String keydatabase) {
		return paymentReceiptRepository.getPaymentReceiptById(id, keydatabase);
	}
	public List<GeneralDetailCommand> getGeneralDetailAddPay_Re(int refTypeID, String keyCompany) {
		// TODO Auto-generated method stub
		return paymentReceiptRepository.getGeneralDetailAddPay_Re(keyCompany, refTypeID);
	}

	public boolean updateWriteCashBook(PaymentReceiptCommand paymentReceiptCommand,String keydatabase) {
		return paymentReceiptRepository.updateWriteCashBook(paymentReceiptCommand,keydatabase);
		
	}
	public boolean deleteWriteCashBook(PaymentReceiptCommand paymentReceiptCommand,String keydatabase) {
		return paymentReceiptRepository.deleteWriteCashBook(paymentReceiptCommand,keydatabase);
		
	}

	

//    public int dayConChungLonNhat(String Patterm, String subStr)
//    {
//        
//        Patterm =Patterm.toLowerCase().trim();
//        subStr = subStr.toLowerCase().trim();
//
//        String[] patterms = Patterm.split(" ");
//        String[] subStrs = subStr.split(" ");
//        int x = subStrs.length + 2;
//        int y = patterms.length + 2;
//        int F[][] = new int[x][y];
//
//        for (int i = 0; i <= subStrs.length; i++)
//        {
//            F[i][0] = 0;
//            if (i == subStrs.length) continue;
//            subStrs[i] = subStrs[i].trim();
//           
//        }
//        for (int i = 0; i <= patterms.length; i++)
//        {
//            F[0][i] = 0;
//            if (i == patterms.length) continue;
//            patterms[i] = patterms[i].trim();
//        }
//
//        for (int i = 0; i < subStrs.length; i++)
//        {
//            for (int j = 0; j < patterms.length; j++)
//            {
//                if (patterms[j].equals(subStrs[i]))
//                {
//                    F[i + 1][j + 1] = F[i][j] + 1;
//                    
//                }
//                else
//                {
//                    F[i + 1][j + 1] = Math.max(F[i + 1][ j], F[i][j + 1]);
//                }
//            }
//        }
//        int ketqua= F[subStrs.length][patterms.length];
//        return ketqua;
//    }
//
//
//	
//    public List<PaymentReceipt> search(String nameSearch,String keyCompany){
//    	List<PaymentReceipt> payments=new ArrayList<PaymentReceipt>();
//    	List<PaymentReceipt> lst=paymentReceiptRepository.getPaymentReceiptOfCompany(keyCompany);
//    	int max=0;
//    	for(PaymentReceipt pay:lst) {
//    		String str=StringUtils.unAccent(pay.getJournalMemo());
//		    	int x=dayConChungLonNhat(str, nameSearch);
//		    	if(x>max) {
//		    		max=x;
//		    		payments.clear();
//		    		payments.add(pay);
//		    	}
//		    	else if(max!=0&&x==max) {
//		    		payments.add(pay);
//		    	}
//    	}
//    	return payments;
//    }
//	public Integer getRefNoFinance() {
//		// TODO Auto-generated method stub
//		return paymentReceiptRepository.getRefNoFinance();
//	}
//	public boolean ifRefNoFinanceExist(String refNoFinance) {
//		// TODO Auto-generated method stub
//		return paymentReceiptRepository.ifRefNoFinanceExist(refNoFinance);
//	}
//	
    
//    public List<PaymentReceipt> searchJounalMeno(String keyword){
//    	List<PaymentReceipt> lst= paymentReceiptRepository.search(keyword);
//    	return lst;
//    }
}
