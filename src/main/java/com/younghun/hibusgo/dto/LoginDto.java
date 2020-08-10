package com.younghun.hibusgo.dto;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class LoginDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}