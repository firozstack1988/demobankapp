package demo.BankApp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.BankApp.Entity.AccountsDetail;
import demo.BankApp.Entity.FundTransfer;
import demo.BankApp.Repository.AccountsDetailRepository;
import demo.BankApp.Repository.FundTransferRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountsDetailServiceImpl implements AccountsDetailService{

	@Autowired
	private AccountsDetailRepository accountsDetailRepository;
	
	@Override
	public void add(AccountsDetail accountsDetail) throws Exception {
	 log.info("start account creation");
	try {
		checkDuplicateAccount(accountsDetail.getAccountNum());
		accountsDetail.setAccountNum(generateAccountNum());
		accountsDetailRepository.save(accountsDetail);
		log.info("Account creation success");
	}
	catch(Exception e) {
		throw new Exception(e.getLocalizedMessage());
	}
		
	}
	
	 private void checkDuplicateAccount(String accountNum) throws Exception{
		 try {
		Optional<AccountsDetail> accountsDetail=accountsDetailRepository.findByAccountNum(accountNum);
		 if(accountsDetail.isPresent()) 
            throw new Exception("Duplicate Account not allowed");
							 		 		 	 		
		}
			catch(Exception e) {
				throw new Exception(e.getLocalizedMessage());
			}
		}
	 
	 private String generateAccountNum() throws Exception{
		 String accNum="";
		 try {
		  String accountNum=accountsDetailRepository.getMaxAccountNum();
		  if(accountNum==null)
			  accNum="1000001";
		  else {
		      long parseAccountNum=Long.parseLong(accountNum)+1;
		      accNum=String.valueOf(parseAccountNum);	
		  }
		 }
		 catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		  }
	     return accNum;	
	 }

	@Override
	public double accAvailBal(String accountNum) throws Exception {
		double accountBal=0;
		try {
			Optional<AccountsDetail>accountDetail=accountsDetailRepository.findByAccountNum(accountNum);
			if(accountDetail.isPresent()) {
				accountBal=accountDetail.get().getAccountBal();
			}
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		return accountBal;
	}

}
