package com.example.vmg.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WelfareForm {
    private Long id;
    private String name;
    private String describe;
    private BigDecimal price;

}
