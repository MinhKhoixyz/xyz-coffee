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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamService {

    SanPhamRepository sanPhamRepository;
    LoaiSanPhamRepository loaiSanPhamRepository;
    CloudinaryService cloudinaryService;

    public Page<SanPhamResponse> getAllSanPham(String keyword, Pageable pageable) {
        Page<SanPham> pageEntities;
        if (keyword != null && !keyword.trim().isEmpty()) {
            pageEntities = sanPhamRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        } else {
            pageEntities = sanPhamRepository.findAll(pageable);
        }
        return pageEntities.map(this::mapToSanPhamResponse);
    }

    public List<LoaiSanPham> getDanhSachLoaiSanPham() {
        return loaiSanPhamRepository.findAll();
    }

    @Transactional
    public void saveOrUpdate(SanPhamRequest request, MultipartFile imageFile) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục!"));

        SanPham entity;
        if (request.getId() != null && !request.getId().trim().isEmpty()) {
            entity = sanPhamRepository.findById(request.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm!"));
        } else {
            entity = new SanPham();
        }

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setIsActive(request.getIsActive() != null ? request.getIsActive() : false);
        entity.setLoaiSanPham(loaiSanPham);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadImage(imageFile);
                entity.setImageUrl(imageUrl);
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi upload ảnh lên Cloudinary: " + e.getMessage());
            }
        } else if (request.getImageUrl() != null && !request.getImageUrl().isEmpty()) {
            entity.setImageUrl(request.getImageUrl());
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