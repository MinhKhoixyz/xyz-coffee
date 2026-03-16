package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.repository.LoaiSanPhamRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiSanPhamService {
    @Autowired
    LoaiSanPhamRepository loaiSanPhamRepository;
}
