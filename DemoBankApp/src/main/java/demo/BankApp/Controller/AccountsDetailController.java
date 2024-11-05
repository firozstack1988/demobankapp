package demo.BankApp.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.BankApp.Entity.AccountsDetail;
import demo.BankApp.Entity.FundTransfer;
import demo.BankApp.Model.ResponseModel;
import demo.BankApp.Service.AccountsDetailService;
import demo.BankApp.Service.FundTransferService;
import demo.BankApp.Utility.AppUtility;
import demo.BankApp.Model.ResponseModel;

@RestController
@RequestMapping("/accountsDetail")
public class AccountsDetailController {

	@Autowired
	private AccountsDetailService accountsDetailService;
	
	@PostMapping("/addAccount")
	public ResponseModel addAccount(@RequestBody AccountsDetail accountsDetail)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
			accountsDetail.setCreatedOn(new Date());
			accountsDetail.setGlCode(AppUtility.SAVINGS_ACC_GL);
			accountsDetail.setActive(true);
			accountsDetail.setAccountBal(0);
			accountsDetailService.add(accountsDetail);
			response.setSuccess(AppUtility.SUCCESS_MESSAGE);
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
	return response;
	}
	
	@GetMapping("/accAvailBal/{accountNum}")
	public ResponseModel accAvailBal(@PathVariable String accountNum)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
		  double  accAvailBal=accountsDetailService.accAvailBal(accountNum);
		  response.setAvailBal(String.valueOf(accAvailBal));
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
 	  return response;
	}
	  
}
