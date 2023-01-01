package server.answer.service;

import org.springframework.stereotype.Service;
import server.answer.entity.Answer;
import server.answer.repository.AnswerRepository;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.question.entity.Question;
import server.user.entity.User;
import server.user.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service
public class AnswerService {
    UserService userService;
    private final AnswerRepository answerRepository;

    public AnswerService(UserService userService,
                         AnswerRepository answerRepository) {
        this.userService = userService;
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Question targetQuestion, Answer answer){
        //answer는 바디, userid만 가지고 있음
        answer.setQuestion(targetQuestion);
        answer.setAccepted(false);
        answer.setVote(0);
        answer.getUser().setNickname(userService.findVerifiedUser(answer.getUser().getUserId()).getNickname());
        verifyAnswer(answer);
        return saveAnswer(answer);
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        if(!Objects.equals(answer.getUser().getUserId(), findAnswer.getUser().getUserId())) throw new BusinessLogicException(ExceptionCode.ACCESS_DENIED);
        if(findAnswer.getAccepted()) throw new BusinessLogicException(ExceptionCode.CANNOT_MODIFY_ANSWER);
        Optional.ofNullable(answer.getBody())
                .ifPresent(findAnswer::setBody);

        return saveAnswer(findAnswer);
    }

    public Answer updateVote(long answerId, long userId, String updown) {
//        Todo: 투표한 유저의 vote 증가
//        User voteUser = userRepository.findById(userId).orElseThrow();
//        voteUser.setVote(voteUser.getVote()+1);
        // 존재하는 회원인지 검증
        User findUser = userService.findVerifiedUser(userId);

        // 자신이 작성한 답변에는 투표 불가
        Answer answer = findVerifiedAnswer(answerId);
        if(Objects.equals(findUser.getUserId(), answer.getUser().getUserId())) throw new BusinessLogicException(ExceptionCode.ACCESS_DENIED);

        Answer findAnswer = findVerifiedAnswer(answerId);
        int vote = (updown.equals("up"))? findAnswer.getVote()+1:findAnswer.getVote()-1;

        findAnswer.setVote(vote);
        return saveAnswer(findAnswer);
    }

    public Answer updateAccept(long answerId, long userId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        long authorId = findAnswer.getQuestion().getUser().getUserId();
        if(authorId!=userId) throw new BusinessLogicException(ExceptionCode.ACCESS_DENIED);

        findAnswer.setAccepted(true);
        return saveAnswer(findAnswer);
    }

    public void deleteAnswer(long answerId, long userId) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        if (findAnswer.getUser().getUserId()!=userId) throw new BusinessLogicException(ExceptionCode.ACCESS_DENIED);

        if(findAnswer.getAccepted()) throw new BusinessLogicException(ExceptionCode.CANNOT_DELETE_ANSWER);
        answerRepository.deleteById(findAnswer.getAnswerId());
    }

    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        return optionalAnswer.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    private Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    private void verifyAnswer(Answer answer) {
        userService.findVerifiedUser(answer.getUser().getUserId());
    }
}
