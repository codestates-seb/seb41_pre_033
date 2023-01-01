package server.user.mapper;

import org.mapstruct.Mapper;
import server.answer.dto.AnswerDto;
import server.answer.entity.Answer;
import server.question.dto.QuestionDto;
import server.question.dto.QuestionTagResponseDto;
import server.question.entity.Question;
import server.question.entity.QuestionTag;
import server.tag.entity.Tag;
import server.user.dto.UserDto;
import server.user.dto.UserPasswordDto;
import server.user.dto.UserTagDto;
import server.user.dto.UserTagResponseDto;
import server.user.entity.User;
import server.user.entity.UserTag;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userPostToUser(UserDto.Post requestBody);
    default User userPatchToUser(UserDto.Patch requestBody) {
        User user = new User();
        user.setUserId(requestBody.getUserId());
        user.setNickname(requestBody.getNickname());
        user.setCountry(requestBody.getCountry());
        user.setTitle(requestBody.getTitle());
        user.setIntroduction(requestBody.getIntroduction());
        user.setLink(requestBody.getLink());
        List<UserTag> userTags = requestBody.getUserTags().stream()
                .map(userTagDto -> {
                    UserTag userTag = new UserTag();
                    Tag tag = new Tag();
                    tag.setName(userTagDto.getTagName());
                    userTag.addTag(tag);
                    userTag.addUser(user);
                    return userTag;
                }).collect(Collectors.toList());
        user.setUserTags(userTags);
        return user;
    }
    default UserTag userTagDtoToUserTag(UserTagDto userTagDto) {
        UserTag userTag = new UserTag();
        Tag tag = new Tag();
        tag.setName(userTagDto.getTagName());
        userTag.addTag(tag);
        return userTag;
    }

    default UserDto.Response userToUserResponse(User user) {
        List<UserTag> userTags = user.getUserTags();
        List<Question> questions = user.getQuestions();
        List<Answer> answers = user.getAnswers();

        UserDto.Response userResponseDto = new UserDto.Response();
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setNickname(user.getNickname());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setCountry(user.getCountry());
        userResponseDto.setReputation(user.getReputation());
        userResponseDto.setTitle(user.getTitle());
        userResponseDto.setIntroduction(user.getIntroduction());
        userResponseDto.setLink(user.getLink());
        userResponseDto.setUserTags(
                userTagsToUserTagResponseDtos(userTags)
        );
        userResponseDto.setQuestions(
                questionToUserTagResponseDtos(questions, user)
        );
        userResponseDto.setAnswers(
                answersToAnswerResponseDtos(answers)
        );
        return userResponseDto;
    }

    // UserTag for userToUserResponse
    default List<UserTagResponseDto> userTagsToUserTagResponseDtos(List<UserTag> userTags) {
        return userTags
                .stream()
                .map(userTag -> UserTagResponseDto
                        .builder()
                        .tagName(userTag.getTag().getName())
                        .explanation(userTag.getTag().getExplanation())
                        .build())
                .collect(Collectors.toList());
    }

    // Question for userToUserResponse
    default List<QuestionDto.ResponseQ> questionToUserTagResponseDtos(List<Question> questions, User user) {
        return questions
                .stream()
                .map(question -> {
                    QuestionDto.ResponseQ question1 = new QuestionDto.ResponseQ();
                    question1.setUserId(user);
                    question1.setQuestionId(question.getQuestionId());
                    question1.setTitle(question.getTitle());
                    question1.setBody(question.getBody());
                    question1.setBounty(question.getBounty());
                    question1.setCreated(question.getCreated());
                    question1.setViewed(question.getViewed());
                    question1.setVote(question.getVote());
                    question1.setQuestionTags(
                            questionTagsToQuestionTagResponseDtos(question.getQuestionTags())
                    );
                    question1.setAnswers(
                            answersToAnswerResponseDtos(question.getAnswers())
                    );
                    return question1;
                }).collect(Collectors.toList());
    }

    default List<QuestionTagResponseDto> questionTagsToQuestionTagResponseDtos(List<QuestionTag> questionTags) {
        return questionTags
                .stream()
                .map(questionTag -> QuestionTagResponseDto
                        .builder()
                        .tagName(questionTag.getTag().getName())
                        .explanation(questionTag.getTag().getExplanation())
                        .build())
                .collect(Collectors.toList());
    }

    // Answer for userToUserResponse
    default List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers){
        return answers
                .stream()
                .map(answer -> AnswerDto.Response
                        .builder()
                        .body(answer.getBody())
                        .accepted(answer.getAccepted())
                        .vote(answer.getVote())
                        .userId(answer.getUser().getUserId())
                        .questionId(answer.getQuestion().getQuestionId())
                        .answerId(answer.getAnswerId())
                        .build())
                .collect(Collectors.toList());
    }


    List<UserDto.Response> usersToUserResponses(List<User> users);
}
