package web.shop.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.shop.entity.Staff;

@Controller
public class StaffController {
    @RequestMapping("/staff/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "/demo/staff-validate";
    }
//bai1
//    @RequestMapping("/staff/create/save")
//    public String createSave(Model model, @ModelAttribute("staff") Staff staff,
//                             @RequestPart("photo_file") MultipartFile photoFile) {
//
//        if(!photoFile.isEmpty()) {
//            staff.setPhoto(photoFile.getName());
//        }
//        model.addAttribute("message", "Xin chào " + staff.getFullname());
//        return "/demo/staff-create";
//    }

//bai2
    @RequestMapping("/staff/create/save")
    public String createSave(Model model,
                             @RequestPart("photo_file") MultipartFile photoFile,
                             @Valid @ModelAttribute("staff") Staff staff, Errors errors) {
        if(!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getName());
        }
        if(errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            model.addAttribute("message", "Dữ liệu đã nhập đúng!");
        }
        return "/demo/staff-validate";
    }
}