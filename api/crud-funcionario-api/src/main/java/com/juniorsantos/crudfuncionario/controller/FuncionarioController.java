package com.juniorsantos.crudfuncionario.controller;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;
import com.juniorsantos.crudfuncionario.service.FuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/funcionarios")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioDto> save(@RequestBody @Validated FuncionarioDto funcionarioDto) {
        FuncionarioDto response = funcionarioService.save(funcionarioDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDto> findById(@PathVariable("id") Long id) {
        FuncionarioDto funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<FuncionarioDto>> pageQuery(FuncionarioDto funcionarioDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FuncionarioDto> funcionarioPage = funcionarioService.findByCondition(funcionarioDto, pageable);
        return ResponseEntity.ok(funcionarioPage);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listAll() {
        List<FuncionarioDto> funcionarios = funcionarioService.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated FuncionarioDto funcionarioDto, @PathVariable("id") Long id) {
        funcionarioService.update(funcionarioDto);
        return ResponseEntity.ok().build();
    }
}