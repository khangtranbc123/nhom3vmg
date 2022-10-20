package com.example.vmg.service;

import com.example.vmg.model.PhongBan;
import com.example.vmg.respository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;

    public List<PhongBan> getList(){
        return phongBanRepository.findAll();
    }

    public  PhongBan getById(Long id){
        return phongBanRepository.findById(id).get();
    }

    public void saveOrUpDate(PhongBan phongBan){
        phongBanRepository.save(phongBan);
    }

    public void delete(Long id){
        phongBanRepository.deleteById(id);
    }
}
