package web.shop.controller;


import web.shop.dao.AccountDAO;
import web.shop.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class AccountController {

    @Autowired
    AccountDAO dao;

    // Hiển thị danh sách và form rỗng
    @GetMapping("/index")
    public String index(Model model) {
        Account item = new Account();
        model.addAttribute("item", item);
        List<Account> items = dao.findAll();
        model.addAttribute("items", items);
        return "user/index";
    }

    // Chọn user để chỉnh sửa
    @GetMapping("/edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {
        Account item = dao.findById(username).orElse(new Account());
        model.addAttribute("item", item);
        List<Account> items = dao.findAll();
        model.addAttribute("items", items);
        return "user/index";
    }

    // Tạo mới user
    @PostMapping("/create")
    public String create(Account item) {
        dao.save(item);
        return "redirect:/user/index";
    }

    // Cập nhật user
    @PostMapping("/update")
    public String update(Account item) {
        dao.save(item);
        return "redirect:/user/edit/" + item.getUsername();
    }

    // Xóa user
    @GetMapping("/delete/{username}")
    public String delete(@PathVariable("username") String username) {
        dao.deleteById(username);
        return "redirect:/user/index";
    }
}

