package com.atm_machine_web.repo;

import com.atm_machine_web.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {
     Integer findValueByType(String type);
     Notes findNotesByType(String noteType);
     List<Notes> findAll();

}
