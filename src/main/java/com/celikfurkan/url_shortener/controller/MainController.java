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

/**
 * REST controller for managing URL shortening operations.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

    private final URLService urlService;

    public MainController(URLService urlService) {
        this.urlService = urlService;
    }

    /**
     * Retrieves a URL response by its code.
     *
     * @param code the code associated with the URL
     * @return a {@link ResponseEntity} containing the URL details and HTTP status
     */
    @GetMapping(value = "/show/{code}")
    public ResponseEntity<UrlResponseDto> getByCode(@PathVariable @NotBlankOrNull(name = "Code") String code) {
        UrlResponseDto urlResponseDto = urlService.getByCode(code);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.OK);
    }

    /**
     * Redirects to the URL associated with the provided code.
     *
     * @param code the code associated with the URL
     * @return a {@link ResponseEntity} with HTTP headers for redirection
     * @throws URISyntaxException if the URI is malformed
     */
    @GetMapping(value = "/{code}")
    public ResponseEntity<?> redirect(@PathVariable @NotBlankOrNull(name = "Code") String code) throws URISyntaxException {
        UrlResponseDto urlResponseDto = urlService.getByCode(code);
        URI uri = new URI(urlResponseDto.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    /**
     * Creates a new URL code entry based on the provided request data.
     *
     * @param requestDto the request data transfer object containing the URL and optional code
     * @return a {@link ResponseEntity} containing the newly created URL code details and HTTP status
     */
    @PostMapping
    public ResponseEntity<UrlResponseDto> create(@Valid @RequestBody RequestDto requestDto) {
        UrlResponseDto urlResponseDto = urlService.create(requestDto);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.OK);
    }
}
