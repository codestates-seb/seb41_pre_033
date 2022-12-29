package server.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserTagDto {
    @ApiModelProperty(example = "태그 이름")
    private String tagName;
}
