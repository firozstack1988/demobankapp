package demo.BankApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.BankApp.Entity.LedgerBal;

@Repository
public interface LedgerBalRepository extends JpaRepository<LedgerBal,Long>{
    @Modifying
    @Transactional
	@Query(value="update ledger_bal set credit_bal=:creditBal,curr_bal=:currBal where gl_code=:glCode ",nativeQuery=true)
	public void updateCrGlCodeBal(@Param("glCode") Long glCode,@Param("creditBal") double creditBal,@Param("currBal") double currBal);
	
    @Transactional
    @Modifying
	@Query(value="update ledger_bal set debit_bal=:debitBal,curr_bal=:currBal where gl_code=:glCode",nativeQuery=true)
	public void updateDrGlCodeBal(@Param("glCode") Long glCode,@Param("debitBal") double debitBal,@Param("currBal") double currBal);
	
	public Optional<LedgerBal> findByGlCode(@Param("glCode") Long glCode);
	
}
