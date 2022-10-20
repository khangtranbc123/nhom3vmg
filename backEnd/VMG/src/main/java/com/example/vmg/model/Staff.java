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
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Date date;
    private String email;
    private BigDecimal welfareMoney;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "idPhongBan")
    private Department department;


    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<WelfareStaff> welfareStaff;



}
