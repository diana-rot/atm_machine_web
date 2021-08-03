package com.atm_machine_web.repo;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findAccountsByOwner(User owner);

    Accounts findAccountsByAccountId(Long accountId);

    Float findSoldByAccountId(Long accountId);

}

