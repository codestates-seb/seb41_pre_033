package server.question.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionTagResponseDto {
    private String tagName;
    private String tagExplanation;
}
