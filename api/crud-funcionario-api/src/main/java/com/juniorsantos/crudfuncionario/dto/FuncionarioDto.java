package com.juniorsantos.crudfuncionario.dto;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class FuncionarioDto extends AbstractDto<Long> {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30, message
            = "Deverá ter entre 2 a 30 caracteres")
    private String nome;

    @NotBlank
    @Size(min = 2, max = 50, message
            = "deverá ter entre 2 a 50 caracteres")
    private String sobrenome;

    @NotBlank
    @Email(message = "Email tem que ser valido")
    private String email;

    @NotBlank
    private Integer nis;

    @NotBlank
    private Integer matricula;

}