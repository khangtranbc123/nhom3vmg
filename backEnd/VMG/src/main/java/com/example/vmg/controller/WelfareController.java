package com.example.vmg.controller;

import com.example.vmg.form.WelfareForm;
import com.example.vmg.model.DangKyPhucLoi;
import com.example.vmg.model.Welfare;
import com.example.vmg.model.GeneralWelfare;
import com.example.vmg.service.DangKyPhucLoiService;
import com.example.vmg.service.GeneralWelfareService;
import com.example.vmg.service.WelfareService;
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
public class WelfareController {
    @Autowired private WelfareService welfareService;
    @Autowired private GeneralWelfareService generalWelfareService;
    @Autowired private DangKyPhucLoiService dangKyPhucLoiService;

    @GetMapping("/list")
    public List<Welfare> getlist(){
        return welfareService.getList();
    }
    @GetMapping("/list-phuc-loi-bi-dong")
    public List<GeneralWelfare> getlistPhucLoiBiDong(){
        return generalWelfareService.getList();
    }
    @PostMapping("/create")
    public ResponseEntity<Void> addPhucLoi(@ModelAttribute WelfareForm welfareForm){
        Welfare phucLoi = new Welfare();
        phucLoi.setName(welfareForm.getName());
        phucLoi.setDescribe(welfareForm.getDescribe());
        phucLoi.setPrice(welfareForm.getPrice());
        welfareService.saveOrUpdate(phucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/create-phucloi-bidong")
    public ResponseEntity<Void> addPhucLoiBiDong(@ModelAttribute WelfareForm welfareForm){
        GeneralWelfare generalWelfare = new GeneralWelfare();
        generalWelfare.setName(welfareForm.getName());
        generalWelfare.setDescribe(welfareForm.getDescribe());
        generalWelfare.setPrice(welfareForm.getPrice());
        generalWelfareService.saveOrUpdate(generalWelfare);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Welfare phucLoi = welfareService.getById(id);
        welfareService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
    @DeleteMapping("/delete-phuc-loi-bi-dong/{id}")
    public ResponseEntity<Void> delete2(@PathVariable Long id){
        GeneralWelfare generalWelfare = generalWelfareService.getById(id);
        generalWelfareService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute WelfareForm welfareForm){
        Welfare phucLoi = welfareService.findById(id).get();
        phucLoi.setName(welfareForm.getName());
        phucLoi.setDescribe(welfareForm.getDescribe());
        phucLoi.setPrice(welfareForm.getPrice());
        welfareService.update(id, phucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping("/update-phuc-loi-bi-dong/{id}")
    public ResponseEntity<Void> update2(@PathVariable Long id, @ModelAttribute WelfareForm welfareForm){
        GeneralWelfare generalWelfare = generalWelfareService.findById(id).get();
        generalWelfare.setName(welfareForm.getName());
        generalWelfare.setDescribe(welfareForm.getDescribe());
        generalWelfare.setPrice(welfareForm.getPrice());
        generalWelfareService.update(id, generalWelfare);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/dangky/{id}")
    public ResponseEntity<Void> DangKyPhucLoi(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        String maNV = authentication.getName();
        Welfare phucLoi = welfareService.getById(id);
        DangKyPhucLoi dangKyPhucLoi = new DangKyPhucLoi();
        dangKyPhucLoi.setMaNV(maNV);
        dangKyPhucLoi.setPhucLoi(phucLoi);
        dangKyPhucLoi.setTrangThai(0);
        dangKyPhucLoiService.saveOrUpdate(dangKyPhucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
