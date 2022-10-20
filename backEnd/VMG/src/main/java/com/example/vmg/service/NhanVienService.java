package com.example.vmg.service;

import com.example.vmg.model.NhanVien;
import com.example.vmg.respository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getList(){
        return nhanVienRepository.findAll();
    }

    public  NhanVien getById(Long id){
        return nhanVienRepository.findById(id).get();
    }

    public void saveOrUpDate(NhanVien nhanVien){
        nhanVienRepository.save(nhanVien);
    }

    public void delete(Long id){
        nhanVienRepository.deleteById(id);
    }
    public void updateMoney(BigDecimal number, List<Long> longs) {
//      personRepository.deleteByIdIn(new ArrayList<>(integers));
        nhanVienRepository.updateMoney(number, longs);
        System.out.println("update money");
    }
}
