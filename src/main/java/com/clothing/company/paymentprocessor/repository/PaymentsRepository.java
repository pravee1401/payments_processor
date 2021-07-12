package com.clothing.company.paymentprocessor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clothing.company.paymentprocessor.domain.model.PaymentsDO;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentsDO, String> {

	List<PaymentsDO> findByPaymentID(String paymentID);
}
