package server.answer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AnswerDto {
    @Getter
    public static class Post{
        private Long userId;
        private String body;
    }

    @Getter
    public static class PatchAnswer{
        private Long answerId;
        private Long userId;
        private String body;

        public void setAnswerId(Long answerId) {
            this.answerId = answerId;
        }
    }

    @Getter
    public static class PatchVote{
        private long userId;
        private long answerId;
        public void setAnswerId(long answerId){this.answerId = answerId;}
    }
    @Getter
    public static class PatchAccept{
        private long userId;
        private long answerId;
        public void setAnswerId(long answerId){this.answerId = answerId;}
    }

    @Getter
    @Setter
    @Builder
    public static class Response{
        private Long answerId;
        private String body;
        private Boolean accepted;
        private int vote;
        private Long userId;
        private Long questionId;
    }
}
