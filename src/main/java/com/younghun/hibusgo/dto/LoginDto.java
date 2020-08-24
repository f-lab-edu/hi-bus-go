package com.younghun.hibusgo.dto;



import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginDto {

    @NotBlank
    @Email
    private String userId;

    @NotBlank
    @Length(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{8,16}$")
    private String password;
}