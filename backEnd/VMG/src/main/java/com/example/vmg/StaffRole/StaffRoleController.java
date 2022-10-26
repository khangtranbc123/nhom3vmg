package com.example.vmg.StaffRole;

import com.example.vmg.form.WelfareForm;
import com.example.vmg.model.Welfare;
import com.example.vmg.model.WelfareStaffEntity;
import com.example.vmg.respository.WelfareRepository;
import com.example.vmg.respository.WelfareStaffEntityRepository;
import com.example.vmg.security.jwt.JwtProvider;
import com.example.vmg.service.AddWelfare;
import com.example.vmg.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class StaffRoleController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private WelfareStaffEntityRepository welfareStaffEntityRepository;
    @PostMapping("/regis_welfare")
    public ResponseEntity<Void> createTutorial(@RequestBody AddWelfare addWelfareRequest) {
        try {
            for (Integer wid: addWelfareRequest.getList()
                 ) {
                welfareStaffEntityRepository.save(new WelfareStaffEntity(2, Long.valueOf(wid),Long.valueOf(addWelfareRequest.getId())));
            }
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/find_id/{username}")
    public Long getIdOfAccount(@PathVariable String username){
        return staffService.getByEmail(username).getId();
    }

    @GetMapping("/find_money/{username}")
    public BigDecimal getMoneyOfAccount(@PathVariable String username){
        return staffService.getByEmail(username).getWelfareMoney();
    }

}
