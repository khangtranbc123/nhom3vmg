package com.example.vmg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuppressWarnings("serial")
@Entity
public class WelfareStaff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "idStaff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "idWelfare")
    private Welfare welfare;

}
