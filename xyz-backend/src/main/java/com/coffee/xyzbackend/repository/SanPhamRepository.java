package com.coffee.xyzbackend.repository;

import com.coffee.xyzbackend.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {

    @Override
    @EntityGraph(attributePaths = {"loaiSanPham"})
    Page<SanPham> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"loaiSanPham"})
    Page<SanPham> findByNameContainingIgnoreCase(String name, Pageable pageable);
}