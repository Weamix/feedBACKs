package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.FormDto;

import java.util.List;

public interface FormService {

    List<FormDto> getAll();
    FormDto getById(Long id);

    void addForm(FormDto form);
}