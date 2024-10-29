package com.celikfurkan.url_shortener.dto;

import com.celikfurkan.url_shortener.entity.UrlCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing the response of a URL request.
 *
 * <p>This class is used to transfer the shortened URL and its associated code
 * back to the client.</p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponseDto {

    /**
     * The original URL that has been shortened.
     */
    private String url;

    /**
     * The code associated with the shortened URL.
     */
    private String code;

    /**
     * Constructs a UrlResponseDto from a given UrlCode entity.
     *
     * @param urlCode the UrlCode entity containing URL and code information
     */
    public UrlResponseDto(UrlCode urlCode) {
        this.url = urlCode.getUrl().getUrl();
        this.code = urlCode.getCode();
    }
}
