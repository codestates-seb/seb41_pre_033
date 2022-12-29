package server.question.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.MultiResponseDto;
import server.dto.SingleResponseDto;
import server.question.dto.QuestionDto;
import server.question.entity.Question;
import server.question.mapper.QuestionMapper;
import server.question.service.QuestionService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Api(tags = { "Question Controller" })
@RestController
@RequestMapping("/questions")
public class QuestionController {
    private static final int SIZE = 36;
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @ApiOperation(value = "질문 등록",notes = "질문을 등록한다.")
    @PostMapping("/ask")
    public ResponseEntity postQuestion(@ApiParam(value = "작성자 user-id, 제목, 본문, 태그(1개 이상) 입력") @Valid @RequestBody QuestionDto.PostQuestion questionPostDto) {
        Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(questionMapper.questionToQuestionResponseDto(question)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "질문 수정",notes = "질문을 수정한다.")
    @PatchMapping("/edit/{question-id}")
    public ResponseEntity patchQuestion(@ApiParam(value = "question-id 값 입력")@PathVariable("question-id") long questionId,
                                        @ApiParam(value = "수정을 시도하는 유저의 user-id, 제목, 본문, 바운티, 태그들을 입력")@Valid @RequestBody QuestionDto.PatchQuestion questionPatchDto) {

        questionPatchDto.setQuestionId(questionId);

        Question question =
                questionService.updateQuestion(questionMapper.patchQuestionDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(questionMapper.questionToQuestionResponseDto(question)),HttpStatus.OK);
    }

    @ApiOperation(value = "질문 투표",notes = "질문에 up 또는 down 으로 투표한다.")
    @PatchMapping("/edit/{question-id}/vote")
    public ResponseEntity patchQuestionVote(@ApiParam(value = "question-id 값 입력") @PathVariable("question-id") long questionId,
                                            @ApiParam(value = "투표가 up인지 down인지 입력") @RequestParam String updown,
                                            @ApiParam(value = "투표하는 유저의 user-id 입력") @RequestBody QuestionDto.PatchQuestionVote votePatchDto){
        votePatchDto.setQuestionId(questionId);
        Question question =
                questionService.updateVote(votePatchDto.getQuestionId(),votePatchDto.getUserId(),updown);
        return new ResponseEntity<>(
                new SingleResponseDto<>(questionMapper.questionToQuestionResponseDto(question)),HttpStatus.OK);
    }

    @ApiOperation(value = "특정 질문 조회",notes = "질문 id로 특정 질문을 조회한다.")
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@ApiParam(value = "question-id 값 입력") @PathVariable("question-id") long questionId) {
        Question question = questionService.findQuestion(questionId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(questionMapper.questionToQuestionResponseDto(question)),
                HttpStatus.OK);
    }

    //tab = 정렬방법 or 조건걸기 -> newest, bountied, unanswered
    @ApiOperation(value = "전체 질문 조회",notes = "모든 질문을 조회한다. 조회 조건 : newest, bountied, unanswered")
    @GetMapping
    public ResponseEntity getQuestions(@ApiParam(value = "페이지 입력")@Positive @RequestParam int page,
                                       @ApiParam(value = "조회 기준 입력(newest, bountied, unanswered)")@RequestParam(required = false,defaultValue = "newest") String tab) {
        Page<Question> pageQuestions = questionService.findQuestions(page-1,SIZE,tab);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions),pageQuestions),
                        HttpStatus.OK);
    }

    //parameter 에 page는 필수
    //q = 검색어
    @ApiOperation(value = "질문 검색",notes = "제목에 '검색어'를 포함하는 질문들을 조회한다.")
    @GetMapping("/search")
    public ResponseEntity searchQuestions(@ApiParam(value = "페이지 입력") @Positive @RequestParam int page,
                                          @ApiParam(value = "검색어 입력(대,소문자 상관x 띄어쓰기 유의)")@RequestParam(required = false, defaultValue = "") String q){
        Page<Question> pageQuestions = questionService.searchQuestions(page-1, SIZE, q);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions),pageQuestions),
                HttpStatus.OK);
    }

    @ApiOperation(value = "태그로 질문 검색",notes = "지정한 태그가 등록된 질문들을 조회한다.")
    @GetMapping("/tagged/{tag-name}")
    public ResponseEntity taggedQuestions(@ApiParam(value = "태그 이름 입력") @PathVariable("tag-name") String tagName,
                                          @ApiParam(value = "페이지 입력")@RequestParam int page,
                                          @ApiParam(value = "미구현")@RequestParam(required = false, defaultValue = "newest") String tab){
        Page<Question> pageQuestions = questionService.taggedQuestions(page-1, SIZE, tagName, tab);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions),pageQuestions),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "질문 삭제",notes = "질문을 삭제한다.")
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@ApiParam(value = "question-id 입력")@PathVariable("question-id") long questionId,
                                         @ApiParam(value = "삭제를 시도하는 유저의 user-id 입력")@RequestBody QuestionDto.DeleteQuestion deleteQuestionDto) {

        questionService.deleteQuestion(questionId, deleteQuestionDto.getUserId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
