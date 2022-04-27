package com.basic.myspringboot.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserForm {
    @NotBlank(message = "이름은 필수입력 항목입니다!")
    private String name;

    @NotBlank(message = "E메일은 필수입력 항목입니다!")
    @Email(message = "올바른 E메일 형식이 아닙니다!")
    private String email;

}
