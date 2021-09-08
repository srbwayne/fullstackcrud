package com.juniorsantos.crudfuncionario.controller;

import com.juniorsantos.crudfuncionario.dto.FuncionarioDto;
import com.juniorsantos.crudfuncionario.service.FuncionarioService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class funcionarioControllerTest {
    private static final String ENDPOINT_URL = "/api/v1/funcionario";
    @InjectMocks
    private FuncionarioController funcionarioController;
    @Mock
    private FuncionarioService funcionarioService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(funcionarioController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<FuncionarioDto> page = new PageImpl<>(Collections.singletonList((FuncionarioDto) funcionarioBuilder.getDto()));

        Mockito.when(funcionarioService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(funcionarioService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(funcionarioService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(funcionarioService.findById(ArgumentMatchers.anyLong())).thenReturn((FuncionarioDto) funcionarioBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(funcionarioService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(funcionarioService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(funcionarioService.save(ArgumentMatchers.any(FuncionarioDto.class))).thenReturn((FuncionarioDto) funcionarioBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(funcionarioBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(funcionarioService, Mockito.times(1)).save(ArgumentMatchers.any(FuncionarioDto.class));
        Mockito.verifyNoMoreInteractions(funcionarioService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(funcionarioService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn((FuncionarioDto) funcionarioBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(funcionarioBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(funcionarioService, Mockito.times(1)).update(ArgumentMatchers.any(FuncionarioDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(funcionarioService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(funcionarioService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(funcionarioBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(funcionarioService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(funcionarioService);
    }
}