package br.com.ic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ic.model.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    University findByName(String name);

}
