package demo.BankApp.Service;

import demo.BankApp.Entity.AccountsDetail;

public interface AccountsDetailService {

	public void add(AccountsDetail accountsDetail)throws Exception;
	public double accAvailBal(String accountNum)throws Exception;
}
