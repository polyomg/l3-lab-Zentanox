package web.shop.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.shop.service.ParamService;

@Controller
public class Bai3Controller {
    @Autowired
    ParamService paramService;

    // Hiển thị form đăng ký
    @GetMapping("/account/photo")
    public String registerForm() {
        return "account/register";
    }

    // Xử lý form đăng ký
    @PostMapping("/account/photo")
    public String register(Model model,
                           @RequestParam("photo") MultipartFile photo) {
        // Lưu hình vào thư mục uploads
        File savedFile = paramService.save(photo, "/uploads");

        if (savedFile != null) {
            model.addAttribute("message", "Đăng thành công!");
            model.addAttribute("imagePath", "/uploads/" + savedFile.getName());
        } else {
            model.addAttribute("message", "Vui lòng chọn hình để upload!");
        }

        return "account/register";
    }
}