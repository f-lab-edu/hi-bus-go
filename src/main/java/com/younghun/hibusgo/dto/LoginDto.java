package com.younghun.hibusgo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}