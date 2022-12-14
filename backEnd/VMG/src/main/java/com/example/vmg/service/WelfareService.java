package com.example.vmg.service;

import com.example.vmg.model.Welfare;
import com.example.vmg.respository.WelfareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WelfareService {
    @Autowired private WelfareRepository welfareRepository;

    public List<Welfare> getList() {
        return welfareRepository.findAll();
    }

    public Welfare getById(Long id) {
        return welfareRepository.findById(id).get();
    }

    public Optional<Welfare> findById(Long id){
        return welfareRepository.findById(id);
    }

    public void saveOrUpdate(Welfare phucLoi) {
        welfareRepository.save(phucLoi);
    }
    public void update(Long id, Welfare phucLoi) {
        welfareRepository.save(phucLoi);
    }

    public void delete(Long id) {
        welfareRepository.deleteById(id);
    }


}
