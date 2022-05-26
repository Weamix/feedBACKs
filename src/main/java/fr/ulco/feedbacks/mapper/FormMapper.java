package fr.ulco.feedbacks.mapper;

import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FormMapper {

    @Mapping(target = "user", source = "user")
    Form mapDtoToForm(FormDto formDto, User user);

    @Mapping(target = "userId", expression = "java(form.getUser().getId())")
    FormDto mapFormToDto(Form form);
}
