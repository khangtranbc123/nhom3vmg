package com.example.vmg.service;

import com.example.vmg.model.PhucLoi;
import com.example.vmg.respository.PhucLoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhucLoiService {
    @Autowired private PhucLoiRepository phucLoiRepository;

    public List<PhucLoi> getList() {
        return phucLoiRepository.findAll();
    }

    public PhucLoi getById(Long id) {
        return phucLoiRepository.findById(id).get();
    }

    public Optional<PhucLoi> findById(Long id){
        return phucLoiRepository.findById(id);
    }

    public void saveOrUpdate(PhucLoi phucLoi) {
        phucLoiRepository.save(phucLoi);
    }
    public void update(Long id, PhucLoi phucLoi) {
        phucLoiRepository.save(phucLoi);
    }

    public void delete(Long id) {
        phucLoiRepository.deleteById(id);
    }

}
