package web.shop.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;
import web.shop.entity.Account;
import web.shop.service.AccountService;


@Controller
public class AuthController {
    @Autowired
    AccountService accountService;
    @Autowired
    HttpSession session;


    @GetMapping("/auth/login")
    public String loginForm(Model model) { return "auth/login"; }


    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
        Account user = accountService.findById(username);
        if (user == null) {
            model.addAttribute("message", "Invalid email!");
            return "auth/login";
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
            return "auth/login";
        } else {
            session.setAttribute("user", user);
            String securityUri = (String) session.getAttribute("securityUri");
            if (securityUri != null) return "redirect:" + securityUri;
            model.addAttribute("message", "Login successfully!");
            return "auth/login";
        }
    }
}
