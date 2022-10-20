package com.example.vmg.service;

import com.example.vmg.model.DangKyPhucLoi;
import com.example.vmg.respository.DangKyPhucLoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DangKyPhucLoiService {
    @Autowired private DangKyPhucLoiRepository dangKyPhucLoiRepository;

    public List<DangKyPhucLoi> getList() {
        return dangKyPhucLoiRepository.findAll();
    }

    public DangKyPhucLoi getById(Long id) {
        return dangKyPhucLoiRepository.findById(id).get();
    }

    public Optional<DangKyPhucLoi> findById(Long id){
        return dangKyPhucLoiRepository.findById(id);
    }

    public void saveOrUpdate(DangKyPhucLoi phucLoi) {
        dangKyPhucLoiRepository.save(phucLoi);
    }
    public void update(Long id, DangKyPhucLoi phucLoi) {
        dangKyPhucLoiRepository.save(phucLoi);
    }

    public void delete(Long id) {
        dangKyPhucLoiRepository.deleteById(id);
    }
}
