package com.younghun.hibusgo.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}