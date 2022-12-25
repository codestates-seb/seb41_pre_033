package server.question.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.MultiResponseDto;
import server.question.dto.QuestionDto;
import server.question.entity.Question;
import server.question.mapper.QuestionMapper;
import server.question.service.QuestionService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

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

    @PostMapping("/ask")
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto) {
        Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question), HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") long questionId,
                                        @Valid @RequestBody QuestionDto.PatchQuestion questionPatchDto) {
        questionPatchDto.setQuestionId(questionId);
        Question question =
                questionService.updateQuestion(questionMapper.patchQuestionDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question),HttpStatus.OK);
    }

    @PatchMapping("/edit/{question-id}/vote")
    public ResponseEntity patchQuestionVote(@PathVariable("question-id") long questionId,
                                            @RequestParam String updown,
                                            @RequestBody QuestionDto.PatchVote votePatchDto){
        //쿼리로 updown String 을 입력받아, up이면 올리고, down이면 내리고
        // + 현재 로그인 한 회원의 votes 에 +1
        // 현재 로그인 한 회원 id는 request body 에 담겨서 들어옴
        votePatchDto.setQuestionId(questionId);
        Question question =
                questionService.updateVote(votePatchDto.getQuestionId(),votePatchDto.getUserId(),updown);
        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question),HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        Question question = questionService.findQuestion(questionId);
        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question),
                HttpStatus.OK);
    }

    //tab = 정렬방법 or 조건걸기 -> newest, bountied, unanswered
    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @RequestParam(required = false,defaultValue = "newest") String tab) {
        Page<Question> pageQuestions = questionService.findQuestions(page-1,SIZE,tab);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions),pageQuestions),
                        HttpStatus.OK);
    }

    //parameter 에 page는 필수
    //q = 검색어
    @GetMapping("/search")
    public ResponseEntity searchQuestions(@Positive @RequestParam int page,
                                          @RequestParam(required = false, defaultValue = "") String q){
        //q 로 들어온 검색어를 StringTokenizer 로 쪼개고
        //각각을 db 에서 select title or body 한 다음 like 사용해서 연관도 순으로 정렬 가능
        Page<Question> pageQuestions = questionService.searchQuestions(page-1, SIZE, q);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions),pageQuestions),
                HttpStatus.OK);
    }

    @GetMapping("/tagged/{tag-name}")
    public ResponseEntity taggedQuestions(@PathVariable("tag-name") String tagName,
                                          @RequestParam int page,
                                          @RequestParam(required = false, defaultValue = "newest") String tab){
        Page<Question> pageQuestions = questionService.taggedQuestions(page-1, SIZE, tagName, tab);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions),pageQuestions),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
