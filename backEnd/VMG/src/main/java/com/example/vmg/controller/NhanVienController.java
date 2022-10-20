package com.example.vmg.controller;

import com.example.vmg.form.NhanVienForm;
import com.example.vmg.model.NhanVien;
import com.example.vmg.respository.NhanVienRepository;
import com.example.vmg.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/nhanvien")
public class NhanVienController {
    @Autowired private NhanVienService nhanVienService;

    @Autowired private NhanVienRepository nhanVienRepository;

    @GetMapping("/listnhanvien")
    public List<NhanVien> getListNhanVien(){
        return nhanVienService.getList();
    }
    @PostMapping("/createnhanvien")
    public ResponseEntity<Void> addNhanVien(@ModelAttribute NhanVienForm nhanVienForm){

        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(nhanVienForm.getId());
        nhanVien.setMaNV(nhanVienForm.getMaNV());
        nhanVien.setHoTen(nhanVienForm.getHoTen());
        nhanVien.setNgaySinh(nhanVienForm.getNgaysinh());
        nhanVien.setEmail(nhanVienForm.getEmail());
        nhanVien.setTienPhucLoi(nhanVienForm.getTienPhucLoi());
        nhanVien.setTrangThai(nhanVienForm.getTrangThai());
        nhanVien.setPhongBan(nhanVienForm.getPhongBan());
        nhanVien.setWelfareStaff(nhanVienForm.getWelfareStaff());

        nhanVienService.saveOrUpDate(nhanVien);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id){
        NhanVien nhanVien = nhanVienService.getById(id);
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
