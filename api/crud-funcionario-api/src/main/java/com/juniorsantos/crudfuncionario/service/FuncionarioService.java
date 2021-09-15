package com.juniorsantos.crudfuncionario.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;
import com.juniorsantos.crudfuncionario.models.Funcionario;
import com.juniorsantos.crudfuncionario.repository.FuncionarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FuncionarioService {
	
	@Autowired
    private  FuncionarioRepository funcionarioRepository;


    public FuncionarioDto save(FuncionarioDto funcionarioDto) {
    	System.out.printf("funcionario: ?",funcionarioDto);
    	Funcionario funcionario = new Funcionario();
    	funcionario.converterToModel(funcionarioDto);
        return new FuncionarioDto().converterToDto(funcionarioRepository.save(funcionario));
    }

    public void deleteById(Long id) {
    	funcionarioRepository.deleteById(id);
    }
    
    public FuncionarioDto findById(Long id) {
    	return new FuncionarioDto().converterToDto(funcionarioRepository.findById(id).get());
    }
    
    public List<FuncionarioDto> findAll() {
    	List<FuncionarioDto> dtoList = new ArrayList<>();
    	List<Funcionario> funcionarios = funcionarioRepository.findAll();
    	if (!funcionarios.isEmpty()){
    		funcionarios.forEach(toDto -> {
    			dtoList.add(new FuncionarioDto().converterToDto(toDto));
    		});
    	}
    	return dtoList;
    }
    
    public Page<FuncionarioDto> findByCondition(FuncionarioDto funcionarioDto, Pageable pageable){
    	List<FuncionarioDto> dtoList = new ArrayList<>();
    	Page<Funcionario> entityPage = funcionarioRepository.findAll(pageable);
    	List<Funcionario> entities = entityPage.getContent();
    	entities.forEach(toDto -> {
			dtoList.add(new FuncionarioDto().converterToDto(toDto));
		});
    	return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }
    
    public FuncionarioDto update(FuncionarioDto funcionarioDto) {
    	return save(funcionarioDto);
    }
    
}