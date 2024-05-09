package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.LanguageDTO;
import com.src.movieandgamereview.model.Language;
import com.src.movieandgamereview.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<LanguageDTO> getAllLanguage() {
        return ((List<Language>) languageRepository.findAll()).stream().map(this::convertToLanguageDTO).collect(Collectors.toList());
    }

    public Language findLanguageById(Long languageId) {
        Optional<Language> getLanguage = languageRepository.findById(languageId);
        return getLanguage.orElse(null);
    }

    public LanguageDTO findAndGetLanguageDTOById(Long languageId) {
        Optional<Language> getLanguage = languageRepository.findById(languageId);
        return getLanguage.map(this::convertToLanguageDTO).orElse(null);
    }

    public void saveLanguage(Language language) {
        languageRepository.save(language);
    }

    public void updateLanguage(Long currentLanguageId, Language newLanguageData) {
        Language currentLanguage = findLanguageById(currentLanguageId);
        if (newLanguageData.getName() != null) {
            currentLanguage.setName(newLanguageData.getName());
        }
        saveLanguage(currentLanguage);
    }

    protected LanguageDTO convertToLanguageDTO(Language language) {
        return new LanguageDTO(language.getName());
    }
}
