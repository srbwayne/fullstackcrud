package com.juniorsantos.crudfuncionario.models;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;

@Entity
@Table(name= "TB_FUNCIONARIO")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private Integer matricula;
    private Integer nis;
    
    public Funcionario() {};
    
    public Funcionario converterToModel(FuncionarioDto dto) {
    
    	id = dto.getId();
    	nome = dto.getNome();
    	sobrenome = dto.getSobrenome();
    	email = dto.getEmail();
    	matricula = dto.getMatricula();
    	nis = dto.getNis();
    	return this;
    }
    
    public Funcionario(Long id, String nome, String sobrenome, String email, Integer matricula, Integer nis) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.matricula = matricula;
		this.nis = nis;
	}
    
	@Override
	public int hashCode() {
		return Objects.hash(email, id, matricula, nis, nome, sobrenome);
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
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public Integer getNis() {
		return nis;
	}
	public void setNis(Integer nis) {
		this.nis = nis;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(matricula, other.matricula) && Objects.equals(nis, other.nis)
				&& Objects.equals(nome, other.nome) && Objects.equals(sobrenome, other.sobrenome);
	}

}
