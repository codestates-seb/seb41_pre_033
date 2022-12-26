package server.question.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.answer.entity.Answer;
import server.answer.service.AnswerService;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.question.entity.Question;
import server.question.entity.QuestionTag;
import server.question.repository.QuestionRepository;
import server.question.repository.QuestionTagRepository;
import server.tag.service.TagService;
import server.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final UserService userService;
    private final QuestionRepository questionRepository;
    private final TagService tagService;
    private final AnswerService answerService;

    private final QuestionTagRepository questionTagRepository;

    public QuestionService(UserService userService, QuestionRepository questionRepository, TagService tagService, AnswerService answerService, QuestionTagRepository questionTagRepository) {
        this.userService = userService;
        this.questionRepository = questionRepository;
        this.tagService = tagService;
        this.answerService = answerService;
        this.questionTagRepository = questionTagRepository;
    }

    public Question createQuestion(Question question){
        verifyQuestion(question);
        question.setCreated(LocalDateTime.now());

        //Todo: ref 에는 updateStamp 가 있는데, tag 나 answer 에 비슷한 처리를 해야하는가?

        return saveQuestion(question);
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getBody())
                .ifPresent(findQuestion::setBody);
        Optional.ofNullable(question.getBounty())
                .ifPresent(findQuestion::setBounty);
        Optional.ofNullable(question.getQuestionTags())
                .ifPresent(findQuestion::setQuestionTags);

        return questionRepository.save(findQuestion);
    }
    public Question updateVote(long questionId, long userId, String updown) {
        Question findQuestion = findVerifiedQuestion(questionId);
        int vote = (updown.equals("up"))? findQuestion.getVote()+1:findQuestion.getVote()-1;

        findQuestion.setVote(vote);
        return questionRepository.save(findQuestion);
    }

    public Page<Question> findQuestions(int page, int size, String tab) {
        Page<Question> result;
        switch (tab){
            case "bountied":
                result = questionRepository.findAll (PageRequest.of(page, size,
                        Sort.by("bounty").descending()));
                break;
            case "unanswered":
                result = questionRepository.findAll(PageRequest.of(page, size,
                        Sort.by("answers").ascending()));
                break;
            default:
                result = questionRepository.findAll(PageRequest.of(page, size,
                    Sort.by("questionId").descending()));
                break;
        }
        return result;
    }

    public Page<Question> searchQuestions(int page, int size, String q) {
        return questionRepository.findAllByTitleContainingIgnoreCase(q,PageRequest.of(page, size));
    }

    public Page<Question> taggedQuestions(int page, int size, String tagName, String tab) {
        List<Question> questions = questionTagRepository.findAll()
                .stream()
                .filter(qt -> qt.getTag().getName().equals(tagName))
                .map(QuestionTag::getQuestion)
                .collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), questions.size());
        Page<Question> questionPage = new PageImpl<>(questions.subList(start, end), pageRequest, questions.size());

        return questionPage;
    }

    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }

    private void verifyQuestion(Question question){
        // User 가 존재하는지 확인
        userService.findVerifiedUser(question.getUser().getUserId());

        // Tag 가 존재하는지 확인
        question.getQuestionTags()
                .forEach(questionTag -> tagService.
                        findVerifiedTag(questionTag.getTag().getName()));
    }

    private Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question findQuestion(long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        int viewed = findQuestion.getViewed();
        findQuestion.setViewed(viewed+1);
        return questionRepository.save(findQuestion);
    }


    public void deleteQuestion(long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        List<Answer> answers = findQuestion.getAnswers();

        if(answers.size()!=0){
            throw new BusinessLogicException(ExceptionCode.CANNOT_DELETE_QUESTION);
        }
        questionRepository.deleteById(findQuestion.getQuestionId());
    }
}
