package com.example.vmg.model;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class PhucLoiDangHuong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "idNhanVien")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "idPhucLoi")
    private PhucLoi phucLoi;

}
