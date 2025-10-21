package web.shop.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    HttpSession session;
    public SessionService(HttpSession session) { this.session = session; }

    public <T> void set(String name, T value) { session.setAttribute(name, value); }

    @SuppressWarnings("unchecked")
    public <T> T get(String name, T defaultValue) {
        Object v = session.getAttribute(name);
        return (v == null) ? defaultValue : (T) v;
    }
}
