package web.shop.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping("/poly/form")
    public String form(Model model) {
        model.addAttribute("title", "Lab1");
        model.addAttribute("subject", "Form đăng nhập");
        return "/demo/form.html";
    }
    @Autowired
    ServletContext application;
    @RequestMapping("/poly/check")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        System.out.printf("MMB");
    String uname = request.getParameter("username");
    String pwd = request.getParameter("password");
    if (uname.equals("") || pwd.equals("")) {
        model.addAttribute("message", "Vui lòng không để trống");
    }
    else if(uname.equals("poly") && pwd.equals("123")) {
        model.addAttribute("message", "Chào " + uname );
    }
    else {
        model.addAttribute("message", "Mật khẩu hoặc tên không hợp lệ" );
    }
        return "/demo/form.html";
    }
}
