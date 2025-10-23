package web.shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.shop.dao.AccountRepository;
import web.shop.entity.Account;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public Account findById(String username) {
        return repo.findById(username).orElse(null);
    }
}
