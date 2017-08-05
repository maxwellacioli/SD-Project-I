package br.com.ic.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ic.model.University;
import br.com.ic.service.UniversityService;
import br.com.ic.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UniversityService universityService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Universities ---------------------------------------------

	@RequestMapping(value = "/university/", method = RequestMethod.GET)
	public ResponseEntity<List<University>> listAllUniversities() {
		List<University> universities = universityService.findAllUniversities();
		if (universities.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<University>>(universities, HttpStatus.OK);
	}

	// -------------------Retrieve Single University ------------------------------------------

	@RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUniversity(@PathVariable("id") long id) {
		logger.info("Fetching University with id {}", id);
		University university = universityService.findById(id);
		if (university == null) {
			logger.error("University with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("University with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<University>(university, HttpStatus.OK);
	}

	// -------------------Create a University-------------------------------------------

	@RequestMapping(value = "/university/", method = RequestMethod.POST)
	public ResponseEntity<?> createUniversity(@RequestBody University university, UriComponentsBuilder ucBuilder) {
		logger.info("Creating University : {}", university);

		if (universityService.isUniversityExist(university)) {
			logger.error("Unable to create. A University with name {} already exist", university.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A University with name " + 
			university.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		universityService.saveUniversity(university);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/university/{id}").buildAndExpand(university.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a University ------------------------------------------------

	@RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUniversity(@PathVariable("id") long id, @RequestBody University university) {
		logger.info("Updating University with id {}", id);

		University currentUniversity = universityService.findById(id);

		if (currentUniversity == null) {
			logger.error("Unable to update. University with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. University with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUniversity.setName(university.getName());

		universityService.updateUniversity(currentUniversity);
		return new ResponseEntity<University>(currentUniversity, HttpStatus.OK);
	}

	// ------------------- Delete a University-----------------------------------------

	@RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUniversity(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting University with id {}", id);

		University university = universityService.findById(id);
		if (university == null) {
			logger.error("Unable to delete. University with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. University with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		universityService.deleteUniversityById(id);
		return new ResponseEntity<University>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Universities-----------------------------

	@RequestMapping(value = "/university/", method = RequestMethod.DELETE)
	public ResponseEntity<University> deleteAllUniversities() {
		logger.info("Deleting All Universities");

		universityService.deleteAllUniversities();
		return new ResponseEntity<University>(HttpStatus.NO_CONTENT);
	}

}