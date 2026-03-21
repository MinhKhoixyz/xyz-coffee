package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.dto.request.LoaiSanPhamRequest;
import com.coffee.xyzbackend.dto.response.LoaiSanPhamResponse;
import com.coffee.xyzbackend.model.LoaiSanPham;
import com.coffee.xyzbackend.repository.LoaiSanPhamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiSanPhamService {

    LoaiSanPhamRepository loaiSanPhamRepository;

    public List<LoaiSanPhamResponse> getAllLoaiSanPham(String keyword) {
        List<LoaiSanPham> listEntities;

        if (keyword != null && !keyword.trim().isEmpty()) {
            listEntities = loaiSanPhamRepository.findByNameContainingIgnoreCase(keyword.trim());
        } else {
            listEntities = loaiSanPhamRepository.findAll();
        }

        return listEntities.stream()
                .map(entity -> LoaiSanPhamResponse.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .isActive(entity.getIsActive())
                        .createdAt(entity.getCreatedAt())
                        .updatedAt(entity.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    @Transactional
    public void saveOrUpdate(LoaiSanPhamRequest request) {
        LoaiSanPham entity;
        if (request.getId() != null && !request.getId().trim().isEmpty()) {
            // Update: Tìm kiếm và set lại các trường được phép sửa
            entity = loaiSanPhamRepository.findById(request.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy loại sản phẩm!"));
            entity.setName(request.getName());
            entity.setDescription(request.getDescription());
            entity.setIsActive(request.getIsActive() != null ? request.getIsActive() : false);
        } else {
            // Create: Khởi tạo mới
            entity = LoaiSanPham.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .isActive(request.getIsActive() != null ? request.getIsActive() : false)
                    .build();
        }
        loaiSanPhamRepository.save(entity);
    }

    @Transactional
    public void deleteById(String id) {
        loaiSanPhamRepository.deleteById(id);
    }
    
    @Transactional
    public void toggleStatus(String id) {
        LoaiSanPham entity = loaiSanPhamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy loại sản phẩm!"));
        entity.setIsActive(!entity.getIsActive());
        loaiSanPhamRepository.save(entity);
    }
}