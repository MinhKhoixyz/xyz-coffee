package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.request.LoaiSanPhamRequest;
import com.coffee.xyzbackend.service.LoaiSanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loai-san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoaiSanPhamController {

    LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/hien-thi")
    public String loaiSanPham(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        model.addAttribute("listLoaiSanPham", loaiSanPhamService.getAllLoaiSanPham(keyword));
        model.addAttribute("keyword", keyword); // Giữ text trong ô tìm kiếm sau khi load lại trang
        model.addAttribute("loaiSanPhamRequest", new LoaiSanPhamRequest()); // Object trống để bind vào Form Modal
        return "views/admin/loai-san-pham-hien-thi";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("loaiSanPhamRequest") LoaiSanPhamRequest request) {
        loaiSanPhamService.saveOrUpdate(request);
        return "redirect:/loai-san-pham/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        loaiSanPhamService.deleteById(id);
        return "redirect:/loai-san-pham/hien-thi";
    }

    @GetMapping("/toggle-status/{id}")
    public String toggleStatus(@PathVariable("id") String id) {
        loaiSanPhamService.toggleStatus(id);
        return "redirect:/loai-san-pham/hien-thi";
    }
}