package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.request.HoaDonRequest;
import com.coffee.xyzbackend.repository.SanPhamKichCoRepository;
import com.coffee.xyzbackend.service.BanHangService;
import com.coffee.xyzbackend.service.SanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ban-hang")
@RequiredArgsConstructor
public class BanHangController {

    private final SanPhamService sanPhamService;
    private final SanPhamKichCoRepository sanPhamKichCoRepository;
    private final BanHangService banHangService;

    @GetMapping
    public String hienThiTrangBanHang(Model model) {
        model.addAttribute("listLoaiSanPham", sanPhamService.getDanhSachLoaiSanPham());
        model.addAttribute("listSanPham", sanPhamService.getAllRaw());
        return "views/admin/ban-hang";
    }

    @ResponseBody
    @GetMapping("/api/kich-co")
    public ResponseEntity<?> getKichCoBySanPham(@RequestParam("productId") String productId) {
        return ResponseEntity.ok(sanPhamKichCoRepository.findBySanPham_Id(productId));
    }

    @ResponseBody
    @PostMapping("/api/thanh-toan")
    public ResponseEntity<?> thanhToan(@RequestBody HoaDonRequest request, HttpServletRequest httpRequest) {
        try {
            String currentUsername = (String) httpRequest.getAttribute("username");
            banHangService.taoHoaDon(request, currentUsername);

            if (currentUsername == null) {
                return ResponseEntity.status(401).body("Lỗi: Không tìm thấy phiên đăng nhập của nhân viên!");
            }

            banHangService.taoHoaDon(request, currentUsername);
            return ResponseEntity.ok("Thanh toán thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi thanh toán: " + e.getMessage());
        }
    }
}