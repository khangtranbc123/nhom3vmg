package com.example.vmg.respository;

import com.example.vmg.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {

    @Modifying
    @Transactional
    @Query("update Staff p set p.tienPhucLoi = :number where p.id in(:longs)")
    void updateMoney(BigDecimal number, List<Long> longs);
}
