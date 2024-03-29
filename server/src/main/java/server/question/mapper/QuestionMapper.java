package server.question.mapper;

import org.mapstruct.Mapper;
import server.answer.dto.AnswerDto;
import server.answer.entity.Answer;
import server.question.dto.QuestionDto;
import server.question.dto.QuestionTagResponseDto;
import server.question.entity.Question;
import server.question.entity.QuestionTag;
import server.tag.entity.Tag;
import server.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    default Question questionPostDtoToQuestion(QuestionDto.PostQuestion questionPostDto) {
        Question question = new Question();
        User user = new User();
        user.setUserId(questionPostDto.getUserId());

        List<QuestionTag> questionTags = questionPostDto.getQuestionTags().stream()
                .map(questionTagDto -> {
                    QuestionTag questionTag = new QuestionTag();
                    Tag tag = new Tag();
                    tag.setName(questionTagDto.getTagName());
                    questionTag.addQuestion(question);
                    questionTag.addTag(tag);
                    return questionTag;
                }).collect(Collectors.toList());
        question.setUser(user);
        question.setQuestionTags(questionTags);
        question.setTitle(questionPostDto.getTitle());
        question.setBody(questionPostDto.getBody());
        return question;
    }

    default Question patchQuestionDtoToQuestion(QuestionDto.PatchQuestion questionPatchDto) {
        Question question = new Question();
        User user = new User();
        user.setUserId(questionPatchDto.getUserId());
        question.setUser(user);
        List<QuestionTag> questionTags = questionPatchDto.getQuestionTags().stream()
                .map(questionTagDto -> {
                    QuestionTag questionTag = new QuestionTag();
                    Tag tag = new Tag();
                    tag.setName(questionTagDto.getTagName());
                    questionTag.addQuestion(question);
                    questionTag.addTag(tag);
                    return questionTag;
                })
                .collect(Collectors.toList());
        question.setQuestionTags(questionTags);
        question.setQuestionId(questionPatchDto.getQuestionId());
        question.setTitle(questionPatchDto.getTitle());
        question.setBody(questionPatchDto.getBody());
        question.setBounty(questionPatchDto.getBounty());
        return question;
    }

    default QuestionDto.ResponseQ questionToQuestionResponseDto(Question question) {
        List<QuestionTag> questionTags = question.getQuestionTags();
        List<Answer> answers = question.getAnswers();

        QuestionDto.ResponseQ questionResponseDto = new QuestionDto.ResponseQ();
        questionResponseDto.setQuestionId(question.getQuestionId());
        questionResponseDto.setUserId(question.getUser());
        questionResponseDto.setNickname(question.getUser().getNickname());
        questionResponseDto.setTitle(question.getTitle());
        questionResponseDto.setBody(question.getBody());
        questionResponseDto.setBounty(question.getBounty());
        questionResponseDto.setCreated(question.getCreated());
        questionResponseDto.setViewed(question.getViewed());
        questionResponseDto.setVote(question.getVote());
        questionResponseDto.setQuestionTags(
                questionTagsToQuestionTagResponseDtos(questionTags)
        );
        questionResponseDto.setAnswers(
                answersToAnswerResponseDtos(answers)
        );

        return questionResponseDto;
    }

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
                        .nickname(answer.getUser().getNickname())
                        .build())
                .collect(Collectors.toList());
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

    List<QuestionDto.ResponseQ> questionsToQuestionResponseDtos(List<Question> questions);
}
