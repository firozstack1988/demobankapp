package demo.BankApp.Service;

import demo.BankApp.Entity.FundTransfer;

public interface FundTransferService {

	public void add(FundTransfer fundTransfer)throws Exception;
	public double getAvailCrBal(Long debitGlCode)throws Exception;
	public double getAvailDrBal(Long creditGlCode)throws Exception;
}
