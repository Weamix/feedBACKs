package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.mapper.FormMapper;
import fr.ulco.feedbacks.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FormDto> getAll() {
        return formRepository.findAll().stream()
                .map(formMapper::mapFormToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public FormDto getById(Long id) {
        return formMapper.mapFormToDto(formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found")));
    }

    @Override
    public void addForm(FormDto form) {
        // TODO: get user from jwt token?
        User user = new User();
        formRepository.save(formMapper.mapDtoToForm(form, user));
    }
}
