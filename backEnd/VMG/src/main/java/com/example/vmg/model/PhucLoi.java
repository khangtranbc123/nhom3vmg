package com.example.vmg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
public class PhucLoi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private String moTa;
    private BigDecimal giaTri;
    private Integer trangThai;
    @JsonIgnore
    @OneToMany(mappedBy = "phucLoi")
    private List<RegisterWelfare> registerWelfareList;


}
