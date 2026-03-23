package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.request.SanPhamRequest;
import com.coffee.xyzbackend.dto.response.SanPhamResponse;
import com.coffee.xyzbackend.service.SanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamController {

    SanPhamService sanPhamService;

    @GetMapping()
    public String hienThi(Model model,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "5") int size,
                          @RequestParam(defaultValue = "createdAt") String sortField,
                          @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<SanPhamResponse> sanPhamPage = sanPhamService.getAllSanPham(keyword, pageable);

        model.addAttribute("listSanPham", sanPhamPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sanPhamPage.getTotalPages());
        model.addAttribute("totalItems", sanPhamPage.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);

        model.addAttribute("sanPhamRequest", new SanPhamRequest());
        model.addAttribute("listLoaiSanPham", sanPhamService.getDanhSachLoaiSanPham());

        return "views/admin/san-pham";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("sanPhamRequest") SanPhamRequest request,
                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        sanPhamService.saveOrUpdate(request, imageFile);
        return "redirect:/san-pham";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        sanPhamService.deleteById(id);
        return "redirect:/san-pham";
    }
}