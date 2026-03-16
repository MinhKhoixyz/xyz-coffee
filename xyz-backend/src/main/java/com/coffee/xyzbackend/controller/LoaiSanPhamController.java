package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.repository.LoaiSanPhamRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/loai-san-pham")
public class LoaiSanPhamController {
    @Autowired
    LoaiSanPhamRepository loaiSanPhamRepository;

    @GetMapping("/hien-thi")
    public String loaiSanPham(){
        return "";
    }

}
