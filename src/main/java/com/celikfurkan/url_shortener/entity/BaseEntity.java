package com.celikfurkan.url_shortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Abstract base class for all entities in the application.
 *
 * <p>This class provides common fields such as ID, creation time,
 * and last updated time for all entity classes that extend it.</p>
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * Unique identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Timestamp indicating when the entity was created.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating when the entity was last updated.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
