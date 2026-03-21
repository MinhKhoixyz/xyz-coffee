package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.dto.request.KichCoRequest;
import com.coffee.xyzbackend.dto.response.KichCoResponse;
import com.coffee.xyzbackend.model.SanPhamKichCo;
import com.coffee.xyzbackend.repository.SanPhamKichCoRepository;
import com.coffee.xyzbackend.repository.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SanPhamKichCoService {
    private final SanPhamKichCoRepository repository;
    private final SanPhamRepository sanPhamRepository;

    public Page<KichCoResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.search(keyword, pageable).map(this::mapToResponse);
    }

    @Transactional
    public void save(KichCoRequest request) {
        var sanPham = sanPhamRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        var entity = SanPhamKichCo.builder()
                .id(request.getId() != null && !request.getId().isEmpty() ? request.getId() : null)
                .sanPham(sanPham)
                .sizeName(request.getSizeName())
                .price(request.getPrice())
                .isActive(request.getIsActive())
                .build();

        repository.save(entity);
    }

    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    private KichCoResponse mapToResponse(SanPhamKichCo entity) {
        return KichCoResponse.builder()
                .id(entity.getId())
                .productName(entity.getSanPham().getName())
                .productId(entity.getSanPham().getId())
                .sizeName(entity.getSizeName())
                .price(entity.getPrice())
                .isActive(entity.getIsActive())
                .build();
    }
}