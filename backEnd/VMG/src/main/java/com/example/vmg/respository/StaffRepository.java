package com.example.vmg.respository;

import com.example.vmg.model.Staff;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {

    @Modifying
    @Transactional
    @Query("update Staff p set p.welfareMoney = :number where p.id in(:longs)")
    void updateMoney(BigDecimal number, List<Long> longs);
    @Modifying
    @Transactional
    @Query("update Staff p set p.status = 1 where p.id =:id")
    void delete(Long id);

    Staff findByEmail(String email);
    @Query("select s from Staff s where s.status = 0")
    public Page<Staff> getPage(Pageable pageable);
    @Query("select s from Staff s where month(s.date) = :number ")
    public List<Staff> getSinhNhat(int number);
    @Query("select code from Staff")
    public List<String> getCode();
    @Query("select email from Staff")
    public List<String> getEmail();
    @Query("SELECT DISTINCT b FROM Staff b JOIN b.department c " +
            "WHERE b.status = 0 and b.name LIKE %?1% " +
            "OR c.name LIKE %?1%" +
            "OR b.code LIKE %?1%"
            )
    public Page<Staff> finByText(String text, Pageable pageable);

}
