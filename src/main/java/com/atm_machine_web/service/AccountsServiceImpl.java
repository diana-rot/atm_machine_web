package com.atm_machine_web.service;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;
import com.atm_machine_web.repo.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public Accounts findAccountsByOwner(User owner) {
        return accountsRepository.findAccountsByOwner(owner);
    }


    @Override
    public Accounts findAccountsByAccountId(Long accountId) {
        return accountsRepository.findAccountsByAccountId(accountId);
    }

    @Override
    public void updateSold(Integer noteValue, Integer Nrnotes, Accounts accountsFromDb) {

        Float sold = accountsFromDb.getSold();
        sold = sold + noteValue * Nrnotes;
        accountsFromDb.setSold(sold);

    }

    @Override
    public Float findSoldByAccountId(Long accountId) {
        return accountsRepository.findSoldByAccountId(accountId);
    }

    @Override
    public Accounts save(Accounts newAccount) {
        return accountsRepository.save(newAccount);
    }
}
