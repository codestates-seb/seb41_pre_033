package server.answer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.answer.dto.AnswerDto;
import server.answer.entity.Answer;
import server.answer.mapper.AnswerMapper;
import server.answer.service.AnswerService;
import server.dto.SingleResponseDto;
import server.question.entity.Question;
import server.question.repository.QuestionRepository;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper,
                            QuestionRepository questionRepository) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/{question-id}")
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerDto.Post requestBody){
        Question targetQuestion = questionRepository.findByQuestionId(questionId);
        Answer answer = answerService.createAnswer(targetQuestion, answerMapper.answerPostToAnswer(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                        HttpStatus.CREATED);
    }
    @PatchMapping("/edit/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.PatchAnswer patchAnswerDto){
        patchAnswerDto.setAnswerId(answerId);

        Answer answer =
                answerService.updateAnswer(answerMapper.patchAnswerDtoToAnswer(patchAnswerDto));
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @PatchMapping("/edit/{answer-id}/vote")
    public ResponseEntity patchVote(@PathVariable("answer-id") @Positive long answerId,
                                    @RequestParam String updown,
                                    @RequestBody AnswerDto.PatchVote patchVoteDto){
        patchVoteDto.setAnswerId(answerId);
        Answer answer =
                answerService.updateVote(patchVoteDto.getAnswerId(),patchVoteDto.getUserId(),updown);
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @PatchMapping("/edit/{answer-id}/accept")
    public ResponseEntity patchAccept(@PathVariable("answer-id") @Positive long answerId,
                                     @RequestBody AnswerDto.PatchAccept patchAcceptDto){
        patchAcceptDto.setAnswerId(answerId);
        Answer answer =
                answerService.updateAccept(patchAcceptDto.getAnswerId(),patchAcceptDto.getUserId());
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId){
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
