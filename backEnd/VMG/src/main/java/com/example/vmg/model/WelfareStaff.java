package com.example.vmg.model;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class WelfareStaff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "idNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "idPhucLoi")
    private Welfare welfare;

}
