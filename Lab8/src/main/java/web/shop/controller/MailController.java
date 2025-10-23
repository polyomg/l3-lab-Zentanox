package web.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.shop.service.MailService;


@Controller
public class MailController {
    @Autowired
    MailService mailService;


    @GetMapping("/mail/form")
    public String form() { return "mail/form"; }

    @ResponseBody
    @PostMapping("/mail/send")
    public String send(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        try {
            mailService.send(MailService.Mail.builder().to(to).subject(subject).body(body).build());
            return "Mail đã được xếp vào hàng đợi";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
