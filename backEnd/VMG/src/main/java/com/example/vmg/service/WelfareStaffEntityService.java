package com.example.vmg.service;

import com.example.vmg.model.WelfareStaffEntity;
import com.example.vmg.respository.WelfareStaffEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelfareStaffEntityService {
    @Autowired
    private WelfareStaffEntityRepository welfareStaffEntityRepository;
    public void deleteByWelfareId(Long id){welfareStaffEntityRepository.deleteWelfareStaffEntitiesByIdWelfare(id);}
}
