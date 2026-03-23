package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.response.HoaDonResponse;
import com.coffee.xyzbackend.service.HoaDonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hoa-don")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonController {

    HoaDonService hoaDonService;

    @GetMapping
    public String hienThiHoaDon(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status) {

        Page<HoaDonResponse> hoaDonPage = hoaDonService.getDanhSachHoaDon(page, size, keyword, status);

        model.addAttribute("listHoaDon", hoaDonPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", hoaDonPage.getTotalPages());
        model.addAttribute("totalItems", hoaDonPage.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);

        return "views/admin/hoa-don";
    }

    @ResponseBody
    @GetMapping("/api/chi-tiet")
    public ResponseEntity<?> getChiTiet(@RequestParam("id") String id) {
        return ResponseEntity.ok(hoaDonService.getChiTietHoaDon(id));
    }
}