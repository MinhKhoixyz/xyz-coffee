package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.dto.response.HoaDonResponse;
import com.coffee.xyzbackend.model.HoaDon;
import com.coffee.xyzbackend.repository.HoaDonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonService {

    HoaDonRepository hoaDonRepository;

    public Page<HoaDonResponse> getDanhSachHoaDon(int page, int size, String keyword, String status) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        String kw = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;
        String st = (status != null && !status.trim().isEmpty()) ? status.trim() : null;

        Page<HoaDon> hoaDonPage = hoaDonRepository.searchHoaDon(kw, st, pageable);

        return hoaDonPage.map(hd -> HoaDonResponse.builder()
                .id(hd.getId())
                .accountName(hd.getAccount().getUsername())
                .totalAmount(hd.getTotalAmount())
                .status(hd.getStatus())
                .paymentMethod(hd.getPaymentMethod())
                .createdAt(hd.getCreatedAt())
                .build());
    }
}