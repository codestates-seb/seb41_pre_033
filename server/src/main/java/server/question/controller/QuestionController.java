package server.question.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.question.dto.QuestionDto;
import server.question.mapper.QuestionMapper;
import server.question.service.QuestionService;
import server.user.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }
    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto requestBody) {
        return null;
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") long questionId,
                                    @Valid @RequestBody QuestionDto.Patch requestBody) {
        return null;
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        return null;
    }

    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        return null;
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        return null;
    }
}
