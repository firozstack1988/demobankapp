package demo.BankApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.BankApp.Entity.FundTransfer;
@Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer,Long>{

}
