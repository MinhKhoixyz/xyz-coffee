package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.dto.request.SanPhamRequest;
import com.coffee.xyzbackend.dto.response.SanPhamResponse;
import com.coffee.xyzbackend.model.LoaiSanPham;
import com.coffee.xyzbackend.model.SanPham;
import com.coffee.xyzbackend.repository.LoaiSanPhamRepository;
import com.coffee.xyzbackend.repository.SanPhamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamService {

    SanPhamRepository sanPhamRepository;
    LoaiSanPhamRepository loaiSanPhamRepository;

    // Lấy dữ liệu phân trang + Tìm kiếm
    public Page<SanPhamResponse> getAllSanPham(String keyword, Pageable pageable) {
        Page<SanPham> pageEntities;
        if (keyword != null && !keyword.trim().isEmpty()) {
            pageEntities = sanPhamRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        } else {
            pageEntities = sanPhamRepository.findAll(pageable);
        }
        return pageEntities.map(this::mapToSanPhamResponse);
    }

    // Phục vụ Dropdown Loại Sản Phẩm trên giao diện
    public List<LoaiSanPham> getDanhSachLoaiSanPham() {
        return loaiSanPhamRepository.findAll();
    }

    @Transactional
    public void saveOrUpdate(SanPhamRequest request) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục!"));

        SanPham entity;
        if (request.getId() != null && !request.getId().trim().isEmpty()) {
            entity = sanPhamRepository.findById(request.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm!"));
            entity.setName(request.getName());
            entity.setImageUrl(request.getImageUrl());
            entity.setDescription(request.getDescription());
            entity.setIsActive(request.getIsActive() != null ? request.getIsActive() : false);
            entity.setLoaiSanPham(loaiSanPham);
        } else {
            entity = SanPham.builder()
                    .name(request.getName())
                    .imageUrl(request.getImageUrl())
                    .description(request.getDescription())
                    .isActive(request.getIsActive() != null ? request.getIsActive() : false)
                    .loaiSanPham(loaiSanPham)
                    .build();
        }
        sanPhamRepository.save(entity);
    }

    @Transactional
    public void deleteById(String id) {
        sanPhamRepository.deleteById(id);
    }

    private SanPhamResponse mapToSanPhamResponse(SanPham entity) {
        return SanPhamResponse.builder()
                .id(entity.getId())
                .categoryId(entity.getLoaiSanPham().getId())
                .categoryName(entity.getLoaiSanPham().getName())
                .name(entity.getName())
                .imageUrl(entity.getImageUrl())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    public List<SanPham> getAllRaw() {
        return sanPhamRepository.findAll();
    }
}