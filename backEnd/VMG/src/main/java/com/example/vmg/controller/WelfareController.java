package com.example.vmg.controller;

import com.example.vmg.dto.respose.MessageResponse;
import com.example.vmg.model.RegisterWelfare;

import com.example.vmg.form.WelfareForm;
import com.example.vmg.model.Welfare;
import com.example.vmg.model.GeneralWelfare;
import com.example.vmg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class WelfareController {
    @Autowired private WelfareService welfareService;
    @Autowired private WelfareStaffEntityService welfareStaffEntityService;
    @Autowired private GeneralWelfareService generalWelfareService;
    @Autowired private RegisterWelfareService registerWelfareService;
    //@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/welfares")
    public List<Welfare> getlist(){
        return welfareService.getList();
    }
    @GetMapping("/general-welfanes")
    public List<GeneralWelfare> getlistPhucLoiBiDong(){
        return generalWelfareService.getList();
    }
    @PostMapping("/welfare")
    public ResponseEntity<Void> addPhucLoi(@ModelAttribute WelfareForm welfareForm){
        Welfare phucLoi = new Welfare();
        phucLoi.setName(welfareForm.getName());
        phucLoi.setText(welfareForm.getText());
        phucLoi.setPrice(welfareForm.getPrice());
        phucLoi.setStatus(0);
        welfareService.saveOrUpdate(phucLoi);
            return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/general-welfane")
    public ResponseEntity<?> addPhucLoiBiDong(@ModelAttribute WelfareForm welfareForm){
        GeneralWelfare generalWelfare = new GeneralWelfare();
        generalWelfare.setName(welfareForm.getName());
        generalWelfare.setText(welfareForm.getText());
        generalWelfare.setPrice(welfareForm.getPrice());
        generalWelfare.setStatus(1);
        generalWelfareService.save(generalWelfare);
        return ResponseEntity.ok(new MessageResponse("create welfane successfully!"));
    }


    @DeleteMapping("/welfare/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Welfare phucLoi = welfareService.getById(id);
        welfareStaffEntityService.deleteByWelfareId(id);
        welfareService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @DeleteMapping("/general-welfane/{id}")
    public ResponseEntity<Void> delete2(@PathVariable Long id){
        GeneralWelfare generalWelfare = generalWelfareService.getById(id);
        generalWelfareService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/welfare/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute WelfareForm welfareForm){
        Welfare phucLoi = welfareService.findById(id).get();
        phucLoi.setName(welfareForm.getName());
        phucLoi.setText(welfareForm.getText());
        phucLoi.setPrice(welfareForm.getPrice());
        welfareService.update(id, phucLoi);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping("/general-welfane/{id}")
    public ResponseEntity<Void> update2(@PathVariable Long id, @ModelAttribute WelfareForm welfareForm){
        GeneralWelfare generalWelfare = generalWelfareService.findById(id).get();
        generalWelfare.setName(welfareForm.getName());
        generalWelfare.setPrice(welfareForm.getPrice());
        generalWelfare.setText(welfareForm.getText());
        generalWelfareService.update(id, generalWelfare);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/dangkyphucloi/{id}")
    public ResponseEntity<Void> DangKyPhucLoi(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        String maNV = authentication.getName();
        RegisterWelfare registerWelfare = new RegisterWelfare();
        registerWelfareService.saveOrUpdate(registerWelfare);
        Welfare phucLoi = welfareService.getById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
