package web.shop.service;

import web.shop.entity.Account;

public interface AccountService {
    Account findById(String username);
}
