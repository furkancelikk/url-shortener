package com.celikfurkan.url_shortener.service;

import com.celikfurkan.url_shortener.dto.RequestDto;
import com.celikfurkan.url_shortener.dto.UrlResponseDto;
import com.celikfurkan.url_shortener.entity.URL;
import com.celikfurkan.url_shortener.entity.UrlCode;
import com.celikfurkan.url_shortener.exception.AlreadyExistsException;
import com.celikfurkan.url_shortener.exception.NotFoundException;
import com.celikfurkan.url_shortener.repository.URLRepository;
import com.celikfurkan.url_shortener.repository.UrlCodeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class URLServiceTest {

    @Mock
    private URLRepository urlRepository;

    @Mock
    private UrlCodeRepository codeRepository;

    @InjectMocks
    private URLService urlService;

    @Test
    public void testGetByCode_whenCodeExists() {
        String testCode = "testCode";
        String testURL = "testURL";
        URL url = new URL();
        url.setUrl(testURL);
        UrlCode urlCode = new UrlCode();
        urlCode.setCode(testCode);
        urlCode.setUrl(url);
        when(codeRepository.findByCode(anyString())).thenReturn(Optional.of(urlCode));
        UrlResponseDto responseDto = urlService.getByCode(testCode);
        Assertions.assertEquals(testCode, responseDto.getCode());
        Assertions.assertEquals(testURL, responseDto.getUrl());
    }

    @Test
    public void testGetByCode_whenCodeDoNotExists() {
        when(codeRepository.findByCode(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> urlService.getByCode("testCode"));
    }

    @Test
    public void testCreate_whenCodeNotNullAndExists() {
        String testCode = "testCode";
        String testURL = "testURL";
        RequestDto requestDto = new RequestDto();
        requestDto.setCode(testCode);
        requestDto.setUrl(testURL);
        UrlCode urlCode = new UrlCode();
        urlCode.setCode(testCode);
        when(codeRepository.findByCode(anyString())).thenReturn(Optional.of(urlCode));
        Assertions.assertThrows(AlreadyExistsException.class, () -> urlService.create(requestDto));
    }

    @Test
    public void testCreate_whenCodeNotNullAndNotExists() {
        String testCode = "testCode";
        String testURL = "testURL";
        RequestDto requestDto = new RequestDto();
        requestDto.setCode(testCode);
        requestDto.setUrl(testURL);

        when(codeRepository.findByCode(anyString())).thenReturn(Optional.empty());

        URL url = new URL();
        url.setUrl(testURL);
        when(urlRepository.save(any(URL.class))).thenReturn(url);

        UrlCode urlCode = new UrlCode();
        urlCode.setCode(testCode);
        urlCode.setUrl(url);
        when(codeRepository.save(any(UrlCode.class))).thenReturn(urlCode);

        UrlResponseDto responseDto = urlService.create(requestDto);
        Assertions.assertEquals(testCode, responseDto.getCode());
        Assertions.assertEquals(testURL, responseDto.getUrl());
    }

    @Test
    public void testCreate_whenCodeIsNull() {
        String testCode = "testCode";
        String testURL = "testURL";
        RequestDto requestDto = new RequestDto();
        requestDto.setUrl(testURL);

        MockedStatic<RandomCodeGenerator> mockedStatic = mockStatic(RandomCodeGenerator.class);
        when(RandomCodeGenerator.generate()).thenReturn(testCode);
        when(codeRepository.findByCode(anyString())).thenReturn(Optional.empty());

        URL url = new URL();
        url.setUrl(testURL);
        when(urlRepository.save(any(URL.class))).thenReturn(url);

        UrlCode urlCode = new UrlCode();
        urlCode.setCode(testCode);
        urlCode.setUrl(url);
        when(codeRepository.save(any(UrlCode.class))).thenReturn(urlCode);

        UrlResponseDto responseDto = urlService.create(requestDto);
        Assertions.assertEquals(testCode, responseDto.getCode());
        Assertions.assertEquals(testURL, responseDto.getUrl());
        mockedStatic.close();
    }

    @Test
    public void testCreate_whenCodeIsNullAndGeneratedCodeExist() {
        String testCode = "testCode";
        String testURL = "testURL";
        RequestDto requestDto = new RequestDto();
        requestDto.setUrl(testURL);

        URL url = new URL();
        url.setUrl(testURL);

        UrlCode urlCode = new UrlCode();
        urlCode.setCode(testCode);
        urlCode.setUrl(url);

        MockedStatic<RandomCodeGenerator> mockedStatic = mockStatic(RandomCodeGenerator.class);
        when(RandomCodeGenerator.generate()).thenReturn(testCode);
        when(codeRepository.findByCode(anyString())).thenReturn(Optional.of(urlCode)).thenReturn(Optional.empty());
        when(urlRepository.save(any(URL.class))).thenReturn(url);
        when(codeRepository.save(any(UrlCode.class))).thenReturn(urlCode);

        UrlResponseDto responseDto = urlService.create(requestDto);
        Assertions.assertEquals(testCode, responseDto.getCode());
        Assertions.assertEquals(testURL, responseDto.getUrl());
        mockedStatic.close();
    }
}