package server.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LoginDto {
    @ApiModelProperty(example = "email@gmail.com")
    private String username;
    @ApiModelProperty(example = "비밀번호")
    private String password;
}
