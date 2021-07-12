package com.clothing.company.paymentprocessor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothing.company.paymentprocessor.domain.model.AccountsDO;

public interface AccountsRepository extends JpaRepository<AccountsDO, Integer> {

	List<AccountsDO> findByAccountID(Integer accountID);
}
