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

/**
 * Service class for managing URL and URL code operations.
 */
@Service
public class URLService {

    private final URLRepository urlRepository;

    private final UrlCodeRepository codeRepository;

    public URLService(URLRepository urlRepository, UrlCodeRepository codeRepository) {
        this.urlRepository = urlRepository;
        this.codeRepository = codeRepository;
    }

    /**
     * Retrieves a URL response by its associated code.
     *
     * @param code the code associated with the URL
     * @return a {@link UrlResponseDto} containing the URL details
     * @throws NotFoundException if no URL is found for the given code
     */
    public UrlResponseDto getByCode(String code) {
        Optional<UrlCode> codeOptional = codeRepository.findByCode(code);
        if (codeOptional.isEmpty()) {
            throw new NotFoundException("URL not found by code: " + code);
        }
        return new UrlResponseDto(codeOptional.get());
    }

    /**
     * Creates a new URL code entry based on the provided request data.
     *
     * <p>If a code is provided in the request, the method checks if it already exists.
     * If it does, an {@link AlreadyExistsException} is thrown.
     * If no code is provided, a random code is generated until a unique one is found.</p>
     *
     * <p>The method also checks if the provided URL already exists. If it does, the existing URL is used;
     * otherwise, a new URL object is created.</p>
     *
     * @param requestDto the request data transfer object containing the URL and optional code
     * @return a {@link UrlResponseDto} containing the newly created URL code details
     * @throws AlreadyExistsException if the provided code already exists
     */
    public UrlResponseDto create(RequestDto requestDto) {
        String requestUrl = requestDto.getUrl().trim();
        String requestCode = requestDto.getCode();
        if (requestCode != null) {
            if (isExistsByCode(requestCode)) {
                throw new AlreadyExistsException("Code already exists: " + requestCode);
            }
        } else {
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

    /**
     * Creates a new URL object and saves it to the repository.
     *
     * @param requestUrl the URL string to be saved
     * @return the saved {@link URL} object
     */
    private URL createURL(String requestUrl) {
        URL url = new URL();
        url.setUrl(requestUrl);
        return urlRepository.save(url);
    }

    /**
     * Checks if a URL code already exists in the repository.
     *
     * @param code the code to check for existence
     * @return true if the code exists, false otherwise
     */
    private boolean isExistsByCode(String code) {
        Optional<UrlCode> codeOptional = codeRepository.findByCode(code);
        return codeOptional.isPresent();
    }
}
