package com.example.vmg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;
@SuppressWarnings("serial")
@Entity
public class UpdateTienPhucLoi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maNV;
    private BigDecimal tongTienUpdate;




}
