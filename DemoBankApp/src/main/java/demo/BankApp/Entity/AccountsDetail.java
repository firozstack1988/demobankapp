package demo.BankApp.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 
	  private Long id;
	  private String accountName;
	  private String accountType;
	  private String accountNum;                
	  private long glCode;	  
	  private long mobileNumber;
	  private String email;
	  private int branchCode;
	  private double accountBal;
	  private boolean isActive;
	  private boolean isDormant;
	  private boolean isFreeze;
	  private boolean isSMSActive;
	  private boolean isEmailActive;
	  private String createdBy;
	  private Date createdOn;
}
