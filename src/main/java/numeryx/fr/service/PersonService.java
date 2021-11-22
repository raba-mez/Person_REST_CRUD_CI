package numeryx.fr.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import numeryx.fr.model.Person;

public interface PersonService {
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMINSYSTEM')")
	List<Person> getAllPersons();
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMINSYSTEM')")
	Person getPersonById(Long id);
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMINSYSTEM')")
	Person getByTel(String tel);
	@PreAuthorize("hasRole('ROLE_ADMINSYSTEM')")
	Person createPerson(Person p);
//	Person updatePerson(PersonDto p);
	@PreAuthorize("hasRole('ROLE_ADMINSYSTEM')")
	void deletePerson(Long id);

}
