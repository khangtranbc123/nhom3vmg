package com.example.vmg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
public class NhanVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maNV;
    private String hoTen;
    private Date ngaySinh;
    private String email;
    private BigDecimal tienPhucLoi;
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "idPhongBan")
    private PhongBan phongBan;


    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien")
    private List<WelfareStaff> welfareStaff;



}
