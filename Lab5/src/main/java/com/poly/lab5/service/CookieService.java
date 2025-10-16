package web.shop.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    public Cookie get(String name) {
        if(name == null) return null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for(Cookie c : cookies){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }

    public Cookie create(String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }


    public String getValue(String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public Cookie add(String name, String value, int hours) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setMaxAge(hours * 3600);
        response.addCookie(c);
        return c;
    }

    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}

