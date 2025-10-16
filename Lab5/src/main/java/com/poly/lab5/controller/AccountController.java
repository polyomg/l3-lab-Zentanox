package web.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import web.shop.service.CookieService;
import web.shop.service.ParamService;
import web.shop.service.SessionService;

import java.io.File;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2() {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        if("poly".equals(un) && "123".equals(pw)) {
            // đăng nhập thành công
            sessionService.set("username", un);
            if(rm) {
                // đề bài yêu cầu ghi nhớ 10 ngày -> 10*24 = 240 giờ
                cookieService.add("user", un, 240);
            } else {
                cookieService.remove("user");
            }
            // redirect đến trang nào đó (ở đây về homepage)
            System.out.println("Success Login");
            return "redirect:/";
        } else {
            // login thất bại -> trả về lại trang login
            System.out.println("Fail login");
            return "/account/login";
        }
    }
}
