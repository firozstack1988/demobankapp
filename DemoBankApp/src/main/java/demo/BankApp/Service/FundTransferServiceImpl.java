package demo.BankApp.Service;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.BankApp.Entity.AccountsDetail;
import demo.BankApp.Entity.FundTransfer;
import demo.BankApp.Entity.LedgerBal;
import demo.BankApp.Entity.TranHistory;
import demo.BankApp.Repository.AccountsDetailRepository;
import demo.BankApp.Repository.FundTransferRepository;
import demo.BankApp.Repository.LedgerBalRepository;
import demo.BankApp.Repository.TranHistoryRepository;
import demo.BankApp.Utility.AppUtility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FundTransferServiceImpl implements FundTransferService{

	@Autowired
	private FundTransferRepository fundTransferRepository;
	@Autowired
	private TranHistoryRepository tranHistoryRepository;
	@Autowired
	private AccountsDetailRepository  accountsDetailRepository;
	
	@Override
	@Transactional
	public void add(FundTransfer fundTransfer) throws Exception {
		   log.info("Start fund Transfer");
		try {
			checkAccount(fundTransfer);
			fundTransferRepository.save(fundTransfer);
			insertTranHistory(fundTransfer);
			updateCustomerAccountBal(fundTransfer);	
			log.info("Fund Transfer Success");
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}
	
    private void checkAccount(FundTransfer fundTransfer) throws Exception{
	 try {
	Optional<AccountsDetail> accountsDetail=accountsDetailRepository.findByAccountNum(fundTransfer.getAccountNumber());
	 if(accountsDetail.isPresent()) {
		 if(accountsDetail.get().isActive()==false) {
			  throw new Exception("Account not active");
		  }
		  if(accountsDetail.get().isDormant()==true) {
			  throw new Exception("Account is Dormant");
		  }
		  if(accountsDetail.get().isFreeze()==true) {
			  throw new Exception("Account is Freezed");
		  } 
	 }
	 else 
		 throw new Exception("Invalid Account Number");
	 	 		
	}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}
    	
	private void insertTranHistory(FundTransfer fundTransfer)throws Exception {
		TranHistory tranHistory=new TranHistory();
		try {
			long batchNum=getnerateBatchNumber();
			//credit block
			tranHistory.setCreatedBy(fundTransfer.getCreatedBy());
			tranHistory.setCreatedOn(new Date());
			tranHistory.setAccountNum(fundTransfer.getAccountNumber());
			if(fundTransfer.getTranType().equalsIgnoreCase("Deposit"))
				tranHistory.setGlCode(AppUtility.SAVINGS_ACC_GL);	
			else if(fundTransfer.getTranType().equalsIgnoreCase("Withdraw"))				
			    tranHistory.setGlCode(AppUtility.lIABILITY_ACC_GL);	
			tranHistory.setCreditTranAmt(fundTransfer.getTranAmt());
			tranHistory.setDebitTranAmt(0);
			tranHistory.setTranDate(new Date());
			tranHistory.setTranType(fundTransfer.getTranType());
			tranHistory.setTranBatchNum(batchNum);
			tranHistoryRepository.save(tranHistory);
			
			//debit block
			tranHistory.setCreatedBy(fundTransfer.getCreatedBy());
			tranHistory.setCreatedOn(new Date());
			tranHistory.setAccountNum(fundTransfer.getAccountNumber());
			if(fundTransfer.getTranType().equalsIgnoreCase("Deposit"))
				tranHistory.setGlCode(AppUtility.lIABILITY_ACC_GL);	
			else if(fundTransfer.getTranType().equalsIgnoreCase("Withdraw"))				
			    tranHistory.setGlCode(AppUtility.SAVINGS_ACC_GL);		
			tranHistory.setDebitTranAmt(fundTransfer.getTranAmt());
			tranHistory.setCreditTranAmt(0);
			tranHistory.setTranDate(new Date());
			tranHistory.setTranType(fundTransfer.getTranType());
			tranHistory.setTranBatchNum(batchNum);
			tranHistoryRepository.save(tranHistory);
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}
	
	private long getnerateBatchNumber() throws Exception {
		long batchNum=0;
		try {
		long count=tranHistoryRepository.count();
		batchNum=count+1;
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		return batchNum;
	}
	
	private void updateCustomerAccountBal(FundTransfer fundTransfer)throws Exception{
		double accPresentBal=0.0;
		try {
		Optional<AccountsDetail> accountsBal=accountsDetailRepository.findByAccountNum(fundTransfer.getAccountNumber());
		if(accountsBal.isPresent()){
			double currentAccBal=accountsBal.get().getAccountBal();
			if(fundTransfer.getTranType().equalsIgnoreCase("Deposit"))
			     accPresentBal =currentAccBal+fundTransfer.getTranAmt();
			
			else if(fundTransfer.getTranType().equalsIgnoreCase("Withdraw")) {
				 if(currentAccBal>=fundTransfer.getTranAmt()) 
					   accPresentBal =currentAccBal-fundTransfer.getTranAmt();				 
				 else
					   throw new Exception("Balance Not Available"); 
			   }
				 
		}
		try {
			accountsDetailRepository.updateCustAccBal(fundTransfer.getAccountNumber(),accPresentBal);
		}
		catch(Exception e) {
		    throw new Exception(e.getLocalizedMessage());
		}
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
	}
		
	@Override
	public double getAvailDrBal(Long debitGlCode) throws Exception {
		double availDrBal=0.0;
		try {
			availDrBal=tranHistoryRepository.getGlDrBalance(debitGlCode);
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		return availDrBal;
		}
	
	@Override
	public double getAvailCrBal(Long creditGlCode) throws Exception {
		double availcrBal=0.0;
		try {
			availcrBal=tranHistoryRepository.getGlCrBalance(creditGlCode);
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		return availcrBal;
		}
		
}
