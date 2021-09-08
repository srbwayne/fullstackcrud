package com.juniorsantos.crudfuncionario.controller;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;

import java.util.Collections;
import java.util.List;

public class funcionarioBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static Object getDto() {
        FuncionarioDto dto = new FuncionarioDto();
        dto.setCreatedBy("1");
        return dto;
    }
}