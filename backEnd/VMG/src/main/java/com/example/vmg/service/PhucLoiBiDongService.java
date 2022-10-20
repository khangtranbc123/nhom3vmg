package com.example.vmg.service;

import com.example.vmg.model.PhucLoi;
import com.example.vmg.model.PhucLoiBiDong;
import com.example.vmg.respository.PhucLoiBiDongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhucLoiBiDongService {
    @Autowired
    private PhucLoiBiDongRepository phucLoiBiDongRepository;

    public List<PhucLoiBiDong> getList() {
        return phucLoiBiDongRepository.findAll();
    }

    public PhucLoiBiDong getById(Long id) {
        return phucLoiBiDongRepository.findById(id).get();
    }

    public Optional<PhucLoiBiDong> findById(Long id){
        return phucLoiBiDongRepository.findById(id);
    }

    public void saveOrUpdate(PhucLoiBiDong phucLoiBiDong) {
        phucLoiBiDongRepository.save(phucLoiBiDong);
    }

    public void update(Long id, PhucLoiBiDong phucLoiBiDong) {
        phucLoiBiDongRepository.save(phucLoiBiDong);
    }

    public void delete(Long id) {
        phucLoiBiDongRepository.deleteById(id);
    }

}
