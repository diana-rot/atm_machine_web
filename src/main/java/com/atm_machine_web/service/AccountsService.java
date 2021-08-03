package com.atm_machine_web.service;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.User;


public interface AccountsService {


    Accounts findAccountsByAccountId(Long accountId);

    Float findSoldByAccountId(Long accountId);

    Accounts save(Accounts newAccount);

    Accounts findAccountsByOwner(User owner);


}
