package com.celikfurkan.url_shortener.controller;

import com.celikfurkan.url_shortener.dto.RequestDto;
import com.celikfurkan.url_shortener.dto.UrlResponseDto;
import com.celikfurkan.url_shortener.exception.GlobalExceptionHandler;
import com.celikfurkan.url_shortener.service.URLService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private URLService urlService;

    @InjectMocks
    private MainController mainController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController, new GlobalExceptionHandler()).build();
    }

    @Test
    public void testGetByCodeSuccess() throws Exception {
        String code = "testCode";
        String url = "https://github.com/furkancelikk";
        UrlResponseDto responseDto = new UrlResponseDto(url, code);
        when(urlService.getByCode(any())).thenReturn(responseDto);

        mockMvc.perform(get(("/api/v1/show/{code}"), code))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(code))
                .andExpect(jsonPath("$.url").value(url));
    }

    @Test
    public void testGetByCode_whenCodeInvalid() throws Exception {
        String code = "   ";
        MockHttpServletResponse response = mockMvc.perform(get(("/api/v1/show/{code}"), code))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
        String res = response.getContentAsString();
        Map<String, String> responseMap = objectMapper.readValue(res, new TypeReference<>() {
        });
        Assertions.assertNotNull(responseMap);
        Assertions.assertNotNull(responseMap.get("error"));
    }

    @Test
    public void testRedirectSuccess() throws Exception {
        String code = "testCode";
        String url = "https://github.com/furkancelikk";
        UrlResponseDto responseDto = new UrlResponseDto(url, code);
        when(urlService.getByCode(code)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/{code}", code))
                .andExpect(status().isSeeOther())
                .andExpect(header().string(HttpHeaders.LOCATION, url));
    }

    @Test
    public void testRedirect_whenCodeInvalid() throws Exception {
        String code = "   ";

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/{code}", code))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        String res = response.getContentAsString();
        Map<String, String> responseMap = objectMapper.readValue(res, new TypeReference<>() {
        });
        Assertions.assertNotNull(responseMap);
        Assertions.assertNotNull(responseMap.get("error"));
    }

    @Test
    public void testCreateSuccess() throws Exception {
        String code = "testCode";
        String url = "https://github.com/furkancelikk";
        UrlResponseDto responseDto = new UrlResponseDto(url, code);
        RequestDto requestDto = new RequestDto(url, code);
        when(urlService.create(any())).thenReturn(responseDto);

        mockMvc.perform(
                        post("/api/v1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(code))
                .andExpect(jsonPath("$.url").value(url));
    }

    @Test
    public void testCreate_whenRequestInvalid() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setCode("   ");
        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/v1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        String res = response.getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(res, new TypeReference<>() {
        });
        Assertions.assertNotNull(responseMap);
        Assertions.assertNotNull(responseMap.get("error"));
    }
}