package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.dto.request.HoaDonRequest;
import com.coffee.xyzbackend.model.Account;
import com.coffee.xyzbackend.model.HoaDon;
import com.coffee.xyzbackend.model.HoaDonChiTiet;
import com.coffee.xyzbackend.model.SanPhamKichCo;
import com.coffee.xyzbackend.repository.AccountRepository;
import com.coffee.xyzbackend.repository.HoaDonRepository;
import com.coffee.xyzbackend.repository.SanPhamKichCoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BanHangService {

    private final HoaDonRepository hoaDonRepository;
    private final SanPhamKichCoRepository sanPhamKichCoRepository;
    private final AccountRepository accountRepository; // Thêm Account Repo

    @Transactional
    public void taoHoaDon(HoaDonRequest request, String currentUsername) {
        Account staff = accountRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nhân viên!"));

        HoaDon hoaDon = new HoaDon();
        hoaDon.setAccount(staff); // Set nhân viên
        hoaDon.setCustomerId(request.getCustomerId()); // Set khách (có thể null)
        hoaDon.setTotalAmount(request.getTotalAmount());
        hoaDon.setPaymentMethod(request.getPaymentMethod());
        hoaDon.setStatus("PAID");
        hoaDon.setNote(request.getNote());
        hoaDon.setChiTietList(new ArrayList<>());

        request.getChiTietList().forEach(itemReq -> {
            SanPhamKichCo kichCo = sanPhamKichCoRepository.findById(itemReq.getProductSizeId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy kích cỡ sản phẩm!"));

            HoaDonChiTiet chiTiet = new HoaDonChiTiet();
            chiTiet.setHoaDon(hoaDon);
            chiTiet.setSanPhamKichCo(kichCo);
            chiTiet.setQuantity(itemReq.getQuantity());
            chiTiet.setPrice(itemReq.getPrice());
            chiTiet.setTotalAmount(itemReq.getPrice() * itemReq.getQuantity());

            hoaDon.getChiTietList().add(chiTiet);
        });

        hoaDonRepository.save(hoaDon);
    }
}