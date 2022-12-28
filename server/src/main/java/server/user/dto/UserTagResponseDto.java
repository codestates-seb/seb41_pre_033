package server.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class UserTagResponseDto {
    @ApiModelProperty(example = "태그 이름")
    private String tagName;

    @ApiModelProperty(example = "태그 설명")
    private String explanation;
}
