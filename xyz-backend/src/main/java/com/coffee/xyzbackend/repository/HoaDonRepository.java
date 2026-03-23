package com.coffee.xyzbackend.repository;

import com.coffee.xyzbackend.model.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT h FROM HoaDon h WHERE " +
            "(:status IS NULL OR h.status = :status) AND " +
            "(:keyword IS NULL OR LOWER(h.id) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<HoaDon> searchHoaDon(String keyword, String status, Pageable pageable);
}