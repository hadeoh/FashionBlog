package com.example.fashionblog.repositories;

import com.example.fashionblog.models.Design;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, Integer> {
//    @Query("SELECT d FROM Design d WHERE d.title LIKE %:title%")
    Slice<Design> findByTitleContaining(String title, Pageable paging);
}
