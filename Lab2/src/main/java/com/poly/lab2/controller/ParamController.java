package web.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class ParamController {
    @RequestMapping("/param/form")
    public String form() {
        return "/demo/form";
    }
    @RequestMapping("/param/save/{x}")
    public String save(
            @PathVariable("x") String x,
            @RequestParam("y") String y,
            Model model) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "/demo/form";
    }
}
