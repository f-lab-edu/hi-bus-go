package com.younghun.hibusgo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class LoginDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
}
