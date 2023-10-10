package com.enviro.assessment.grad001.alithembamakongwana.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.WithdrawalNotice;

public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {

    List<WithdrawalNotice> findByProductIdAndWithdrawalDateBetween(
    Long productId, Date startDate, Date endDate);
}
