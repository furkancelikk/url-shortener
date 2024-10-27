package com.celikfurkan.url_shortener.repository;

import com.celikfurkan.url_shortener.entity.UrlCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlCodeRepository extends JpaRepository<UrlCode, Long> {
    Optional<UrlCode> findByCode(String code);
}
