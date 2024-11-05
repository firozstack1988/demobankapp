package demo.BankApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
   
	private double currAvailBal;
	private Long glCode;
	private String branchCode;
	
}
