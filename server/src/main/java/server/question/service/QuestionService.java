package server.question.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.question.entity.Question;
import server.question.entity.QuestionTag;
import server.question.repository.QuestionRepository;
import server.question.repository.QuestionTagRepository;
import server.tag.entity.Tag;
import server.tag.repository.TagRepository;
import server.tag.service.TagService;
import server.user.service.UserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {
    private final UserService userService;
    private final QuestionRepository questionRepository;
    private final TagService tagService;
    private final QuestionTagRepository questionTagRepository;
    private final TagRepository tagRepository;

    public QuestionService(UserService userService, QuestionRepository questionRepository, TagService tagService, QuestionTagRepository questionTagRepository,
                           TagRepository tagRepository) {
        this.userService = userService;
        this.questionRepository = questionRepository;
        this.tagService = tagService;
        this.questionTagRepository = questionTagRepository;
        this.tagRepository = tagRepository;
    }

    public Question createQuestion(Question question){
        verifyQuestion(question);
        question.setCreated(LocalDateTime.now());
        for (QuestionTag questionTag : question.getQuestionTags()) {
            Tag tag = tagRepository.findByName(questionTag.getTag().getName()).orElseThrow(()->new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
            if(tag.getUsed()==null) tag.setUsed(0);
            tag.setUsed(tag.getUsed()+1);
        }
        return saveQuestion(question);
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
        if(!question.getUser().getUserId().equals(findQuestion.getUser().getUserId())){
            throw new BusinessLogicException(ExceptionCode.ACCESS_DENIED);
        }

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getBody())
                .ifPresent(findQuestion::setBody);
        Optional.of(question.getBounty())
                .ifPresent(findQuestion::setBounty);
        if(Optional.ofNullable(question.getQuestionTags())
                .isPresent()){
            for (QuestionTag questionTag : findQuestion.getQuestionTags()) {
                Tag tag = tagRepository.findByName(questionTag.getTag().getName()).orElseThrow(()->new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
                tag.setUsed(tag.getUsed()-1);
            }
            questionTagRepository.deleteAllByQuestion(findQuestion);
            findQuestion.setQuestionTags(question.getQuestionTags());
            for (QuestionTag questionTag : findQuestion.getQuestionTags()) {
                Tag tag = tagRepository.findByName(questionTag.getTag().getName()).orElseThrow();
                if(tag.getUsed()==null) tag.setUsed(0);
                tag.setUsed(tag.getUsed()+1);
            }
        }

        return saveQuestion(findQuestion);
    }
    public Question updateVote(long questionId, long userId, String updown) {
//        Todo: 투표한 유저의 vote 증가
//        User voteUser = userRepository.findById(userId).orElseThrow();
//        voteUser.setVote(voteUser.getVote()+1);
        Question findQuestion = findVerifiedQuestion(questionId);
        int vote = (updown.equals("up"))? findQuestion.getVote()+1:findQuestion.getVote()-1;

        findQuestion.setVote(vote);
        return saveQuestion(findQuestion);
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
        tagService.findVerifiedTag(tagName);
        List<Question> questions = questionTagRepository.findAll()
                .stream()
                .filter(qt -> qt.getTag().getName().equals(tagName))
                .map(QuestionTag::getQuestion)
                .collect(Collectors.toList());
//        newest, bountied, unanswered
        PageRequest pageRequest;
        switch (tab) {
            case "bountied":
                pageRequest = PageRequest.of(page, size,
                        Sort.by("bounty").descending());
                break;
            case "unanswered":
                pageRequest = PageRequest.of(page, size,
                        Sort.by("answers").ascending());
                break;
            default:
                pageRequest = PageRequest.of(page, size,
                        Sort.by("questionId").descending());
                break;
        }
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), questions.size());

        return new PageImpl<>(questions.subList(start, end), pageRequest, questions.size());
    }

    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        return optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    private void verifyQuestion(Question question){
        // User 가 존재하는지 확인
        userService.findVerifiedUser(question.getUser().getUserId());

        // Tag 가 존재하는지 확인
        // Todo : tag not found 추가
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


    public void deleteQuestion(long questionId, long userId) {
        Question findQuestion = findVerifiedQuestion(questionId);

        if(findQuestion.getUser().getUserId()!=userId) throw new BusinessLogicException(ExceptionCode.ACCESS_DENIED);

        if(findQuestion.getAnswers().size()!=0){
            throw new BusinessLogicException(ExceptionCode.CANNOT_DELETE_QUESTION);
        }
        for (QuestionTag questionTag : findQuestion.getQuestionTags()) {
            Tag tag = tagRepository.findByName(questionTag.getTag().getName()).orElseThrow();
            tag.setUsed(tag.getUsed()-1);
        }
        questionRepository.deleteById(findQuestion.getQuestionId());
    }
}
