package web.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.shop.entity.Product;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/form")
    public String form(Model model) {
        System.out.printf("mmb");
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(4000.0);

        model.addAttribute("p1", p);
        model.addAttribute("p2", new Product() );
        return "product/form";
    }

    @PostMapping("/save")
    public String save( @ModelAttribute Product p, Model model) {
        model.addAttribute("p1", new Product("iPhone 30", 4000.0));
        model.addAttribute("p2", p);
        return "product/form";
    }

    @ModelAttribute("items")
    public List<Product> getItems() {
        return Arrays.asList(
                new Product("A", 1.0),
                new Product("B", 12.0)
        );
    }

////  Bai3
//    @GetMapping("/form")
//    public String form(Model model) {
//        model.addAttribute("product", new Product());
//        return "product/form";
//    }
//
//    @PostMapping("/save")
//    public String save(Product bean) {
//        return "product/form";
//    }

}
