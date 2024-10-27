package com.celikfurkan.url_shortener.dto;

import com.celikfurkan.url_shortener.annotation.NotBlankOrNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class RequestDto {
    @NotEmpty(message = "URL can not be empty or null")
    @URL(message = "URL is not valid")
    private String url;

    @NotBlankOrNull(name = "Code", isNullable = true)
    private String code;
}
