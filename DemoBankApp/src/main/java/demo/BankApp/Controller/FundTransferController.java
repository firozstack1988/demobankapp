package demo.BankApp.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import demo.BankApp.Entity.FundTransfer;
import demo.BankApp.Model.ResponseModel;
import demo.BankApp.Model.ValidationResponse;
import demo.BankApp.Service.FundTransferService;
import demo.BankApp.Utility.AppUtility;

@RestController
@RequestMapping("/fundTransfer")
public class FundTransferController {

	@Autowired
	private FundTransferService fundTransferService;
	
	@PostMapping("/amtTransfer")
	public ResponseModel saveUser(@RequestBody FundTransfer fundTransfer,HttpSession session)throws Exception {
		ResponseModel response=new ResponseModel();
		try {   			
			fundTransfer.setCreatedBy("");
			fundTransfer.setCreatedOn(new Date());
			fundTransfer.setTrandate(new Date());
			fundTransferService.add(fundTransfer);
			response.setSuccess(AppUtility.TRANSACTION_MESSAGE);
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
	return response;
	}	
	
	@GetMapping("/getDebitGLBal/{debitGlCode}")
	public ResponseModel getDebitGLBal(@PathVariable String debitGlCode)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
		  double  debitAccAvailBal=fundTransferService.getAvailDrBal(Long.parseLong(debitGlCode));
		  response.setAvailBal(String.valueOf(debitAccAvailBal));
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
 	  return response;
	}
	
	@GetMapping("/getCreditGLBal/{creditGlCode}")
	public ResponseModel getCreditGLBal(@PathVariable String creditGlCode)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
		  double  debitAccAvailBal=fundTransferService.getAvailCrBal(Long.parseLong(creditGlCode));
		  response.setAvailBal(String.valueOf(debitAccAvailBal));
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
 	  return response;
	}
	
	 
}
