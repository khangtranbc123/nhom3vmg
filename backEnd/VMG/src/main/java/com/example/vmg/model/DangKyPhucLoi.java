package com.example.vmg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuppressWarnings("serial")
@Entity
public class DangKyPhucLoi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maNV;
    private Integer trangThai;
    @ManyToOne
    @JoinColumn(name = "idPhucLoi")
    private Welfare phucLoi;
}
