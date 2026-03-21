package com.coffee.xyzbackend.repository;

import com.coffee.xyzbackend.model.SanPhamKichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamKichCoRepository extends JpaRepository<SanPhamKichCo, String> {

    @Query("SELECT skc FROM SanPhamKichCo skc " +
            "WHERE (:keyword IS NULL OR skc.sanPham.name LIKE %:keyword% " +
            "OR skc.sizeName LIKE %:keyword%)")
    Page<SanPhamKichCo> search(@Param("keyword") String keyword, Pageable pageable);
}