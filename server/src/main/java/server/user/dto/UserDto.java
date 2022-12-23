package server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "닉네임을 입력해주세요.")
        private String nickName;
        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long userId;

        private String nickName;

        private String country;

        private String title;

        // tag 작성 후에 watchedTag, ignoredTag 추가

        private String link;
    }

    public static class Response {
        private long userId;

        private String nickName;

        private String country;

        private Integer reputation;

        private String title;

        private String introduction;

        // tag 작성 후에 watchedTag, ignoredTag 추가

        private String link;
    }
}
