package com.celikfurkan.url_shortener.repository;

import com.celikfurkan.url_shortener.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    Optional<URL> findByUrl(String url);
}
