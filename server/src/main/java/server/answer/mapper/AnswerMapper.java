package server.answer.mapper;

import org.mapstruct.Mapper;
import server.answer.dto.AnswerDto;
import server.answer.entity.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post requestBody);
    default AnswerDto.Response answerToAnswerResponseDto(Answer answer){
        if ( answer == null ) return null;
        AnswerDto.Response response = AnswerDto.Response
                .builder()
                .body(answer.getBody())
                .accepted(answer.getAccepted())
                .vote(answer.getVote())
                .userId(answer.getUserId())
                .questionId(answer.getQuestion().getQuestionId())
                .answerId(answer.getAnswerId())
                .build();
        return response;
    }

    default Answer patchAnswerDtoToAnswer(AnswerDto.PatchAnswer patchAnswerDto){
        Answer answer = new Answer();
        answer.setUserId(patchAnswerDto.getUserId());
        answer.setBody(patchAnswerDto.getBody());
        answer.setAnswerId(patchAnswerDto.getAnswerId());
        return answer;
    }
}
