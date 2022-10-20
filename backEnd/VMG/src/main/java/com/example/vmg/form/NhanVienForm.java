package com.example.vmg.form;

import com.example.vmg.model.PhongBan;
import com.example.vmg.model.PhucLoiDangHuong;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class NhanVienForm {
    private Long id;
    private String maNV;
    private String hoTen;
    private Date ngaysinh;
    private String email;
    private BigDecimal tienPhucLoi;
    private Integer trangThai;
    private PhongBan phongBan;
    private List<PhucLoiDangHuong> phucLoiDangHuong;
}
