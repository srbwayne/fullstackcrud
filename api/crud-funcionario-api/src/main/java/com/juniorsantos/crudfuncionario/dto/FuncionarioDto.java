package com.juniorsantos.crudfuncionario.dto;


import javax.validation.constraints.*;

import com.juniorsantos.crudfuncionario.models.Funcionario;



public class FuncionarioDto {

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
    
    public FuncionarioDto converterToDto( Funcionario model) {
    	id = model.getId();
    	nome = model.getNome();
    	sobrenome = model.getSobrenome();
    	email = model.getEmail();
    	matricula = model.getMatricula();
    	nis = model.getNis();
    	return this;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNis() {
		return nis;
	}

	public void setNis(Integer nis) {
		this.nis = nis;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	@NotBlank
    @Email(message = "Email tem que ser valido")
    private String email;

    @NotBlank
    private Integer nis;

    @NotBlank
    private Integer matricula;
    
    

}