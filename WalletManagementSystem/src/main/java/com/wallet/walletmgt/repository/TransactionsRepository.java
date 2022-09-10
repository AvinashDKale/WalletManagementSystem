package com.wallet.walletmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wallet.walletmgt.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

//	@Query(nativeQuery = true, value = "select * from transactions where user_id like %:userId% order by transactions_id DESC limit 1;")
//	Transactions getUserIdByLetestDate(@Param("user_id") int userId);

	Transactions getById(int id);

	List<Transactions> findByUserId(int id);

	Object findAllById(int id);

}
