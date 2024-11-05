package demo.BankApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.BankApp.Entity.AccountsDetail;
import demo.BankApp.Entity.LedgerBal;;
@Repository
public interface AccountsDetailRepository extends JpaRepository<AccountsDetail,Long>{
 
	    @Transactional
	    @Modifying
		@Query(value="update accounts_detail set account_bal=:accCurrBal where account_num=:accountNum",nativeQuery=true)
		public void updateCustAccBal(@Param("accountNum") String accountNum,@Param("accCurrBal") double accCurrBal);
		
		public Optional<AccountsDetail> findByAccountNum(@Param("accountNum") String accountNum);
		
		 
		@Query(value="select max(account_num) from accounts_detail ",nativeQuery=true)
		public String getMaxAccountNum();
			
}
