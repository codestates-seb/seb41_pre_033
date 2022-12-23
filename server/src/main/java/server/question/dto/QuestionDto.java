package server.question.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import server.answer.dto.AnswerDto;
import server.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    public static class Post {
        private long userId;

        private String title;

        private String body;

        private List<QuestionTagDto> questionTags;
    }
    @Getter
    public static class PatchQuestion{
        private long userId;
        private long questionId;
        private String title;
        private String body;
        private String bounty;
        private List<QuestionTagDto> questionTags;
    }

    @Getter
    public static class PatchVote{
        private long userId;
        private long questionId;
    }
    @Getter
    public static class Response {
        private long questionId;
        @Setter(AccessLevel.NONE)
        private long userId;
        private String nickname;
        private String title;
        private String body;
        private int bounty;
        private LocalDateTime created;
        private int views;
        private int vote;
        private List<QuestionTagResponseDto> questionTags;
        private List<AnswerDto.Response> answers;

        public void setUserId(User user){
            this.userId = user.getUserId();
        }
    }
}
