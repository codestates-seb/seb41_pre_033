package server.question.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class QuestionTagDto {
    @ApiModelProperty(example = "태그 이름")
    private String tagName;
}
