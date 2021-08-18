package com.atm_machine_web.serviceImplementation;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Transactions;
import com.atm_machine_web.repo.TransactionsRepository;
import com.atm_machine_web.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {
@Autowired
    TransactionsRepository transactionsRepository;
    @Override
    public Transactions findTransactionsByAccountId(Accounts accountId) {
        return transactionsRepository.findFirstTransactionsByAccountId(accountId);
    }

    @Override
    public Transactions findTransactionsByDate(LocalDate date) {
        return transactionsRepository.findTransactionsByDate(date);
    }

    @Override
    public Transactions save(Transactions newTransaction) {
       return transactionsRepository.save(newTransaction);
    }

    @Override
    public List<Transactions> findTransactionsByAccountIdOrderByTransactionId(Accounts accountsFromDb) {
        return transactionsRepository. findTransactionsByAccountIdOrderByTransactionId( accountsFromDb) ;
    }


}
