package server.answer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = { "Answer Controller" })
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

    @ApiOperation(value = "답변 작성", notes = "질문에 대한 답변을 작성한다")
    @PostMapping("/{question-id}")
    public ResponseEntity postAnswer(@ApiParam(value = "답변 대상 질문 아이디 입력")@PathVariable("question-id") @Positive long questionId,
                                     @ApiParam(value = "작성자의 user-id, 답변 내용 입력")@Valid @RequestBody AnswerDto.PostAnswer requestBody){
        Question targetQuestion = questionRepository.findByQuestionId(questionId);
        Answer answer = answerService.createAnswer(targetQuestion, answerMapper.answerPostToAnswer(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                        HttpStatus.CREATED);
    }

    @ApiOperation(value = "답변 수정", notes = "작성된 답변을 수정한다.")
    @PatchMapping("/edit/{answer-id}")
    public ResponseEntity patchAnswer(@ApiParam(value = "답변 아이디 입력")@PathVariable("answer-id") @Positive long answerId,
                                      @ApiParam(value = "answer-id, 작성자의 user-id, 수정된 내용 입력")@Valid @RequestBody AnswerDto.PatchAnswer patchAnswerDto){
        patchAnswerDto.setAnswerId(answerId);

        Answer answer =
                answerService.updateAnswer(answerMapper.patchAnswerDtoToAnswer(patchAnswerDto));
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "답변 투표", notes = "특정 답변에 투표한다.")
    @PatchMapping("/edit/{answer-id}/vote")
    public ResponseEntity patchVote(@ApiParam(value = "투표할 대상 답변의 answer-id 입력")@PathVariable("answer-id") @Positive long answerId,
                                    @ApiParam(value = "투표가 up인지 down인지 입력")@RequestParam String updown,
                                    @ApiParam(value = "투표하는 유저의 user-id 입력")@RequestBody AnswerDto.PatchVote patchVoteDto){
        patchVoteDto.setAnswerId(answerId);
        Answer answer =
                answerService.updateVote(patchVoteDto.getAnswerId(),patchVoteDto.getUserId(),updown);
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "답변 채택", notes = "특정 답변을 채택한다.")
    @PatchMapping("/edit/{answer-id}/accept")
    public ResponseEntity patchAccept(@ApiParam(value = "채택할 대상 답변의 answer-id 입력")@PathVariable("answer-id") @Positive long answerId,
                                      @ApiParam(value = "채택하는 유저의 user-id 입력")@RequestBody AnswerDto.PatchAccept patchAcceptDto){
        patchAcceptDto.setAnswerId(answerId);
        Answer answer =
                answerService.updateAccept(patchAcceptDto.getAnswerId(),patchAcceptDto.getUserId());
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "답변 삭제", notes = "특정 답변을 삭제한다.")
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@ApiParam(value = "삭제할 답변의 answer-id 입력")@PathVariable("answer-id") @Positive long answerId,
                                       @ApiParam(value = "삭제를 요청하는 유저의 user-id 입력")@RequestBody AnswerDto.DeleteAnswer deleteAnswerDto){
        answerService.deleteAnswer(answerId, deleteAnswerDto.getUserId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
