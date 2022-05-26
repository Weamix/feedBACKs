package fr.ulco.feedbacks.mapper;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.Question;
import fr.ulco.feedbacks.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(target = "question", source = "question")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "content", source = "answerDto.content")
    Answer mapDtoToAnswer(AnswerDto answerDto, Question question, User user);

    @Mapping(target = "userId", expression = "java(answer.getUser().getId())")
    AnswerDto mapAnswerToDto(Answer answer);
}
