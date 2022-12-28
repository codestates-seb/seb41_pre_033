package server.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

//        private Integer reputation;

        private String title;

        private String introduction;

        private String link;
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

//        private String password;

        private String country;

        private int reputation;

        private String title;

        private String introduction;

        private String link;
    }
}
