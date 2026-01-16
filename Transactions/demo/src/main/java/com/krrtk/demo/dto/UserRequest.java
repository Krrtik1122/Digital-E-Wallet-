package com.krrtk.demo.dto;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserRequest {

    private String name ;
    private String email;
    private BigDecimal initialBalance;
    private String password;
}
