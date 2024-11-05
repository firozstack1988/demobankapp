package demo.BankApp.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String loginUser;
	private String userName;
	private String userType;
	private String password;
	private String userRole;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	 
 
	

}
