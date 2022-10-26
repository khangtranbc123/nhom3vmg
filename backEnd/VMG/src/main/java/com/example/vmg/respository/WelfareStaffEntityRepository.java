package com.example.vmg.respository;

import com.example.vmg.model.WelfareStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface WelfareStaffEntityRepository extends JpaRepository<WelfareStaffEntity, Integer> {
    @Transactional
    @Modifying
   @Query(value = "delete from welfare_staff where id_welfare = :id",nativeQuery = true)
    void deleteWelfareStaffEntitiesByIdWelfare(@Param("id") Long id);
}