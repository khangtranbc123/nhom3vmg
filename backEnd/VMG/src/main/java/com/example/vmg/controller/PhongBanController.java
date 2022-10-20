package com.example.vmg.controller;

import com.example.vmg.form.NhanVienForm;
import com.example.vmg.form.PhongBanForm;
import com.example.vmg.model.NhanVien;
import com.example.vmg.model.PhongBan;
import com.example.vmg.respository.PhongBanRepository;
import com.example.vmg.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/PhongBan")
public class PhongBanController  {
    @Autowired
    private PhongBanService phongBanService;
    @Autowired
    private PhongBanRepository phongBanRepository;

    @GetMapping("/listPhongBan")
    public List<PhongBan> getListPhongBan(){
        return phongBanRepository.findAll();
    }
    @GetMapping("/createPhongBan")
    public ResponseEntity<Void> addNhanVien(@ModelAttribute PhongBanForm phongBanForm){
        PhongBan phongBan = new PhongBan();

        phongBan.setId(phongBanForm.getId());
        phongBan.setTen(phongBanForm.getTen());
        phongBan.setTrangThai(phongBanForm.getTrangThai());
        phongBanService.saveOrUpDate(phongBan);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
