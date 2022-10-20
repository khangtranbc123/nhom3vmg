package com.example.vmg.respository;

import com.example.vmg.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {

    @Modifying
    @Transactional
    @Query("update NhanVien p set p.tienPhucLoi = :number where p.id in(:longs)")
    void updateMoney(BigDecimal number, List<Long> longs);
}
