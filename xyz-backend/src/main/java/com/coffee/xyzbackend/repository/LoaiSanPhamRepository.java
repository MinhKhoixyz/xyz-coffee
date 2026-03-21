package com.coffee.xyzbackend.repository;

import com.coffee.xyzbackend.model.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, String> {
    List<LoaiSanPham> findByNameContainingIgnoreCase(String name);
}