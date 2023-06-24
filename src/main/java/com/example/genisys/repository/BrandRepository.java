package com.example.genisys.repository;

import com.example.genisys.entity.Brand;
import com.example.genisys.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByBrandName(String brandName);
}
