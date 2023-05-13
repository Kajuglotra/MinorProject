package org.gfg.minor1.Repository;

import org.gfg.minor1.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<TransactionDetails, Integer> {
}
