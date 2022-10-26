package com.example.vmg.respository;

import com.example.vmg.model.WelfareStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WelfareStaffRepository extends JpaRepository<WelfareStaff, Long> {
    List<WelfareStaff> findByStaff_Id(Long id);
}
