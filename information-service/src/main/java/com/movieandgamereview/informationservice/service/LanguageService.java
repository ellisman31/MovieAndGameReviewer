package com.movieandgamereview.informationservice.service;

import com.movieandgamereview.informationservice.dto.LanguageDTO;
import com.movieandgamereview.informationservice.model.Information;
import com.movieandgamereview.informationservice.model.Language;
import com.movieandgamereview.informationservice.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    @Autowired(required=false)
    private LanguageRepository languageRepository;
    @Autowired(required=false)
    private InformationService informationService;

    public List<LanguageDTO> getAllLanguage() {
        return ((List<Language>) languageRepository.findAll()).stream().map(this::convertToLanguageDTO).collect(Collectors.toList());
    }

    public Language findLanguageById(Long currentLanguageId) {
        Optional<Language> getLanguage = languageRepository.findById(currentLanguageId);
        return getLanguage.orElse(null);
    }

    public LanguageDTO findAndGetLanguageDTOById(Long currentLanguageId) {
        Optional<Language> getLanguage = languageRepository.findById(currentLanguageId);
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

    public void deleteLanguage(Long currentLanguageId) {
        Language getLanguage = findLanguageById(currentLanguageId);
        List<Information> informationList = informationService.findInformationByLanguage(AggregateReference.to(currentLanguageId));
        informationList.forEach(information -> {
            information.set_language(null);
            informationService.updateInformation(information.getId(), information);
        });
        languageRepository.delete(getLanguage);
    }

    protected LanguageDTO convertToLanguageDTO(Language language) {
        return new LanguageDTO(language.getName());
    }
}
