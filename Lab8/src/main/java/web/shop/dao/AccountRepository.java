package web.shop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import web.shop.entity.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
}
