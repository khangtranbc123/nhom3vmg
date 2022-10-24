package com.example.vmg.controller;

import com.example.vmg.dto.respose.MessageResponse;
import com.example.vmg.form.StaffForm;
import com.example.vmg.model.*;
import com.example.vmg.respository.StaffRepository;
import com.example.vmg.security.jwt.JwtProvider;
import com.example.vmg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class StaffController {
    @Autowired private StaffService staffService;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegisterWelfareService registerWelfareService;
    @Autowired
    private WelfareStaffService welfareStaffService;
    @Autowired private WelfareService welfareService;
    @GetMapping("/staffs")
    public List<Staff> getListNhanVien(){
        return staffService.getList();
    }
    @PostMapping("/staff")
    public ResponseEntity<?> addNhanVien( @ModelAttribute StaffForm staffForm){
        try {
            Staff staff = new Staff();
            staff.setCode(staffForm.getCode());
            staff.setName(staffForm.getName());
            staff.setDate(staffForm.getDate());
            staff.setEmail(staffForm.getEmail());
            staff.setWelfareMoney(staffForm.getWelfareMoney());
            staff.setDepartment(staffForm.getDepartment());
            staff.setStatus(0);

            //lấy thông tin user
            if (userService.existsByUsername(staffForm.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already taken!"));
            }
            String pass = "123";
            User user = new User(staffForm.getEmail(),
                    passwordEncoder.encode(pass));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setRoles(roles);
            staffService.saveOrUpDate(staff);
            userService.save(user);
            return ResponseEntity.ok(new MessageResponse("create staff successfully!"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(new RuntimeException("Erorr!"));
        }
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id){
        Staff staff = staffService.getById(id);
        staffService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping("/staff/update-money")
    public ResponseEntity<?> updateMoney(@RequestParam("ids") List<Long> ids, @RequestBody BigDecimal money){
        staffService.updateMoney(money, ids);
        String.join(",", ids.stream()
                .map(value ->  Long.toString(value)).collect(Collectors.toList()));
        return ResponseEntity.ok(new MessageResponse("update money staff successfully!"));

    }

    @GetMapping("/staff-show/{id}")
    public ResponseEntity <List<WelfareStaff>> showWelfare(@PathVariable("id") Long id){

        return new ResponseEntity<List<WelfareStaff>>(welfareStaffService.getbystaff(id), HttpStatus.OK);
    }
    @GetMapping("/registers")
    public ResponseEntity <List<RegisterWelfare>> showRegisterWelfare(){
        return new ResponseEntity<List<RegisterWelfare>>(registerWelfareService.getByStatus(), HttpStatus.OK);
    }
    @PutMapping("/register/{id}")
    public ResponseEntity <?> successRegister(@PathVariable Long id){
        RegisterWelfare registerWelfare = registerWelfareService.findById(id).get();
        Staff staff = staffService.getByEmail(registerWelfare.getEmail());

        WelfareStaff welfareStaff = new WelfareStaff();
        welfareStaff.setStaff(staff);
        welfareStaff.setWelfare(registerWelfare.getWelfare());
        welfareStaff.setStatus(0);
        welfareStaffService.save(welfareStaff);

        registerWelfare.setStatus(1);
        registerWelfareService.update(id, registerWelfare);
        return ResponseEntity.ok(new MessageResponse("successfully!"));
    }
    @PutMapping("/register-delete/{id}")
    public ResponseEntity <?> DeleteRegister(@PathVariable Long id){
        RegisterWelfare registerWelfare = registerWelfareService.findById(id).get();
        registerWelfare.setStatus(2);
        registerWelfareService.update(id, registerWelfare);
        return ResponseEntity.ok(new MessageResponse("successfully!"));
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
