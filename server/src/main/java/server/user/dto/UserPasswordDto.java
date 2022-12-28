package server.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDto {
    @ApiModelProperty(example = "User id")
    private long userId;

    @ApiModelProperty(example = "현재 비밀번호")
    private String currentPassword;

    @ApiModelProperty(example = "바꿀 비밀번호")
    private String newPassword;

    @ApiModelProperty(example = "바꿀 비밀번호를 체크할 비밀번호")
    private String checkPassword;

}
