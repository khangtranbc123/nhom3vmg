package com.example.vmg.controller;

import com.example.vmg.form.NhanVienForm;
import com.example.vmg.model.Staff;
import com.example.vmg.respository.StaffRepository;
import com.example.vmg.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/nhanvien")
public class StaffController {
    @Autowired private NhanVienService nhanVienService;

    @Autowired private StaffRepository staffRepository;

    @GetMapping("/listnhanvien")
    public List<Staff> getListNhanVien(){
        return nhanVienService.getList();
    }
    @PostMapping("/createnhanvien")
    public ResponseEntity<Void> addNhanVien(@ModelAttribute NhanVienForm nhanVienForm){

        Staff staff = new Staff();
        staff.setId(nhanVienForm.getId());
        staff.setCode(nhanVienForm.getCode());
        staff.setName(nhanVienForm.getName());
        staff.setDate(nhanVienForm.getDate());
        staff.setEmail(nhanVienForm.getEmail());
        staff.setWelfareMoney(nhanVienForm.getWelfareMoney());
        staff.setStatus(nhanVienForm.getStatus());
        staff.setDepartment(nhanVienForm.getDepartment());
//        staff.setPhucLoiDangHuong(nhanVienForm.);

        nhanVienService.saveOrUpDate(staff);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id){
        Staff staff = nhanVienService.getById(id);
        nhanVienService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Void> update2(@PathVariable Long id, @ModelAttribute NhanVienForm nhanVienForm){
//        NhanVien nhanVien = nhanVienService.findById(id).get();
//        nhanVien.setHoTen(nhanVienForm.getHoTen());
//        nhanVien.setNgaySinh(nhanVienForm.getNgaysinh());
//        nhanVien.setEmail(nhanVienForm.getEmail());
//        nhanVien.setTienPhucLoi(nhanVienForm.getTienPhucLoi());
//        nhanVien.setTrangThai(nhanVienForm.getTrangThai());
//        nhanVien.setPhongBan(nhanVienForm.getPhongBan());
//        nhanVien.setPhucLoiDangHuong(nhanVienForm.getPhucLoiDangHuong());
//        nhanVienService.update(id, nhanVien);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }


}
