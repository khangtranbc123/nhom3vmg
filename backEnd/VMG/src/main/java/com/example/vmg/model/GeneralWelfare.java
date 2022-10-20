package com.example.vmg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
public class GeneralWelfare implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String describe;
    private BigDecimal price;
    private Integer status;


}
