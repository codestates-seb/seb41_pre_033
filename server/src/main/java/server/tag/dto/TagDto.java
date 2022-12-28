package server.tag.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


public class TagDto {
    @Getter
    @Setter
    public static class Response {
        @ApiModelProperty(example = "태그 이름")
        String name;

        @ApiModelProperty(example = "태그 설명")
        String explanation;

        @ApiModelProperty(example = "태그 사용 여부 확인")
        Integer used;
    }
}