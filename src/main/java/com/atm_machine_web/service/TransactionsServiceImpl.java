package com.atm_machine_web.service;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.model.Transactions;
import com.atm_machine_web.repo.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {
@Autowired
    TransactionsRepository transactionsRepository;
    @Override
    public Transactions findTransactionsByAccountId(Accounts accountId) {
        return transactionsRepository.findTransactionsByAccountId(accountId);
    }

    @Override
    public Transactions findTransactionsByDate(LocalDate date) {
        return transactionsRepository.findTransactionsByDate(date);
    }

    @Override
    public Transactions save(Transactions newTransaction) {
       return transactionsRepository.save(newTransaction);
    }


}
