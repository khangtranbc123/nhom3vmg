package com.example.vmg.respository;

import com.example.vmg.model.Welfare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface WelfareRepository extends JpaRepository<Welfare, Long> {
//    @Modifying
//    @Transactional
//    @Query()
//    void saveRegisterWelfare(Long id_welfare,Long id_user);
}
