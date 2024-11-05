package demo.BankApp.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import demo.BankApp.Entity.Users;
import demo.BankApp.Model.UserDto;
import demo.BankApp.Repository.UserRepository;

@Component
public class UserInfoService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=new Users();
		UserDto	userDto= userRepository.getUserDetail(username);
		if(userDto==null) {
			throw new UsernameNotFoundException("User not Exits");
		}
		user.setPassword(userDto.getPassword());
		user.setUserRole(userDto.getUser_role());
		user.setLoginUser(userDto.getLogin_user());
		return new UserInfoUserDetails(user);
	}

}
