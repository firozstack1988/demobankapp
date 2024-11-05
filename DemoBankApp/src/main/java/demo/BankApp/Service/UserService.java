package demo.BankApp.Service;

import demo.BankApp.Entity.Users;
import demo.BankApp.Model.UserModel;

public interface UserService {
   
	public void addUser(Users userDetail) throws Exception;
	public UserModel findUserById(String userId) throws Exception;
}
