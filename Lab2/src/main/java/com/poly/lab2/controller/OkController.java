package web.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl")
public class OkController {
    @RequestMapping("/ok")
    public String ok() {
        return "/demo/ok";
    }
    @PostMapping("/ok")
    public String m1(Model model) {
        model.addAttribute("message", "m1() được gọi");
        return "/demo/ok";
    }

    // OK 2: GET /ctrl/ok
    @GetMapping( "/ok")
    public String m2(Model model) {
        model.addAttribute("message", "m2() được gọi");
        return "/demo/ok";
    }

    @RequestMapping(value = "/ok", params = "x")
    public String m3(Model model) {
        model.addAttribute("message", "m3() được gọi");
        return "/demo/ok";   // trỏ đến ok.html
    }
}
