package com.atm_machine_web.service;

import com.atm_machine_web.model.*;

import java.time.LocalDate;
import java.util.List;

public interface TransactionsService {

    Transactions findTransactionsByAccountId(Accounts accountId);
    Transactions findTransactionsByDate(LocalDate date);
    Transactions save(Transactions newTransaction);

}
