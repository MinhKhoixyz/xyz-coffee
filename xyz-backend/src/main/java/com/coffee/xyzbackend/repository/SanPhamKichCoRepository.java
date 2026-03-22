package com.coffee.xyzbackend.repository;

import com.coffee.xyzbackend.model.SanPhamKichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamKichCoRepository extends JpaRepository<SanPhamKichCo, String> {

    @EntityGraph(attributePaths = {"sanPham"})
    @Query("SELECT kc FROM SanPhamKichCo kc " +
            "WHERE (:keyword IS NULL OR :keyword = '' " +
            "OR LOWER(kc.sanPham.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(kc.sizeName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<SanPhamKichCo> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}