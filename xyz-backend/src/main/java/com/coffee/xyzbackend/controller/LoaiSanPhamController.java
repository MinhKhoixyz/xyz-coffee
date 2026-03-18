package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.service.LoaiSanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loai-san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiSanPhamController {

    LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/hien-thi")
    public String loaiSanPham(Model model) {
        model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAllLoaiSanPham());
        return "views/admin/loai-san-pham-hien-thi";
    }
}