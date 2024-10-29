package com.celikfurkan.url_shortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a URL.
 *
 * <p>This class maps to the database table that stores the original URLs
 * that are associated with their corresponding shortened codes.</p>
 */
@Entity
@Getter
@Setter
public class URL extends BaseEntity {

    /**
     * The original URL that users will be redirected to.
     * This field is required and cannot be null.
     */
    @Column(nullable = false)
    private String url;
}
