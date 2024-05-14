package com.src.movieandgamereview.service;


import com.src.movieandgamereview.dto.PersonDTO;
import com.src.movieandgamereview.model.Actor;
import com.src.movieandgamereview.model.Director;
import com.src.movieandgamereview.model.Person;
import com.src.movieandgamereview.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private ActorService actorService;

    public List<PersonDTO> getAllPeople() {
        return ((List<Person>) personRepository.findAll()).stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    public Person findPersonById(Long currentPersonId) {
        Optional<Person> getPerson = personRepository.findById(currentPersonId);
        return getPerson.orElse(null);
    }

    public PersonDTO findAndGetPersonDTOById(Long currentPersonId) {
        Optional<Person> getPerson = personRepository.findById(currentPersonId);
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

    public void deletePerson(Long currentPersonId) {
        Person getPerson = findPersonById(currentPersonId);
        Director director = directorService.findDirectorByPerson(AggregateReference.to(currentPersonId));
        directorService.deleteDirector(director.getId());
        Actor actor = actorService.findActorByPerson(AggregateReference.to(currentPersonId));
        actor.setPerson(null);
        actorService.updateActor(actor.getId(), actor);
        personRepository.delete(getPerson);
    }

    protected PersonDTO convertToPersonDTO(Person person) {
        return new PersonDTO(person.getFirstName(), person.getLastName(), person.getBirthDate());
    }

}
