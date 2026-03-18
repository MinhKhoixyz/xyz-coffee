package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.dto.response.LoaiSanPhamResponse;
import com.coffee.xyzbackend.repository.LoaiSanPhamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiSanPhamService {

    LoaiSanPhamRepository loaiSanPhamRepository;

    public List<LoaiSanPhamResponse> getAllLoaiSanPham() {
        return loaiSanPhamRepository.findAll().stream()
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
}