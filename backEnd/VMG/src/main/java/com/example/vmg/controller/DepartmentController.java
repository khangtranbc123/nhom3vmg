package com.example.vmg.controller;

import com.example.vmg.form.PhongBanForm;
import com.example.vmg.model.Department;
import com.example.vmg.respository.DepartmentRepository;
import com.example.vmg.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/PhongBan")
public class DepartmentController {
    @Autowired
    private PhongBanService phongBanService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/listPhongBan")
    public List<Department> getListPhongBan(){
        return departmentRepository.findAll();
    }
    @GetMapping("/createPhongBan")
    public ResponseEntity<Void> addNhanVien(@ModelAttribute PhongBanForm phongBanForm){
        Department department = new Department();

        department.setId(phongBanForm.getId());
        department.setName(phongBanForm.getName());
        department.setStatus(phongBanForm.getStatus());
        phongBanService.saveOrUpDate(department);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
