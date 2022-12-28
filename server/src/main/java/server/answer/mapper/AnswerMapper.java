package server.answer.mapper;

import org.mapstruct.Mapper;
import server.answer.dto.AnswerDto;
import server.answer.entity.Answer;
import server.user.entity.User;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostToAnswer(AnswerDto.Post requestBody){
        if ( requestBody == null ) return null;
        Answer answer = new Answer();
        User user = new User();
        user.setUserId(requestBody.getUserId());

        answer.setBody( requestBody.getBody() );
        answer.setUser( user );
        return answer;
    }
    default AnswerDto.Response answerToAnswerResponseDto(Answer answer){
        if ( answer == null ) return null;
        return AnswerDto.Response
                .builder()
                .body(answer.getBody())
                .accepted(answer.getAccepted())
                .vote(answer.getVote())
                .userId(answer.getUser().getUserId())
                .questionId(answer.getQuestion().getQuestionId())
                .answerId(answer.getAnswerId())
                .build();
    }

    default Answer patchAnswerDtoToAnswer(AnswerDto.PatchAnswer patchAnswerDto){
        Answer answer = new Answer();
        answer.getUser().setUserId(patchAnswerDto.getUserId());
        answer.setBody(patchAnswerDto.getBody());
        answer.setAnswerId(patchAnswerDto.getAnswerId());
        return answer;
    }
}
