package demo.BankApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.BankApp.Entity.Users;
import demo.BankApp.Model.UserDto;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{
 
	  @Query(value = "Select e.login_user,e.password,e.user_type,e.user_role,e.user_name from users e WHERE e.login_user=:userId",nativeQuery=true)
	  public UserDto getUserDetail(@Param(value="userId") String userId);
}
