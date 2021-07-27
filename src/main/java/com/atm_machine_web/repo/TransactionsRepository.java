package com.atm_machine_web.repo;

import com.atm_machine_web.model.Accounts;
import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import com.atm_machine_web.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
    Transactions findFirstTransactionsByAccountId(Accounts accountId);
Transactions findTransactionsByDate(LocalDate date);
List<Transactions>  findTransactionsByAccountIdOrderByTransactionId(Accounts accountId);



}
