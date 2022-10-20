package com.example.vmg.controller;

import com.example.vmg.form.PhucLoiForm;
import com.example.vmg.model.DangKyPhucLoi;
import com.example.vmg.model.PhucLoi;
import com.example.vmg.model.PhucLoiBiDong;
import com.example.vmg.service.DangKyPhucLoiService;
import com.example.vmg.service.PhucLoiBiDongService;
import com.example.vmg.service.PhucLoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/phucloi")
public class PhucLoiController {
    @Autowired private PhucLoiService phucLoiService;
    @Autowired private PhucLoiBiDongService phucLoiBiDongService;
    @Autowired private DangKyPhucLoiService dangKyPhucLoiService;

    @GetMapping("/list")
    public List<PhucLoi> getlist(){
        return phucLoiService.getList();
    }
    @GetMapping("/list-phuc-loi-bi-dong")
    public List<PhucLoiBiDong> getlistPhucLoiBiDong(){
        return phucLoiBiDongService.getList();
    }
    @PostMapping("/create")
    public ResponseEntity<Void> addPhucLoi(@ModelAttribute PhucLoiForm phucLoiForm){
        PhucLoi phucLoi = new PhucLoi();
        phucLoi.setTen(phucLoiForm.getTen());
        phucLoi.setMoTa(phucLoiForm.getMoTa());
        phucLoi.setGiaTri(phucLoiForm.getGiaTri());
        phucLoiService.saveOrUpdate(phucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/create-phucloi-bidong")
    public ResponseEntity<Void> addPhucLoiBiDong(@ModelAttribute PhucLoiForm phucLoiForm){
        PhucLoiBiDong phucLoiBiDong = new PhucLoiBiDong();
        phucLoiBiDong.setTen(phucLoiForm.getTen());
        phucLoiBiDong.setMoTa(phucLoiForm.getMoTa());
        phucLoiBiDong.setGiaTri(phucLoiForm.getGiaTri());
        phucLoiBiDongService.saveOrUpdate(phucLoiBiDong);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        PhucLoi phucLoi = phucLoiService.getById(id);
        phucLoiService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
    @DeleteMapping("/delete-phuc-loi-bi-dong/{id}")
    public ResponseEntity<Void> delete2(@PathVariable Long id){
        PhucLoiBiDong phucLoiBiDong = phucLoiBiDongService.getById(id);
        phucLoiBiDongService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute PhucLoiForm phucLoiForm){
        PhucLoi phucLoi = phucLoiService.findById(id).get();
        phucLoi.setTen(phucLoiForm.getTen());
        phucLoi.setMoTa(phucLoiForm.getMoTa());
        phucLoi.setGiaTri(phucLoiForm.getGiaTri());
        phucLoiService.update(id, phucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping("/update-phuc-loi-bi-dong/{id}")
    public ResponseEntity<Void> update2(@PathVariable Long id, @ModelAttribute PhucLoiForm phucLoiForm){
        PhucLoiBiDong phucLoiBiDong = phucLoiBiDongService.findById(id).get();
        phucLoiBiDong.setTen(phucLoiForm.getTen());
        phucLoiBiDong.setMoTa(phucLoiForm.getMoTa());
        phucLoiBiDong.setGiaTri(phucLoiForm.getGiaTri());
        phucLoiBiDongService.update(id, phucLoiBiDong);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/dangky/{id}")
    public ResponseEntity<Void> DangKyPhucLoi(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        String maNV = authentication.getName();
        PhucLoi phucLoi = phucLoiService.getById(id);
        DangKyPhucLoi dangKyPhucLoi = new DangKyPhucLoi();
        dangKyPhucLoi.setMaNV(maNV);
        dangKyPhucLoi.setPhucLoi(phucLoi);
        dangKyPhucLoi.setTrangThai(0);
        dangKyPhucLoiService.saveOrUpdate(dangKyPhucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
