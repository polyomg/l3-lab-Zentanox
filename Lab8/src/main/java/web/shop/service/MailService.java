package web.shop.service;

import lombok.Builder;
import lombok.Data;


public interface MailService {
    @Data
    @Builder
    class Mail {
        @Builder.Default
        private String from = "WebShop <web-shop@gmail.com>";
        private String to;
        private String cc;
        private String bcc;
        private String subject;
        private String body;
        private String filenames;
    }


    void send(Mail mail);
    default void send(String to, String subject, String body) {
        send(Mail.builder().to(to).subject(subject).body(body).build());
        System.out.printf("SEND");
    }


    void push(Mail mail);
    default void push(String to, String subject, String body){
        push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
