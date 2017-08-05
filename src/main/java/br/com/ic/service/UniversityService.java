package br.com.ic.service;


import java.util.List;

import br.com.ic.model.University;

public interface UniversityService {
	
	University findById(Long id);

	University findByName(String name);

	void saveUniversity(University university);

	void updateUniversity(University university);

	void deleteUniversityById(Long id);

	void deleteAllUniversities();

	List<University> findAllUniversities();

	boolean isUniversityExist(University university);
}