package com.younghun.hibusgo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
}