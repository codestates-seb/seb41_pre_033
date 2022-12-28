package server.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.Nullable;
import server.answer.dto.AnswerDto;
import server.answer.entity.Answer;
import server.question.dto.QuestionDto;
import server.question.entity.Question;
import server.user.entity.UserTag;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @ApiModelProperty(example = "닉네임")
        @NotBlank
        private String nickname;

        @ApiModelProperty(example = "email@gmail.com")
        @NotBlank
        @Email
        private String email;

        @ApiModelProperty(example = "비밀번호")
        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        // TODO: 바꾸고 싶은 값만 입력받아야 한다.
        // TODO: Setter 는 나중에 빼줘야함(12/24)
        @ApiModelProperty(example = "경로에 포함되어 있기 때문에 입력값으로 포함하지 않습니다. 지우고 사용하세요.")
        private long userId;

        @ApiModelProperty(example = "닉네임")
        private String nickname;

        @ApiModelProperty(example = "국가")
        private String country;

//        서비스에서 로직 추가해서 부가 기능으로 구현 예정(안할 수도 있음)
//        private Integer reputation;

        @ApiModelProperty(example = "소제목")
        private String title;

        @ApiModelProperty(example = "자기소개")
        private String introduction;

        @ApiModelProperty(example = "개인 사이트 링크")
        private String link;

        @ApiModelProperty(example = "[\n" +
                "        {\n" +
                "            \"tagName\": \"태그1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"tagName\": \"태그2\"\n" +
                "        }\n" +
                "    ]")
        private List<UserTagDto> userTags;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        // TODO: Setter 는 나중에 빼줘야함(12/24)
        @ApiModelProperty(example = "User id")
        private long userId;

        @ApiModelProperty(example = "닉네임")
        private String nickname;

        @ApiModelProperty(example = "email@gmail.com")
        private String email;

        @ApiModelProperty(example = "국가")
        private String country;

        @ApiModelProperty(example = "평판")
        private int reputation;

        @ApiModelProperty(example = "소제목")
        private String title;

        @ApiModelProperty(example = "자기소개")
        private String introduction;

        @ApiModelProperty(example = "개인 사이트 링크")
        private String link;

        @ApiModelProperty(example = "태그")
        private List<UserTagResponseDto> userTags;

        @ApiModelProperty(example = "질문 목록")
        private List<QuestionDto.ResponseQ> questions;

        @ApiModelProperty(example = "답변 목록")
        private List<AnswerDto.Response> answers;
    }
}
