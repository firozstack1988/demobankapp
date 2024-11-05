package demo.BankApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	
	private String loginUser;
	private String userName;
	private String userType;
	private String password;
	private String userRole;
}
