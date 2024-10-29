package com.celikfurkan.url_shortener.dto;

import com.celikfurkan.url_shortener.entity.UrlCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponseDto {
    private String url;

    private String code;

    public UrlResponseDto(UrlCode urlCode) {
        this.url = urlCode.getUrl().getUrl();
        this.code = urlCode.getCode();
    }
}
