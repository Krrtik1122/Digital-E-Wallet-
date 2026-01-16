package com.krrtk.demo.auth.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
}
