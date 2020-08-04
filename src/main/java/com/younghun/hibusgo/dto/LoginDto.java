package com.younghun.hibusgo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private final String id;

    @NotBlank
    private final String password;
}