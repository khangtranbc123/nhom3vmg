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
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "idPhucLoi")
    private Welfare welfare;

}