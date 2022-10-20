package com.example.vmg.form;

import com.example.vmg.model.Department;
import com.example.vmg.model.PhucLoiDangHuong;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class NhanVienForm {
    private Long id;
    private String code;
    private String name;
    private Date date;
    private String email;
    private BigDecimal welfareMoney;
    private Integer status;
    private Department department;
}
