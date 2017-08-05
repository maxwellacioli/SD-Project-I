package br.com.ic.service;

import java.util.List;

import br.com.ic.model.University;
import br.com.ic.repositories.UniversityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("universityService")
@Transactional
public class UniversityServiceImpl implements UniversityService{

	@Autowired
	private UniversityRepository universityRepository;

	public University findById(Long id) {
		return universityRepository.findOne(id);
	}

	public University findByName(String name) {
		return universityRepository.findByName(name);
	}

	public void saveUniversity(University university) {
		universityRepository.save(university);
	}

	public void updateUniversity(University university){
		saveUniversity(university);
	}

	public void deleteUniversityById(Long id){
		universityRepository.delete(id);
	}

	public void deleteAllUniversities(){
		universityRepository.deleteAll();
	}

	public List<University> findAllUniversities(){
		return universityRepository.findAll();
	}

	public boolean isUniversityExist(University university) {
		return findByName(university.getName()) != null;
	}

}
