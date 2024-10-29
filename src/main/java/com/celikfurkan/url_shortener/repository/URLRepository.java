package com.celikfurkan.url_shortener.repository;

import com.celikfurkan.url_shortener.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link URL} entities.
 *
 * <p>This interface provides methods to perform CRUD operations on URL entities
 * and additional query methods for retrieving URLs by their string value.</p>
 */
@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    /**
     * Retrieves a URL entity by its string value.
     *
     * @param url the string representation of the URL
     * @return an {@link Optional} containing the found URL entity, or empty if not found
     */
    Optional<URL> findByUrl(String url);
}
