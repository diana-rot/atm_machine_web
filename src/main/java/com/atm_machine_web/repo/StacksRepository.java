package com.atm_machine_web.repo;

import com.atm_machine_web.model.Notes;
import com.atm_machine_web.model.Stacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StacksRepository extends JpaRepository<Stacks, Long> {
    Integer findCountByNote(Notes note);
    List<Stacks> findAllByNote(Notes note);


}
