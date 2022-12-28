package server.user.dto;

import lombok.*;
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
        @NotBlank
        private String nickname;

        @NotBlank
        @Email
        private String email;

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
        private long userId;

        private String nickname;

        private String country;

//        서비스에서 로직 추가해서 부가 기능으로 구현 예정(안할 수도 있음)
//        private Integer reputation;

        private String title;

        private String introduction;

        private String link;

        private List<UserTagDto> userTags;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        // TODO: Setter 는 나중에 빼줘야함(12/24)
        private long userId;

        private String nickname;

        private String email;

        private String country;

        private int reputation;

        private String title;

        private String introduction;

        private String link;

        private List<UserTagResponseDto> userTags;

        private List<QuestionDto.Response> questions;

        private List<AnswerDto.Response> answers;
    }
}
