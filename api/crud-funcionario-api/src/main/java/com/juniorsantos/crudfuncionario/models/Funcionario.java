package com.juniorsantos.crudfuncionario.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name= "TB_FUNCIONARIO")
public class Funcionario implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private Integer matricula;
    private Integer nis;

}
