package com.juniorsantos.crudfuncionario.mapper;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;
import com.juniorsantos.crudfuncionario.models.Funcionario;

//@Mapper(componentModel = "spring")
public interface FuncionarioMapper extends EntityMapper<FuncionarioDto, Funcionario> {
}