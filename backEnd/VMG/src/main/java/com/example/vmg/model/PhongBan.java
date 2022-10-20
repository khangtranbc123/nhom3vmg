package com.example.vmg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@SuppressWarnings("serial")
@Entity
public class PhongBan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private Integer TrangThai;
    @JsonIgnore
    @OneToMany(mappedBy = "phongBan")
    private List<NhanVien> nhanVienList;

}
