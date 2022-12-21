package server.answer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.answer.dto.AnswerDto;
import server.answer.mapper.AnswerMapper;
import server.answer.service.AnswerService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private AnswerService answerService;
    private AnswerMapper answerMapper;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody){
        return null;
    }
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch requestBody){
        return null;
    }
    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId){
        return null;
    }
    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        return null;
    }
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId){
        return null;
    }
}
