package com.celikfurkan.url_shortener.repository;

import com.celikfurkan.url_shortener.entity.UrlCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link UrlCode} entities.
 *
 * <p>This interface provides methods to perform CRUD operations on UrlCode entities
 * and additional query methods for retrieving UrlCodes by their string code.</p>
 */
@Repository
public interface UrlCodeRepository extends JpaRepository<UrlCode, Long> {

    /**
     * Retrieves a UrlCode entity by its string code.
     *
     * @param code the string representation of the URL code
     * @return an {@link Optional} containing the found UrlCode entity, or empty if not found
     */
    Optional<UrlCode> findByCode(String code);
}
