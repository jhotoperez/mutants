package com.mutants.mutants.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mutants.mutants.model.Person;

import java.time.LocalDateTime;


@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

    @Query("select count(p.type) from Person p where p.type = ?1 group by p.type")
    int findAllByType(String type);

}
