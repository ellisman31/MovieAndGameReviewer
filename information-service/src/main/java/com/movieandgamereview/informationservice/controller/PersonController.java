package com.movieandgamereview.informationservice.controller;

import com.movieandgamereview.informationservice.dto.PersonDTO;
import com.movieandgamereview.informationservice.model.Person;
import com.movieandgamereview.informationservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/getAllPeople")
    @ResponseBody
    public List<PersonDTO> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/getPersonById/{personId}")
    @ResponseBody
    public PersonDTO getMovieById(@PathVariable("personId") Long personId) {
        return personService.findAndGetPersonDTOById(personId);
    }

    @PostMapping("/addPerson")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody Person person) {
        personService.savePerson(person);
    }

    @PutMapping("/updatePerson/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovie(@PathVariable("personId") Long personId, @RequestBody Person person) {
        personService.updatePerson(personId, person);
    }

    @DeleteMapping("/deletePerson/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
    }
}
