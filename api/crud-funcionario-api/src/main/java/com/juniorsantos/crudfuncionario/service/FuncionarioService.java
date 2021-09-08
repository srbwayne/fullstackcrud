package com.juniorsantos.crudfuncionario.service;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;
import com.juniorsantos.crudfuncionario.mapper.FuncionarioMapper;
import com.juniorsantos.crudfuncionario.models.Funcionario;
import com.juniorsantos.crudfuncionario.repository.FuncionarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class FuncionarioService {
    private final FuncionarioRepository repository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository repository, FuncionarioMapper funcionarioMapper) {
        this.repository = repository;
        this.funcionarioMapper = funcionarioMapper;
    }

    public FuncionarioDto save(FuncionarioDto funcionarioDto) {
        Funcionario entity = funcionarioMapper.toEntity(funcionarioDto);
        return funcionarioMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public FuncionarioDto findById(Long id) {
        return funcionarioMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public List<FuncionarioDto> findAll() {
        List<FuncionarioDto> dtoList = new ArrayList<>();
        List<Funcionario> funcionarios = repository.findAll();

        if (!funcionarios.isEmpty()){
            repository.findAll().forEach(toDto -> {
                dtoList.add(funcionarioMapper.toDto(toDto));
            });
        }

        return dtoList;
    }

    public Page<FuncionarioDto> findByCondition(FuncionarioDto funcionarioDto, Pageable pageable) {
        Page<Funcionario> entityPage = repository.findAll(pageable);
        List<Funcionario> entities = entityPage.getContent();
        return new PageImpl<>(funcionarioMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FuncionarioDto update(FuncionarioDto funcionarioDto, Long id) {
        FuncionarioDto data = findById(id);
        Funcionario entity = funcionarioMapper.toEntity(funcionarioDto);
        return save(funcionarioMapper.toDto(entity));
    }
}