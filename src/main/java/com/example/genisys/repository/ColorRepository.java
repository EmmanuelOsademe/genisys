package com.example.genisys.repository;

import com.example.genisys.entity.Color;
import com.example.genisys.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByColor(String color);
}
