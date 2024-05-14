package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.LanguageDTO;
import com.src.movieandgamereview.model.Language;
import com.src.movieandgamereview.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping("/getAllLanguage")
    @ResponseBody
    public List<LanguageDTO> getAllLanguage() {
        return languageService.getAllLanguage();
    }

    @GetMapping("/getLanguageById/{languageId}")
    @ResponseBody
    public LanguageDTO getLanguageById(@PathVariable("languageId") Long languageId) {
        return languageService.findAndGetLanguageDTOById(languageId);
    }

    @PostMapping("/addLanguage")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLanguage(@RequestBody Language language) {
        languageService.saveLanguage(language);
    }

    @PutMapping("/updateLanguage/{languageId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLanguage(@PathVariable("languageId") Long languageId, @RequestBody Language language) {
        languageService.updateLanguage(languageId, language);
    }

    @DeleteMapping("/deleteLanguage/{languageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLanguage(@PathVariable("languageId") Long languageId) {
        languageService.deleteLanguage(languageId);
    }
}
