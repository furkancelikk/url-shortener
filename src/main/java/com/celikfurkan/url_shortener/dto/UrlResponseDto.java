package com.celikfurkan.url_shortener.dto;

import com.celikfurkan.url_shortener.entity.UrlCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlResponseDto {
    private String url;

    private String code;

    public UrlResponseDto(UrlCode urlCode) {
        this.url = urlCode.getUrl().getUrl();
        this.code = urlCode.getCode();
    }
}
