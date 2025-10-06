package web.shop.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cal")
public class SqPeriController {
    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("title", "Lab1");
        model.addAttribute("subject", "Tính chu vi và diện tích");
        return "/demo/cal.html";
    }
    @Autowired
    ServletContext application;
    @RequestMapping("/check")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        String cdh = request.getParameter("cd");
        String crh = request.getParameter("cr");
    if (cdh == null || cdh.isEmpty() || crh == null || crh.isEmpty()) {
        model.addAttribute("message", "Vui lòng không để trống");
        return "/demo/cal.html";
    }
        double a = Double.parseDouble(cdh);
        double b = Double.parseDouble(crh);
        model.addAttribute("message", "Chu vi = "+ (a+b)*2 +", điện tích = "+ a*b );
        return "/demo/cal.html";
    }
}
