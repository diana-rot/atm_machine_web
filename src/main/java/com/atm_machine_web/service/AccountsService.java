package com.atm_machine_web.service;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;


public interface AccountsService {


    Accounts findAccountsByAccountId(Long accountId);

    void updateSold(Integer noteValue, Integer Nrnotes, Accounts accountsFromDb);

    Float findSoldByAccountId(Long accountId);

    Accounts save(Accounts newAccount);

    Accounts findAccountsByOwnerAndCurrencyType(User owner, String currencyType);

    Accounts findAccountsByOwner(User owner);

}
