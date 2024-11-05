package demo.BankApp.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.BankApp.Entity.Users;
import demo.BankApp.Model.UserDto;
import demo.BankApp.Model.UserModel;
import demo.BankApp.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void addUser(Users userDetail) throws Exception {
		try {
			 userRepository.save(userDetail);
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}
		
	}

	@Override
	public UserModel findUserById(String userId) throws Exception {
		UserModel userModel=new UserModel();
		try {
			UserDto	userDto= userRepository.getUserDetail(userId);
			if(userDto==null) {
				return null;
			}
			else {	
			userModel.setUserName(userDto.getUser_name());
			userModel.setUserType(userDto.getUser_type());
			userModel.setUserRole(userDto.getUser_role());
			userModel.setLoginUser(userDto.getLogin_user());
			}
		}
		catch(Exception e) {
			throw new Exception(e.getLocalizedMessage());
		}	
	    return userModel;	
	}
	 
}
