package demo.BankApp.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import demo.BankApp.Model.TranHistoryCompositId;
import lombok.Data;

@Entity
@Data
@IdClass(TranHistoryCompositId.class)
public class TranHistory {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	@Id
	private long glCode;
	@Id
	private Date tranDate;
	private double debitTranAmt;
	private double creditTranAmt;
	private String accountNum;
	private String tranType;
	private long tranBatchNum;
	private String createdBy;
	private Date createdOn;
}
