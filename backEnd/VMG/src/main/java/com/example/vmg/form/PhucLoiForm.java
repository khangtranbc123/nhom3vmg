package com.example.vmg.form;

import com.example.vmg.model.PhongBan;
import com.example.vmg.model.PhucLoiDangHuong;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PhucLoiForm {
    private Long id;
    private String ten;
    private String moTa;
    private BigDecimal giaTri;
    private PhongBan phongBan;
    private List<PhucLoiDangHuong> phucLoiDangHuong;


}
