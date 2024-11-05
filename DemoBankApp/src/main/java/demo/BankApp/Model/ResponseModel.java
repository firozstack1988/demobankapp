package demo.BankApp.Model;

import lombok.Data;

@Data
public class ResponseModel {

	private String success;
	private String errorMsg;
	private Object content;
	private String generatedSerial;
	private String jwtToken;
	private String availBal;
}
