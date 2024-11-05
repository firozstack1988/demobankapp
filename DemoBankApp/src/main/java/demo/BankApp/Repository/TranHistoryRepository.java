package demo.BankApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.BankApp.Entity.TranHistory;
import demo.BankApp.Model.TranHistoryCompositId;

@Repository
public interface TranHistoryRepository extends JpaRepository<TranHistory,TranHistoryCompositId>{
  
	@Query(value="select sum(credit_tran_amt) as crediBal from tran_history where gl_code=:glCode",nativeQuery=true)
	public double getGlCrBalance(@Param("glCode") Long glCode);
	
	@Query(value="select sum(debit_tran_amt) as debitBal from tran_history where gl_code=:glCode",nativeQuery=true)
	public double getGlDrBalance(@Param("glCode") Long glCode);
}
