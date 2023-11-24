package com.example.furniture.repositories;

import com.example.furniture.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    Size findSizeByLengthAndWidthAndHeight(int length, int width, int height);
}
