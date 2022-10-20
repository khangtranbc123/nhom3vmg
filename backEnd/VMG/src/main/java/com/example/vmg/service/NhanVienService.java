package com.example.vmg.service;

import com.example.vmg.model.Staff;
import com.example.vmg.respository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getList(){
        return staffRepository.findAll();
    }

    public Staff getById(Long id){
        return staffRepository.findById(id).get();
    }

    public void saveOrUpDate(Staff staff){
        staffRepository.save(staff);
    }

    public void delete(Long id){
        staffRepository.deleteById(id);
    }
    public void updateMoney(BigDecimal number, List<Long> longs) {
//      personRepository.deleteByIdIn(new ArrayList<>(integers));
        staffRepository.updateMoney(number, longs);
        System.out.println("update money");
    }
}
