package fr.ulco.feedbacks.mapper;

import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.Question;
import fr.ulco.feedbacks.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "form", source = "form")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdAt", source = "questionDto.createdAt")
    Question mapDtoToQuestion(QuestionDto questionDto, User user, Form form);

    QuestionDto mapQuestionToDto(Question question);
}
