package web.shop.controller;

import web.shop.dao.ProductDAO;
import web.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.shop.service.SessionService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO dao;

    @Autowired
    SessionService session;

    @GetMapping("/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        String f = field.orElse("price");
        Sort sort = Sort.by(Sort.Direction.ASC, f);
        model.addAttribute("field", f.toUpperCase());
        List<Product> items = dao.findAll(sort);
        model.addAttribute("items", items);
        return "product/sort";
    }

    @GetMapping("/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        return "product/page";
    }
    //Lab 7 bai 1
    @RequestMapping("/search")
    public String search(Model model, @RequestParam("min") Optional<Double> min,@RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
//        List<Product> items = dao.findByPrice(minPrice, maxPrice);
    //Bai 4
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }
    //Lab 7 bai 2
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model, @RequestParam("keywords") Optional<String> kw, @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
//        Page<Product> page = dao.findByKeywords("%"+kwords+"%", pageable);
        //Bai 5
        Page<Product> page = dao.findAllByNameLike("%"+kwords+"%", pageable);
        model.addAttribute("page", page);
        return "product/search-and-page";
    }
}
