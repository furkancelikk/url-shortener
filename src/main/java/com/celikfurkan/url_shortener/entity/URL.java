package com.celikfurkan.url_shortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class URL extends BaseEntity {

    /**
     * Redirect URL
     */
    @Column(nullable = false)
    private String url;
}
