package com.celikfurkan.url_shortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UrlCode extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne(optional = false)
    private URL url;
}
