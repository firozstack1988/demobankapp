package demo.BankApp.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LedgerBal {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Id
	private Long glCode;
	private double debitBal;
	private double creditBal;
	private double currBal;
}
