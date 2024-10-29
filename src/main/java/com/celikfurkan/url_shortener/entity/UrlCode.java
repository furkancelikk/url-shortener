package com.celikfurkan.url_shortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a URL with code.
 *
 * <p>This class maps to the database table that stores unique codes associated with URLs.</p>
 */
@Entity
@Getter
@Setter
public class UrlCode extends BaseEntity {

    /**
     * The unique code associated with the URL.
     * This field is required and must be unique across all entries.
     */
    @Column(unique = true, nullable = false)
    private String code;

    /**
     * The {@link URL} entity associated with this code.
     * This relationship is mandatory.
     */
    @ManyToOne(optional = false)
    private URL url;
}
