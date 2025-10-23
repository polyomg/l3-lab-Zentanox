package web.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;


    private final List<Mail> queue = new ArrayList<>();


    @Override
    public synchronized void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");


            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());
            helper.setTo(mail.getTo());
            if (mail.getCc() != null && !mail.getCc().trim().isEmpty()) helper.setCc(mail.getCc());
            if (mail.getBcc() != null && !mail.getBcc().trim().isEmpty()) helper.setBcc(mail.getBcc());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);


            if (mail.getFilenames() != null) {
                for (String fn : mail.getFilenames().split("[,;]+")) {
                    File f = new File(fn.trim());
                    if (f.exists()) helper.addAttachment(f.getName(), f);
                }
            }


            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void push(Mail mail) {
        queue.add(mail);
    }


    @Scheduled(fixedDelay = 500)
    public void run() {
        while (!queue.isEmpty()) {
            try {
                Mail mail = queue.remove(0);
                send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}