package com.celikfurkan.url_shortener.controller;

import com.celikfurkan.url_shortener.annotation.NotBlankOrNull;
import com.celikfurkan.url_shortener.dto.RequestDto;
import com.celikfurkan.url_shortener.dto.UrlResponseDto;
import com.celikfurkan.url_shortener.service.URLService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

    private final URLService urlService;

    public MainController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "/show/{code}")
    private ResponseEntity<UrlResponseDto> getByCode(@PathVariable @NotBlankOrNull(name = "Code") String code) {
        UrlResponseDto urlResponseDto = urlService.getByCode(code);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{code}")
    private ResponseEntity<?> redirect(@PathVariable @NotBlankOrNull(name = "Code") String code) throws URISyntaxException {
        UrlResponseDto urlResponseDto = urlService.getByCode(code);
        URI uri = new URI(urlResponseDto.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @PostMapping
    private ResponseEntity<UrlResponseDto> create(@Valid @RequestBody RequestDto requestDto) {
        UrlResponseDto urlResponseDto = urlService.create(requestDto);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.OK);
    }
}
