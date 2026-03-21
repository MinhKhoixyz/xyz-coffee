package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.request.KichCoRequest;
import com.coffee.xyzbackend.service.SanPhamKichCoService;
import com.coffee.xyzbackend.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kich-co")
@RequiredArgsConstructor
public class SanPhamKichCoController {
    private final SanPhamKichCoService service;
    private final SanPhamService sanPhamService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "") String keyword,
                          @RequestParam(defaultValue = "0") int page) {
        var pageKichCo = service.getAll(keyword, page, 5);

        model.addAttribute("listKichCo", pageKichCo.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageKichCo.getTotalPages());
        model.addAttribute("totalElements", pageKichCo.getTotalElements()); // THÊM DÒNG NÀY
        model.addAttribute("keyword", keyword);
        model.addAttribute("listSanPham", sanPhamService.getAllRaw());
        model.addAttribute("kichCoRequest", new KichCoRequest());

        return "views/admin/kich-co-hien-thi";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute KichCoRequest request) {
        service.save(request);
        return "redirect:/kich-co/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/kich-co/hien-thi";
    }
}