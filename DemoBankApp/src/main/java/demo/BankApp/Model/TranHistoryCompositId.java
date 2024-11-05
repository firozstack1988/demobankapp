package demo.BankApp.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranHistoryCompositId implements Serializable{
   
	private Long id;
	private long glCode;
	private Date tranDate;
	
}
