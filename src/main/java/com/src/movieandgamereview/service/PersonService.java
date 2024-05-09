package com.src.movieandgamereview.service;


import com.src.movieandgamereview.dto.PersonDTO;
import com.src.movieandgamereview.model.Person;
import com.src.movieandgamereview.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> getAllPeople() {
        return ((List<Person>) personRepository.findAll()).stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    public Person findPersonById(Long personId) {
        Optional<Person> getPerson = personRepository.findById(personId);
        return getPerson.orElse(null);
    }

    public PersonDTO findAndGetPersonDTOById(Long personId) {
        Optional<Person> getPerson = personRepository.findById(personId);
        return getPerson.map(this::convertToPersonDTO).orElse(null);
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(Long personId, Person newPersonData) {
        Person currentPerson = findPersonById(personId);
        if (newPersonData.getFirstName() != null) {
            currentPerson.setFirstName(newPersonData.getFirstName());
        }
        if (newPersonData.getLastName() != null) {
            currentPerson.setLastName(newPersonData.getLastName());
        }
        if (newPersonData.getBirthDate() != null) {
            currentPerson.setBirthDate(newPersonData.getBirthDate());
        }
        savePerson(currentPerson);
    }

    protected PersonDTO convertToPersonDTO(Person person) {
        return new PersonDTO(person.getFirstName(), person.getLastName(), person.getBirthDate());
    }

}
