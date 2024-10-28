package com.celikfurkan.url_shortener.service;

import com.celikfurkan.url_shortener.dto.RequestDto;
import com.celikfurkan.url_shortener.dto.UrlResponseDto;
import com.celikfurkan.url_shortener.entity.URL;
import com.celikfurkan.url_shortener.entity.UrlCode;
import com.celikfurkan.url_shortener.exception.AlreadyExistsException;
import com.celikfurkan.url_shortener.exception.NotFoundException;
import com.celikfurkan.url_shortener.repository.URLRepository;
import com.celikfurkan.url_shortener.repository.UrlCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class URLService {

    private final URLRepository urlRepository;

    private final UrlCodeRepository codeRepository;

    public URLService(URLRepository urlRepository, UrlCodeRepository codeRepository) {
        this.urlRepository = urlRepository;
        this.codeRepository = codeRepository;
    }

    public UrlResponseDto getByCode(String code) {
        Optional<UrlCode> codeOptional = codeRepository.findByCode(code);
        if (codeOptional.isEmpty()) {
            throw new NotFoundException("URL not found by code: " + code);
        }
        return new UrlResponseDto(codeOptional.get());
    }

    public UrlResponseDto create(RequestDto requestDto) {
        String requestUrl = requestDto.getUrl();
        String requestCode = requestDto.getCode();
        if (requestCode != null) {
            if (isExistsByCode(requestCode)) {
                throw new AlreadyExistsException("Code already exists: " + requestCode);
            }
        }
        else {
            do {
                requestCode = RandomCodeGenerator.generate();
            } while (isExistsByCode(requestCode));
        }
        Optional<URL> url = urlRepository.findByUrl(requestUrl);
        URL newURL = url.orElseGet(() -> createURL(requestUrl));
        UrlCode urlCode = new UrlCode();
        urlCode.setCode(requestCode.toUpperCase(Locale.ROOT));
        urlCode.setUrl(newURL);
        return new UrlResponseDto(codeRepository.save(urlCode));
    }

    private URL createURL(String requestUrl) {
        URL url = new URL();
        url.setUrl(requestUrl);
        return urlRepository.save(url);
    }

    private boolean isExistsByCode(String code) {
        Optional<UrlCode> codeOptional = codeRepository.findByCode(code);
        return codeOptional.isPresent();
    }
}
