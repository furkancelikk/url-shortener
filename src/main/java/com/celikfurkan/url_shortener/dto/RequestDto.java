package com.celikfurkan.url_shortener.dto;

import com.celikfurkan.url_shortener.annotation.NotBlankOrNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

/**
 * Data Transfer Object (DTO) for handling URL requests.
 *
 * <p>This class is used to transfer data between the client and server
 * for creating or retrieving URL codes.</p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    /**
     * The URL to be shortened.
     * This field must not be empty or null and must be a valid URL format.
     */
    @NotEmpty(message = "URL can not be empty or null")
    @URL(message = "URL is not valid")
    private String url;

    /**
     * The optional code to be associated with the URL.
     * This field can be blank or null.
     */
    @NotBlankOrNull(name = "Code", isNullable = true)
    private String code;
}
